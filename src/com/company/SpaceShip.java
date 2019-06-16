package com.company;

public interface SpaceShip {

    /**
     * Method to launch the spaceship
     * @return - true/false if launch was/was not successful
     */

    public boolean launch();

    /**
     * Method to land the spaceship
     * @return - true/false if land was/was not successful
     */

    public boolean land();

    /**
     * Method to check if spaceship can carry given item
     * @param item - item to load into the spaceship
     * @return - true/false if spaceship can/can't carry item
     */

    public boolean canCarry(Item item);

    /**
     * Method to load given item into spaceship
     * @param item - item to load into the spaceship
     */

    public void carry(Item item);

}
