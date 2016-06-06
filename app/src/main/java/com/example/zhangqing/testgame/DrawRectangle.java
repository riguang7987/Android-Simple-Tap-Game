package com.example.zhangqing.testgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Created by zhangqing on 2016-05-07.
 */
public class DrawRectangle extends View{

    private Random randomGenerator1 = new Random();//for rectangle position
    private List<Rectangle> rectangles = new ArrayList<Rectangle>();
    private int count = 0;
    private int randomLeft,randomTop;
    private String colors[];
    private String text[];
    private Random randomGenerator2 = new Random();//for color;
    private Paint randomColor;
    private Rect redRect = new Rect();
    private int score = 0;
    private static final float MYTEXTSIZE = 50.0f;
    private int textSizePx;
    private Paint randomText;
    private Random randomGenerator3 = new Random();
    private List<Integer> ranTops = new ArrayList<Integer>();
    private List<Integer> ranLefts = new ArrayList<Integer>();


    public DrawRectangle(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        if(count == 0 || count == 1) {
                drawNewRect(canvas);
        } else if(count == 2){

            final float scale = getResources().getDisplayMetrics().density;

            textSizePx = (int) (MYTEXTSIZE * scale + 0.5f);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setSubpixelText(true);
            paint.setTextSize(textSizePx);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("Score " + score, canvas.getWidth()/2, canvas.getHeight()/2, paint);
        }
    }

    public void drawNewRect(Canvas canvas){
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int rectangleWidth = width / 5;//Width of a rectangle
        int rectangleHeight = height / 15; //height of a rectangle
        if(count == 0) {
            for (int i = 0; i < 5; i++) {
                randomLeft = randomGenerator1.nextInt(width - rectangleWidth);
                ranLefts.add(randomLeft);
                randomTop = randomGenerator1.nextInt(height - rectangleHeight);
                ranTops.add(randomTop);
                //color for rectangle
                colors = new String[]{"red", "black", "blue", "green", "yellow"};
                int randomColorIndex = randomGenerator2.nextInt(colors.length);
                randomColor = new Paint();
                randomColor.setColor(Color.parseColor(colors[randomColorIndex]));
                randomColor.setStyle(Paint.Style.FILL);
                //for the text on top
                text = new String[]{"RED","BLACK","BLUE","GREEN","YELLOW"};
                randomText = new Paint();
                randomText.setTextAlign(Paint.Align.CENTER);
                int randomTextIndex = randomGenerator3.nextInt(text.length);
                int randomTextColor = randomGenerator1.nextInt(colors.length);
                while(randomTextIndex == randomTextColor || randomColorIndex == randomTextColor){
                    randomTextColor = randomGenerator1.nextInt(colors.length);
                }
                randomText.setColor(Color.parseColor(colors[randomTextColor]));
                randomText.setTextSize(rectangleWidth/5);


                Rectangle newRectangle = new Rectangle(rectangleWidth, rectangleHeight, randomLeft, randomTop);
                newRectangle.setColor(colors[randomColorIndex]);
                newRectangle.setText(text[randomTextIndex]);
                rectangles.add(newRectangle);
                redRect.set(randomLeft, randomTop, randomLeft + rectangleWidth, randomTop + rectangleHeight);
                canvas.drawRect(redRect, randomColor);
                canvas.drawText(text[randomTextIndex],randomLeft+rectangleWidth/2,
                        randomTop+rectangleHeight/2, randomText);
            }
        }
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                invalidate();
                rectangles.clear();
                ranLefts.clear();
                ranTops.clear();
            }
        }, 3000);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        final float touchX = event.getX();
        final float touchY = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for(Rectangle newRectangle: rectangles){
                    if(newRectangle.contains((int)touchX, (int) touchY)){
                        if(newRectangle.getText() == "RED"){
                            score += 1;
                        } else {
                            count = 2;
                        }
                    }
                }
                break;
        }
        return true;
    }
}