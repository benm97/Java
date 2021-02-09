import java.util.Random;

/**
 * This class represents the drunkard ship.
 * To represents this ship it moves, teleports and turns randomly.
 */
public class DrunkardShip extends SpaceShip {

    /*Int variable representing the probability of a move*/
    private static final int MOVE_PROBABILITY = 10;

    /*Int variable representing the probability of accelerating*/
    private static final int ACCELERATE_PROBABILITY = 3;

    /*Int variable representing the probability of firing*/
    private static final int FIRING_PROBABILITY = 10;

    /**
     * Int variable that represents the left side
     */
    protected static final int LEFT = 1;

    /**
     * Int variable that represents the right side
     */
    protected static final int RIGHT = -1;

    /**
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        Random random = new Random();
        int randomMove = random.nextInt(FIRING_PROBABILITY); /* the random variable of turning */
        int turn;
        switch (randomMove) {
            case 0:
                turn = LEFT;
                break;
            case 1:
                turn = 0;
                break;
            default:
                turn = RIGHT;

        }
        int randomAcc = random.nextInt(FIRING_PROBABILITY); /* the random variable of accelerating */
        boolean accelerate;
        switch (randomAcc) {
            case 0:
                accelerate = true;
                break;
            default:
                accelerate = false;
        }
        shipPhysics.move(accelerate, turn);
        int randomFire = random.nextInt(FIRING_PROBABILITY);
        if (randomFire == 0)
            fire(game);
        roundBasics();
    }
}
