import oop.ex2.SpaceShipPhysics;

/**
 * This class represents the Basher ship.
 * The basher tries to run after other ships.
 * So it's accelerate and constantly turn in direction of the closest ship.
 * If it is to close from another ship it will turns on his shields.
 */
public class BasherShip extends SpaceShip {

    /**
     * Int variable representing the distance when we start to shield on.
     */
    private static final double SHIELD_DISTANCE = 0.19;

    /**
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        shield = false;
        SpaceShipPhysics closestPhysics = game.getClosestShipTo(this).shipPhysics;
        pursuit(game, closestPhysics);
        if (shipPhysics.distanceFrom(closestPhysics) <= SHIELD_DISTANCE) { /* If it is close enough from the closest
        ship it will shield on. */
            shieldOn();

        }
        roundBasics();
    }
}
