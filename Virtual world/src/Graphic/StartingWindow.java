package Graphic;

import Main.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StartingWindow extends JFrame implements ActionListener {

    private int board_size;
    JPanel main_panel;
    JPanel settings;
    JPanel buttons;
    JButton new_game;
    JButton load_game;
    JButton confirm = new JButton("");
    JButton back;
    JButton exit;
    JCheckBox checkbox1 = new JCheckBox("");
    JCheckBox checkbox2= new JCheckBox("");
    JCheckBox checkbox3= new JCheckBox("");
    Container contentPane;

    public StartingWindow(){
        super("Virtual World S.K 182633");
        main_panel = new JPanel();
        contentPane=getContentPane();
        main_panel.setBackground(new Color(159, 203, 60,255));
        main_panel.setPreferredSize(new Dimension(1280,720));
        Init();

    }

    private void Init(){

        main_panel.removeAll();
        main_panel.invalidate();
        main_panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        //button panel
        buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons , BoxLayout.PAGE_AXIS));
        buttons.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        buttons.setPreferredSize(new Dimension(500,670));
        buttons.setBackground(new Color(250, 104, 0,255));

        JLabel title = new JLabel("Virtual World", JLabel.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Verdana", Font.BOLD, 30));

        new_game = new JButton("New game");
        new_game.setAlignmentX(Component.CENTER_ALIGNMENT);
        new_game.setMaximumSize(new Dimension(300,80));
        new_game.addActionListener(this);

        load_game = new JButton("Load game");
        load_game.setAlignmentX(Component.CENTER_ALIGNMENT);
        load_game.setMaximumSize(new Dimension(300,80));
        load_game.addActionListener(this);

        exit = new JButton("Exit");
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setMaximumSize(new Dimension(300,80));
        exit.addActionListener(this);

        buttons.add(title);
        buttons.add(Box.createRigidArea(new Dimension(0, 30)));
        buttons.add(new_game);
        buttons.add(Box.createRigidArea(new Dimension(0, 30)));
        buttons.add(load_game);
        buttons.add(Box.createRigidArea(new Dimension(0, 30)));
        buttons.add(exit);
        main_panel.add(buttons);
        contentPane.add(main_panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280,720);
        setFocusable(true);
        setResizable(false);
        setVisible(true);
        main_panel.revalidate();

    }
    private void CreateNewWorld(){
        GameWindow gameWindow = new GameWindow(board_size);
        contentPane.removeAll();
        contentPane.invalidate();
        contentPane.add(gameWindow);
        gameWindow.setVisible(true);
        contentPane.revalidate();

    }

    private void LoadWorld(){

        String fileName="/world.sav";
        Path path = Paths.get(System.getProperty("user.home")+"/VirtualWorldJava");
        FileInputStream f=null;

        try{
            f = new FileInputStream(path +fileName);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try{
            ObjectInputStream p = new ObjectInputStream(f) ;
            World loaded;

            try{
                loaded = (World) p.readObject();
                GameWindow gameWindow = new GameWindow(loaded);
                contentPane.removeAll();
                contentPane.invalidate();
                contentPane.add(gameWindow);
                gameWindow.setVisible(true);
                contentPane.revalidate();

            }catch (EOFException ex){
                p.close();
            }

        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }

    }
    private void IsBoardSizeChosen(){
        if(checkbox1.equals(null) || checkbox2.equals(null) || checkbox3.equals(null))
            return;
        if(checkbox1.isSelected() || checkbox2.isSelected() || checkbox3.isSelected())
            confirm.setEnabled(true);
        else confirm.setEnabled(false);
    }
    private void ShowSettings(){
        main_panel.setBorder(BorderFactory.createEmptyBorder(40,0,0,0));
        settings = new JPanel();
        settings.setLayout(new BoxLayout(settings , BoxLayout.PAGE_AXIS));
        settings.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        settings.setPreferredSize(new Dimension(300,500));
        settings.setBackground(new Color(250, 104, 0,255));

        JLabel title = new JLabel("Choose map size", JLabel.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Verdana", Font.BOLD, 30));

        checkbox1 = new JCheckBox("10x10");
        checkbox1.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkbox1.setMaximumSize(new Dimension(100,60));
        checkbox1.setBackground(new Color(250, 58, 0,255));
        checkbox1.addActionListener(this);

        checkbox2 = new JCheckBox("20x20");
        checkbox2.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkbox2.setMaximumSize(new Dimension(100,60));
        checkbox2.setBackground(new Color(250, 58, 0,255));
        checkbox2.addActionListener(this);

        checkbox3 = new JCheckBox("30x30");
        checkbox3.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkbox3.setMaximumSize(new Dimension(100,60));
        checkbox3.setBackground(new Color(250, 58, 0,255));
        checkbox3.addActionListener(this);

        confirm = new JButton("Confirm");
        confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirm.setMaximumSize(new Dimension(200,60));
        confirm.addActionListener(this);
        confirm.setEnabled(false);

        back = new JButton("Back");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setMaximumSize(new Dimension(200,60));
        back.addActionListener(this);


        settings.add(title);
        settings.add(Box.createRigidArea(new Dimension(0, 30)));
        settings.add(checkbox1);
        settings.add(Box.createRigidArea(new Dimension(0, 30)));
        settings.add(checkbox2);
        settings.add(Box.createRigidArea(new Dimension(0, 30)));
        settings.add(checkbox3);
        settings.add(Box.createRigidArea(new Dimension(0, 30)));
        settings.add(confirm);
        settings.add(Box.createRigidArea(new Dimension(0, 10)));
        settings.add(back);

        main_panel.removeAll();
        main_panel.invalidate();
        main_panel.add(settings);
        main_panel.revalidate();

    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        Object source = ev.getSource();

        if(source==new_game){
            ShowSettings();
        }
        if(source==load_game){
            System.out.println("Loading");
            LoadWorld();

        }
        if(source==confirm){
            CreateNewWorld();
        }
        if(source==back){
            Init();
        }
        if(source==checkbox1){
            board_size=10;
            checkbox2.setSelected(false);
            checkbox3.setSelected(false);

        }
        if(source==checkbox2){
            board_size=20;
            checkbox1.setSelected(false);
            checkbox3.setSelected(false);
        }
        if(source==checkbox3){
            board_size=30;
            checkbox1.setSelected(false);
            checkbox2.setSelected(false);

        }
        if(source==exit){
            System.exit(0);
        }
        IsBoardSizeChosen();
        repaint();

    }

}
