package com.wei.entities;

/*
*
* 定时落下
* */

import com.wei.Listener.ShapeLister;

import java.awt.*;

import static com.wei.util.Global.CELL_SIZE;

public class Shape {

    //定义状态
    public static final int ROTATE = 0;//是否旋转
    public  static final int LEFT = 1;//是否向左
    public static final int RIGHT = 2;//是否向右
    public static final int DOWN = 3;//是否向下

    //图形的各种形态
    private  int [][] body;
    //一种图形的各种形态
    private  int status;
    //left--位置信息
    private int left;
    //top--位置信息
    private int top;

    public ShapeLister shapeLister;

    public void moveLeft(){
        System.out.println("moveLeft");
        left--;

    }
    public void moveRight(){
        System.out.println("moveRight");
        left++;
    }
    public void moveDown(){
        System.out.println("moveDown");
        top++;
    }
    //进行旋转操作
    public void rotate(){
        System.out.println("rotate进行操作");
        status = (status + 1) % body.length;
    }

    //显示的方法
    public void drawMe(Graphics g){
        System.out.println("shape----drawMe()----------");

        //方块颜色
        g.setColor(Color.blue);

        //画一个4 * 4 的方块
        for (int x = 0; x < 4; x++){
            for (int y = 0; y < 4; y++){

                //画出我们想要的图像
                if (getFlagByPoint(x, y)){
                    g.fill3DRect((left + x) * CELL_SIZE, (top + y) * CELL_SIZE, CELL_SIZE, CELL_SIZE, true);
                }
            }
        }

    }

    public boolean getFlagByPoint(int x, int y){
        return body[status][y * 4 + x] == 1;

    }

    //判断是否在图形当中
    public boolean isMember(int x, int y,boolean roatae){
        int tempStatus = status;
        if (roatae){
            tempStatus = (status + 1) % body.length;
        }
        return body[tempStatus][y * 4 + x] == 1;
    }

    //进行定时落下，线程不停的的调用moveDown()方法，然后进行休眠一秒继续落下
    private  class  shapeDriver implements Runnable{

        @Override
        public void run() {
            //下落到不能下落为止
            while (shapeLister.isShapeMoveable(Shape.this)){
                moveDown();
                //监听这个图形
                shapeLister.shapeMoveDown(Shape.this);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Shape(){
        //调用这个线程
        new Thread(new shapeDriver()).start();
    }

    //注册监听器
    public void addShapeListener(ShapeLister lister){
        if (lister != null){
            this.shapeLister = lister;
        }
    }

    //添加两个方法的到图形的形状和状态
    public void setBody(int body[][]){
        this.body = body;
    }
    public void setStatus(int status){
        this.status = status;
    }

    //我們現在添加两个方法得到位置信息
    public int getTop(){
        //返回上距离的位置
        return top;
    }

    public int getLeft(){
        //返回左距离的位置
        return  left;
    }
}
