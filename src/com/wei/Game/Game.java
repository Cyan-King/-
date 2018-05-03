package com.wei.Game;

import com.wei.controllet.Controllet;
import com.wei.entities.Ground;
import com.wei.entities.ShapeFactory;
import com.wei.view.GamePanel;

import javax.swing.*;

public class Game {

    public static void main(String[] args){

        Ground ground = new Ground();
        ShapeFactory shapeFactory = new ShapeFactory();
        GamePanel gamePanel = new GamePanel();

        Controllet controllet = new Controllet(shapeFactory, ground, gamePanel);

        JFrame jFrame = new JFrame();
        //关闭一圈
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(gamePanel.getSize().width+20, gamePanel.getSize().height+50 );
        jFrame.add(gamePanel);
        //添加监听器
        jFrame.addKeyListener(controllet);
        gamePanel.addKeyListener(controllet);

        jFrame.setVisible(true);
        controllet.newGame();


    }
}
