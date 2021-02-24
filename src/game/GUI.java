package game;

import phoneadditions.PhoneBrand;
import phoneadditions.SerialNumber;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public class GUI extends JFrame {

    SerialNumber serialNumber = new SerialNumber();
    PhoneBrand phoneBrand     = new PhoneBrand();
    Random random             = new Random();

    public final int[][] pixelBoxes = new int[64][64];

    protected final byte BOX_WIDTH_HEIGHT = 14;

    protected int mouseClickedColumn = 0;
    protected int mouseClickedRow    = 0;

    public int brokenPixelCounter = 0;
    public int clickCounter       = 0;

    public String phoneSerialNumber;
    public String testedPhoneBrand;

    public GUI() {

        this.setTitle("Broken Pixels");
        this.setSize(912,949);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        this.phoneSerialNumber = this.serialNumber.serialNumberSetter();
        this.testedPhoneBrand =  this.phoneBrand.phoneBrandSetter();

        Board board = new Board(this);
        this.setContentPane(board);

        setBoardPixelType();
        pixelCondition();

        Move move   = new Move();
        this.addMouseMotionListener(move);

        Click click = new Click();
        this.addMouseListener(click);

        System.out.printf("%s - %s\n\n", this.testedPhoneBrand, this.phoneSerialNumber);
    }

    public String getPhoneSerialNumber() { return this.phoneSerialNumber; }

    public String getTestedPhoneBrand()  { return this.testedPhoneBrand; }

    public int[][] getPixelBoxes()       { return this.pixelBoxes; }

    public byte getBOX_WIDTH_HEIGHT()    { return this.BOX_WIDTH_HEIGHT; }

    /**
     * Method that does the logic for clicking in Pixel Boxes
     */
    private void pixelBoxClickLogic() {

        this.clickCounter++;

        this.pixelBoxes[this.mouseClickedColumn][this.mouseClickedRow]  += 100;

        boolean checkForBrokenPixel = (this.pixelBoxes[this.mouseClickedColumn][this.mouseClickedRow] / 10) % 10 == 3 &&
                this.pixelBoxes[this.mouseClickedColumn][this.mouseClickedRow] / 100 == 1;

        boolean checkForAlmostBrokenPixel = (this.pixelBoxes[this.mouseClickedColumn][this.mouseClickedRow] / 10) % 10 == 2 &&
                this.pixelBoxes[this.mouseClickedColumn][this.mouseClickedRow] / 100 == 3;

        if(checkForBrokenPixel || checkForAlmostBrokenPixel) {

            this.pixelBoxes[this.mouseClickedColumn][this.mouseClickedRow] = 4;

            this.brokenPixelCounter++;

            showInfo();
        }
    }


    /**
     * Method to show information when Broken pixel is found
     */
    private void showInfo() {

        System.out.printf("Clicked  Column:[%d] Row:[%d]\n", this.mouseClickedColumn, this.mouseClickedRow);
        System.out.printf("The Pixel at Column:[%d] Row:[%d] just broke down\n", this.mouseClickedColumn, this.mouseClickedRow);
        System.out.printf("Broken Pixels:[%d]\n", this.brokenPixelCounter);
        System.out.printf("Click counter:[%d]\n\n", this.clickCounter);
    }

    /**
     * Method that checks Pixel Box's condition
     */
    private void pixelCondition() {

        for (int column = 0; column < 64; column++) {

            for (int row = 0; row < 64; row++) {

                int randomNumber = this.random.nextInt(4) + 1;

                if (randomNumber == 1) {

                    this.pixelBoxes[column][row] += 10;
                }
                if (randomNumber == 2) {

                    this.pixelBoxes[column][row] += 20;
                }
                if (randomNumber == 3) {

                    this.pixelBoxes[column][row] += 30;
                }
            }
        }
    }

    /**
     * Method that sets Pixel Box's type to get a color
     */
    private void setBoardPixelType() {

        int randomNumber;

        for (int column = 0; column < 64; column++) {

            for (int row = 0; row < 64; row++) {

                randomNumber = this.random.nextInt(30);

                if (randomNumber < 10) {

                    this.pixelBoxes[column][row] = 1;

                } else if (randomNumber < 20) {

                    this.pixelBoxes[column][row] = 2;

                } else if (randomNumber < 30) {

                    this.pixelBoxes[column][row] = 3;
                }
            }
        }
    }


    /**
     * Method that resets Serial Number, Phone Brand and Pixels
     */
    public void resetBoardWhenPixelsAreChecked() {

        this.phoneSerialNumber = this.serialNumber.serialNumberSetter();
        this.testedPhoneBrand  = this.phoneBrand.phoneBrandSetter();

        this.brokenPixelCounter = 0;
        this.clickCounter = 0;

        setBoardPixelType();
        pixelCondition();

        System.out.printf("%s - %s\n\n", this.testedPhoneBrand, this.phoneSerialNumber);

    }

    public class Move implements MouseMotionListener {


        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

           mouseClickedColumn = e.getX();
           mouseClickedRow    = e.getY();
        }
    }

    public class Click implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

             final byte OFFSET_X = 5;
             final byte OFFSET_Y = 48;

            mouseClickedColumn = (e.getX() - OFFSET_X) / BOX_WIDTH_HEIGHT;
            mouseClickedRow    = (e.getY() - OFFSET_Y) / BOX_WIDTH_HEIGHT;

            checkIfClickedInBox();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        /**
         * method that checks if the player clicked inside a Pixel Box
         */
        private void checkIfClickedInBox() {

            if (mouseClickedColumn != -1 && mouseClickedRow != -1  && mouseClickedColumn != 64 && mouseClickedRow != 64) {

                pixelBoxClickLogic();
            }
            else {

                System.out.println("You did not click in a Box");
            }
        }
    }
}