package com.tyrese.deviceinfo;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInfo = (TextView) findViewById(R.id.info);
        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();
        float widthDp = dm.widthPixels/dm.scaledDensity;
        float heightDp = dm.heightPixels/dm.scaledDensity;
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;//第英寸像素数
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getRealSize(point);

        mInfo.setText("dpi : " + dm.densityDpi + "\nwidthDp : " + (int) widthDp  + "\nheightDp : " + (int) heightDp
        + "\nwidth : " + width + "\nheight : " + height
        + "\nxdpi : " + xdpi + "\nydpi : " + ydpi);

        mInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rect rectangle = new Rect();
                Window window = getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
                final int statusBarHeight = rectangle.top;
                mInfo.append("\nstatusbar height : " + statusBarHeight);
            }
        });

        //用户在点击返回退出应用后，这个还会执行。
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while (count < 300) {
                    count ++;
                    try {
                        TimeUnit.MILLISECONDS.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("count : " + count);
                }
            }
        }).start();

    }
}
