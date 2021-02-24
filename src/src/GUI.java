import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public class GUI extends JFrame {

    Random random = new Random();
    SerialNumber serialNumber = new SerialNumber();

    private final byte BOX_WIDTH_AND_HEIGHT = 14;
    private int mouseClickedColumn = 0;
    private int mouseClickedRow = 0;

    int[][] pixelBoxes = new int[64][64];

    String phoneSerialNumber;

    public GUI() {

        this.setTitle("Broken Pixels");
        this.setSize(912, 950);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        Board board = new Board(this);
        this.setContentPane(board);

        board.setBoardPixelColors();
        board.checkPixelCondition();

        phoneSerialNumber = serialNumber.serialNumberSetter();

        Move move = new Move();
        this.addMouseMotionListener(move);

        Click click = new Click();
        this.addMouseListener(click);
    }

    public byte getBOX_WIDTH_AND_HEIGHT() {
        return BOX_WIDTH_AND_HEIGHT;
    }

    public int[][] getPixelBoxes() {
        return pixelBoxes;
    }

    /**
     * Method that does the logic when click in Pixel Box
     */
    private void pixelBoxClickLogic() {

        pixelBoxes[mouseClickedColumn][mouseClickedRow] += 100;

        if (pixelBoxes[mouseClickedColumn][mouseClickedRow] / 10 % 10 == 3 &&
                pixelBoxes[mouseClickedColumn][mouseClickedRow] / 100 == 1) {

            pixelBoxes[mouseClickedColumn][mouseClickedRow] = 4;

            System.out.printf("The Pixel Column:[%d] Row:[%d] broke down\n", mouseClickedColumn, mouseClickedRow);
        }
       else if (pixelBoxes[mouseClickedColumn][mouseClickedRow] / 10 % 10 == 2 &&
                pixelBoxes[mouseClickedColumn][mouseClickedRow] / 100 == 3) {

            pixelBoxes[mouseClickedColumn][mouseClickedRow] = 4;

            System.out.printf("The Pixel Column:[%d] Row:[%d] broke down\n", mouseClickedColumn, mouseClickedRow);
        }
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

            mouseClickedColumn = e.getX() / BOX_WIDTH_AND_HEIGHT;
            mouseClickedRow    = (e.getY() - 60) / BOX_WIDTH_AND_HEIGHT;

            checkIfClickedInBox();

            repaint();
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
         * Method that checks if the player clicked inside a Pixel box
         */
        private void checkIfClickedInBox() {

            if(mouseClickedColumn != -1 || mouseClickedRow != -1 ) {

                pixelBoxClickLogic();
            }
            else {

                System.out.println("you did not click in a box");
            }
        }
    }
}