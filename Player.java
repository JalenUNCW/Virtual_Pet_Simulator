public class Player {
    private double money;
    private int points;
    private Achievements[] achievements;

    public enum Achievements {
        Healthy,
        WellFed,
        AlwaysHappy,
        Unhealthy //.....

    }
    public double getMoney() { return money; }
    public int getPoints() { return points; }

    public void setMoney(double money) { this.money = money; }
    public void setPoints(int points) { this.points = points; }


}
