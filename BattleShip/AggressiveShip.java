import oop.ex2.SpaceShipPhysics;

/**
 * This class represents the aggressive ship.
 * The aggressive ship is trying to pursuit other ships and fire them.
 * So it's always accelerate and turn towards the closest ship.
 * If it's close enough from the closest ship it will fire.
 */
public class AggressiveShip extends SpaceShip {

    /**
     * Int variable representing the distance when we start to fire.
     */
    private static final double SHOT_DISTANCE = 0.21;

    /**
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        shield = false;
        SpaceShipPhysics closestPhysics = game.getClosestShipTo(this).shipPhysics;
        pursuit(game, closestPhysics);
        if (shipPhysics.distanceFrom(closestPhysics) <= SHOT_DISTANCE) { /* If the ship is close enough from the
         closest ship it will fire. */
            fire(game);
        }
        roundBasics();
    }
}
