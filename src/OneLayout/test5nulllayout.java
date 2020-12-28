package OneLayout;

import java.awt.*;

public class test5nulllayout {
    public static void main(String[] args) {
        Frame frame = new Frame("nullLayout");
        frame.setLayout(null);
        Button button = new Button("1");
        Button button1 = new Button("2");
        button.setBounds(20,20,50,50);
          button1.setBounds(60,60,50,50);
        frame.add(button);
        frame.add(button1);
        frame.setBounds(500,400,500,400);
        frame.setVisible(true);
    }
}
