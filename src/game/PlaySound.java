package game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class PlaySound extends Thread{
    private String filename;

    public PlaySound(String wavfile){
        filename = "src/game/res/" + wavfile;
    }
    public void run(){
        File soundFile = new File(filename);
        AudioInputStream audioInputStream = null;
        try{
            //�����Ƶ������udioInputStream = AudioIn
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        }catch (Exception e1){
            e1.printStackTrace();
            return;
        }
        //ָ�����������ص����ݰ���
        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class,format);
        try {
            //�ӻ�Ƶ�����Դ������
            auline = (SourceDataLine) AudioSystem.getLine(info);
            //�򿪾���ָ����ʽ���У�������ʹ�л�����������ϵͳ��Դ����ÿɲ�����
            auline.open(format);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        //����������ִ������ I/O
        auline.start();
        int nBytesRead = 0;
        // ���ǻ���
        byte[] abData = new byte[512];
        try {
            while (nBytesRead != -1) {
                //����Ƶ����ȡָ������������������ֽڣ����������������ֽ�������
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0)
                    //ͨ����Դ�����н���Ƶ����д���Ƶ��
                    auline.write(abData, 0, nBytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } finally {
            auline.drain();
            auline.close();
        }
    }
}