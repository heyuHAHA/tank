package tank;

import java.awt.*;

public class MyTank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private boolean moving = false;
    private final int speed = 10;
    public final int width = ResourceManager.tankD.getWidth();
    public final int height = ResourceManager.tankD.getHeight();
    private boolean living = true;

    private TankFrame tankFrame;

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    public MyTank(int x, int y, Dir dir) {

        this.x = x;
        this.y = y;
        this.dir = dir;
    }
    public MyTank(int x, int y, Dir dir, TankFrame tankFrame) {

        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paint(Graphics g) {
        if(!living) {
            //这里不能直接return,要移除tank
            tankFrame.removeTank(this);
            //加了这个就不会闪烁，不知道为什么。
           // tankFrame.repaint();
            return;
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceManager.tankL,x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.tankR,x, y, null);
                break;
            case UP:
                g.drawImage(ResourceManager.tankU,x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.tankD,x, y, null);
                break;
            default:break;

        }


        move();
    }

    public void move() {
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
        int x = this.x + width/2;
        int y = this.y + height/2 - Bullet.HEIGHT/2;
        tankFrame.addBullet(new Bullet (x, y,this.dir,this.tankFrame));
    }

    public void die() {
        this.living = false;
    }
}
