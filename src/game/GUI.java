package game;

import gameadditions.SerialNumber;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GUI extends JFrame {

    SerialNumber serialNumber = new SerialNumber();

    private final byte BOX_WIDTH_HEIGHT = 14;
    public final int[][] pixelBoxes = new int[64][64];
    private int mouseClickedColumn = 0;
    private int mouseClickedRow = 0;

    public String phoneSerialNumber;

    public GUI() {

        this.setTitle("Broken Pixels");
        this.setSize(912,948);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        phoneSerialNumber = serialNumber.serialNumberSetter();

        Board board = new Board(this);
        this.setContentPane(board);

        board.setBoardPixelType();
        board.checkPixelCondition();

        Move move = new Move();
        this.addMouseMotionListener(move);

        Click click = new Click();
        this.addMouseListener(click);
    }

    public String getPhoneSerialNumber() {
        return phoneSerialNumber;
    }

    public int[][] getPixelBoxes() {
        return pixelBoxes;
    }

    public byte getBOX_WIDTH_HEIGHT() { return BOX_WIDTH_HEIGHT; }

    /**
     * Method that does the logic for clicking in Pixel Boxes
     */
    private void pixelBoxClickLogic() {

        pixelBoxes[mouseClickedColumn][mouseClickedRow]  += 100;

        boolean checkForBrokenPixel = pixelBoxes[mouseClickedColumn][mouseClickedRow] / 10 % 10 == 3 &&
                pixelBoxes[mouseClickedColumn][mouseClickedRow] / 100 == 1;

        boolean checkForAlmostBrokenPixel = pixelBoxes[mouseClickedColumn][mouseClickedRow] / 10 % 10 == 2 &&
                pixelBoxes[mouseClickedColumn][mouseClickedRow] / 100 == 3;

        if(checkForBrokenPixel || checkForAlmostBrokenPixel) {

            pixelBoxes[mouseClickedColumn][mouseClickedRow] = 4;

            //brokenPixelCounter++;

            System.out.printf("Clicked  Column:[%d] Row:[%d]\n", mouseClickedColumn, mouseClickedRow);
            System.out.printf("The Pixel at Column:[%d] Row:[%d] broke\n\n", mouseClickedColumn, mouseClickedRow);
        }
    }

    public class Move implements MouseMotionListener {


        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

           mouseClickedColumn = e.getX();
           mouseClickedRow = e.getY();
        }
    }

    public class Click implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {

            mouseClickedColumn = e.getX() / BOX_WIDTH_HEIGHT;
            mouseClickedRow = (e.getY() - 50) / BOX_WIDTH_HEIGHT;

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