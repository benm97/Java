import oop.ex2.SpaceShipPhysics;

/**
 * This class represents the runner ship.
 * The runner tries to run from all other ships.
 * So it's always accelerates and runs from his closest ship.
 * If it is to close from another ship it will teleport.
 */
public class RunnerShip extends SpaceShip {

    /*Double variable representing the angle when we start to teleport*/
    private static final double TELEPORT_ANGLE = 0.23;

    /*Double variable representing the distance when we start to teleport*/
    private static final double TELEPORT_DISTANCE = 0.25;

    /**
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        shield = false;
        SpaceShipPhysics closestPhysics = game.getClosestShipTo(this).shipPhysics;
        if (shipPhysics.angleTo(closestPhysics) < TELEPORT_ANGLE && shipPhysics.distanceFrom(closestPhysics)
                < TELEPORT_DISTANCE) { /* If the ship is too close to the closest ship it will teleport*/
            teleport();
        }
        run(game, closestPhysics);
        roundBasics();
    }
}
