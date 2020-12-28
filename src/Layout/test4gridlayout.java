package Layout;
/**
 * 表格布局管理器
 * */
import javax.swing.*;
import java.awt.*;

public class test4gridlayout {
    private JFrame frame;
    private Container container;
    private  JButton[] buts;
    test4gridlayout(){
        frame=new JFrame("This is Gridlayout");
        container= frame.getContentPane();
        frame.setBounds(500,400,500,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        frame.setVisible(true);
    }
    void init(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3,3));
        buts=new JButton[9];
        container.add(jPanel);
        for (int i = 0; i < buts.length; i++) {
            buts[i]=new JButton(String.valueOf(i));
            jPanel.add(buts[i]);
        }

    }
    public static void main(String[] args) {
        new test4gridlayout();
    }
}
