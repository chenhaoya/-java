package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

public class componentListener extends KeyAdapter implements ActionListener {
    private boolean aaaa=false;//背景音乐
    //声明窗体
    private gamestart ui;
    //
    private int numbers[][];
    //生成随机数
    private Random rand=new Random();
    //用于备份numbers数组的，用于退一步
    private int backup[][]=new int[4][4];
    //用于起死回生
    private int backup2[][]=new int[4][4];
    public JLabel lb;
    //计分
    int score=0;
    //退一步和起死回生的分数
    int tempscore,tempscore2;

    public JButton tb,about,back;
    public JCheckBox issoundbox;
    //是否胜利，true表示胜利，false表示失败
    private boolean iswi=false;
    //是否复活
    private boolean relive=false;
    //是否可以回退
    private boolean hasback=false;
    //是否播放音乐
    private boolean issound=true;

    public componentListener(gamestart ui, int[][] number, JLabel scoreLabel, JButton start, JButton about, JButton back, JCheckBox issoundbox) {
        this.ui=ui;
        this.tb=start;
        this.numbers=number;
        this.lb=scoreLabel;
        this.about=about;
        this.back=back;
        this.issoundbox=issoundbox;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
/*        if (issound==true&&aaaa==false){
            new PlaySound("11145.wav").start();
            aaaa=true;
        }*/
        if (e.getSource()==tb){
            //游戏开始监听
            iswi=false;
            //各个小单元格赋初值0
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    numbers[j][i]=0;
                }
            }
            //初始化分数
            score=0;
            //设置标签
            lb.setText("分数："+score);
            //生成四个0-3的随机数：坐标
            int r1=rand.nextInt(4);//x坐标
            int r2=rand.nextInt(4);
            int c1=rand.nextInt(4);//y坐标
            int c2=rand.nextInt(4);
            //组成两个坐标且不重复
            while (r1==r2&&c1==c2){
                r2= rand.nextInt(4);
                c2=rand.nextInt(4);
            }
            //生成初始数字（）2、4
            int value1=rand.nextInt(2)*2+2;
            int value2=rand.nextInt(2)*2+2;
            //把数字存入对应位置
            numbers[r1][c1]=value1;
            numbers[r2][c2]=value2;
            //数字更改，重新绘制图形
            ui.paint(ui.getGraphics());

        }
        else if (e.getSource()==about){
            //点击了帮助标签
            JOptionPane.showMessageDialog(ui,"游戏规则：\n"+
            "1、开始游戏时棋盘内随机出现两个数字，出现的数字仅可能是2或4\n" +
            "2、玩家可以选择上下左右四个方向，若棋盘内出现位移或合并，则是为有效移动\n"+
            "3、玩家选择的方向上若有相同的数字则合并，每次有效移动可以同时合并，单不能连续合并\n"+
            "4、合并所得到的所有新生成数字相加即为该步的有效得分\n"+
            "5、玩家选择的方向行或列前方有空格则出现位移\n"+
            "6、每有有效移动一步，棋盘的空位随机出现一个2或4\n"+
            "7、棋盘被数字填满，无法进行有效移动，判负，游戏结束\n"+
            "8、棋盘上出现2048，判胜，游戏结束\n"
                    );
        }
        else if (e.getSource()==back&&hasback==false){
            System.out.println("回退");
            //点击回退一步按钮，只能回退一次，只有在执行一次有效移动才能再次回退
            hasback=true;
            //本次回退是回退上一步，还是复活
            if (relive==false){
                //替换上一步的分数
                score=tempscore;
                lb.setText("分数："+score);
                for (int i = 0; i < backup.length; i++) {
                    numbers[i]= Arrays.copyOf(backup[i],backup[i].length);
                }
            }else {
                //复活
                score=tempscore;
                lb.setText("分数："+score);
                for (int i = 0; i < backup2.length; i++) {
                    numbers[i]= Arrays.copyOf(backup2[i],backup2[i].length);
                }
                //在给一次复活的机会
                relive=false;
            }

            ui.paint(ui.getGraphics());
        }
        /**------------------------静音----------------------*/
        else if (e.getSource().equals(issoundbox)){
            issound=false;//静音判断的变量
        }else {
            issound=true;
        }
    }
    //键盘事件监听，上、下、左、右方向键的值：左：37 右：39 上：38 下：40
    public  void keyPressed(KeyEvent event){
        //记录游戏数字有效移动位数，判断是否移动了
        int counter=0;
        //判断当前右数字的小单元格数量，判断是否已满
        int numcounter=0;
        //记录相邻格子数字相同的对数
        int numnerconter=0;
        //给一次退回的机会
        hasback=false;
        //备份上上步
        if (backup!=null||backup.length!=0){
            //先把分数备份好
            tempscore2=tempscore;
            //用for循环调用java.util.Arrays.copyof()方法复制数组实现备份
            for (int i = 0; i < backup.length; i++) {
                backup2[i]=Arrays.copyOf(backup[i],backup[i].length);

            }
        }
        /**------------------------备份分数；返回上一步------------------------------*/
        tempscore=score;
        //备份数组
        for (int i = 0; i < numbers.length; i++) {
            backup[i]=Arrays.copyOf(numbers[i],numbers[i].length);
        }
        if (iswi==false){

            switch (event.getKeyCode()) {

                case 37:
                    if (issound==true){
                        new PlaySound("move.wav").start();
                    }
                    //第一步，把所有不为0的单元格移动到最左边
                    for (int h = 0; h < 4; h++) {//行
                        for (int l = 0; l < 4; l++) {//列
                            if (numbers[h][l] != 0) {//如果单元格数字不为零
                                int temp = numbers[h][l];//保存单元格的值
                                int pre = l - 1;//表示我们要移动的单元格左边单元格的列标
                                while (pre >= 0 && numbers[h][pre] == 0) {//左边单元格为0
                                    numbers[h][pre] = temp; //覆盖左边单元格
                                    numbers[h][pre + 1] = 0;//原来单元格值为0
                                    pre--;//继续左移
                                    counter++;//数字有效移动位数
                                }
                            }
                        }
                    }
                    //第二步：左右相邻值相同 的单元格值相加，放入左边单元格
                    for (int h = 0; h < 4; h++) {
                        for (int l = 0; l < 4; l++) {
                            //合并的条件
                            if (l + 1 < 4 && (numbers[h][l] == numbers[h][l + 1] && (numbers[h][l] != 0 || numbers[h][l + 1] != 0))) {
                                if (issound==true){
                                    new PlaySound("merge.wav").start();
                                }
                                numbers[h][l] = numbers[h][l] + numbers[h][l + 1];//合并两个单元格
                                numbers[h][l + 1] = 0;//右边单元格初始化
                                counter++;
                                score += numbers[h][l];//分数增加
                                if (numbers[h][l] == 2048) {//右2048值的单元格，胜利
                                    iswi = true;
                                }
                            }
                        }
                    }
                    //第三步：在进行一次左移操作
                    for (int h = 0; h < 4; h++) {//行
                        for (int l = 0; l < 4; l++) {//列
                            if (numbers[h][l] != 0) {//如果单元格数字不为零
                                int temp = numbers[h][l];//保存单元格的值
                                int pre = l - 1;//表示我们要移动的单元格左边单元格的列标
                                while (pre >= 0 && numbers[h][pre] == 0) {//左边单元格为0
                                    numbers[h][pre] = temp; //覆盖左边单元格
                                    numbers[h][pre + 1] = 0;//原来单元格值为0
                                    pre--;//继续左移
                                    counter++;//数字有效移动位数
                                }
                            }
                        }
                    }
                    break;

                    //向右移动
                case 39 :
                    if (issound==true){
                        new PlaySound("move.wav").start();
                    }
                    for (int h = 3; h >= 0 ; h--) {
                        for (int l = 3; l >= 0; l--) {
                            if (numbers[h][l] != 0) {//判断单元格不为0
                                int temp = numbers[h][l];//备份单元格
                                int pre = l + 1;//右侧单元格的列标
                                while (pre <= 3 && numbers[h][pre] == 0) {
                                    numbers[h][pre] = temp;
                                    numbers[h][pre - 1] = 0;
                                    pre++;
                                    counter++;
                                }
                            }
                        }
                    }
                    //左右值相同的单元格合并
                    for (int h = 3; h >=0 ; h--) {
                        for(int l=3;l>=0;l--){
                            /**
                             * 1.小于4:没有越界；
                             * 2.左右单元的相同
                             * 3.值相同的单元格的值不为0
                             * */
                            if (l+1<4&&(numbers[h][l]==numbers[h][l+1])&&(numbers[h][l]!=0||numbers[h][l+1]!=0)){
                                if (issound==true){
                                    new PlaySound("merge.wav").start();
                                }
                                numbers[h][l+1]=numbers[h][l]+numbers[h][l+1];
                                numbers[h][l]=0;
                                counter++;
                                score+=numbers[h][l+1];
                                if (numbers[h][l+1]==2048){
                                    iswi=true;
                                }
                            }
                        }
                    }
                    //再次右移
                    for (int h = 3; h >= 0 ; h--) {
                        for (int l = 3; l >= 0; l--) {
                            if (numbers[h][l] != 0) {
                                int temp = numbers[h][l];//备份单元格
                                int pre = l + 1;//右侧单元格的列标
                                while (pre <= 3 && numbers[h][pre] == 0) {
                                    numbers[h][pre] = temp;
                                    numbers[h][pre - 1] = 0;
                                    pre++;
                                    counter++;
                                }
                            }
                        }
                    }
                    break;

                    /**
                     * 上移
                     * 1.向上移动操作
                     * 2.是否和平单元格
                     * */
                case 38:
                    if (issound==true){
                        new PlaySound("move.wav").start();
                    }
                    //上移
                    for (int l=0;l<4;l++){
                        for (int h=0;h<4;h++){
                            if (numbers[h][l]!=0){
                                int temp=numbers[h][l];
                                int pre=h-1;
                                while (pre>=0&&numbers[pre][l]==0){
                                    numbers[pre][l]=temp;
                                    numbers[pre+1][l]=0;
                                    pre--;
                                    counter++;
                                }
                            }
                        }
                    }
                    //合并上下值相同的单元格
                    for (int l = 0; l < 4; l++) {
                        for (int h = 0; h <4 ; h++) {
                            if (h+1<4&&(numbers[h][l]==numbers[h+1][l])&&(numbers[h][l]!=0||numbers[h+1][l]==0)){
                                if (issound==true){
                                    new PlaySound("merge.wav").start();
                                }
                                numbers[h][l]=numbers[h+1][l]+numbers[h+1][l];
                                numbers[h+1][l]=0;
                                counter++;
                                score+=numbers[h][l];
                                if (numbers[h][l]==2048){
                                    iswi=true;
                                }
                            }
                        }
                    }
                    //再一次上移
                    for (int l=0;l<4;l++){
                        for (int h=0;h<4;h++){
                            if (numbers[h][l]!=0){
                                int temp=numbers[h][l];
                                int pre=h-1;
                                while (pre>=0&&numbers[pre][l]==0){
                                    numbers[pre][l]=temp;
                                    numbers[pre+1][l]=0;
                                    pre--;
                                    counter++;
                                }
                            }
                        }
                    }
                   break;
                case 40:
                    if (issound==true){
                        new PlaySound("move.wav").start();
                    }
                    //下移
                    for (int l=3;l>=0;l--){
                        for (int h=3;h>=0;h--){
                            if (numbers[h][l]!=0){
                                int temp=numbers[h][l];
                                int pre=h+1;
                                while (pre<=3&&numbers[pre][l]==0){
                                    numbers[pre][l]=temp;
                                    numbers[pre-1][l]=0;
                                    pre++;
                                    counter++;
                                }
                            }
                        }
                    }
                    //合并上下值相同的单元格
                    for (int l = 3; l >= 0; l--) {
                        for (int h = 3; h >= 0 ; h--) {
                            if (h+1<4&&(numbers[h][l]==numbers[h+1][l])&&(numbers[h][l]!=0||numbers[h+1][l]==0)){
                                if (issound==true){
                                    new PlaySound("merge.wav").start();
                                }
                                numbers[h][l]=numbers[h+1][l]+numbers[h+1][l];
                                numbers[h+1][l]=0;
                                counter++;
                                score+=numbers[h][l];
                                if (numbers[h][l]==2048){
                                    iswi=true;
                                }
                            }
                        }
                    }
                    //再一次上移
                    for (int l=3;l>=0;l--){
                        for (int h=3;h>=0;h--){
                            if (numbers[h][l]!=0){
                                int temp=numbers[h][l];
                                int pre=h+1;
                                while (pre<=3&&numbers[pre][l]==0){
                                    numbers[pre][l]=temp;
                                    numbers[pre-1][l]=0;
                                    pre++;
                                    counter++;
                                }
                            }
                        }
                    }
                    break;
            }
            /**
             * 结束判断
             * 在上下左右处理后，统计是否有相邻单元格值有相同的情况
             * 如果没有，游戏结束，有继续运行，
             * 方法：遍历
             * h:行标 ：i
             * l:列标 : j
             * */
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    /**
                     *如果numnerconter等于0表示整个单元格找不到相邻相同的元素
                     **/
                    if (numbers[i][j]==numbers[i][j+1]&&numbers[i][j]!=0)
                        numnerconter++;
                    if (numbers[i][j]==numbers[i+1][j]&&numbers[i][j]!=0)
                        numnerconter++;

                    //第四行判断  //*/只需要判断是否与右边有相同值
                    if (numbers[3][j]==numbers[3][j+1]&&numbers[3][j]!=0)
                        numnerconter++;

                    //第四列判断      只需要判断与下边是否相同
                    if (numbers[i][3]==numbers[i+1][3]&&numbers[i][3]!=0)
                        numnerconter++;/**-------------------统计相邻单元格相同---------------------------*/
                }
            }

            //统计不为零单元格的数目
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (numbers[i][j]!=0){
                        numcounter++;/**--------------------统计不为零单元格数目-------------------*/
                    }
                }
            }

            //有效位移数>0，则补充一个新的2或4
            if(counter>0){
                lb.setText("分数："+score);
                int r1=rand.nextInt(4);
                int c1=rand.nextInt(4);
                /**
                 * 如果全屏满了，如何处理
                 * */
                while (numbers[r1][c1]!=0){
                     // 如果生成这个数上的单元格有数，这里重新生成
                    r1=rand.nextInt(4);
                    c1=rand.nextInt(4);
                }
                //随机生成2或者4
                int value1=rand.nextInt(2)*2+2;
                numbers[r1][c1]=value1;
            }

            /**--------------------这里是游戏结束--------------------**/
            if (iswi==true){
                ui.paint(ui.getGraphics());
                JOptionPane.showMessageDialog(ui,"恭喜你赢了！\n最终得分为："+score);
            }
            if (numcounter==16&&numnerconter==0){
                relive=true;//给一次复活的机会
                JOptionPane.showMessageDialog(ui,"没有可以合并的单元格了\n很遗憾，您输了");
                /**-----------游戏失败界面，这里只是提示，创新可以在这里---------*/
            }

            ui.paint(ui.getGraphics());
        }

    }
}
