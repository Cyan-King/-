package com.wei.entities;

import com.wei.util.Global;

import java.awt.*;

public class Ground {

    //创建一个二维数组
    private int[][] obstacles = new int[Global.WIDTH][Global.HEIGHT];

    //接收图形的方法
    public void accept(Shape shape) {
        System.out.println("接收图形的方法");
        for (int x = 0; x < 4; x++){
            for (int y = 0; y < 4; y++){
                //设置图像的留
                //x,y的值，然后再判断我们不乱动（rotate()方法不调用）
                if (shape.isMember(x, y, false)){
                    //如果想对应的的位置是障碍就是1，不是的话就是0
                    obstacles[shape.getLeft() + x][shape.getTop() + y] = 1;
                }
            }
        }
        //检查障碍物然后再次消除
        deltetFullLine();
    }

    //判断是否是满行
    public void deltetFullLine(){
        for (int y = Global.HEIGHT - 1; y > 0; y--){
            boolean full = true;
            for (int x = 0; x < Global.WIDTH; x++){
                //如果这一行有一列是空白
                if (obstacles[x][y] == 0){
                    full =false;
                }
            }
            if (full){
                delteLine(y);
                
            }
        }
    }

    private void delteLine(int lineNum) {
        for (int y = lineNum; y > 0; y--){
            for (int x = 0; x < Global.WIDTH; x++){
                obstacles[x][y] = obstacles[x][y-1];
            }
        }

        for (int x = 0; x < Global.WIDTH; x++){
            obstacles[x][0] = 0;
        }
    }

    //显示图形的方法
    public void drawMe(Graphics g) {
        System.out.println("显示图形的方法------Ground---drawMe");

        //方块落下后的颜色
        g.setColor(Color.yellow);

        //显示出障碍物，循环遍历障碍物的数组
        for (int x = 0; x < Global.WIDTH; x++){
            for (int y = 0; y < Global.HEIGHT; y++){
                if (obstacles[x][y] == 1){
                    g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
                }
            }
        }
    }

    //是否能够进行某个动作---主要是防止图像方块超出边界
    public boolean isMoveable(Shape shape, int action){
        //得到图形当前的位置信息
        int left = shape.getLeft();
        int top = shape.getTop();

        //得到最新的位置信息
        switch (action){
            case Shape.LEFT:
                left--;
                break;
            case Shape.RIGHT:
                left++;
                break;
            case Shape.DOWN:
                top++;
                break;
        }

        //判断这个点是否超出了显示区域
        for (int x = 0; x < 4; x++){
            for (int y = 0; y < 4; y++){
                //判断是否能超出了边框
                if (shape.isMember(x, y, action == Shape.ROTATE)){
                    if (top + y >= Global.HEIGHT || left + x < 0 || left + x >= Global.WIDTH || obstacles[left + x][top + y] == 1){
                        return  false;
                    }
                }
            }
        }
        return true;
    }

    public boolean ifFull(){

        for (int x = 0; x < Global.WIDTH; x++){
            if (obstacles[x][0] == 1){
                return true;
            }
        }
        return  false;

    }
}
