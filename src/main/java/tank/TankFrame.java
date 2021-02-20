package tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    private MyTank tank1;
    private Bullet bullet;
    private int speed = 10;
    public TankFrame() {
        tank1 = new MyTank(50,50);
        bullet = new Bullet(50,50,Dir.DOWN);
        setSize(800,600);
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
    }

    @Override
    public void paint(Graphics g) {
        tank1.paint(g);
        bullet.paint(g);

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
