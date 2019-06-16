package com.company;

public class Item implements Comparable{

    private String name;
    private int weight;

    public Item(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    /**
     * Method to compare two item by weight
     * @param comparedItem - item to compare
     * @return - result of comparision
     */

    @Override
    public int compareTo(Object comparedItem) {
        int comparedWeight = ((Item)comparedItem).getWeight();
        return this.getWeight()-comparedWeight;
    }

}
