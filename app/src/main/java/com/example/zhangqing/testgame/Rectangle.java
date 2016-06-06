package com.example.zhangqing.testgame;

/**
 * Created by zhangqing on 2016-05-08.
 */
public class Rectangle {
    private int positionX;//==random left(left position)
    private int positionY;
    private int width;
    private int height;
    String color;
    String text;


    public Rectangle(int width, int height, int positionX, int positionY){
        this.width = width;
        this.height = height;
        this.positionX = positionX;
        this.positionY = positionY;
        this.color = "";
        this.text = "";
    }

    public void setText(String text){this.text = text;}

    public String getText(){return text;}

    public int getWidth(){return width;}

    public int getHeight(){return height;}

//    public void setWidth(int width){this.width = width;}
//
//    public void setHeight(int height){this.height = height;}

    public int getPositionX(){return positionX;}

    public float getPositionY(){return positionY;}

//    public void setPositionX(int x){this.positionX = x;}
//
//    public void setPositionY(int y){this.positionY = y;}

    public void setColor(String color){this.color = color;}

    public String getColor(){return color;}

    public Boolean contains(int x, int y){
        return (x>this.getPositionX() && x<this.getPositionX()+this.getWidth() &&
        y>this.getPositionY() && y<this.getPositionY()+this.getHeight());
    }

}
