package Organisms.Animals;
import Main.World;

import javax.swing.*;
import java.awt.*;

public class Sheep extends Animal {
    private final static ImageIcon icon = new ImageIcon(icons_path+"sheep.png");
    public Sheep(World w, int s, int i){
        super(w,s,i);
        name="Sheep";

    }

    @Override
    public Sheep NewOrganism(){
        return new Sheep(world, 4,4);
    }

    @Override
    public ImageIcon getIcon(){
        return icon;
    }

    private void Escape(Point p) {

        if(LegalPosition(p)){
            this.setPosition(new Point(p.x,p.y));
        }
    }

    private boolean InDanger(){

        // -1 -1
        if(world.OrganismNearby(new Point(this.getPosition().x-1,this.getPosition().y-1), Dog.class) ){
            Escape(new Point(this.getPosition().x+1,this.getPosition().y+1));
            return true;
        }
        //0 -1
        if(world.OrganismNearby(new Point(this.getPosition().x,this.getPosition().y-1), Dog.class) ){
            Escape(new Point(this.getPosition().x,this.getPosition().y+1));
            return true;
        }
        //1 -1
        if(world.OrganismNearby(new Point(this.getPosition().x+1,this.getPosition().y-1), Dog.class) ){
            Escape(new Point(this.getPosition().x-1,this.getPosition().y+1));
            return true;
        }
        //1 0
        if(world.OrganismNearby(new Point(this.getPosition().x+1,this.getPosition().y-1), Dog.class) ){
            Escape(new Point(this.getPosition().x-1,this.getPosition().y+1));
            return true;
        }
        //1 1
        if(world.OrganismNearby(new Point(this.getPosition().x+1,this.getPosition().y+1), Dog.class) ){
            Escape(new Point(this.getPosition().x-1,this.getPosition().y-1));
            return true;
        }
        //0 1
        if(world.OrganismNearby(new Point(this.getPosition().x,this.getPosition().y+1), Dog.class) ){
            Escape(new Point(this.getPosition().x,this.getPosition().y-1));
            return true;
        }
        //-1 1
        if(world.OrganismNearby(new Point(this.getPosition().x-1,this.getPosition().y+1), Dog.class) ){
            Escape(new Point(this.getPosition().x+1,this.getPosition().y-1));
            return true;
        }
        //-1 0
        if(world.OrganismNearby(new Point(this.getPosition().x-1,this.getPosition().y), Dog.class) ){
            Escape(new Point(this.getPosition().x+1,this.getPosition().y));

            return true;
        }
        return false;

    }

    @Override
    public void Action() {
        this.age++;
        this.reproductive_ability++;
        previous_position=new Point(position.x,position.y);
        if(!InDanger())
            Move();
        else
            world.statistics.add(this.getName() + " runs away from Dog");

        world.LookForCollision(this);

    }


}
