import game.gamelogic.GameLogic;

public class Main implements Runnable {

    GameLogic gamelogic = new GameLogic();

    public static void main(String[] args) {

        new Thread(new Main()).start();
    }

    @Override
    public void run() {

        gamelogic.gameLogic();
    }
}