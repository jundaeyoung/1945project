package project;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable {

   PlayerWay playerWay;
   AirplaneFrame mContext;

   private int x;
   private int y;
   private int bulletX;
   private int bulletY;
   private int status;
   private ImageIcon player;
   private ImageIcon bullet;
   private ImageIcon gost;
   private int life = 3;

   // 플레이어의 현재 움직임 상태 기록
   private boolean left;
   private boolean right;
   private boolean up;
   private boolean down;
   private boolean attack;

   // 벽에 충돌한 상태
   private boolean leftWallCrash;
   private boolean rightWallCrash;
   private boolean upWallCrash;
   private boolean downWallCrash;

   // 플레이어의 속도
   // 상수 선언 X 아이템을 먹으면 속도가 빨라져야 하기 때문에 값이 바뀔 수 있음
	private int speed = 4;

   public int getLife() {
      return life;
   }

   public void setLife(int life) {
      life = life;
   }

   public int getStatus() {
      return status;
   }

   public void setStatus(int status) {
      this.status = status;
   }

   public AirplaneFrame getmContext() {
      return mContext;
   }

   public void setmContext(AirplaneFrame mContext) {
      this.mContext = mContext;
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

   public ImageIcon getPlayer() {
      return player;
   }

   public void setPlayer(ImageIcon player) {
      this.player = player;
   }

   public ImageIcon getBullet() {
      return bullet;
   }

   public void setBullet(ImageIcon bullet) {
      this.bullet = bullet;
   }

   public boolean isLeft() {
      return left;
   }

   public void setLeft(boolean left) {
      this.left = left;
   }

   public boolean isRight() {
      return right;
   }

   public void setRight(boolean right) {
      this.right = right;
   }

   public boolean isUp() {
      return up;
   }

   public void setUp(boolean up) {
      this.up = up;
   }

   public boolean isDown() {
      return down;
   }

   public void setDown(boolean down) {
      this.down = down;
   }

   public boolean isLeftWallCrash() {
      return leftWallCrash;
   }

   public void setLeftWallCrash(boolean leftWallCrash) {
      this.leftWallCrash = leftWallCrash;
   }

   public boolean isRightWallCrash() {
      return rightWallCrash;
   }

   public void setRightWallCrash(boolean rightWallCrash) {
      this.rightWallCrash = rightWallCrash;
   }

   public boolean isUpWallCrash() {
      return upWallCrash;
   }

   public void setUpWallCrash(boolean upWallCrash) {
      this.upWallCrash = upWallCrash;
   }

   public boolean isDownWallCrash() {
      return downWallCrash;
   }

   public void setDownWallCrash(boolean downWallCrash) {
      this.downWallCrash = downWallCrash;
   }

   public int getSpeed() {
      return speed;
   }

   public Player(AirplaneFrame mContext) {
      this.mContext = mContext;

      initData();
      setInitLayout();
   }

   public void initData() {
      player = new ImageIcon("imagesProject/BigPlane2.png");
      gost = new ImageIcon("imagesProject/PLANE2무적.png");
      playerWay = PlayerWay.RIGHT;
   }

   public void setInitLayout() {
      x = 470;
      y = 805;
      setSize(70, 70);
      setLocation(x, y);
      setIcon(player);
   }

   @Override
   public void left() {
      left = true;
      playerWay  = PlayerWay.LEFT;
      new Thread(new Runnable() {
         @Override
         public void run() {
            while (left) {
               x -= speed;
               setLocation(x, y);

               try {
                  Thread.sleep(15);
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
            }
         }
      }).start();
   }

   @Override
   public void right() {
      playerWay  = PlayerWay.RIGHT;
      right = true;

      new Thread(new Runnable() {
         @Override
         public void run() {
            while (right) {
               x += speed;
               setLocation(x, y);

               try {
                  Thread.sleep(15);
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
            }
         }
      }).start();
   }

   @Override
   public void up() {
      playerWay  = PlayerWay.UP;
      up = true;

      new Thread(new Runnable() {
         @Override
         public void run() {
            while (up) {
               y -= speed;
               setLocation(x, y);

               try {
                  Thread.sleep(15);
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
            }
         }
      }).start();

   }

   @Override
   public void down() {
      playerWay  = PlayerWay.DOWN;
      down = true;

      new Thread(new Runnable() {
         @Override
         public void run() {
            while (down) {
               y += speed;
               setLocation(x, y);

               try {
                  Thread.sleep(15);
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
            }
         }
      }).start();
   }

   public void space() {
      new Thread(new Runnable() {
         @Override
         public void run() {
            while (attack) {
               System.out.println("space");
               setIcon(bullet);
               bulletX += 10;
               setLocation(bulletX, bulletY);
               try {
                  Thread.sleep(3);
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
            }
         }
      }).start();
   }

   public void beAttack() {
      life = life-1;
      System.out.println("추락합니다.");
      if (life == 2) {
         mContext.getLife2().setIcon(null);
         setIcon(gost);
   
      } else if (life == 1) {
         mContext.getLife1().setIcon(null);
         System.out.println("2번째 추락");
         setIcon(gost);
      } else {
         mContext.getLife0().setIcon(null);
         System.out.println("3번째 추락");
         setIcon(gost);
      }
   }

   public void attack() {
      Bullet bullet = new Bullet(mContext);
      mContext.add(bullet);
   }
}