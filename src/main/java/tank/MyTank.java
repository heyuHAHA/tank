package tank;

import java.awt.*;

public class MyTank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private boolean moving = false;
    private final int speed = 10;

    private TankFrame tankFrame;

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    public MyTank(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.BLUE);
        g.fillRect(x,y,100,100);
        g.setColor(c);
        if (!moving) return;
        switch (dir) {
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case DOWN:
                y += speed;
                break;
            case UP:
                y -= speed;
                break;
            default:
                break;
        }
    }

    public void setTankDir(Dir dir) {
        this. dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
        tankFrame.addBullet(new Bullet (this.x, this.y,this.dir));
    }
}
