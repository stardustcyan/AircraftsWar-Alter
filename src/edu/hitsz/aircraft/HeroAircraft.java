package edu.hitsz.aircraft;

import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.factory.AbstractItemFactory;
import edu.hitsz.item.AbstractItem;

import java.util.LinkedList;
import java.util.List;

public class HeroAircraft extends AbstractAircraft{
    private volatile static HeroAircraft hero;
    private int direction = -1;
    private int shootNum = 1;
    private int power = 20;

    private HeroAircraft() { }
    public static HeroAircraft getInstance() {
        if(hero == null) {
            synchronized (HeroAircraft.class) {
                if(hero == null)
                    hero = new HeroAircraft();
            }
        }
        return hero;
    }

    public void setStatus(int locationX, int locationY, int speedX, int speedY, int hp) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.hp = hp;
        this.maxHp = hp;
    }

    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction*8;
        BaseBullet baseBullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            baseBullet = new HeroBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
            res.add(baseBullet);
        }
        return res;
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    public AbstractItem dropItem(AbstractItemFactory itemFactory) {
        return null;
    }

    public void increaseHp(int increase) {
        hp += increase;
        if(hp > maxHp)
            hp = maxHp;
    }
}
