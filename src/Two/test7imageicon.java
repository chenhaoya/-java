package Two;

import javax.swing.*;
import java.awt.*;

public class test7imageicon extends JFrame {
    private Container container;
    private JButton jButton;
    private JLabel jLabel;
    test7imageicon(String title)throws HeadlessException{
        super(title);//设置标题
        container=getContentPane();//绑定
        container.setLayout(new BorderLayout());

        init();//图片和标签设置
        //基本设置
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(500,400,500,400);
        this.setVisible(true);
    }

    private void init() {

        jButton=new JButton(new ImageIcon("src/mouse.jpg"));//添加图片
        container.add(jButton,BorderLayout.NORTH);

        jLabel=new JLabel(new ImageIcon("src/mouse.jpg"));
        container.add(jLabel);
    }

    public static void main(String[] args) {
        new test7imageicon("添加图片");
    }
}
