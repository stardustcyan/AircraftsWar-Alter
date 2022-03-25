package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;

public abstract class AbstractFactory {
    public abstract AbstractAircraft createEnemy(int locationX, int locationY, int speedX, int speedY, int hp);
}
