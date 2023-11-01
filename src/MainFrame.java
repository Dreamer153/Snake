import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class MainFrame extends JFrame
{
    private Snake Snake;//蛇
    private JPanel jpanel;//棋盘
    private Timer timer;//计时器
    private Node food;//食物
    public MainFrame()throws HeadlessException
    {   //初始化窗口
        initFrame();
        //初始化棋盘
        initGamePanel();
        //初始化蛇
        initSnake();
        //初始化食物
        initFood();
        //初始化计时器
        initTimer();
        //设置键盘监听
        setKeyListener();
    }
    //初始化食物
    private void initFood()
    {
        food=new Node();
        food.random();
    }
    //设置键盘监听
    private void setKeyListener()
    {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if(Snake.getDirection()!=Direction.DOWN) {
                        Snake.setDirection(Direction.UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(Snake.getDirection()!=Direction.UP) {
                        Snake.setDirection(Direction.DOWN);
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if(Snake.getDirection()!=Direction.RIGHT) {
                        Snake.setDirection(Direction.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(Snake.getDirection()!=Direction.LEFT) {
                        Snake.setDirection(Direction.RIGHT);
                    }
                    break;
                }
            }
        });
    }

    //初始化计时器
    private void initTimer()
    {
        timer=new Timer();
        TimerTask timerTask=new TimerTask()
        {
            @Override
            public void run()
            {
                Snake.move();
                //判断蛇头是否与食物重合
                Node head=Snake.getBody().getFirst();
                if(head.getx()==food.getx()&&head.gety()==food.gety())
                {
                    Snake.eat(food);
                    food.random();
                }
                //重绘游戏棋盘
                jpanel.repaint();
            }
        };
        //每100毫秒，执行一次
        timer.scheduleAtFixedRate(timerTask,0,100);
    }

    private void initSnake()
    {
        Snake=new Snake();
    }
    //初始化棋盘
    public void initGamePanel()
    {
        jpanel=new JPanel()
        {
            @Override
            public void paint(Graphics g)
            {
                //清空棋盘
                g.clearRect(0,0,600,600);
                //绘制蛇
                LinkedList<Node> body=Snake.getBody();
                for(Node node:body)
                {
                    g.fillRect(node.getx()*15,node.gety()*15,15,15);
                }
                //绘制食物
                g.fillRect(food.getx()*15,food.gety()*15,15,15);
            }
        };
        add(jpanel);
    }
    //初始化窗口
    public void initFrame()
    {
        //设置标题
        setTitle("Snake");
        //设置窗体长宽高
        setSize(540,570);
        //设置窗口位置
        setLocation(240,50);
        //设置关闭按钮
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //规定窗口大小不可变
        setResizable(false);
    }
    public static void main(String[]args)
    {
        new MainFrame().setVisible(true);
    }
}