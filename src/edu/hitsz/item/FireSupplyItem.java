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
        Runnable rFunc = () -> {
            hero.fireObj.setStrategy(new HeroSprayStrategy());
            System.out.println("FireSupply active!");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hero.fireObj.setShootNum(1);
            hero.fireObj.setStrategy(new HeroStraightStrategy());
        };

        Thread tSpray = new Thread(rFunc, "SprayPeriod");
        tSpray.start();
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
