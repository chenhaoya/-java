package OneLayout;

import javax.swing.*;
/**
 * JFrame �C java��GUI����Ļ���˼·����JFrameΪ������������Ļ��window�Ķ����ܹ���󻯡���С�����رա�
 *
 * JPanel �C Javaͼ���û�����(GUI)���߰�swing�е���������࣬������javax.swing ���У����Խ���Ƕ�ף������ǶԴ����о�����ͬ�߼����ܵ����������ϣ���һ�����������������Լ��뵽JFrame�����С���
 *
 * JLabel �C JLabel ���������ʾ�ı���ͼ���ͬʱ��ʾ���ߡ�����ͨ�����ô�ֱ��ˮƽ���뷽ʽ��ָ����ǩ��ʾ���б�ǩ�����ںδ����롣Ĭ������£���ǩ������ʾ���ڴ�ֱ���ж��롣Ĭ������£�ֻ��ʾ�ı��ı�ǩ�ǿ�ʼ�߶��룻��ֻ��ʾͼ��ı�ǩ��ˮƽ���ж��롣
 *
 * JTextField �Cһ�������������������༭�����ı���
 *
 * JPasswordField �C ��������������һ����������򣬵������Ǻ�(*) ��㴴������(����)
 *
 * JButton �C JButton ���ʵ�������ڴ�����ť����ʵ���е� "Login"��
 * */
public class jframe {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(300,300,300,300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
