package com.company;


public abstract class Rocket implements SpaceShip {

    private int cost;
    private int currentWeight;
    private int maxWeight;
    private double chanceOfLaunchExplosion;
    private double chanceOfLandingCrash;

    /**
     * Abstract method to calculate chance of explosion during launch,
     * according to given conditions for each type of rocket
     */

    public abstract void calculateChanceOfLaunchExplosion();

    /**
     * Abstract method to calculate chance of crush during landing,
     * according to given conditions for each type of rocket
     */

    public abstract void calculateChanceOfLandingCrash();

    /**
     * Method to launch the rocket with calculation of probability of failure.
     * Compares given chance of failure with random generated number
     * @return - true/false if launch was/was not successful
     */

    @Override
    public boolean launch() {
        double randomNumber = Math.random();

        this.calculateChanceOfLaunchExplosion();

        return (randomNumber >= this.getChanceOfLaunchExplosion());
    }

    /**
     * Method to land the rocket with calculation of probability of failure.
     * Compares given chance of failure with random generated number
     * @return - true/false if landing was/was not successful
     */

    @Override
    public boolean land() {

        double randomNumber = Math.random();

        this.calculateChanceOfLandingCrash();

        return (randomNumber >= this.getChanceOfLandingCrash());

    }

    /**
     * Method check if in rocket is enough space to carry given item
     * @param item - item which we want to load to the rocket
     * @return - true/false if item can/can't be loaded to rocket
     */

    @Override
    public boolean canCarry(Item item) {
        double availableSpace = this.getMaxWeight() - this.getCurrentWeight();
        return (item.getWeight() <= availableSpace);
    }

    /**
     * Method to load item to the rocket. Method updates current weight of rocket
     * with weight of the loaded item.
     * @param item - item laded to the rocket
     */

    @Override
    public void carry(Item item) {

        this.setCurrentWeight(this.getCurrentWeight() + item.getWeight());

    }

    //Getters and setters

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public double getChanceOfLaunchExplosion() {
        return chanceOfLaunchExplosion;
    }

    public void setChanceOfLaunchExplosion(double chanceOfLaunchExplosion) {
        this.chanceOfLaunchExplosion = chanceOfLaunchExplosion;
    }

    public double getChanceOfLandingCrash() {
        return chanceOfLandingCrash;
    }

    public void setChanceOfLandingCrash(double chanceOfLandingCrash) {
        this.chanceOfLandingCrash = chanceOfLandingCrash;
    }
}
