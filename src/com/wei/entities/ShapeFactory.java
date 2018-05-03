package com.wei.entities;

import com.wei.Listener.ShapeLister;

import java.util.Random;

/*
 * 图形工厂
 * */
public class ShapeFactory {

    private int shapes[][][] = new int[][][]{
            {
                    {1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
            }
    };

    //生产图形的方法
    public Shape getShape(ShapeLister shapeLister) {
        System.out.println("生产图形");

        Shape shape = new Shape();
        shape.addShapeListener(shapeLister);
        int type = new Random().nextInt(shapes.length);
        //图形的类型
        shape.setBody(shapes[type]);
        //图形的状态
        shape.setStatus(0);
        return shape;
    }
}