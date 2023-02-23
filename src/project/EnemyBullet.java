package project;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class EnemyBullet extends JLabel implements Moveable {

   private int x;
   private int y;

   private boolean down;

   private int state;
   private int attackCount;

   private ImageIcon enemyBullet;
   private ImageIcon boom;
   private ImageIcon player;

   private AirplaneFrame mContext;
   private BackgroundEnemyBulletService backgroundenemyBulletService;

   public EnemyBullet(AirplaneFrame mContext) {
      this.mContext = mContext;
      initData();
      setInitLayout();
      backgroundenemyBulletService = new BackgroundEnemyBulletService(this);
      initThread();
   }

   public void initData() {
      enemyBullet = new ImageIcon("imagesProject/bullet4.png");
      boom = new ImageIcon("imagesProject/explosion.gif");
      player = new ImageIcon("imagesProject/BigPlane2.png");
      state = 0;
      // 1로 계속 초기화
      attackCount = 1;
      mContext.getPlayer().setIcon(player);

   }

   public void setInitLayout() {
      x = mContext.getEnemy().getX() + 30;
      y = mContext.getEnemy().getY() + 50;
      setIcon(enemyBullet);
      setSize(100, 100);
      setLocation(x, y);
   }

   public void initThread() {
      new Thread(new Runnable() {
         public void run() {
            down();
         }
      }).start();
   }

   public void crash() {
//      Player player = new Player(mContext);

      if (attackCount == 1) {
         attackCount--;
         mContext.getPlayer().beAttack();
         mContext.repaint();

         if (mContext.getPlayer().getLife() == 0) {
            setIcon(boom);
            state = 1;
            mContext.remove(mContext.getPlayer());
            mContext.repaint();
         }
      }

   }

   @Override
   public void left() {

   }

   @Override
   public void right() {

   }

   @Override
   public void up() {

   }

   @Override
   public void down() {
      down = true;
      while (true) {
         y++;
         setLocation(x, y);
         if (Math.abs(x - mContext.getPlayer().getX()) < 10 && Math.abs(y - mContext.getPlayer().getY()) < 50) {
            if (mContext.getPlayer().getAlive() == 0) {
               crash();
            }
         }
         try {
            Thread.sleep(3);
         } catch (InterruptedException e) {
            e.printStackTrace();

         }

      }
   }

}