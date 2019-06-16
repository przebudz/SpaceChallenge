package com.company;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Simulation simulation = new Simulation();

        File filePhase1 = new File("./src/resources/phase-1.txt");
        File filePhase2 = new File("./src/resources/phase-2.txt");
        Long costs[] = new Long[2];

        for (int i = 0; i < 2;i++) {
            int rocketType = i+1;                   //1 - U1, 2 - U2
            System.out.println("\r\n***************************************************************************\r\n");
            System.out.println("Simulation of expedition to Mars with U"+rocketType+" rockets\r\n");
            System.out.println("Starting phase 1\r\n");

            Long costOfPhase1 = phase(rocketType,simulation,filePhase1);

            System.out.println("Total cost of phase 1 is: " + costOfPhase1 + "\r\n");
            System.out.println("Starting phase 2\r\n");

            Long costOfPhase2 = phase(rocketType,simulation,filePhase2);

            System.out.println("Total cost of phase 2 is: " + costOfPhase2 + "\r\n");
            costs[i] = (costOfPhase1 + costOfPhase2);
            System.out.println("Total cost of expedition to Mars with U"+rocketType+" rockets is: " + costs[i]);
            System.out.println("\r\n***************************************************************************\r\n");
        }

        System.out.println("Result of the simulation:\r\n");
        System.out.println("Cost of expedition to Mars using U1 rockets is: " + costs[0]);
        System.out.println("Cost of expedition to Mars using U2 rockets is: " + costs[1]);
        System.out.println("\r\n***************************************************************************\r\n");
    }

    /**
     * Method simulates phase of expedition: load of items, load of rockets, launching, calculation of costs
     * @param rocketType - type of the used rockets: U1, U2
     * @param simulation - running simulation
     * @param filePhase - file with fist of item
     * @return - total cost of phase
     */
    public static long phase(int rocketType, Simulation simulation, File filePhase){
        ArrayList<Item> itemsPhase = simulation.loadItems(filePhase);
        ArrayList<Rocket> fleetPhase;
        if (rocketType==1) {
            fleetPhase = simulation.loadU1(itemsPhase);
        }
        else {
            fleetPhase = simulation.loadU2(itemsPhase);
        }
        return simulation.runSimulation(fleetPhase);
    }
}
