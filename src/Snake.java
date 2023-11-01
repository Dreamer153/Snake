import java.util.LinkedList;

/*
    Snake类，使用LinkedList集合存储Node节点
     */
public class Snake
{
    //新建蛇的身体
    private LinkedList<Node>body;
    //设置蛇的运动方向,默认向左
    private Direction direction=Direction.LEFT;
    //判断蛇是否活着
    private boolean isLiving=true;
    public Snake() {
        initSnake();
    }
    public void initSnake()
    {
        //创建蛇的身体
        body=new LinkedList<>();
        body.add(new Node(20,20));
        body.add(new Node(20,21));
        body.add(new Node(20,22));
        body.add(new Node(21,22));
    }
    //控制蛇移动:新建蛇头节点，删除蛇尾节点
    public void move()
    {
        if(isLiving) {
            Node head = body.getFirst();
            switch (direction) {
                case UP:
                    body.addFirst(new Node(head.getx(), head.gety() - 1));
                    break;
                case DOWN:
                    body.addFirst(new Node(head.getx(), head.gety() + 1));
                    break;
                case LEFT:
                    body.addFirst(new Node(head.getx() - 1, head.gety()));
                    break;
                case RIGHT:
                    body.addFirst(new Node(head.getx() + 1, head.gety()));
                    break;
            }
            //删除蛇尾节点
            body.removeLast();
            //判断蛇是否越界
            head=body.getFirst();
            if(head.getx()<0){
                head.setx(34);
            } else if (head.getx()>34) {
                head.setx(0);
            } else if (head.gety()<0) {
                head.sety(35);
            } else if (head.gety()>35) {
                head.sety(0);
            }
            //判断蛇是否碰到自己的身体
            for(int i=1;i<body.size();i++)
            {
                Node node=body.get(i);
                if(head.getx()==node.getx()&&head.gety()==node.gety())
                {
                    isLiving=false;
                }
            }
        }
    }
    public LinkedList<Node>getBody()
    {
        return body;
    }
    public void setBody(LinkedList<Node>body)
    {
        this.body=body;
    }
    public Direction getDirection()
    {
        return direction;
    }
    public void setDirection(Direction direction)
    {
        this.direction=direction;
    }

    public boolean isLiving() {
        return isLiving;
    }

    public void setLiving(boolean living) {
        isLiving = living;
    }

    //蛇吃食物时的逻辑判断
    public void eat(Node food)
    {
        Node head=body.getFirst();
        switch (direction) {
            case UP:
                body.addFirst(new Node(head.getx(), head.gety() - 1));
                break;
            case DOWN:
                body.addFirst(new Node(head.getx(), head.gety() + 1));
                break;
            case LEFT:
                body.addFirst(new Node(head.getx() - 1, head.gety()));
                break;
            case RIGHT:
                body.addFirst(new Node(head.getx() + 1, head.gety()));
                break;
        }
    }
}