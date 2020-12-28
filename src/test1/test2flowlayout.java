package test1;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;

public class test2flowlayout {
    JFrame frame;
    JButton button[];
    Container container;
    JPanel jPanel;
    test2flowlayout(){
        frame=new JFrame("flowlayout");
        container=frame.getContentPane();
        frame.setBounds(500,400,500,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        frame.setVisible(true);
    }
    void init(){
        jPanel=new JPanel();
        //container.setLayout(new BorderLayout());
        container.add(jPanel);
        jPanel.setLayout(new FlowLayout());

        button=new JButton[50];
        for(int i=0;i<button.length;i++){
            jPanel.add(new JButton(String.valueOf(i)));
        }
    }
    public static void main(String[] args) {
        new test2flowlayout();
    }
}
