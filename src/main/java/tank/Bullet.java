package tank;

import java.awt.*;

public class Bullet {
    private static final int speed = 30;
    private int x, y;
    private Dir dir;
    private final int WIDTH = 10, HEIGHT = 10;
    private boolean live = true;
    private TankFrame frame;

    public Bullet(int x, int y, Dir dir,TankFrame frame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.frame = frame;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);
        move();
        if( !live ) {
            frame.removeBullet(this);
        }
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
            live = false;
    }
}
