package Organisms.Animals;
import Main.World;
import Organisms.Organism;

import javax.swing.*;
import java.util.Random;

public class Snail extends Animal {

    private final static ImageIcon icon = new ImageIcon(icons_path+"snail.png");
    public Snail(World w, int s, int i){
        super(w,s,i);
        name="Snail";
    }

    @Override
    public Snail NewOrganism(){
        return new Snail(world, 1,1);
    }

    @Override
    public ImageIcon getIcon(){
        return icon;
    }

    @Override
    public boolean Reaction(Organism organism){

        if(organism.getStrength()<2){
            world.statistics.add(new String(this.getName()+" is immune to the " + organism.getName()+" attack"));
            return false;
        }
        if(organism.getStrength()>4){
            Random r = new Random();
            int randed=r.nextInt(10);

            if(randed >=0 && randed<=4){
                world.statistics.add(new String(organism.getName()+" kills " + this.getName()));
                return true;

            }else{
                world.statistics.add(new String(organism.getName()+" did not notice " + this.getName()));
                return false;
            }


        }
        return false;


    }

}
