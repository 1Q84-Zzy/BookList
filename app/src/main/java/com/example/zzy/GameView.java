package com.example.zzy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.casper.R;

import java.util.ArrayList;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder;
    private DrawThread drawThread;
    private ArrayList<Sprite> sprites = new ArrayList<>();
    float xTouch = -1, yTouch = -1;


    public GameView(Context context) {
        super(context);
        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this); //设置其callback回调对象为自己

        sprites.add(new Sprite(R.drawable.book_icon));
        sprites.add(new Sprite(R.drawable.book_icon));
        sprites.add(new Sprite(R.drawable.book_icon));
        sprites.add(new Sprite(R.drawable.book_icon));


        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                xTouch = event.getX();
                yTouch = event.getY();
                return false;
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (null == drawThread) {
            drawThread = new DrawThread();
            drawThread.start();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (null != drawThread) {
            drawThread.stopThread();
            drawThread = null;
        }
    }

    private class DrawThread extends Thread {
        private Boolean beAlive = true;

        public void stopThread() {
            beAlive = false;
        }

        @Override
        public void run() {
            while (beAlive) {
                try {
                    //获得绘画的画布
                    Canvas canvas = surfaceHolder.lockCanvas();
                    //背景设成白色
                    canvas.drawColor(Color.WHITE);

                    //在20，40的地方画一个文本hello world!
                    Paint p = new Paint();
                    p.setTextSize(50);
                    p.setColor(Color.BLACK);

                    if (xTouch > 0) {
                        canvas.drawText("你击中了！", 20, 40, p);
                    } else
                        canvas.drawText("你还没击中目标！", 20, 40, p);

                    for (Sprite sprite : sprites) {
                        sprite.move();
                    }

                    for (int i = 0; i < sprites.size(); i++) sprites.get(i).move();//让所有精灵移动
                    for (int i = 0; i < sprites.size(); i++) sprites.get(i).draw(canvas);//让所有精灵画图

                    surfaceHolder.unlockCanvasAndPost(canvas);//解锁
                    Thread.sleep(20);
                } catch (Exception e) {
                }
            }
        }
    }

    private class Sprite {
        private int resouceId;
        private int x, y;
        private double direction;

        public Sprite(int book_icon) {
            this.resouceId = resouceId;
            x = (int) (Math.random() * getWidth());
            y = (int) (Math.random() * getHeight());
            direction = Math.random() * 2 * Math.PI;
        }

        public void move() {
            x += 15 * Math.cos(direction);
            y += 15 * Math.sin(direction);
            if (x < 0) x += GameView.this.getWidth();
            if (x > GameView.this.getWidth()) x -= GameView.this.getWidth();
            if (y < 0) y += GameView.this.getHeight();
            if (y > GameView.this.getHeight()) y -= GameView.this.getHeight();

            if (Math.random() < 0.05) direction = Math.random() * 2 * Math.PI;
        }

        public void draw(Canvas canvas) {
            Drawable drawable = getContext().getResources().getDrawable(R.drawable.book_icon);
            Rect drawableRect = new Rect(x, y, x + drawable.getIntrinsicWidth(), y + drawable.getIntrinsicHeight());
            drawable.setBounds(drawableRect);
            drawable.draw(canvas);
        }
                }
                }

