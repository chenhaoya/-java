package Two;
/**
 * java Swing基本组件的了解
 * 密码框、文本框、文本域、多选菜单、单选菜单、下拉菜单、
 * */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class teste6Swing {
    private JFrame jf = new JFrame("常用组件");

    private JPanel jp1 = new JPanel();
    private JLabel jl = new JLabel("密码:");
    private JPasswordField jpf = new JPasswordField(20);

    private JPanel jp2 = new JPanel();
    private JTextField jtf = new JTextField(20);
    private JTextArea jta = new JTextArea(3,20);

    private JPanel jp3 = new JPanel();
    //多选
    private JCheckBox jc1 = new JCheckBox("篮球");
    private JCheckBox jc2 = new JCheckBox("足球");
    private JCheckBox jc3 = new JCheckBox("排球");
    //单选
    private JRadioButton jr1 = new JRadioButton("F");
    private JRadioButton jr2 = new JRadioButton("M");

    //下拉菜单·
    private JPanel jp4 = new JPanel();
    private JComboBox<String> jcb = new JComboBox<>(new String[]{"河南","江苏","上海"});

    private JButton jbtn = new JButton("确定");

    public void init(){
        //获取JFrame的顶级容器
        Container cp = jf.getContentPane();//附属容器
        cp.setLayout(new GridLayout(4, 1));
        //JLabel，JPassword
        jp1.add(jl);
        jpf.setEchoChar('*');
        jp1.add(jpf);
        cp.add(jp1);
        //第二行
        jp2.add(jtf);
        jp2.add(jta);
        cp.add(jp2);
        //第三行
        jp3.add(jc1);
        jp3.add(jc2);
        jp3.add(jc3);
        ButtonGroup bg = new ButtonGroup();//按钮组，添加后不能被同时选中
        bg.add(jr1);
        bg.add(jr2);
        jp3.add(jr1);
        jp3.add(jr2);
        cp.add(jp3);
        //第四行
        jcb.addItem("陕西");
        jp4.add(jcb);
        jp4.add(jbtn);
        cp.add(jp4);
        //确定监听
        jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**使用isSelected来检查当前选中哪些*/
                //输出密码
                System.out.println("密  码:"+String.valueOf(jpf.getPassword()));
                //输出文本框和文本域类容
                System.out.println("文本框:"+jtf.getText());
                System.out.println("文本域:"+jta.getText());
                //复选框选中判断
                if(jc1.isSelected()){
                    System.out.println("复选框:"+jc1.getText());
                }
                if(jc2.isSelected()){
                    System.out.println("复选框:"+jc2.getText());
                }
                if(jc3.isSelected()){
                    System.out.println("复选框:"+jc3.getText());
                }
                //单选框选中检测
                if(jr1.isSelected()){
                    System.out.println("单  选:"+jr1.getText());
                }else if(jr2.isSelected()){
                    System.out.println("单  选："+jr2.getText());
                }
                //选项卡检测
                System.out.println(jcb.getSelectedItem());
                jcb.removeItemAt(1);//去除anindex指定的
//                jcb.removeAllItems();//清除所有
//                jcb.addItem("韩国");//加入
            }
        });

        jf.setBounds(200, 200, 300, 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
    public static void main(String[] args) {
        new teste6Swing().init();
    }
}
