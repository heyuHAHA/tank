package tank;

import java.awt.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Frame tankFrame = new TankFrame();
        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
