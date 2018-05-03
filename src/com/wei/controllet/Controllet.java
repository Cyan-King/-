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
                if (ground.isMoveable(shape, Shape.DOWN))
                    shape.moveDown();
                break;
        }

        //调用显示的组件
        gamePanel.disPlay(shape, ground);

    }

    @Override
    public void shapeMoveDown(Shape shape) {
        gamePanel.disPlay(shape, ground);
    }

    @Override
    public boolean isShapeMoveable(Shape shape) {
        boolean moveable = ground.isMoveable(shape, Shape.DOWN);
        return moveable;
    }

    public void newGame() {
        shape = shapeFactory.getShape(this);
    }

    public Controllet(ShapeFactory shapeFactory, Ground ground, GamePanel gamePanel) {
        this.shapeFactory = shapeFactory;
        this.ground = ground;
        this.gamePanel = gamePanel;
    }
}
