package Two;
/**
 * Ŀ�ģ�����һ����ǩ��ʾ���֣���10�ݼ���0ֹͣ
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
        Container container=jFrame.getContentPane();//���븽���������ʹ��ڰ�
        container.setLayout(new BorderLayout());//�߽粼�ֹ�����
        //���ñ�ǩ������Ϊˮƽ���ж���
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(jLabel);
        timer=new Timer(1000, new ActionListener() {//1000����
            //ÿ��һ�룬����һ���¼����һᱻAction Listener�ӿڼ������������ýӿ��еķ������¼�����
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getSource()+"��ʱ��");
//                System.out.println(e.getActionCommand()+"��ʱ��");
//                System.out.println(e.getSource().);
                //�ı��ǩ�е����֣���0Ϊֹ
                int num=Integer.parseInt(jLabel.getText());//�����ַ����е�����
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
