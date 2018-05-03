package com.wei.controllet;

import com.wei.Listener.ShapeLister;
import com.wei.entities.Ground;
import com.wei.entities.Shape;
import com.wei.entities.ShapeFactory;
import com.wei.view.GamePanel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controllet extends KeyAdapter implements ShapeLister {

    private Shape shape;
    private Ground ground;
    private ShapeFactory shapeFactory;
    private GamePanel gamePanel;

    //键盘事件
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (ground.isMoveable(shape, Shape.ROTATE))
                    shape.rotate();
                break;
            case KeyEvent.VK_LEFT:
                if (ground.isMoveable(shape, Shape.LEFT))
                    shape.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                if (ground.isMoveable(shape, Shape.RIGHT))
                    shape.moveRight();
                break;
            case KeyEvent.VK_DOWN:
                if (isShapeMoveable(shape))
                    shape.moveDown();
                break;
        }

        //调用显示的组件
        gamePanel.disPlay(shape, ground);

    }

    //不停的下落
    @Override
    public void shapeMoveDown(Shape shape) {
        gamePanel.disPlay(shape, ground);
    }

    //监听界面的边框底部的触底事件
    @Override
    public synchronized boolean isShapeMoveable(Shape shape) {

        if (this.shape != shape){
            return  false;
        }

        //如果到底的话就执行isMoveable方法
        if (ground.isMoveable(shape, Shape.DOWN)){
            return true;
        }
        //然后再执行acept()方法---再Ground里面接收一个图像
        ground.accept(this.shape);
        if (!ground.ifFull()){
            //然后再次生产一个图像
            this.shape = shapeFactory.getShape(this);
        }
        return false;
    }

    //创建一个新的界面
    public void newGame() {
        shape = shapeFactory.getShape(this);
    }

    //控制层的构造方法
    public Controllet(ShapeFactory shapeFactory, Ground ground, GamePanel gamePanel) {
        this.shapeFactory = shapeFactory;
        this.ground = ground;
        this.gamePanel = gamePanel;
    }
}
