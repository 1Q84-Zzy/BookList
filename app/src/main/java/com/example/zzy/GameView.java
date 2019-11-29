package com.example.zzy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.example.casper.R;

import java.util.ArrayList;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder;
    private DrawThread drawThread;
    private ArrayList<Sprite> sprites=new ArrayList<>();
    public GameView(Context context) {
        super(context);
        surfaceHolder=this.getHolder();
        surfaceHolder.addCallback(this); //设置其callback回调对象为自己

        sprites.add(new Sprite(R.drawable.book_icon));
        sprites.add(new Sprite(R.drawable.book_icon));
        sprites.add(new Sprite(R.drawable.book_icon));
        sprites.add(new Sprite(R.drawable.book_icon));
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(null==drawThread){
            drawThread=new DrawThread();
            drawThread.start();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(null!=drawThread)
        {
            drawThread.stopThread();
            drawThread=null;
        }
    }

    private class DrawThread extends Thread{
        private boolean beAlive=false;//用于控制线程结束

        public void stopThread(){
            beAlive=false;
            while (true){
                try {
                    this.join();//保证run方法执行完毕
                    break;
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        public void run(){
            beAlive=true;
            while (beAlive){ //使函数永久执行，直到beAlive在其他线程被设置为false
                Canvas canvas=null;
                try {
                    synchronized (surfaceHolder) {
                        canvas = surfaceHolder.lockCanvas(); //同步锁定
                        canvas.drawColor(Color.WHITE); //获取画布对象 用白色填充

                        Paint paint=new Paint();
                        paint.setTextSize(50);
                        paint.setColor(Color.BLACK);
                        canvas.drawText("Hello",40,40,paint);//输出黑色的文字“Hello”
                        for(int i=0;i<sprites.size();i++)sprites.get(i).move();//让所有精灵移动
                        for(int i=0;i<sprites.size();i++)sprites.get(i).draw(canvas);//让所有精灵画图
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (null != surfaceHolder) {
                        surfaceHolder.unlockCanvasAndPost(canvas);//通知界面更新
                    }
                }
                try{
                    Thread.sleep(10); //线程休眠10ms，之后开始下一轮刷新
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }



        }
    }

    private class Sprite {
        private int resouceId;
        private int x,y;
        private double direction;
        public Sprite(int book_icon) {
            this.resouceId=resouceId;
            x=(int)(Math.random()*getWidth());
            y=(int)(Math.random()*getHeight());
            direction=Math.random()*2*Math.PI;
        }
        public void move()
        {
            x+=15*Math.cos(direction);
            y+=15*Math.sin(direction);
            if(x<0) x+=GameView.this.getWidth();
            if(x>GameView.this.getWidth())x-=GameView.this.getWidth();
            if(y<0) y+=GameView.this.getHeight();
            if(y>GameView.this.getHeight())y-=GameView.this.getHeight();

            if(Math.random()<0.05)direction=Math.random()*2*Math.PI;
        }
        public void draw(Canvas canvas)
        {
            Drawable drawable=getContext().getResources().getDrawable(R.drawable.book_icon);
            Rect drawableRect=new Rect(x,y,x+drawable.getIntrinsicWidth(),y+drawable.getIntrinsicHeight());
            drawable.setBounds(drawableRect);
            drawable.draw(canvas);
        }
    }
}
