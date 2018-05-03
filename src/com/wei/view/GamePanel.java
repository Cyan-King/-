package com.wei.view;

import com.wei.Listener.ShapeLister;
import com.wei.entities.Ground;
import com.wei.entities.Shape;
import com.wei.entities.ShapeFactory;
import com.wei.util.Global;

import javax.swing.*;
import java.awt.*;

/*
* 游戏显示页面
* */
public class GamePanel extends JPanel {

    private Shape shape;
    private Ground ground;

    public void disPlay(Shape shape, Ground ground){
        System.out.println("display()--------GamePanel");
        //重绘
        this.ground = ground;
        this.shape = shape;
        //重绘的方法
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        //背景颜色
        g.setColor(Color.white);
        //覆盖
        g.fillRect(0, 0, Global.CELL_SIZE * Global.HEIGHT, Global.CELL_SIZE * Global.WIDTH);


        //重新显示----显示障碍物的信息
        if (shape != null && ground != null){
            ground.drawMe();
            shape.drawMe(g);
        }
    }

    public GamePanel(){
        this.setSize(Global.CELL_SIZE * Global.HEIGHT, Global.CELL_SIZE * Global.WIDTH);
    }
}
