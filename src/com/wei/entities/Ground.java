package com.wei.entities;

import com.wei.util.Global;

public class Ground {

    //接收图形的方法
    public void sccept() {
        System.out.println("接收图形的方法");
    }

    //显示图形的方法
    public void drawMe() {
        System.out.println("显示图形的方法------Ground---drawMe");
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
                    if (top + y >= Global.HEIGHT || left + x < 0 || left + x >= Global.WIDTH){
                        return  false;
                    }
                }
            }
        }
        return true;
    }
}
