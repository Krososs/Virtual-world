package Graphic;

import Main.World;
import javax.swing.*;
import java.awt.*;

public class Statistics extends JPanel {

    private World world;

    public Statistics(World w){
        world = w;
        this.setPreferredSize(new Dimension(300, 650));


    }

    @Override
    protected void paintComponent(Graphics g){
        Font font=new Font("Helvetica", Font.BOLD, 20);
        g.setFont(font);
        g.drawString("Organisms alive: " + world.getAmount_of_organisms(), 1,20);
        g.drawString("Events", 1,60);
        g.setFont(new Font("Helvetica",Font.BOLD, 15));
        int y=90;
        for(String stat : world.statistics){
            g.drawString(stat,0,y);
            y+=20;
        }
        world.statistics.clear();

    }




}
