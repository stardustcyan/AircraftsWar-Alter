package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.factory.AbstractItemFactory;
import edu.hitsz.item.AbstractItem;
import edu.hitsz.strategy.Context;
import edu.hitsz.strategy.EnemySprayStrategy;

import java.util.List;

public class BossEnemy extends AbstractAircraft{
    private Context fireObj;

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        bossFlag = true;
        fireObj = new Context(new EnemySprayStrategy(), locationX, locationY, 1, speedY, 30, 7);
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
