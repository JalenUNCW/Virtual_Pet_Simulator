public class Pet {
    private int age;
    private int petWeight;
    private int health;
    private int hunger;
    private int happiness;
    private int energy;

    // accessors
    public int getAge() { return this.age; }
    public int getPetWeight() { return petWeight; }
    public int getHealth() { return this.health; }
    public int getHunger() { return this.hunger; }
    public int getEnergy() { return energy; }
    public int getHappiness() { return happiness; }


    //mutators
    public void setAge(int age) { this.age = age; }
    public void setPetWeight(int petWeight) { this.petWeight = petWeight; }
    public void setHealth(int health) { this.health = health; }
    public void setHunger(int hunger) { this.hunger = hunger; }
    public void setEnergy(int energy) { this.energy = energy; }
    public void setHappiness(int happiness) { this.happiness = happiness; }

    // the following methods are just a rough outline we can continue tweaking
    public void feed(FoodQuality foodQuality) {

        switch (foodQuality) {
            case FoodQuality.Low:
                this.hunger += 20;
                this.happiness -= 20;
                break;

            case FoodQuality.Medium:
                this.hunger += 40;
                break;

            case FoodQuality.High:
                this.hunger += 40;
                this.happiness += 20;
        }
    }

    public void train() {
        this.energy -= 20;
        this.happiness += 20;
    }

    public void outing() {
        this.happiness += 20;
        this.energy -= 30;
    }

    public void play() {
        this.energy -= 20;
        this.happiness += 40;
    }

    public void checkup() {
        this.health += 50;
        this.happiness -= 20;
    }
}
