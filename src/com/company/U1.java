package com.company;

public class U1 extends Rocket {

    public U1() {
        this.setCost(100000);
        this.setCurrentWeight(10000);
        this.setMaxWeight(18000);
        this.setChanceOfLaunchExplosion(0);
        this.setChanceOfLandingCrash(0);
    }

    /**
     * Method calculates the chance of explosion after launch
     * The equation is: 5% * (current weight / max weight)
     */

    @Override
    public void calculateChanceOfLaunchExplosion(){
        this.setChanceOfLaunchExplosion(5.0/100.0 * ((double)this.getCurrentWeight()/(double)this.getMaxWeight()));
    }

    /**
     * Method calculates the chance of crash during landing
     * The equation is: 1% * (current weight / max weight)
     */

    @Override
    public void calculateChanceOfLandingCrash(){
        this.setChanceOfLandingCrash(1.0/100.0 * ((double)this.getCurrentWeight()/(double)this.getMaxWeight()));
    }

}
