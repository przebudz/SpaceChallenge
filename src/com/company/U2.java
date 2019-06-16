package com.company;

public class U2 extends Rocket {

    public U2() {
        this.setCost(120000);
        this.setCurrentWeight(18000);
        this.setMaxWeight(29000);
        this.setChanceOfLaunchExplosion(0);
        this.setChanceOfLandingCrash(0);
    }

    /**
     * Method calculates the chance of explosion after launch
     * The equation is: 4% * (current weight / max weight)
     */

    @Override
    public void calculateChanceOfLaunchExplosion(){
        this.setChanceOfLaunchExplosion(4.0/100.0 * ((double)this.getCurrentWeight()/(double)this.getMaxWeight()));
    }

    /**
     * Method calculates the chance of crash during landing
     * The equation is: 8% * (current weight / max weight)
     */

    @Override
    public void calculateChanceOfLandingCrash(){
        this.setChanceOfLandingCrash(8.0/100.0 * ((double)this.getCurrentWeight()/(double)this.getMaxWeight()));
    }

}
