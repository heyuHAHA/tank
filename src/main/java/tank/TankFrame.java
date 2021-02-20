package tank;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    private MyTank tank1;
    private List<Bullet> bulletList;
    private int speed = 10;
    private final int GAME_WIDTH = 800;
    private final int GAME_HEIGHT = 600;
    public TankFrame() {
        tank1 = new MyTank(50,50);
        bulletList = new ArrayList<Bullet>(50);
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        addKeyListener(new MyKeyListener());

        addWindowStateListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        tank1.setTankFrame(this);
    }

    public void addBullet(Bullet bullet) {
        bulletList.add(bullet);
        if(bulletList.size() > 40) {
            bulletList.clear();
        }
    }

    //用双缓冲解决闪烁问题
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics graphicsImage = offScreenImage.getGraphics();
        Color c = graphicsImage.getColor();
        graphicsImage.setColor(Color.BLACK);
        graphicsImage.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        graphicsImage.setColor(c);
        paint(graphicsImage);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        tank1.paint(g);
        for(Bullet bullet : bulletList) {
            bullet.paint(g);
        }

    }

    private class MyKeyListener extends KeyAdapter {
        boolean bl = false;
        boolean br = false;
        boolean bt = false;
        boolean bb = false;
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bl = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = true;break;
                case KeyEvent.VK_UP:
                    bt = true;
                break;
                case KeyEvent.VK_DOWN:
                    bb = true;
                    break;
                default:
                    break;

            }

            setTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bl = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = false;
                    break;
                case KeyEvent.VK_UP:
                    bt = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bb = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    tank1.fire();
                    break;
                default:
                    break;
            }
            setTankDir();
        }

        private void setTankDir() {
            if(!bl && !br && !bt && !bb) {
                tank1.setMoving(false);
            } else {
                tank1.setMoving(true);
                if (bl) tank1.setTankDir(Dir.LEFT);
                if (br) tank1.setTankDir(Dir.RIGHT);
                if (bt) tank1.setTankDir(Dir.UP);
                if (bb) tank1.setTankDir(Dir.DOWN);
            }

        }


    }
}
