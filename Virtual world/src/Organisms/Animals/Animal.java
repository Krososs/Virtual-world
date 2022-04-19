package Organisms.Animals;

import Main.World;
import Organisms.Organism;
import Organisms.Plants.Plant;

import java.awt.*;
import java.util.Random;

public abstract class Animal extends Organism {

    public Animal(World w, int s, int i){
        super(w, s, i);
        alive=true;
        age=0;
        position = new Point(-1,-1);

    }


    public boolean LegalPosition(Point p){
        return p.x < world.getBoardSize() && p.x >= 0 && p.y < world.getBoardSize() && p.y >= 0;

    }

    protected void Move(){
        Random r = new Random();
        int rand;
        boolean moved=false;

        while(!moved){
            rand=r.nextInt(8);

            switch (rand){
                case 0:
                {
                    if(LegalPosition(new Point(this.getPosition().x-1, this.getPosition().y-1))){
                        this.setPosition(new Point(this.getPosition().x-1, this.getPosition().y-1));

                        moved=true;
                    }
                    break;
                }
                case 1:
                {
                    if(LegalPosition(new Point(this.getPosition().x, this.getPosition().y-1))){
                        this.setPosition(new Point(this.getPosition().x, this.getPosition().y-1));
                        moved=true;
                    }
                    break;
                }
                case 2:
                {
                    if(LegalPosition(new Point(this.getPosition().x+1, this.getPosition().y-1))){
                        this.setPosition(new Point(this.getPosition().x+1, this.getPosition().y-1));
                        moved=true;
                    }
                    break;
                }
                case 3:
                {
                    if(LegalPosition(new Point(this.getPosition().x+1, this.getPosition().y))){
                        this.setPosition(new Point(this.getPosition().x+1, this.getPosition().y));
                        moved=true;
                    }
                    break;
                }
                case 4:
                {
                    if(LegalPosition(new Point(this.getPosition().x+1, this.getPosition().y+1))){
                        this.setPosition(new Point(this.getPosition().x+1, this.getPosition().y+1));
                        moved=true;
                    }
                    break;
                }
                case 5:
                {
                    if(LegalPosition(new Point(this.getPosition().x, this.getPosition().y+1))){
                        this.setPosition(new Point(this.getPosition().x, this.getPosition().y+1));
                        moved=true;
                    }
                    break;
                }
                case 6:
                {
                    if(LegalPosition(new Point(this.getPosition().x-1, this.getPosition().y+1))){
                        this.setPosition(new Point(this.getPosition().x-1, this.getPosition().y+1));
                        moved=true;
                    }
                    break;
                }
                case 7:
                {
                    if(LegalPosition(new Point(this.getPosition().x-1, this.getPosition().y))){
                        this.setPosition(new Point(this.getPosition().x-1, this.getPosition().y));
                        moved=true;
                    }
                    break;
                }
            }
        }
//        System.out.println("New position");
//        System.out.println("X: " + this.getPosition().x+" Y: "+this.getPosition().y);


    }

    private boolean CanReproduce(Organism organism){
        return age > 5 && reproductive_ability >= 5 && organism.getAge() > 5 && organism.getReproductive_ability() >= 5;
    }
    public void GoToAnotherPlace(){

        //-1 -1
        if(world.AvailablePlace(new Point(this.position.x-1, this.position.y-1))){
            this.setPosition(new Point(this.position.x-1, this.position.y-1));
        }
        //0 -1
        if(world.AvailablePlace(new Point(this.position.x, this.position.y-1))){
            this.setPosition(new Point(this.position.x, this.position.y-1));
        }
        //1 -1
        if(world.AvailablePlace(new Point(this.position.x+1, this.position.y-1))){
            this.setPosition(new Point(this.position.x+1, this.position.y-1));
        }
        //1 0
        if(world.AvailablePlace(new Point(this.position.x+1, this.position.y))){
            this.setPosition(new Point(this.position.x+1, this.position.y));
        }
        //1 1
        if(world.AvailablePlace(new Point(this.position.x+1, this.position.y+1))){
            this.setPosition(new Point(this.position.x+1, this.position.y+1));
        }
        //0 1
        if(world.AvailablePlace(new Point(this.position.x, this.position.y+1))){
            this.setPosition(new Point(this.position.x, this.position.y+1));
        }
        //-1 1
        if(world.AvailablePlace(new Point(this.position.x-1, this.position.y+1))){
            this.setPosition(new Point(this.position.x-1, this.position.y+1));
        }
        //-1 1
        if(world.AvailablePlace(new Point(this.position.x-1, this.position.y+1))){
            this.setPosition(new Point(this.position.x-1, this.position.y+1));
        }
        else
            this.setPosition(new Point(this.previous_position.x,this.previous_position.y));

    }

    @Override
    public void Colision(Organism organism) {

        if(this.getClass().equals(organism.getClass())){

            if(this.CanReproduce(organism)) {
                this.setPosition(previous_position);
                world.Reproduce(this, organism);
            } else GoToAnotherPlace();

        }else{

            if(this.strength>=organism.getStrength() || organism instanceof Plant){

                if(organism.Reaction(this)){
                    organism.setLive(false);

                }else GoToAnotherPlace();

            }else GoToAnotherPlace();

        }

    }

    @Override
    public void Action() {
        this.age++;
        this.reproductive_ability++;
        previous_position=new Point(position.x,position.y);
        Move();
        world.LookForCollision(this);



    }

    @Override
    public boolean Reaction(Organism organism) {
        world.statistics.add(organism.getName() + " kills " + this.getName());
        return true;

    }

    @Override
    public Organism NewOrganism() {
        return null;
    }


}
