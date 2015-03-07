
//Touch/app/src/main/java/edu/nyu/scps/touch/TouchView.java

        package com.example.dan.imagetouch;

        import android.content.Context;
        import android.content.res.Resources;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.graphics.Color;
        //import android.graphics.Paint;
        import android.graphics.Matrix;
        import android.graphics.PointF;
        import android.view.MotionEvent;
        import android.view.View;
        //import android.widget.RelativeLayout;


/**
 * TODO: document your custom view class.
 */
public class TouchView extends View {
    private PointF p = new PointF();	//holds 2 floats
    //private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean first = true;

    public TouchView(Context context) {
        super(context);
       // paint.setColor(Color.RED);
       // paint.setStyle(Paint.Style.FILL);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //Put center of circle where finger touched.
                        p.set(event.getX(), event.getY());
                        invalidate();    //call onDraw method of TouchView
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        //Put center of drawable where finger touched.
                        p.set(event.getX(), event.getY());
                        invalidate();	//call onDraw method of TouchView
                        return true;	//do nothing else

                    default:
                        return false;
                }
            }
        });
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        p.set(getWidth() / 2f, getHeight() / 2f);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int width = getWidth();
        final int height = getHeight();
        if(first)
        {
            first = false;
            p.set(width/2, height/2);
        }

        //float radius = .1f * Math.min(width, height);

        canvas.drawColor(Color.WHITE);	//background
        //canvas.drawCircle(p.x, p.y, radius, paint);
        Resources resources = getResources();
        Bitmap bitMap = BitmapFactory.decodeResource(resources, R.drawable.basketball);
        int w = bitMap.getWidth();
        int h = bitMap.getHeight();
       

        //coordinates of upper left corner of BitMap, paint is null
        canvas.drawBitmap(bitMap, p.x - w/2 , p.y - h/2, null);

    }
}

