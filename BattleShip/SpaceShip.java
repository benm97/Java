import java.awt.Image;

import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game.
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 * a base class for the other spaceships or any other option you will choose.
 *
 * @author oop
 */
public abstract class SpaceShip {

    /**
     * Int variable representing the raising of the current energy level after bashing
     */
    private static final int BASHING_BONUS = 18;

    /**
     * Int variable representing the decreasing of the current energy level after get hit
     */
    private static final int HIT_ENERGY_COST = 10;

    /**
     * Int variable representing the decreasing of the current energy level after firing
     */
    private static final int FIRING_ENERGY = 19;

    /**
     * Int variable representing the decreasing of the current energy level after activating shield
     */
    private static final int SHIELD_ENERGY = 3;

    /**
     * Int variable representing the decreasing of the current energy level after teleport
     */
    private static final int TELEPORT_ENERGY = 140;

    /**
     * Int variable that represents the left side
     */
    protected static final int LEFT = 1;

    /**
     * Int variable that represents the right side
     */
    protected static final int RIGHT = -1;

    /* Int variable representing the default max energy level */
    private static final int DEFAULT_MAX_ENERGY = 210;

    /* Int variable representing the default cur energy level */
    private static final int DEFAULT_CUR_ENERGY = 190;

    /* Int variable representing the default health level */
    private static final int DEFAULT_HEALTH = 22;

    /* Int variable representing the number of rounds that we need to wait before firing again */
    private static final int FIRE_COOL_TIME = 7;

    /**
     * SpacesShipPhysics object representing the physical state of the spaceship.
     */
    protected SpaceShipPhysics shipPhysics;

    /* Int variable representing the max energy level */
    private int maxEnergy;

    /* Int variable representing the cur energy level */
    private int curEnergy;

    /* Int variable representing the health level */
    private int health;

    /**
     * Boolean variable representing the shields state
     */
    protected boolean shield;

    /**
     * Int variable representing the number of rounds from the last shot
     */
    protected int fireCool;

    /* SpaceShip constructor */
    public SpaceShip() {
        init();
    }

    /* Function that initializes the ships' parameters */
    private void init() {
        shipPhysics = new SpaceShipPhysics();
        shield = false;
        maxEnergy = DEFAULT_MAX_ENERGY;
        curEnergy = DEFAULT_CUR_ENERGY;
        health = DEFAULT_HEALTH;
        fireCool = 0;
    }

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public abstract void doAction(SpaceWars game);

    /**
     * Function that does the basics actions of a single round
     */
    protected void roundBasics() {
        if (curEnergy < maxEnergy)
            curEnergy++;
        if (fireCool > 0)
            fireCool--;
    }

    /**
     * React to a shot or a collision when shield down
     */
    private void touche() {
        health--;
        maxEnergy -= HIT_ENERGY_COST;
        if (curEnergy > maxEnergy)
            curEnergy = maxEnergy;
    }

    /**
     * This method is called every time a collision with this ship occurs
     */
    public void collidedWithAnotherShip() {
        if (shield) {
            maxEnergy += BASHING_BONUS;
            curEnergy += BASHING_BONUS;
        } else
            touche();
    }

    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset() {
        init();
    }

    /**
     * Checks if this ship is dead.
     *
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        if (health == 0)
            return true;
        return false;
    }

    /**
     * Gets the physics object that controls this ship.
     *
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return shipPhysics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (!shield)
            touche();
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public Image getImage() {
        if (shield)
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }

    /**
     * Attempts to fire a shot.
     *
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if (curEnergy - FIRING_ENERGY >= 0 && fireCool == 0) {
            curEnergy -= FIRING_ENERGY;
            fireCool = FIRE_COOL_TIME;
            game.addShot(shipPhysics);
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (curEnergy - SHIELD_ENERGY >= 0) {
            curEnergy -= SHIELD_ENERGY;
            shield = true;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (curEnergy - TELEPORT_ENERGY >= 0) {
            curEnergy -= TELEPORT_ENERGY;
            shipPhysics = new SpaceShipPhysics();
        }
    }

    /**
     * Function that drags the ship to pursuit the closest ship.
     *
     * @param game           the game object
     * @param closestPhysics the closest ship
     */
    protected void pursuit(SpaceWars game, SpaceShipPhysics closestPhysics) {
        int turn = 0;
        if (shipPhysics.angleTo(closestPhysics) < 0)
            turn = RIGHT;
        else if (shipPhysics.angleTo(closestPhysics) > 0)
            turn = LEFT;
        shipPhysics.move(true, turn);
    }

    /**
     * Function that drags the ship to run from the closest ship.
     *
     * @param game           the game object
     * @param closestPhysics the closest ship
     */
    protected void run(SpaceWars game, SpaceShipPhysics closestPhysics) {
        int turn = 0;
        if (shipPhysics.angleTo(closestPhysics) < 0)
            turn = LEFT;
        else if (shipPhysics.angleTo(closestPhysics) > 0)
            turn = RIGHT;
        shipPhysics.move(true, turn);
    }
}
