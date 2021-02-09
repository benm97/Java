import oop.ex2.SpaceShipPhysics;

/**
 * This class represents the special ship.
 * To represent this ship I implemented a more strong and successful ship.
 */
public class SpecialShip extends SpaceShip {

    /*Double variable representing the distance when we start to pursue the closest ship and fire him*/
    private static final double PURSUE_AND_FIRE_DISTANCE = 0.3;

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
        if (shipPhysics.distanceFrom(closestPhysics) > PURSUE_AND_FIRE_DISTANCE) /* If the ship is far enough from the
        closest ships it runs. */
            run(game, closestPhysics);
        else /* If the ship is too close from the closest ship it pursuits it. */
            pursuit(game, closestPhysics);

        if (shipPhysics.distanceFrom(closestPhysics) <= SHIELD_DISTANCE) { /* If the ship is close enough from the
        closest ship the ship shields on. */
            shieldOn();

        }
        if (shipPhysics.distanceFrom(closestPhysics) <= PURSUE_AND_FIRE_DISTANCE) { /* If the ship is clos enough
        from the closest ship the ship fires on. */
            fire(game);
        }
        roundBasics();
    }
}
