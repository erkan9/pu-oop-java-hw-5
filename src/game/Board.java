package game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Board extends JPanel {

    Random random = new Random();

    private final byte BOX_WIDTH_HEIGHT;
    private final int[][] pixelBoxes;

    private int brokenPixelCounter = 0;

    private final String phoneSerialNumber;

    public Board(GUI gui) {

        this.BOX_WIDTH_HEIGHT = gui.getBOX_WIDTH_HEIGHT();
        this.pixelBoxes = gui.getPixelBoxes();
        this.phoneSerialNumber = gui.getPhoneSerialNumber();
    }

    @Override
    public void paintComponent(Graphics g) {

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 896, 950);

        paintBoxesForPixels(g);
        displaySerialNumber(g);
    }

    /**
     * Method that displays the Serial Number of the Phone on top of Pixels
     *
     * @param g Object of Graphics
     */
    private void displaySerialNumber(Graphics g) {

        g.drawString(this.phoneSerialNumber, 410, 10);
    }

    /**
     * Method that paints the Pixel Boxes on the game.Board
     *
     * @param g Object of Graphics
     */
    private void paintBoxesForPixels(Graphics g) {

        for (int column = 0; column < 64; column++) {

            for (int row = 0; row < 64; row++) {

                whichColorToPaintBox(column, row, g);
            }
        }
    }

    /**
     * Method that checks Pixel Box's condition
     */
    public void checkPixelCondition() {

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
     * Method that paints the Pixel Box depending on it's type
     *
     * @param column The Column from the For loop
     * @param row    The Row from the nester For loop
     * @param g      The object of Graphics
     */
    private void whichColorToPaintBox(int column, int row, Graphics g) {

        if (this.pixelBoxes[column][row] % 10 == 1) {

            g.setColor(Color.GREEN);

        } else if (this.pixelBoxes[column][row] % 10 == 2) {

            g.setColor(Color.BLUE);

        } else if (this.pixelBoxes[column][row] % 10 == 3) {

            g.setColor(Color.RED);
        }
        else if (this.pixelBoxes[column][row] % 10 == 4) {

            g.setColor(Color.BLACK);

            brokenPixelCounter++;
        }
        drawPixelBox(column, row, g);
    }

    /**
     * Method that draws a Pixel Box
     *
     * @param column The Column from the For loop
     * @param row    The Row from the nester For loop
     * @param g      The object of Graphics
     */
    private void drawPixelBox(int column, int row, Graphics g) {

        g.fillRect(column * BOX_WIDTH_HEIGHT, row * BOX_WIDTH_HEIGHT + BOX_WIDTH_HEIGHT, BOX_WIDTH_HEIGHT, BOX_WIDTH_HEIGHT);
    }


    /**
     * Method that sets Pixel Box's type to get a color
     */
    public void setBoardPixelType() {

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
}