public class Pet {
    private int age;
    private int health;
    private int hunger;
    private int thirst;
    private int happiness;
    private int energy;
    private Location location;

    public enum Location{
        House,
        Park,
        SideWalk,
        Vet;
    }

    // accessors
    public int getAge() { return this.age; }
    public int getHealth() { return this.health; }
    public int getHunger() { return this.hunger; }
    public int getThirst() { return this.thirst; }
    public int getEnergy() { return energy; }
    public int getHappiness() { return happiness; }
    public Location getLocation() { return location; }

    //mutators
    public void setAge(int age) { this.age = age; }
    public void setHealth(int health) { this.health = health; }
    public void setHunger(int hunger) { this.hunger = hunger; }
    public void setThirst(int thirst) { this.thirst = thirst; }
    public void setEnergy(int energy) { this.energy = energy; }
    public void setHappiness(int happiness) { this.happiness = happiness; }
    public void setLocation(Location location) { this.location = location; }


}
