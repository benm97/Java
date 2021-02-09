import oop.ex2.GameGUI;

import java.awt.*;

/**
 * This class represents the human ship.
 * The Human ship can accelerate, turn and teleport, activate his shields and fire
 * when each action is activated by the user when he presses the appropriate button.
 * This ship has to run and accelerate at the same time.
 */
public class HumanShip extends SpaceShip {

    /**
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        shield = false;
        if (game.getGUI().isTeleportPressed())
            teleport();

        int turn = 0;
        boolean accelerate = false;
        if (game.getGUI().isLeftPressed())
            turn++;
        if (game.getGUI().isRightPressed())
            turn--;
        if (game.getGUI().isUpPressed())
            accelerate = true;
        shipPhysics.move(accelerate, turn);
        if (game.getGUI().isShieldsPressed())
            shieldOn();
        if (game.getGUI().isShotPressed())
            fire(game);
        roundBasics();
    }

    /**
     * @return the appropriate image
     */
    @Override
    public Image getImage() {
        if (shield)
            return GameGUI.SPACESHIP_IMAGE_SHIELD;
        return GameGUI.SPACESHIP_IMAGE;
    }
}
