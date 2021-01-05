package game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;

 class musicStuff {
    void playMusic(String musicLocation)
    {
        try
        {
            File musicPath = new File(musicLocation);

            if(musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else
            {

            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
public class gamestart extends JFrame {
    public static void main(String[] args) {

        String filepath = "11145.wav";
        musicStuff musicObject = new musicStuff();
        musicObject.playMusic(filepath);

        gamestart view = new gamestart();
        view.init();

    }
    private int number[][]=new int[4][4];

    private void init() {
        this.setTitle("2048游戏");
        this.setBounds(350, 450, 400, 500);
        this.setLayout(null);


        //设置开始按钮
        ImageIcon imageIcon = new ImageIcon("src/game/res/start.png");//申请一个开始图片对象

        //申请了一个按钮，他们标题就是上面图片文件
        JButton start = new JButton(imageIcon);

        //设置此按钮不可获取焦点
        start.setFocusable(false);

        //设置按钮没有边框
        start.setBorderPainted(false);

        //设置不绘制边框，设置paintFocus属性，对于要绘制的焦点状态，该属性必须给true
        //一些外观没有绘制焦点状态，可忽略该属性
        start.setFocusPainted(false);

        //设置不绘制边框，如果ContenAreaFilled属性位true按钮则绘制内容区域
        //如果希望有个透明的按钮，比如只是一个图标的按钮，那么该属性必须要设置位false
        start.setContentAreaFilled(false);

        start.setBounds(5, 10, 120, 30);
        this.add(start);

        //帮助按钮
        //生成图片对象
        ImageIcon imageIcon1 = new ImageIcon("src/game/res/about.png");
        JButton about = new JButton(imageIcon1);
        about.setFocusable(false);
        about.setBorderPainted(false);
        about.setFocusPainted(false);
        about.setContentAreaFilled(false);
        about.setBounds(160, 10, 70, 30);
        this.add(about);

        //上一步
        ImageIcon imageIcon2 = new ImageIcon("src/game/res/backicon.png");
        JButton back = new JButton(imageIcon2);
        back.setFocusable(false);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);
        back.setBounds(270, 10, 120, 30);
        this.add(back);

        //分数显示
        JLabel scoreLabel = new JLabel("分数:0");
        scoreLabel.setBounds(40, 50, 120, 30);
        //设置字体
        scoreLabel.setFont(new Font("幼园", Font.CENTER_BASELINE, 18));
        //设置字体颜色
        scoreLabel.setForeground(new Color(0x000000));
        this.add(scoreLabel);

        //静音复选框
//        生成一个复选框
        JCheckBox issoundbox = new JCheckBox("静音");
        issoundbox.setBounds(290,45,120,30);
        //设置显示字体
        issoundbox.setFont(new Font("幼园",Font.CENTER_BASELINE,18));
        //设置不获取焦点
        issoundbox.setFocusable(false);
        //设置没有边框
        issoundbox.setBorderPainted(false);
        //设置不绘制边框
        issoundbox.setFocusPainted(false);
        issoundbox.setContentAreaFilled(false);
        this.add(issoundbox);

        //设置关闭
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //设置窗口大小不可改变
        this.setResizable(false);
        this.setVisible(true);

        number[2][2]=1024;
        number[2][1]=1024;
        /**
         * 创建事件监听器：对按钮的监听
         * */
        componentListener cl=new componentListener(this,number,scoreLabel,start,about,back,issoundbox);
        start.addActionListener(cl);
        about.addActionListener(cl);
        back.addActionListener(cl);
        issoundbox.addActionListener(cl);
        this.addKeyListener(cl);

    }
    //绘制游戏的下半部分
    @Override
    public void paint(Graphics g){
        //画一个白板
        super.paint(g);
        //设置画笔颜色
        g.setColor(new Color(0xbbada0));
        //绘制4*4圆角矩形区域，使用当前颜色填充指定圆角矩形
        g.fillRoundRect(15,110,370,370,15,15);

        //设置小圆角矩形颜色
        g.setColor(new Color(0xcdc1b4));
        //绘制16个小圆角矩形
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                g.fillRoundRect(25+i*90,120+j*90,80,80,15,15);
            }
        }
        //根据单元格不同的值绘制单元格不同的颜色
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //如果小单元格上数字不为0，则说明有值，则可以绘制背景颜色和数字
                if (number[j][i]!=0){
                    int fontsize=30;
                    int movx=0,movy=0;
                    switch (number[j][i]){
                        case 2:
                            g.setColor(new Color(0xeeee4da));
                            fontsize=30;
                            movx=32;movy=50;
                            break;

                        case 4:
                            g.setColor(new Color(0xede0c8));
                            fontsize=30;
                            movx=32;movy=50;
                            break;

                        case 8:
                            g.setColor(new Color(0xf2b179));
                            fontsize=30;
                            movx=32;movy=50;
                            break;

                        case 16:
                            g.setColor(new Color(0xf59563));
                            fontsize=29;
                            movx=26;movy=50;
                            break;

                        case 32:
                            g.setColor(new Color(0xf67c5f));
                            fontsize=29;
                            movx=26;movy=50;
                            break;

                        case 64:
                            g.setColor(new Color(0xf65e3b));
                            fontsize=29;
                            movx=26;movy=50;
                            break;

                        case 128:
                            g.setColor(new Color(0xedcf72));
                            fontsize=28;
                            movx=20;movy=50;
                            break;

                        case 256:
                            g.setColor(new Color(0xedcc61));
                            fontsize=28;
                            movx=20;movy=50;
                            break;

                        case 512:g.setColor(new Color(0xedc850));
                            fontsize=28;
                            movx=20;movy=50;
                            break;

                        case 1024:g.setColor(new Color(0xedc53f));
                            fontsize=27;
                            movx=14;movy=50;
                            break;

                        case 2048:g.setColor(new Color(0xedc22e));
                            fontsize=27;
                            movx=14;movy=50;
                            break;

                        default :g.setColor(new Color(0x00000));
                        break;
                    }
                    //在相应单元格绘制选中的背景颜色
                    //g.fillRoundRect(25*i*90,j*90+120,80,80,15,15);//格式错误
                    g.fillRoundRect(25+i*90,120+j*90,80,80,15,15);
                    //绘制单元格上的数字
                    //设置数字颜色
                    g.setColor(new Color(0x000000));
                    //设置数字字体
                    g.setFont(new Font("Kristen ITC",Font.PLAIN,fontsize));
                    //绘制单元格上的数字
                    g.drawString(number[j][i]+"",25+i*90+movx,120+j*90+movy);
                    //g.drawString(number[j][i]+"",25+i*90+30+movx,120+i*90+50+movy);//格式错误
                    /**
                     * 这里小矩形的坐标进行的微调
                     * */
                }
            }
        }
    }
}
