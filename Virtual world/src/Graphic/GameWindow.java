package Graphic;
import Main.World;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.*;
import java.util.stream.Stream;


public class GameWindow extends JPanel implements ActionListener {

    JButton next_turn;
    JButton new_world;
    JButton save;
    JButton exit;

    private final GameArena arena;
    private final Statistics statistics;
    private final World world;

    public GameWindow(World w){
        world=w;
        world.setWorld();
        arena = new GameArena(world.getBoardSize(), world);
        statistics= new Statistics(world);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0,250,0,255));
        panel.setVisible(true);

        //buttons panel
        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(241, 224, 203,255));
        buttons.setLayout(new BoxLayout(buttons , BoxLayout.PAGE_AXIS));
        buttons.setVisible(true);
        buttons.setPreferredSize(new Dimension(300,650));

        next_turn= new JButton("Next turn");
        next_turn.setAlignmentX(Component.CENTER_ALIGNMENT);
        next_turn.setMaximumSize(new Dimension(240,80));
        next_turn.addActionListener(this);

        new_world= new JButton("New world");
        new_world.setAlignmentX(Component.CENTER_ALIGNMENT);
        new_world.setMaximumSize(new Dimension(240,80));
        new_world.addActionListener(this);

        save= new JButton("Save");
        save.setAlignmentX(Component.CENTER_ALIGNMENT);
        save.setMaximumSize(new Dimension(240,80));
        save.addActionListener(this);

        exit= new JButton("Exit");
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setMaximumSize(new Dimension(240,80));
        exit.addActionListener(this);

        //statistic panel
        JPanel statistics_panel = new JPanel();
        statistics_panel.setBackground(new Color(241, 224, 203,255));
        statistics_panel.add(statistics);


        JPanel arena_panel = new JPanel();
        arena_panel.add(arena);
        panel.add(arena_panel, BorderLayout.CENTER);

        buttons.add(Box.createRigidArea(new Dimension(0, 60)));
        buttons.add(next_turn);
        buttons.add(Box.createRigidArea(new Dimension(0, 20)));
        buttons.add(new_world);
        buttons.add(Box.createRigidArea(new Dimension(0, 20)));
        buttons.add(save);
        buttons.add(Box.createRigidArea(new Dimension(0, 20)));
        buttons.add(exit);
        buttons.add(Box.createRigidArea(new Dimension(0, 20)));

        add(statistics_panel);
        add(panel);
        add(buttons);
    }

    public GameWindow(int s){
        world = new World(s);
        world.SetNewWorld();
        arena = new GameArena(s, world);
        statistics= new Statistics(world);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(141, 64, 27,255));
        panel.setVisible(true);

        //buttons panel
        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(241, 224, 203,255));
        buttons.setLayout(new BoxLayout(buttons , BoxLayout.PAGE_AXIS));
        buttons.setVisible(true);
        buttons.setPreferredSize(new Dimension(300,650));

        next_turn= new JButton("Next turn");
        next_turn.setAlignmentX(Component.CENTER_ALIGNMENT);
        next_turn.setMaximumSize(new Dimension(240,80));
        next_turn.addActionListener(this);

        new_world= new JButton("New world");
        new_world.setAlignmentX(Component.CENTER_ALIGNMENT);
        new_world.setMaximumSize(new Dimension(240,80));
        new_world.addActionListener(this);

        save= new JButton("Save");
        save.setAlignmentX(Component.CENTER_ALIGNMENT);
        save.setMaximumSize(new Dimension(240,80));
        save.addActionListener(this);

        exit= new JButton("Exit");
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setMaximumSize(new Dimension(240,80));
        exit.addActionListener(this);

        //statistic panel
        JPanel statistics_panel = new JPanel();
        statistics_panel.setBackground(new Color(241, 224, 203,255));
        statistics_panel.add(statistics);
        JPanel arena_panel = new JPanel();
        arena_panel.add(arena);
        panel.add(arena_panel, BorderLayout.CENTER);

        buttons.add(Box.createRigidArea(new Dimension(0, 60)));
        buttons.add(next_turn);
        buttons.add(Box.createRigidArea(new Dimension(0, 20)));
        buttons.add(new_world);
        buttons.add(Box.createRigidArea(new Dimension(0, 20)));
        buttons.add(save);
        buttons.add(Box.createRigidArea(new Dimension(0, 20)));
        buttons.add(exit);
        buttons.add(Box.createRigidArea(new Dimension(0, 20)));

        add(statistics_panel);
        add(panel);
        add(buttons);

    }

    private void SaveWorld() throws IOException {

        Path path = Paths.get(System.getProperty("user.home")+"/VirtualWorldJava");

        if( !(new File(path.toString()).exists())){

            try {
                Files.createDirectories(path);
            }catch(IOException e){
                System.err.println("Failed to create directory" + e.getMessage());
            }

        }

        try (Stream<Path> files = Files.list(path)) {

            FileOutputStream f = new FileOutputStream(path +"/world.sav");
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(world);
            o.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void actionPerformed(ActionEvent ev){

        Object source = ev.getSource();
        if(source==next_turn){
            world.PlayTurn();
        }
        if(source==save){
            try {
                SaveWorld();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (source == new_world){
            world.SetNewWorld();

        }
        if(source==exit){
            System.exit(0);
        }

        repaint();

    }

}
