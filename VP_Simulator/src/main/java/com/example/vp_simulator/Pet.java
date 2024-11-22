package com.example.vp_simulator;

public class Pet {
    private int health;
    private int hunger;
    private int happiness;
    private int energy;

    Pet() {
        this.health = 100;
        this.hunger = 100;
        this.happiness = 100;
        this.energy = 100;
    }

    // accessors
    public int getHealth() { return this.health; }
    public int getHunger() { return this.hunger; }
    public int getEnergy() { return energy; }
    public int getHappiness() { return happiness; }


    //mutators
    public void setHealth(int health) { this.health = health; }
    public void setHunger(int hunger) { this.hunger = hunger; }
    public void setEnergy(int energy) { this.energy = energy; }
    public void setHappiness(int happiness) { this.happiness = happiness; }

    // the following methods are just a rough outline we can continue tweaking
    public void feed() {}

    public void train() {}

    public void outing() {}

    public void play() {}

    public void checkup() {}
}
