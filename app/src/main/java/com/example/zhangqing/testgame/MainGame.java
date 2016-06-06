package com.example.zhangqing.testgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;


/**
 * Created by zhangqing on 2016-05-07.
 */
public class MainGame extends Activity{
    DrawRectangle newDrawRect = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        init();
    }

    private void init(){
        newDrawRect = new DrawRectangle(this);
        setContentView(newDrawRect);
    }
}
