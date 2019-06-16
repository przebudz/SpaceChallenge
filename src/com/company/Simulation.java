package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Simulation {

    /**
     * Method load items from list given in file
     * Items are added to the ArrayList of Items
     * @param file - file with list of item to load in format: item name=weight
     * @return - ArrayList of Items - list of items to take
     */

    ArrayList<Item> loadItems(File file){

        Scanner fileScanner = null;

        ArrayList<Item> listOfItems = new ArrayList<>();

        String itemFromFile;
        String itemName;
        int itemWeight;
        int index;
        int totalWeight=0;

        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        System.out.println("Creating list of items to take to Mars");
        while (fileScanner.hasNextLine()){
            itemFromFile = fileScanner.nextLine();
            index = itemFromFile.indexOf("=");
            itemName = itemFromFile.substring(0,index);
            itemWeight = Integer.valueOf(itemFromFile.substring(index+1));
            Item item = new Item(itemName, itemWeight);

            listOfItems.add(item);
            totalWeight+=itemWeight;

        }

        System.out.println("Added " + listOfItems.size() + " items to the list");
        System.out.println("Total weight of items is " + totalWeight + " kg\n\r");

        return listOfItems;
    }

    /**
     * Method loads items from given list to the rockets of U1 type
     * @param items - list of items
     * @return - list of U1 rockets loaded with items
     */

    ArrayList<Rocket> loadU1(ArrayList<Item> items){

        ArrayList<Rocket> listOfU1Rockets = new ArrayList<>();

        return loadRockets(items, listOfU1Rockets, "U1");
    }

    /**
     * Method loads items from given list to the rockets of U2 type
     * @param items - list of items
     * @return - list of U2 rockets loaded with items
     */

    public ArrayList<Rocket> loadU2(ArrayList<Item> items){

        ArrayList<Rocket> listOfU2Rockets = new ArrayList<>();

        return loadRockets(items, listOfU2Rockets, "U2");
    }

    /**
     * Method loads items from given list to rockets of given type
     * List of Items is sorted by weight in descending order
     * Method tries to load the heaviest items first, then fills available space with lighter items
     * When cursor reach the end of items list, current rocket is loaded optimally
     * and no additional cargo can be added
     * The next rocket is added and the list of remaining items is checked again
     * Process ends when no items left to load.
     * @param items - list of items to load
     * @param rockets - list of rockets which will be filled with loaded rockets
     * @param rocketType - type of the rockets used to loading (U1 or U2)
     * @return - list of loaded rockets
     */

    public ArrayList<Rocket> loadRockets(ArrayList<Item> items, ArrayList<Rocket> rockets, String rocketType){

        ArrayList<Item> itemsLoaded = new ArrayList<>();

        int rocketCounter = 0;

        Collections.sort(items);            //sort by weight
        Collections.reverse(items);         //in descending order

        Rocket rocket;

        while (items.size() > 0){           //process ends when there is nothing to load

            if (rocketType.equals("U1")) {  //the next rocket
                rocket = new U1();
            } else {
                rocket = new U2();
            }
            rocketCounter++;

            for (Item item :
                    items) {
                if(rocket.canCarry(item)) {
                    rocket.carry(item);
                    itemsLoaded.add(item);
                }
                if (rocket.getCurrentWeight() == rocket.getMaxWeight()) break;  //rocket is full so no need to proceed

            }
            //end of item list check = rocket is optimally loaded
            rocket.calculateChanceOfLaunchExplosion();
            rocket.calculateChanceOfLandingCrash();
            rockets.add(rocket);

            items.removeAll(itemsLoaded);               // remove loaded items from list
            itemsLoaded.clear();

        }

        System.out.println("Total number of loaded rockets: " + rocketCounter + "\r\n");

        return rockets;
    }

    /**
     * Method simulates the flight of the rockets to Mars
     * It tries launch and land rockets one by one
     * If rockets will launch and land successful it is removed from list of rockets
     * If rockets fails during launch or land it stays in list for another turn
     * Process ends when list of rockets is empty
     * @param listOfRockets - list of rockets to send
     * @return  - total cost of all rockets, including crushed ones
     */

    public long runSimulation(ArrayList<Rocket> listOfRockets){

        long totalBudget = 0;

        int rocketLaunchedCounter = 0;
        int rocketLandedCounter = 0;

        boolean isSuccessfulLaunch = false;
        boolean isSuccessfulLand = false;

        ArrayList<Rocket> rocketsSuccessfullySent = new ArrayList<>();

        System.out.println("Begin to sending rockets to Mars");

        while(listOfRockets.size() > 0){            //process ends when there is no rocket to send

            int turnCounter = 1;

            System.out.println("Launch turn: " + turnCounter);
            System.out.println("Remaining rockets to send: " + listOfRockets.size() + "\n\r");

            for (Rocket rocket:
                 listOfRockets) {

                isSuccessfulLaunch = rocket.launch();            //rocket launched so we need update budget and counter
                totalBudget += rocket.getCost();
                rocketLaunchedCounter++;

                if(isSuccessfulLaunch) {                         //if not exploded we try to land
                    isSuccessfulLand = rocket.land();
                    if(isSuccessfulLand) {
                        rocketsSuccessfullySent.add(rocket);                          //rocket landed so we dont need to sent it again
                        rocketLandedCounter++;
                    }

                }
            }

            listOfRockets.removeAll(rocketsSuccessfullySent);
            rocketsSuccessfullySent.clear();                                         //clear list for another turn
            turnCounter++;

        }

        System.out.println("Total number of launched rockets: " + rocketLaunchedCounter);
        System.out.println("Total number of landed rockets: " + rocketLandedCounter);

        return totalBudget;
    }

}
