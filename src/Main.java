import game.GUI;
import Phones.Phones;

public class Main implements Runnable {

    GUI gui = new GUI();

    Phones workingPhones = new Phones();
    Phones brokenPhones = new Phones();

    byte testedPhonesCounter = 0;

    public static void main(String[] args) {

        new Thread(new Main()).start();
    }

    @Override
    public void run() {

        while (testedPhonesCounter < 5) {

            gui.repaint();

            // workingPhones.add(new WorkingPhones(phoneSerialNumber));
            // brokenPhones.add(new BrokenPhones(phoneSerialNumber));
        }
    }
}