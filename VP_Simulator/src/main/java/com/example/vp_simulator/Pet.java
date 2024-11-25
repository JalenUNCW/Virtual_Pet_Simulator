package com.example.vp_simulator;

/**
 * Represents a generic pet with basic attributes and actions.
 * This class serves as a base class for specific types of pets (e.g., Dog, Cat).
 */

public class Pet {
    private final String name;  // The name of the pet
    private int health;         // The pet's health level (0-100)
    private int hunger;         // The pet's hunger level (0-100, higher means less hungry)
    private int happiness;      // The pet's happiness level (0-100)
    private int energy;         // The pet's energy level (0-100)

    /**
     * Constructor to initialize a pet with a given name.
     * All attributes (health, hunger, happiness, energy) are initialized to 100.
     *
     * @param name the name of the pet
     */
    Pet(String name) {
        this.name = name;
        this.health = 100;
        this.hunger = 100;
        this.happiness = 100;
        this.energy = 100;
    }

    // Accessor methods for retrieving pet attributes
    public String getName() { return this.name; }
    public int getHealth() { return this.health; }
    public int getHunger() { return this.hunger; }
    public int getEnergy() { return energy; }
    public int getHappiness() { return happiness; }

    /**
     * Converts the pet's breed to a string representation.
     * This method is intended to be overridden by subclasses.
     *
     * @return a string indicating the breed (default: "Breed not found")
     */
    public String breedToString() { return "Breed not found"; }


    // Mutator methods for updating pet attributes
    public void setHealth(int health) { this.health = health; }
    public void setHunger(int hunger) { this.hunger = hunger; }
    public void setEnergy(int energy) { this.energy = energy; }
    public void setHappiness(int happiness) { this.happiness = happiness; }

    // Placeholder methods for pet actions
    // These methods are designed to be overridden by subclasses for specific behaviors.
    public void feed() {}

    public void train() {}

    public void outing() {}

    public void play() {}

    public void checkup() {}
}
