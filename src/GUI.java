import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public class GUI extends JFrame{

    private final byte SPACING = 0;
    private int mouseMoveXCoordinate = -100;
    private int mouseMoveYCoordinate = -100;

    int[][] pixelBoxes = new int[64][64];

    Random random = new Random();

    SerialNumber serialNumber = new SerialNumber();

    String phoneSerialNumber;

    public GUI() {

        this.setTitle("Broken Pixels");
        this.setSize(912,950);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        Board board = new Board();
        this.setContentPane(board);

        board.setBoardPixelColors();
        board.checkPixelCondition();

        phoneSerialNumber = serialNumber.serialNumberSetter();

        Move move = new Move();
        this.addMouseMotionListener(move);

        Click click = new Click();
        this.addMouseListener(click);
    }

    public class Board extends JPanel{

        /*
        Method that draws the components of our Board/Phone Screen
         */
        @Override
        public void paintComponent(Graphics g) {

            //paint the background's color
            g.setColor(Color.WHITE);
            g.fillRect(0,0,912,950);

            paintBoxesForPixels(g);
            displaySerialNumber(g);
        }

        private void displaySerialNumber(Graphics g) {

            g.drawString(phoneSerialNumber, 410, 10);
        }

        private void paintBoxesForPixels(Graphics g) {

            int randomNumber;

            for (int column = 0; column < 64; column++) {

                for (int row = 0; row < 64; row++) {

                    whichColorToPaintBox(column, row, g);
                }
            }
        }

        private void checkPixelCondition() {

            Random random = new Random();

            for (int column = 0; column < 64; column++) {

                for (int row = 0; row < 64; row++) {

                    int randomNumber = random.nextInt(4) + 1;

                 if(randomNumber == 1) {

                     pixelBoxes[column][row] += 10;
                 }
                 if(randomNumber == 2) {

                     pixelBoxes[column][row] += 20;
                 }
                 if(randomNumber == 3) {

                     pixelBoxes[column][row] += 30;
                 }
                }
            }
        }

        //private void

        private void whichColorToPaintBox(int column, int row, Graphics g) {

            if(pixelBoxes[column][row] % 10 == 1) {

                g.setColor(Color.GREEN);
            }
            else if (pixelBoxes[column][row] % 10 == 2) {

                g.setColor(Color.BLUE);
            }
            else if (pixelBoxes[column][row] % 10 == 4) {

                g.setColor(Color.BLACK);
            }
            else  if (pixelBoxes[column][row] % 10 == 3) {

                g.setColor(Color.RED);
            }

            g.fillRect(SPACING + column * 14, SPACING + row * 14 + 14, 14 - 2 * SPACING, 14 - 2 * SPACING);
        }

        private void setBoardPixelColors() {

            int randomNumber;

            for (int column = 0; column < 64; column++) {

                for (int row = 0; row < 64; row++) {

                    randomNumber = random.nextInt(30);

                    if(randomNumber < 10 ) {

                        pixelBoxes[column][row] = 1;
                    }
                    else if (randomNumber < 20) {

                        pixelBoxes[column][row] = 2;
                    }
                    else if (randomNumber < 30) {

                        pixelBoxes[column][row] = 3;
                    }
                }
            }
        }
    }

    public class Move implements MouseMotionListener {


        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

           mouseMoveXCoordinate = e.getX();
           mouseMoveYCoordinate = e.getY();
        }
    }

    public class Click implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {

            mouseMoveXCoordinate = e.getX() / 14;
            mouseMoveYCoordinate = (e.getY() - 42) / 14;

            System.out.printf("Clicked Column[%d]\n", mouseMoveXCoordinate);
            System.out.printf("Clicked Row[%d]\n", mouseMoveYCoordinate);

            System.out.println(pixelBoxes[mouseMoveXCoordinate][mouseMoveYCoordinate]);

            pixelBoxes[mouseMoveXCoordinate][mouseMoveYCoordinate]  += 100;

            System.out.println(pixelBoxes[mouseMoveXCoordinate][mouseMoveYCoordinate]);

            if(pixelBoxes[mouseMoveXCoordinate][mouseMoveYCoordinate] / 10 % 10 == 3 &&
                    pixelBoxes[mouseMoveXCoordinate][mouseMoveYCoordinate] / 100 == 1) {

                pixelBoxes[mouseMoveXCoordinate][mouseMoveYCoordinate] = 4;
            }
            if(pixelBoxes[mouseMoveXCoordinate][mouseMoveYCoordinate] / 10 % 10 == 2 &&
                    pixelBoxes[mouseMoveXCoordinate][mouseMoveYCoordinate] / 100 == 3) {

                pixelBoxes[mouseMoveXCoordinate][mouseMoveYCoordinate] = 4;
            }

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
    }
}