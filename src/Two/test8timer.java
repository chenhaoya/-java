package Two;
/**
 * 目的：设置一个标签显示数字，从10递减到0停止
 * */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test8timer {
    private JFrame jFrame=new JFrame("timer");
    private JLabel jLabel=new JLabel("10");
    private Timer timer;
    public void init(){
        Container container=jFrame.getContentPane();//申请附属容器并和窗口绑定
        container.setLayout(new BorderLayout());//边界布局管理器
        //设置标签中文字为水平居中对其
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(jLabel);
        timer=new Timer(1000, new ActionListener() {//1000毫秒
            //每隔一秒，触发一个事件，且会被Action Listener接口监听到，并调用接口中的方法对事件处理
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getSource()+"定时器");
//                System.out.println(e.getActionCommand()+"定时器");
//                System.out.println(e.getSource().);
                //改变标签中的数字，到0为止
                int num=Integer.parseInt(jLabel.getText());//解析字符串中的整数
                if (num>0)
                    jLabel.setText(--num+"");
                else timer.stop();
            }
        });
        timer.start();
        jFrame.setBounds(500,400,500,400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new test8timer().init();
    }
}
