package Organisms.Animals;

import Main.World;

import javax.swing.*;

public class Wolf extends Animal {
    private final static ImageIcon icon = new ImageIcon(icons_path+"wolf.png");
    public Wolf(World w, int s, int i){
        super(w,s,i);
        name="Wolf";

    }

    @Override
    public Wolf NewOrganism(){
        return new Wolf(world, 9,5);
    }

    @Override
    public ImageIcon getIcon(){
        return icon;
    }
}
