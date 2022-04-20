package edu.hitsz.item;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.strategy.HeroSprayStrategy;
import edu.hitsz.strategy.HeroStraightStrategy;

public class FireSupplyItem extends AbstractItem {
    public FireSupplyItem(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void itemFunction() {
        HeroAircraft hero = HeroAircraft.getInstance();
        hero.fireObj.setStrategy(new HeroSprayStrategy());
        System.out.println("FireSupply active!");
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }
}
