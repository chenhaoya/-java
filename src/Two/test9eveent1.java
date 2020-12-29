package Two;
/**
 *  了解监听器
 *  一个组件可被多个监听器监听
 *  一个监听器也可以监听多个组件
 *  监听器的自定义
 * */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class test9eveent1 {
    private Frame frame=new Frame("event1");
    private TextArea textArea=new TextArea(5,30);
    private Panel panel=new Panel();//次级容器
    private Button button=new Button("click1");
    private Button button2=new Button("click2");
    public void init(){
        /**        创建监听器
        * 一个组件可被多个监听器监听
        * 一个监听器也可以监听多个组件
        * */
        Listener1 l1=new Listener1();
        Listener2 l2=new Listener2();
        //给组件添加监听
        button.addActionListener(l1);
        button.addActionListener(l2);
        button2.addActionListener(l1);
        frame.add(textArea);
        panel.add(button);
        panel.add(button2);
        frame.add(panel,BorderLayout.SOUTH);
        frame.setBounds(500,400,500,400);
        frame.setVisible(true);

        //关闭按钮
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }


    public static void main(String[] args) {
        new test9eveent1().init();
    }
    class Listener1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.append("***监听器1"+e.getActionCommand()+"被点击\r\n");//在文本域追加信息
            if(e.getSource()==button) {
                System.out.println("button被点击");
                textArea.append("button被点击\n");
            }
            else if(e.getSource()==button2) {
                System.out.println("button2被点击");
                textArea.append("button2被点击\n");
            }
        }
    }
    class Listener2 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.append("***监听器2"+e.getActionCommand()+"被点击\r\n");
            if(e.getSource()==button) {
                System.out.println("button被点击");
                textArea.append("button被点击\n");
            }
            else if(e.getSource()==button2) {
                System.out.println("button2被点击");
                textArea.append("button2被点击\n");
            }
        }
    }
}
