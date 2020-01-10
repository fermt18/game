package roles;

public class Soldier {

    private String team;
    private int hitPoints;
    private int[] coordinates;

    public String getTeam(){ return this.team; }
    public int getHitPoints(){ return this.hitPoints; }
    public int[] getCoordinates(){ return this.coordinates; }

    public Soldier(String team, int hitPoints, int[] coordinates){
        this.team = team;
        this.hitPoints = hitPoints;
        this.coordinates = coordinates;
    }

    void isHit(int pointsHit){
        hitPoints -= pointsHit;
    }

    public void hits(Soldier soldier, int pointsHit){
        soldier.isHit(pointsHit);
    }

    public void moves(int incX, int incY){
        coordinates[0] += incY;
        coordinates[1] += incX;
    }
}
