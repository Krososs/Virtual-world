package Organisms.Animals;
import Main.World;

import javax.swing.*;

public class Dog extends Animal {
    private final static ImageIcon icon = new ImageIcon(icons_path+"dog.png");

    public Dog(World w, int s, int i){
        super(w,s,i);
        name="Dog";
    }

    @Override
    public Dog NewOrganism(){
        return new Dog(world, 7,6);
    }

    @Override
    public ImageIcon getIcon(){
        return icon;
    }

}
