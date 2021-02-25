package tank;

import java.awt.*;

public class Bullet {
    private static final int speed = 30;
    private int x, y;
    private Dir dir;
    public static final int WIDTH = ResourceManager.bulletD.getWidth();
    public static final int HEIGHT = ResourceManager.bulletD.getHeight();
    private boolean living = true;
    private TankFrame frame;

    public Bullet(int x, int y, Dir dir,TankFrame frame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.frame = frame;
    }

    public void paint(Graphics g) {
        if( !living ) {
            frame.removeBullet(this);
            return;
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceManager.bulletL,x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.bulletR,x, y, null);
                break;
            case UP:
                g.drawImage(ResourceManager.bulletU,x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.bulletD,x, y, null);
                break;
            default:break;

        }
        move();

    }

    private void move() {
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
        if (x < 0 || y < 0|| x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT)
            living = false;
    }

    public void collideWith(MyTank myTank) {
        Rectangle rect =  new Rectangle(this.x, this.y, WIDTH,HEIGHT);
        Rectangle rect2 = new Rectangle(myTank.getX(), myTank.getY(), myTank.width, myTank.height);
        if (rect.intersects(rect2)) {
            myTank.die();
            this.die();
        }
    }

    private void die() {
        living = false;
    }
}
