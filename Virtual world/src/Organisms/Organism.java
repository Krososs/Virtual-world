package Organisms;

import Main.World;
import javax.swing.*;
import java.io.Serializable;
import java.awt.*;

public abstract class Organism implements Serializable {

    protected static World world;
    protected static final String icons_path ="src/icons/";
    protected int strength;
    protected int initiative;
    protected int reproductive_ability;
    protected Point position;
    protected Point previous_position;
    protected boolean alive;
    protected int age;
    protected int index;
    protected  String name;

    protected Organism(World w, int s, int i){
        world=w;
        strength=s;
        initiative=i;
        reproductive_ability=0;
    }

    public abstract void Colision(Organism organism);
    public abstract void Action();
    public abstract boolean Reaction(Organism organism);
    public abstract Organism NewOrganism();
    public abstract ImageIcon getIcon();
    public void setWorld(World w){
           world=w;
    }
    public int getStrength(){
        return strength;
    }
    public  void setStrength(int s){
        strength=s;
    }
    public int getInitiative(){
        return initiative;
    }
    public int getAge(){
        return age;
    }
    public  String getName(){
        return name;
    }
    public boolean isAlive(){
        return alive;
    }
    public void setLive(boolean x){
        alive=x;
    }
    public Point getPosition(){
        return position;
    }
    public void setPosition(Point p){
        position=p;
    }
    public int getReproductive_ability(){
        return reproductive_ability;
    }
    public void setReproductive_ability(int x){
        reproductive_ability=x;
    }
    public int getIndex(){
        return index;
    }
    public void setIndex(int x){
        index=x;
    }




}
