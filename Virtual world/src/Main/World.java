package Main;
import Organisms.*;
import Organisms.Animals.*;
import Organisms.Plants.Grass;
import Organisms.Plants.Guarana;
import Organisms.Plants.Thorn;

import java.awt.*;
import java.io.Serializable;
import java.util.*;

public class World implements Serializable {

    private int organisms_amount=0;
    private int indeks =0;
    private final int board_size;
    public Vector<String> statistics;
    private Vector<Organism> organisms;

    public World(int board_size){

        this.board_size=board_size;
        statistics= new Vector<>();
        organisms= new Vector<>();

    }

    public void setWorld(){
        for(Organism o : organisms){
            o.setWorld(this);
        }
    }

    private void RemoveDeadOrganisms(){

        for(int i=0; i<organisms_amount;){
            if(!organisms.get(i).isAlive()){
                organisms.remove(organisms.get(i));
                organisms_amount--;
            }else i++;
        }
    }

    private void Sort(){

        for(int i=0; i<organisms_amount; i++){
            for(int j=i+1; j<organisms_amount-1; j++){
                if(organisms.get(i).getInitiative() < organisms.get(j).getInitiative())
                    Collections.swap(organisms, i,j);
                if(organisms.get(i).getInitiative() == organisms.get(j).getInitiative()){
                    if(organisms.get(i).getAge() < organisms.get(j).getAge())
                        Collections.swap(organisms, i,j);
                }

            }
        }


    }

    public boolean OrganismNearby(Point p,  Class t){

        for(Organism org : organisms){
            if(org.getPosition().equals(p) && org.getClass().equals(t))
                return true;

        }
        return false;
    }

    public void PlayTurn(){
        Sort();
        for(int i=0; i<organisms_amount; i++){
            if(organisms.get(i).isAlive())
                organisms.get(i).Action();
        }
        RemoveDeadOrganisms();
    }

    public void LookForCollision(Organism organism){
        for(int i=0; i<organisms_amount; i++){
            if(organisms.get(i).getIndex()!=organism.getIndex() && organisms.get(i).getPosition().equals(organism.getPosition()))
                organism.Colision(organisms.get(i));
        }
    }

    public boolean AvailablePlace(Point p){
        if( p.x >= board_size || p.x <0 || p.y >= board_size || p.y<0)
            return false;

        for (int i = 0; i < organisms.size(); i++) {
            if( organisms.get(i).getPosition().equals(p) ){
                return false;
            }
        }
        return true;
    }

    private boolean AllocateChildPlace(Organism parent, Organism child){

        //-1 -1
        if(AvailablePlace(new Point(parent.getPosition().x-1, parent.getPosition().y-1))){
            child.setPosition(new Point(parent.getPosition().x-1, parent.getPosition().y-1));
            return true;
        }
        // 0 -1
        if(AvailablePlace(new Point(parent.getPosition().x, parent.getPosition().y-1))){
            child.setPosition(new Point(parent.getPosition().x, parent.getPosition().y-1));
            return true;
        }
        //1 -1
        if(AvailablePlace(new Point(parent.getPosition().x+1, parent.getPosition().y-1))){
            child.setPosition(new Point(parent.getPosition().x+1, parent.getPosition().y-1));
            return true;
        }
        //1 0
        if(AvailablePlace(new Point(parent.getPosition().x+1, parent.getPosition().y))){
            child.setPosition(new Point(parent.getPosition().x+1, parent.getPosition().y));
            return true;
        }
        //1 1
        if(AvailablePlace(new Point(parent.getPosition().x+1, parent.getPosition().y+1))){
            child.setPosition(new Point(parent.getPosition().x+1, parent.getPosition().y+1));
            return true;
        }
        //0 1
        if(AvailablePlace(new Point(parent.getPosition().x, parent.getPosition().y+1))){
            child.setPosition(new Point(parent.getPosition().x, parent.getPosition().y+1));
            return true;
        }
        // -1 1
        if(AvailablePlace(new Point(parent.getPosition().x-1, parent.getPosition().y+1))){
            child.setPosition(new Point(parent.getPosition().x-1, parent.getPosition().y+1));
            return true;
        }
        //-1 0
        if(AvailablePlace(new Point(parent.getPosition().x-1, parent.getPosition().y))){
            child.setPosition(new Point(parent.getPosition().x-1, parent.getPosition().y));
            return true;
        }
        return false;
    }

    public void Reproduce(Organism p1, Organism p2){
        p1.setReproductive_ability(-4);
        p2.setReproductive_ability(-4);
        Organism child = p1.NewOrganism();

        if(AllocateChildPlace(p1,child) || AllocateChildPlace(p2, child)){
            statistics.add(new String(p1.getName()+" is born "));
            child.setReproductive_ability(-5);
            AddNewOrganism(child, true);
        }

    }

    public void Spread(Organism parent){
        Organism child = parent.NewOrganism();
        parent.setReproductive_ability(-3);

        if(AllocateChildPlace(parent,child)){
            statistics.add(new String(parent.getName()+" is spreading "));
            child.setReproductive_ability(-4);
            AddNewOrganism(child, true);
        }

    }

    public void SetNewWorld(){
        //10 -  2 / 4 /4
        //20 -  4 /5 /5
        //30 -  6/7/7
        statistics= new Vector<>();
        organisms = new Vector<>();
        organisms_amount=0;
        indeks=0;
        //wolf
        for(int i=0; i<board_size/5; i++){
            AddNewOrganism(new Wolf(this,9,5),false);
        }
        //sheep
        for(int i=0; i<board_size/5; i++){
            AddNewOrganism(new Sheep(this,4,4),false);
        }
        //sloth
        for(int i=0; i<board_size/5; i++){
            this.AddNewOrganism(new Sloth(this,2,1),false);
        }
        //snail
        for(int i=0; i<board_size/5; i++){
            this.AddNewOrganism(new Snail(this,1,1),false);
        }
        //dog
        for(int i=0; i<board_size/5; i++){
            this.AddNewOrganism(new Dog(this,7,6),false);
        }
        //grass
        for(int i=0; i<board_size/3; i++){
            this.AddNewOrganism(new Grass(this,0,0),false);
        }
        //guarana
        for(int i=0; i<board_size/3; i++){
            this.AddNewOrganism(new Guarana(this,0,0),false);
        }
        //thorn
        for(int i=0; i<board_size/5; i++){
            this.AddNewOrganism(new Thorn(this,2,0),false);
        }

    }

    private void AllocateNewPlace (Organism organism){

        Random r = new Random();
        Point point=new Point();
        boolean alocated=false;
        while(!alocated) {

            alocated=true;
            point=new Point(r.nextInt(board_size),r.nextInt(board_size));
            if(!AvailablePlace(point))
                alocated=false;
        }
        organism.setPosition(point);


    }

    public void AddNewOrganism(Organism organism, boolean isChild){

        organisms_amount++;
        organism.setIndex(indeks);
        organisms.add(organism);
        indeks++;
        if(!isChild) AllocateNewPlace(organism);

    }

    public int getBoardSize(){
        return this.board_size;
    }
    public Organism getOrganism(int i){
        return organisms.get(i);
    }

    public int getAmount_of_organisms(){
        return organisms.size();
    }



}
