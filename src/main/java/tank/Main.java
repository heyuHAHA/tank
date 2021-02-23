package tank;

import java.awt.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        for (int i = 0; i < 5; i++) {
            tankFrame.addTank(new MyTank(100 *i, 200, Dir.DOWN,tankFrame));
        }
        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
