package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.factory.AbstractItemFactory;
import edu.hitsz.item.AbstractItem;
import edu.hitsz.strategy.Context;
import edu.hitsz.strategy.EnemyStraightStrategy;

import java.util.List;

public class EliteEnemy extends AbstractAircraft {
    public Context fireObj;

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        bossFlag = false;
        fireObj = new Context(new EnemyStraightStrategy(), locationX, locationY, 1, speedY, 20, 2);
    }

    @Override
    public void forward() {
        super.forward();

        if(locationY >= Main.WINDOW_HEIGHT) {
            vanish();
        }
    }

    @Override
    public List<BaseBullet> shoot() {
        fireObj.updateStatus(getLocationX(), getLocationY(), getSpeedY());
        return fireObj.executeStrategy();
    }

    @Override
    public AbstractItem dropItem(AbstractItemFactory itemFactory) {
        double dirProb = Math.random();

        return itemFactory.createItem(locationX, locationY, dirProb >= 0.5 ? -2 : 2, 5);
    }
}
