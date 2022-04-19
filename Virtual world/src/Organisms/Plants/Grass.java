package Organisms.Plants;

import Main.World;

import javax.swing.*;

public class Grass extends Plant {
    private final static ImageIcon icon = new ImageIcon(icons_path+"grass.png");
    public Grass(World w, int s, int i) {
        super(w, s, i);
        name="Grass";
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public Grass NewOrganism(){
        return new Grass(world, 0,0);
    }
}
