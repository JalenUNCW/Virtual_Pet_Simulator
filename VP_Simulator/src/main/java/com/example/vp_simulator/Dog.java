package com.example.vp_simulator;

/**
 * The Dog class represents a dog, which is a subclass of Pet.
 * It includes specific attributes and behaviors unique to dogs.
 *
 * This class extends the {@code Pet} class and implements additional
 * dog-specific functionality, such as handling the dog's breed and activities.
 *
 * @see Pet
 */
public class Dog extends Pet {

    /**
     * Enum representing the different breeds of dogs supported in this class.
     */
    public enum DogBreed {
        Lab,        // Represents a Labrador Retriever
        Shepherd;   // Represents a German Shepherd
    }

    private DogBreed breed;

    /**
     * Constructs a new Dog with the specified name and breed.
     *
     * @param name  the name of the dog
     * @param breed the breed of the dog, as a {@link DogBreed} enum value
     */
    public Dog(String name, DogBreed breed) {
        super(name); // Call the superclass constructor to initialize name
        this.breed = breed;
    }

    /**
     * Sets the breed of the dog.
     *
     * @param breed the new breed of the dog, as a {@link DogBreed} enum value
     */
    public void setBreed(DogBreed breed) {
        this.breed = breed;
    }

    /**
     * Gets the breed of the dog.
     *
     * @return the breed of the dog as a {@link DogBreed} enum value
     */
    public DogBreed getBreed() {
        return breed;
    }

    /**
     * Converts the breed of the dog to a string representation.
     *
     * @return a string representation of the dog's breed
     */
    @Override
    public String breedToString() {
        switch (breed) {
            case Lab:
                return "Lab";
            case Shepherd:
                return "Shepherd";
            default:
                return "Unknown Breed";
        }
    }

    /**
     * Feeds the dog, restoring hunger and energy to 100 and improving happiness.
     * If the happiness is less than 80, it is increased by 20, capped at 100.
     */
    @Override
    public void feed() {
        setHunger(100);
        setEnergy(100);
        if (getHappiness() < 80) {
            setHappiness(getHappiness() + 20);
        } else {
            setHappiness(100);
        }
    }

    /**
     * Trains the dog, decreasing energy by 10 and improving happiness.
     * If happiness is less than 80, it is increased by 20, capped at 100.
     */
    @Override
    public void train() {
        setEnergy(getEnergy() - 10);
        if (getHappiness() < 80) {
            setHappiness(getHappiness() + 20);
        } else {
            setHappiness(100);
        }
    }

    /**
     * Takes the dog on an outing, decreasing energy by 10 and improving happiness.
     * If happiness is less than 80, it is increased by 20, capped at 100.
     */
    @Override
    public void outing() {
        setEnergy(getEnergy() - 10);
        if (getHappiness() < 80) {
            setHappiness(getHappiness() + 20);
        } else {
            setHappiness(100);
        }
    }

    /**
     * Plays with the dog, decreasing energy by 10 and improving happiness.
     * If happiness is less than 80, it is increased by 20, capped at 100.
     */
    @Override
    public void play() {
        setEnergy(getEnergy() - 10);
        if (getHappiness() < 80) {
            setHappiness(getHappiness() + 20);
        } else {
            setHappiness(100);
        }
    }

    /**
     * Performs a checkup on the dog, restoring health to 100 but decreasing happiness.
     * Happiness is reduced by 20, but not below 0.
     */
    @Override
    public void checkup() {
        setHealth(100);
        if (getHappiness() > 20) {
            setHappiness(getHappiness() - 20);
        } else {
            setHappiness(0);
        }
    }
}
