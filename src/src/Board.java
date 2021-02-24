import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Board extends JPanel {

    private final GUI gui;

    Random random = new Random();

    private final byte BOX_WIDTH_AND_HEIGHT;
    int[][] pixelBoxes = new int[64][64];

    public Board(GUI gui) {

        this.gui = gui;
        BOX_WIDTH_AND_HEIGHT = gui.getBOX_WIDTH_AND_HEIGHT();
        pixelBoxes = gui.getPixelBoxes();
    }

    /**
     * Method that draws the components of our Board/Phone Screen
     * @param g Object of Graphics g
     */
    @Override
    public void paintComponent(Graphics g) {

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 912, 950);

        paintBoxesForPixels(g);
        displaySerialNumber(g);
    }

    /**
     * Method that displays the Serial Number on top of the Pixels
     *
     * @param g Object of Graphics
     */
    private void displaySerialNumber(Graphics g) {

        g.drawString(gui.phoneSerialNumber, 410, 10);
    }

    /**
     * Method that paints the Boxes that look like Pixels
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
     * Method that checks every Pixel's condition
     */
    public void checkPixelCondition() {

        Random random = new Random();

        for (int column = 0; column < 64; column++) {

            for (int row = 0; row < 64; row++) {

                int randomNumber = random.nextInt(4) + 1;

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
     * Method that chooses The Pixel box's color
     *
     * @param column The Column from For loop
     * @param row    The Row from the nested For loop
     * @param g      Object of Graphics
     */
    private void whichColorToPaintBox(int column, int row, Graphics g) {

        if (this.pixelBoxes[column][row] % 10 == 1) {

            g.setColor(Color.GREEN);
        } else if (this.pixelBoxes[column][row] % 10 == 2) {

            g.setColor(Color.BLUE);
        } else if (this.pixelBoxes[column][row] % 10 == 3) {

            g.setColor(Color.RED);
        } else if (this.pixelBoxes[column][row] % 10 == 4) {

            g.setColor(Color.BLACK);
        }

        createBoxDimensions(column, row, g);
    }

    /**
     * Method that creates a Pixel box
     *
     * @param column The Column from For loop
     * @param row    The Row from the nested For loop
     * @param g      Object of Graphics
     */
    private void createBoxDimensions(int column, int row, Graphics g) {

        g.fillRect(column * BOX_WIDTH_AND_HEIGHT, row * BOX_WIDTH_AND_HEIGHT + BOX_WIDTH_AND_HEIGHT,
                BOX_WIDTH_AND_HEIGHT, BOX_WIDTH_AND_HEIGHT);
    }

    /**
     * Method that sets Pixel box's color
     */
    public void setBoardPixelColors() {

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