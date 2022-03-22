package edu.hitsz.aircraft;

import edu.hitsz.application.Game;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.item.AbstractItem;

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
    public List<AbstractBullet> shoot() {
        List<AbstractBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction * 2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction * 8;
        AbstractBullet abstractBullet;

        for(int i = 0; i < shootNum; i++) {
            abstractBullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
            res.add(abstractBullet);
        }

        return res;
    }

    public boolean isElite() {
        return true;
    }
}
