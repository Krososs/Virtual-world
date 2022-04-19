package Organisms.Plants;

import Main.World;
import Organisms.Organism;

import javax.swing.*;

public class Guarana extends Plant {
    private final static ImageIcon icon = new ImageIcon(icons_path+"guarana.png");
    public Guarana(World w, int s, int i) {
        super(w, s, i);
        name="Guarana";
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public Guarana NewOrganism(){
        return new Guarana(world, 0,0);
    }

    @Override
    public boolean Reaction(Organism organism) {
        world.statistics.add(organism.getName() + " eats " + this.getName());
        world.statistics.add("and increases his strength by 3");
        organism.setStrength(organism.getStrength()+3);
        return true;

    }
}
