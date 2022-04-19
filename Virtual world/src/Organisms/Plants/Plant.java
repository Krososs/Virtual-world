package Organisms.Plants;
import Main.World;
import Organisms.Organism;

import java.awt.*;
import java.util.Random;

public abstract class Plant extends Organism {

    public Plant(World w, int s, int i) {
        super(w, s, i);
        alive = true;
        age=0;
        position = new Point(-1,-1);

    }
    @Override
    public void Colision(Organism organism) {

    }
    @Override
    public void Action() {
        this.age++;
        this.reproductive_ability++;
        Random r = new Random();
        int randed=r.nextInt(450);
        if(randed<=30 && reproductive_ability>=6) {
            world.Spread(this);
        }
    }
    @Override
    public boolean Reaction(Organism organism) {
        world.statistics.add(organism.getName() + " eats " + this.getName());
        return true;
    }
    @Override
    public Organism NewOrganism() {
        return null;
    }

}
