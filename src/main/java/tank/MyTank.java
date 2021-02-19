package tank;

import java.awt.*;

public class MyTank {
    private int x, y;
    private Dir dir = Dir.DOWN;

    public MyTank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g) {
        g.fillRect(x,y,50,50);
        switch (dir) {
            case LEFT:
                x -= 10;
                break;
            case RIGHT:
                x += 10;
                break;
            case DOWN:
                y += 10;
                break;
            case UP:
                y -= 10;
                break;
            default:
                break;
        }
    }

    public void setTankDir(Dir dir) {
        this. dir = dir;
    }
}
