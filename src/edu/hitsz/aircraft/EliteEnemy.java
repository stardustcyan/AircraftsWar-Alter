package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.item.AbstractItem;
import edu.hitsz.item.BombSupplyItem;
import edu.hitsz.item.FireSupplyItem;
import edu.hitsz.item.HealingItem;

import java.util.LinkedList;
import java.util.List;

public class EliteEnemy extends AbstractAircraft {
    private int direction = 1;
    private int shootNum = 2;
    private int power = 20;

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        super.forward();

        if(locationY >= Main.WINDOW_HEIGHT)
            vanish();
    }

    @Override
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction * 2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction * 8;
        BaseBullet baseBullet;

        for(int i = 0; i < shootNum; i++) {
            baseBullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
            res.add(baseBullet);
        }

        return res;
    }

    public AbstractItem dropItem() {
        double prob = Math.random();
        double dirProb = Math.random();

        if (prob < 0.25) {
            return new HealingItem(locationX, locationY, dirProb >= 0.5 ? -2 : 2, 5);
        } else if (prob >= 0.25 && prob <= 0.5) {
            return new FireSupplyItem(locationX, locationY, dirProb >= 0.5 ? -3 : 3, 5);
        } else if (prob > 0.5 && prob <= 0.75) {
            return new BombSupplyItem(locationX, locationY, dirProb >= 0.5 ? -3 : 3, 5);
        } else {
            return null;
        }
    }
}
