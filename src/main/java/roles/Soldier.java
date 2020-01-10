package roles;

public class Soldier {

    private Status status;
    private int hitPoints;
    private int[] coordinates;

    public Status getStatus(){ return this.status; }
    public int getHitPoints(){ return this.hitPoints; }
    public int[] getCoordinates(){ return this.coordinates; }

    public Soldier(Status status, int hitPoints, int[] coordinates){
        this.status = status;
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

    public boolean isEnemyOnSight(int[] enemyCoordinates){
        return calculateEuclideanDistance(this.coordinates, enemyCoordinates) < 3.0;
    }

    private double calculateEuclideanDistance(int[] ownCoords, int[] enemyCoords){
        return Math.sqrt(Math.pow(ownCoords[0] - enemyCoords[0], 2) + Math.pow(ownCoords[1] - enemyCoords[1], 2));
    }

    @Override
    public boolean equals(Object other){
        Soldier otherSoldier = (Soldier)other;
        return this.status.equals(otherSoldier.getStatus());
    }
}
