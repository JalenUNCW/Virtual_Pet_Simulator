public class Player {
    private int points;
    private Achievements[] achievements;

    public enum Achievements {
        Healthy,
        WellFed,
        AlwaysHappy,
        Unhealthy //.....

    }
    public int getPoints() { return points; }

    public void setPoints(int points) { this.points = points; }


}
