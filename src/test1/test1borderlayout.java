package test1;

import javax.swing.*;
import java.awt.*;

public class test1borderlayout {
    private JFrame frame;
    private Container container;
    private JButton north,south,west,east,center;


    test1borderlayout(){
        frame=new JFrame("BorderLayout");
        container=frame.getContentPane();
        frame.setBounds(500,400,500,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        frame.setVisible(true);
    }
    void init(){
         north = new JButton("north");
         south=new JButton("south");
         west=new JButton("west");
         east=new JButton("east");
         center=new JButton("center");

         container.setLayout(new BorderLayout());

         container.add(north,BorderLayout.NORTH);
         container.add(south, BorderLayout.SOUTH);
         container.add(west,BorderLayout.WEST);
         container.add(east,BorderLayout.EAST);
         container.add(center,BorderLayout.CENTER);

    }
    public static void main(String[] args) {
        new test1borderlayout();
    }
}
