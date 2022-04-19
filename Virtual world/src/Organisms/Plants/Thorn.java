package Organisms.Plants;

import Main.World;

import javax.swing.*;

public class Thorn extends Plant {
    private final static ImageIcon icon = new ImageIcon(icons_path+"thorn.png");

    public Thorn(World w, int s, int i) {
        super(w, s, i);
        name="Thorn";
    }
    @Override
    public Thorn NewOrganism(){
        return new Thorn(world, 2,0);
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }
}
