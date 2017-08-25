package xyz.romakononovich.battleship;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by romank on 24.08.17.
 */

public class CompasView extends View {
    private Paint mCompasPaint;
    private int mCustomColorEmpty;

    public CompasView(Context context) {
        super(context);
        init(null);

    }

    public CompasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CompasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CompasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }


    private void init(@Nullable AttributeSet attrs){

        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CompasView,
                0, 0);

        try {
            mCustomColorEmpty = a.getInteger(R.styleable.CompasView_custom_color_compas, Color.DKGRAY);
        } finally {
            a.recycle();
        }
        invalidate();
        createPaint();
        //setOnTouchListener(this);
    }

    private void createPaint() {

        mCompasPaint = new Paint();
        mCompasPaint.setColor(mCustomColorEmpty);
        mCompasPaint.setStrokeWidth(getContext().getResources().getDimension(R.dimen.line_stroke));
        mCompasPaint.setStyle(Paint.Style.STROKE);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,getWidth(),getHeight(),mCompasPaint);
        canvas.drawLine(getWidth()/2,0,getWidth()/4,getHeight(),mCompasPaint);
        canvas.drawLine(getWidth()/2,0,getWidth()/4*3,getHeight(),mCompasPaint);
        canvas.drawLine(getWidth()/2,0,getWidth()/4*3,getHeight(),mCompasPaint);
    }
}
