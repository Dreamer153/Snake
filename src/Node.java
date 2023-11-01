import java.util.Random;
/*
     Node类，每个Node都有横纵坐标
*/
public class Node {
    private int x;//横坐标
    private int y;//纵坐标

    public Node(){}
    public Node(int x,int y){
        this.x=x;
        this.y=y;
    }
    public int getx()
    {
        return x;
    }
    public void setx(int x)
    {
        this.x=x;
    }
    public int gety()
    {
        return y;
    }
    public void sety(int y)
    {
        this.y=y;
    }
    //随机位置方法
    public void random()
    {
        //创建对象
        Random r=new Random();
        //随机生成横坐标
        this.x=r.nextInt(35);
        //随机生成纵坐标
        this.y=r.nextInt(36);
    }
}
 
 