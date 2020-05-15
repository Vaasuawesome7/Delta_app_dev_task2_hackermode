package com.example.dotsandboxes.smileyAnim;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.dotsandboxes.R;

public class smileyAnimMove extends View {
    private Bitmap smiley;
    private int x, y;

    public smileyAnimMove(Context context) {
        super(context);
        smiley = BitmapFactory.decodeResource(getResources(), R.drawable.champion);
        x = getWidth()/2; y = 0;
    }

    public smileyAnimMove(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        smiley = BitmapFactory.decodeResource(getResources(), R.drawable.champion);
    }

    public smileyAnimMove(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        smiley = BitmapFactory.decodeResource(getResources(), R.drawable.champion);
    }

    public smileyAnimMove(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        smiley = BitmapFactory.decodeResource(getResources(), R.drawable.champion);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}