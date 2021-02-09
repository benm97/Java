/**
 * This is the SpaceShipFactory class.
 * This class creates all the SpaceShip objects according to the command line arguments and return them in a list.
 */
public class SpaceShipFactory {

    /**
     * String variable that represents the human ship
     */
    private static final String HUMAN = "h";

    /**
     * String variable that represents the runner ship
     */
    private static final String RUNNER = "r";

    /**
     * String variable that represents the basher ship
     */
    private static final String BASHER = "b";

    /**
     * String variable that represents the aggressive ship
     */
    private static final String AGGRESSIVE = "a";

    /**
     * String variable that represents the drunkard ship
     */
    private static final String DRUNKARD = "d";

    /**
     * String variable that represents the special ship
     */
    private static final String SPECIAL = "s";

    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] array = new SpaceShip[args.length];
        int counter = 0;
        for (String shipSymbol : args) {
            switch (shipSymbol) {
                case HUMAN:
                    array[counter] = new HumanShip();
                    break;
                case RUNNER:
                    array[counter] = new RunnerShip();
                    break;
                case BASHER:
                    array[counter] = new BasherShip();
                    break;
                case AGGRESSIVE:
                    array[counter] = new AggressiveShip();
                    break;
                case DRUNKARD:
                    array[counter] = new DrunkardShip();
                    break;
                case SPECIAL:
                    array[counter] = new SpecialShip();
                    break;

            }
            counter++;
        }
        return array;
    }
}
