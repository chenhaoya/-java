package test1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class test3cardlayout {
    private JFrame frame;
    private Container container;
    private JPanel panel;
    private JPanel[] jPanels;

    test3cardlayout(){
        frame=new JFrame("this is CardLayout");
        container=frame.getContentPane();
        frame.setBounds(500,400,500,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        frame.setVisible(true);
    }
    void init(){
        panel=new JPanel();
        container.add(panel);

        jPanels=new JPanel[4];
        for (int i = 0; i < jPanels.length; i++) {
            jPanels[i]=new JPanel();
        }
        jPanels[0].setBackground(Color.RED);
        jPanels[1].setBackground(Color.BLUE);
        jPanels[2].setBackground(Color.GREEN);
        jPanels[3].setBackground(Color.BLACK);

        panel.setLayout(new CardLayout());

        for (int i = 0; i < jPanels.length; i++) {
            panel.add(String.valueOf(i),jPanels[i]);
        }

        //添加监听器，使用鼠标进行组件切换
        addLiSter(panel);
    }

    private void addLiSter(final JPanel panel) {
        /**给panel添加鼠标事件*/
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //获得panel容器上面的布局管理器
                //并转为CardLayout类型的对象
                CardLayout cl = (CardLayout) panel.getLayout();
                //显示CardLayout管理器中的下一个组件
                //这个参数表示:在哪一个容器中设置的这个CardLayout管理器
                cl.next(panel);
//				cl.show(panel, "3");
            }
        });
    }

    public static void main(String[] args) {
        new test3cardlayout();
    }
}
