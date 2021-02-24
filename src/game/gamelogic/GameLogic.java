package game.gamelogic;

import Phones.Phones;
import game.GUI;

import static javax.swing.JOptionPane.showMessageDialog;

public class GameLogic {

    GUI gui = new GUI();

    Phones workingPhones = new Phones();
    Phones brokenPhones  = new Phones();

    byte testedPhonesCounter = 0;

    boolean isPhoneBroken = false;

    public GameLogic() { }

    public void gameLogic() {

        while (this.testedPhonesCounter < 3) {

            while (gui.clickCounter <= 12_288) {

                //instead of checking for 2 048 pixels i check 3
                if (gui.brokenPixelCounter >= 3) {

                    isPhoneBroken = true;
                    break;
                }
                gui.repaint();
            }

            checkPhoneCondition();

            this.testedPhonesCounter++;
            gui.resetBoardWhenPixelsAreChecked();
        }

        showBrokenPhones();
        showWorkingPhones();

        showMessageDialog(null, "Working and Broken phones are at The Console");
        System.exit(0);
    }

    /**
     * Method that checks the condition of the phone
     */
    private void checkPhoneCondition() {

        if (this.isPhoneBroken) {

            showMessageDialog(null, "Pixel counting ended, this Phone is Broken");
            this.brokenPhones.add(gui.phoneSerialNumber);
        } else {

            showMessageDialog(null, "Pixel counting ended, this Phone is Working");
            this.workingPhones.add(gui.phoneSerialNumber);
        }
    }

    /**
     * Method that outputs the Working phones at the Console
     */
    private void showWorkingPhones() {

        for (int i = 0; i < 5; i++) {

            if (this.workingPhones.get(i) != null) {

                System.out.printf("[%s] - Working Phone\n", this.workingPhones.get(i));
            }
        }
    }


    /**
     * Method that outputs the Broken phones at the Console
     */
    private void showBrokenPhones() {

        for (int i = 0; i < 5; i++) {

            if (this.brokenPhones.get(i) != null) {

                System.out.printf("[%s] - Broken Phone\n", this.brokenPhones.get(i));
            }
        }
    }
}