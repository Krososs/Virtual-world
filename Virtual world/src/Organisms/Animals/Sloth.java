package Organisms.Animals;

import Main.World;

import javax.swing.*;
import java.awt.*;


public class Sloth extends Animal {

    private final static ImageIcon icon = new ImageIcon(icons_path+"sloth.png");
    private boolean laziness = false;
    public Sloth(World w, int s, int i){
        super(w,s,i);
        name="Sloth";
    }

    @Override
    public Sloth NewOrganism(){
        return new Sloth(world, 2,1);
    }

    @Override
    public ImageIcon getIcon(){
        return icon;
    }

    @Override
    public void Action(){
        this.age++;
        this.reproductive_ability++;
        if(!laziness) {
            previous_position = new Point(position.x, position.y);
            Move();
            world.LookForCollision(this);
        }
        laziness=!laziness;

    }


}
