package com.example.ch9_ex1;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
        setTitle("20154010 이병준 직접풀기9-1");
    }

    private class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint=new Paint();
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(50);

            canvas.drawLine(70,70,370,70,paint);

            paint.setStrokeCap(Paint.Cap.ROUND);
            Rect f=new Rect(70,140,370,140);

            canvas.drawOval(new RectF(70, 210, 70 + 300, 210 + 140), paint);

            canvas.drawArc(new RectF(70,280,70+300,280+210),35,100,true,paint);

            Rect rect1=new Rect(100,600,100+200,600+200);
            paint.setColor(Color.BLUE);
            canvas.drawRect(rect1,paint);

            Rect rect2=new Rect(100+60,600+60,100+60+200,600+60+200);
            paint.setColor(Color.argb(120,255,0,0));
            canvas.drawRect(rect2,paint);

        }
    }
}