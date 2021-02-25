package game;

import javax.swing.*;
import java.awt.*;

//TODO fix not changing Serial num at board

public class Board extends JPanel {

    GUI gui;
    Font serialNumberFont = new Font("Tacoma", Font.BOLD, 12);

    private final byte BOX_WIDTH_HEIGHT;
    private final int[][] pixelBoxes;

    public Board(GUI gui) {

        this.gui = gui;
        this.BOX_WIDTH_HEIGHT = gui.getBOX_WIDTH_HEIGHT();
        this.pixelBoxes = gui.getPixelBoxes();
    }

    /**
     * Method that paints the components
     * @param g Object of Graphics
     */
    @Override
    public void paintComponent(Graphics g) {

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 896, 950);

        paintBoxesForPixels(g);
        displaySerialNumberAndBrand(g);
    }

    /**
     * Method that displays the Serial Number of the Phone on top of Pixels
     *
     * @param g Object of Graphics
     */
    private void displaySerialNumberAndBrand(Graphics g) {

        g.setFont(this.serialNumberFont);
        g.setColor(Color.BLACK);
        g.drawString(this.gui.phoneSerialNumber, 420, 11);
        g.drawString(this.gui.testedPhoneBrand, 5,11);
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

        g.fillRect(column * this.BOX_WIDTH_HEIGHT, row * this.BOX_WIDTH_HEIGHT + this.BOX_WIDTH_HEIGHT, this.BOX_WIDTH_HEIGHT, this.BOX_WIDTH_HEIGHT);
    }
}