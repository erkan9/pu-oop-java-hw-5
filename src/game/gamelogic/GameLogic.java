package game.gamelogic;

import Phones.Phones;
import game.GUI;

import static javax.swing.JOptionPane.showMessageDialog;

public class GameLogic {

    GUI gui = new GUI();

    Phones workingPhones = new Phones();
    Phones brokenPhones  = new Phones();

    private byte testedPhonesCounter = 0;
    private final int maxClicksPossible = 12_228;
    private final int maxBrokenPixelsLimit = 2048;

    boolean isPhoneBroken = false;

    public GameLogic() { }

    /**
     * Method that contains the main logic of the game
     */
    public void gameLogic() {

        while (this.testedPhonesCounter < 5) {

            while (this.gui.clickCounter <= maxClicksPossible) {

                //instead of checking for 2 048 [50%] pixels I check for 3 broken pixels
                if (this.gui.brokenPixelCounter >= maxBrokenPixelsLimit) {

                    this.isPhoneBroken = true;
                    break;
                }
                this.gui.repaint();
            }

            checkPhoneCondition();

            this.testedPhonesCounter++;
            this.gui.resetBoardWhenPixelsAreChecked();
        }

        getBrokenPhones();
        getWorkingPhones();

        showMessageDialog(null, "Working and Broken phones are at The Console");
        System.exit(0);
    }

    /**
     * Method that checks the condition of the phone
     */
    private void checkPhoneCondition() {

        if (this.isPhoneBroken) {

            showMessageDialog(null, "Pixel counting ended, this Phone is Broken");
            this.brokenPhones.add(this.gui.phoneSerialNumber);

        } else {

            showMessageDialog(null, "Pixel counting ended, this Phone is Working");
            this.workingPhones.add(this.gui.phoneSerialNumber);
        }
    }

    /**
     * Method that outputs the Working phones at the Console
     */
    private void getWorkingPhones() {

        for (int index = 0; index < 5; index++) {

            if (this.workingPhones.get(index) != null) {

                System.out.printf("[%s] - Working Phone\n", this.workingPhones.get(index));
            }
        }
    }

    /**
     * Method that outputs the Broken phones at the Console
     */
    private void getBrokenPhones() {

        for (int index = 0; index < 5; index++) {

            if (this.brokenPhones.get(index) != null) {

                System.out.printf("[%s] - Broken Phone\n", this.brokenPhones.get(index));
            }
        }
    }
}