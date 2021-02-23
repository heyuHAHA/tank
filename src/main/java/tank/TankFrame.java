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
    //敌方坦克
    private List<MyTank> tanklist;
    private int speed = 10;
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    public TankFrame() {
        tank1 = new MyTank(50,50,Dir.UP);
        bulletList = new ArrayList<Bullet>(50);
        tanklist = new ArrayList<MyTank>();
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

    }

    public void removeBullet(Bullet bullet) {
        bulletList.remove(bullet);
    }

    public void addTank(MyTank tank) {
        tanklist.add(tank);
    }

    public void removeTank(MyTank tank) {
        tanklist.remove(tank);
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
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量 : " + bulletList.size(),10,60);
        g.drawString("坦克的数量 : " + tanklist.size(),10,80);
        g.setColor(c);
        tank1.paint(g);
       for(int i = 0; i < bulletList.size(); i++) {
           bulletList.get(i).paint(g);
       }

       for(int k = 0; k < tanklist.size(); k++) {
               tanklist.get(k).paint(g);
       }

       //子弹碰撞检
        for(int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j < tanklist.size(); j++) {
                bulletList.get(i).collideWith(tanklist.get(j));
            }
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
