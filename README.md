# ๐์๋ํ์ธ์. 1945๋ฅผ ๋ง๋  E์กฐ ์๋๋ค.
<br>
๐ฅ๏ธ ํ๋ก์ ํธ ์๊ฐ
์๋ฐ ์ค์์ ์ด์ฉํ์ฌ ์ค์  STRIKER 1945 ๊ฒ์์ ๋ชจํฐ๋ธ๋ก ๊ตฌํํจ. 

ํ๋ ์ด์ด๊ฐ ์ด์์ ๋ฐ์ฌํด์ ์ ๊ตฐ์ ๋ฌผ๋ฆฌ์น๋ ๊ฒ์์ผ๋ก 
1๋ถ ๋์ ์ด์๋จ์ผ๋ฉด GAMECLEAR, ๋ชฉ์จ 3๊ฐ๋ฅผ ์์ผ๋ฉด GAMEOVER๊ฐ ๋๋๋ก ์ค์ ํจ. 

<br>

## ๐ฐ๏ธ ๊ฐ๋ฐ ๊ธฐ๊ฐ
* 23.02.23~23.02.27

### ๐งโ๐คโ๐ง ๋งด๋ฒ๊ตฌ์ฑ
 - ํ์1 : ๊ฐ๋ฏผ์  
1. Item ํด๋์ค ์๋ ์์ดํ, ๋ชฉ์จ ์์ดํ ๊ตฌํ
2. Item๊ณผ Player๊ฐ ๋ฟ์์ ์ ์ ๋๊ฐ ์กฐ์ ํ์ฌ ์๋์ ๋ชฉ์จ ๊ตฌํ
3. Bullet์ ์ ๊ทผํ์ฌ Enemy๊ฐ ์ฃฝ์์ ๋ ๋๋ค๊ฐ์ ๋ฐ๋ผ ์์ดํ์ด ๋์ค๋๋ก ๊ตฌํ
4. Player์ ๋ชฉ์จ์ด 0์ด ๋๋ฉด ๊ฒ์ ์ค๋ฒ ๊ตฌํ
5. Timer ํด๋์ค๋ฅผ ์ด์ฉํ์ฌ ์ ํ ์๊ฐ 1๋ถ๋์ Player๊ฐ ์ด์๋จ์ผ๋ฉด ๊ฒ์ ํด๋ฆฌ์ด ๊ตฌํ


 - ํ์2 : ์ด์์
1. Enemy ํด๋์ค ์ ๋๋ณ ์๋ช๋ ฅ, ๊ณต๊ฒฉ์๋, ์ด๋์๋, ์ด๋ํจํด ๊ตฌํ
2. Enemy ํด๋์ค ์ ๋๋ณ ํผ๊ฒฉ ๋ฒ์, ์ด์ ๋ฐ์ฌ ์์น ์กฐ์ 
3. Timer ํด๋์ค ์ ํ ์๊ฐ ๊ตฌํ
4. ์์ฑ๋ Enemyํด๋์ค AirlineFrame์ ArrayList๋ก ๊ด๋ฆฌ
5. ArrayList ์ฌ์ฉ ๋ฐฉ์์ ํธํ๋๋๋ก ์ ์ฒด์ ์ธ ์ฝ๋ ์์ 
6. Timer ํด๋์ค๋ฅผ ํ์ฉํ EnemyUnit ํด๋์ค๋ค ์ํ ํ๋ฆ ๊ตฌํ

 - ํ์3 : ์ด์ง์ด
1. Enemy1, 2 ํด๋์ค ๋ฆฌ์คํธ๋ก ๊ตฌํํ์ฌ ์์ฑ 
2.Enemy1, 2 ํด๋์ค ์์ง์ ๊ตฌํ
3. BackgroundEnemyService ๊ตฌํ
4. EnemyBullet calss ๊ตฌํ
5. BackgroundPlayer ์ค๋ฅ ์์ 

 - ํ์4 : ์ ๋์ 
1. mainframe ์ค๊ณ
2. Player ๋ฐฉํฅ ์ ํ, ๊ณต๊ฒฉ ๊ธฐ๋ฅ, ์๋ ์ค๊ณ
3. BackgroundPlayer ๋ฒฝ ์ถฉ๋ ๊ฐ์ง ๊ธฐ๋ฅ ์ถ๊ฐ
4. Bullet ํด๋์ค player๊ฐ space๋ฅผ ๋๋ฅด๋ฉด ๊ณต๊ฒฉํ๋๋ก ์ค๊ณ 
5. EnemyBullet calss ๊ตฌํ
6. ๋ฐฐ๊ฒฝ ํ๋ฌ๊ฐ๋๋ก ๊ตฌํ
7. ์๋ช๋ ฅ ๊ธฐ๋ฅ ์ถ๊ฐ player๊ฐ ๊ณต๊ฒฉ๋นํ๋ฉด ํํธ ์ฌ๋ผ์ง๋๋ก ์ค๊ณ
8. BGM ์ถ๊ฐ
9. ์ค์ฝ์ด ๊ธฐ๋ฅ ์ถ๊ฐ, ์  ์ ๋ ์ข๋ฅ๋ง๋ค ์ค์ฝ์ด ๋ค๋ฅด๊ฒ ์ค๊ณ
10. ์์ดํ ์ค๋ฅ ์์ 


### โ๏ธ ๊ฐ๋ฐ ํ๊ฒฝ
- `Java`
- `JDK 11.0`
- **IDE** : Eclipse


## ๐ ์ฃผ์ ๊ธฐ๋ฅ
#### AirplaneFrame.java - <a href="https://github.com/jundaeyoung/1945project/wiki/AirplaneFrame.java" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>
#### BackgroundBulletService .java - <a href="https://github.com/jundaeyoung/1945project/wiki/BackgroundBulletService-.java" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>
#### BackgroundEnemyBulletService.java - <a href="https://github.com/jundaeyoung/1945project/wiki/BackgroundEnemyBulletService.java" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>
#### BackgroundEnemyService.java - <a href="https://github.com/jundaeyoung/1945project/wiki/BackgroundEnemyService.java" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>
#### BackgroundPlayerService .java - <a href="https://github.com/jundaeyoung/1945project/wiki/BackgroundPlayerService-.java" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>
#### Bgm.java - <a href="https://github.com/jundaeyoung/1945project/wiki/Bgm.java" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>
#### Bullet.java - <a href="https://github.com/jundaeyoung/1945project/wiki/Bullet.java" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>
#### Enemy.java - <a href="https://github.com/jundaeyoung/1945project/wiki/Enemy.java" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>
#### EnemyBullet.java - <a href="https://github.com/jundaeyoung/1945project/wiki/EnemyBullet.java" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>
#### EnemyUnit1.java - <a href="https://github.com/jundaeyoung/1945project/wiki/EnemyUnit1.java" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>
#### EnemyUnit2.java - <a href="https://github.com/jundaeyoung/1945project/wiki/EnemyUnit2.java" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>
#### EnemyUnit3.java - <a href="https://github.com/jundaeyoung/1945project/wiki/EnemyUnit3.java" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>
#### EnemyUnit4 - <a href="https://github.com/jundaeyoung/1945project/wiki/EnemyUnit4-.java" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>
#### Item.java - <a href="https://github.com/jundaeyoung/1945project/wiki/Item.java" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>

#### ItemWay.java - <a href="https://github.com/jundaeyoung/1945project/wiki/ItemWay.java" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>
#### Life .java - <a href="https://github.com/jundaeyoung/1945project/wiki/Life-.java" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>
#### Moveable.java - <a href="https://github.com/jundaeyoung/1945project/wiki/Moveable.java" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>
#### Player.java - <a href="https://github.com/jundaeyoung/1945project/wiki/Player.java" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>
#### PlayerWay.java - <a href="https://github.com/jundaeyoung/1945project/wiki/PlayerWay.java" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>
#### Score .java - <a href="https://github.com/jundaeyoung/1945project/wiki/Score-.java" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>

๐ท ์ฌ์ฉ๋์ด๋ฏธ์ง  - <a href="https://github.com/jundaeyoung/1945project/issues/1" >์์ธ๋ณด๊ธฐ - WIKI ์ด๋</a>

