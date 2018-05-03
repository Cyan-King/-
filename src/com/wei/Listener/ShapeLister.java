package com.wei.Listener;

import com.wei.entities.Shape;

//凸显监听
public interface ShapeLister {

    //图形下落,事件源是shape图形
    void shapeMoveDown(Shape shape);

    //监听事件。下落的界限
    boolean isShapeMoveable(Shape shape);
}
