package com.example.carl.animationclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {


    private ImageView imageView;
    private int l;
    private int r;
    private int t;
    private int b;
    private static TranslateAnimation animation = new TranslateAnimation(400,
            -400, 0, 0);;

    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = getLayoutInflater().inflate(R.layout.activity_main, null,
                true);

        imageView = (ImageView) view.findViewById(R.id.testImage);

        view.setOnTouchListener(onTouchListener);
        this.setContentView(view);

        animation.setDuration(4000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setInterpolator(new LinearInterpolator());

        imageView.setAnimation(animation);

        date = new Date();
        imageView.startAnimation(animation);
    }

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            /**
             * Projection 用于将屏幕坐标转换为地理位置坐标
             */
            float dx = event.getX();
            float dy = event.getY();
            l = imageView.getLeft();
            t = imageView.getTop();
            r = imageView.getRight();
            b = imageView.getBottom();



            Date time = new Date();
            long a = time.getTime() - date.getTime();

            boolean isTouch = isTouch(l, r, t, b, dx, dy, (int)a);

            Toast.makeText(MainActivity.this, "isTouch:" + isTouch, 0).show();

            return false;
        }
    };

    private boolean isTouch(int l, int r, int t, int b, float dx, float dy,
                            int time) {
        float scale = ((float)(time % 4000))/4000;
        dx = dx - (int)((220 * (1-scale)) + 0.5 * (r - l));

        return isTouch(l, r, t, b, dx, dy);
    }

    private boolean isTouch(int l, int r, int t, int b, float dx, float dy) {
        if (l < dx && dx < r && t < dy && dy < b) {
            return true;
        }
        return false;
    }


}
