package edu.hitsz.item;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicThread;
import edu.hitsz.basic.EnemyInstance;
import edu.hitsz.bullet.BaseBullet;

import java.util.ArrayList;
import java.util.List;

public class BombSupplyItem extends AbstractItem {
    List<EnemyInstance> enemyList = new ArrayList<>();

    public void addEnemyInstance(EnemyInstance e) {
        enemyList.add(e);
    }

    public void removeEnemyInstance(EnemyInstance e) {
        enemyList.remove(e);
    }

    public void updateAll() {
        for(AbstractAircraft enemy: Game.enemyAircrafts) {
            if(!enemy.bossFlag) {
                addEnemyInstance(enemy.trans());
                if(enemy.eliteFlag) {
                    Game.score += 20;
                    Game.bossScore += 20;
                } else {
                    Game.score += 10;
                    Game.score += 10;
                }
            }
        }
        for(BaseBullet bullet: Game.enemyBullets) {
            addEnemyInstance(bullet.trans());
        }
        for(EnemyInstance enemy: enemyList) {
            enemy.update();
        }
    }
    public BombSupplyItem(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void itemFunction() {
        updateAll();
        System.out.println("BombSupply active!");
        if(Main.bgmFlag) {
            MusicThread tBomb = new MusicThread("src/videos/bomb_explosion.wav");
            tBomb.start();
        }
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT || locationY <= 0) {
            speedY = -speedY;
        }
    }
}
