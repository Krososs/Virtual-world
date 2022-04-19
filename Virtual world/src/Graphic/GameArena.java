package Graphic;

import Main.World;
import javax.swing.*;
import java.awt.*;


public class GameArena extends JPanel {

    private final int field_size;
    private final int board_size;
    private World world;

    public GameArena(int board_size, World w){
        world=w;
        field_size = 600/board_size;
        this.board_size=board_size;
        Dimension size = new Dimension(601, 601);
        this.setPreferredSize(size);
        this.setBackground(Color.WHITE);

    }

    public void setWorld(World w)
    {
     world =w;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D) g;
        gr.setColor(new Color(141, 64, 27));



        for (int i = 0; i < board_size; i++) {
            for (int k = 0; k < board_size; k++) {
                gr.drawRect(i * field_size, k * field_size, field_size, field_size);

            }
        }

        for(int i=0; i<world.getAmount_of_organisms(); i++){
            if(world.getOrganism(i).isAlive()) {
                Image image = world.getOrganism(i).getIcon().getImage();
                g.drawImage(image, field_size * world.getOrganism(i).getPosition().x, field_size * world.getOrganism(i).getPosition().y, field_size, field_size, null);
            }
        }

    }


}
