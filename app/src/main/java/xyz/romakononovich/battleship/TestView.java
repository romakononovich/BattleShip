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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import xyz.romakononovich.battleship.model.Cell;

public class TestView extends View implements View.OnTouchListener{
    private static final String TAG = TestView.class.getSimpleName();
    private int mCustomColorEmpty;
    private Paint mPaintEmpty;
    private Cell[][] mCells;
    private Paint mPaintShip;
    private int mCustomColorShip;
    private Paint mPaintShipStroke;
    private int columnWidth;

    public int getCustomColor() {
        return mCustomColorEmpty;
    }

    public void setCustomColor(int mCustomColor) {
        this.mCustomColorEmpty = mCustomColor;
        createPaint();
        invalidate();
    }

    public void assignGameField(Cell[][] cells){
        mCells = cells;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        columnWidth = getWidth()/10;
        if (mCells!=null){
            for (int i=0; i<mCells.length;i++){
                for (int j = 0; j<mCells[i].length;j++) {
                    Cell cell = mCells[i][j];
                    if (cell !=null) {
                        canvas.drawRect(i*columnWidth,j*columnWidth,i*columnWidth+columnWidth,
                                j*columnWidth+columnWidth, mPaintShip);
                        canvas.drawRect(i*columnWidth,j*columnWidth,i*columnWidth+columnWidth,
                                j*columnWidth+columnWidth, mPaintShipStroke);
                    } else {
                        canvas.drawRect(i*columnWidth,j*columnWidth,i*columnWidth+columnWidth,
                                j*columnWidth+columnWidth, mPaintEmpty);
                    }
                }
//                canvas.drawLine(columnWidth*i, 0, columnWidth*i, getHeight(), mPaintEmpty);
//                canvas.drawLine(0, columnWidth*i, getWidth(), columnWidth*i, mPaintEmpty);


            }
            canvas.drawLine(0, 0, getWidth(), 0, mPaintEmpty);
            canvas.drawLine(getWidth(), 0, getWidth(), getHeight(), mPaintEmpty);
            canvas.drawLine(getWidth(), getHeight(), 0, getHeight(), mPaintEmpty);
            canvas.drawLine(0, getHeight(), 0, 0, mPaintEmpty);
        }

//        float startX = getWidth()/2;
//        float startY = 0;
//        float endX = getWidth()/2;
//        float endY = getHeight();
//        canvas.drawLine(startX,startY,endX,endY,paint);
//        Log.d(TAG, "width: "+canvas.getWidth()+"\n"+"height: "+canvas.getHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    public TestView(Context context) {
        super(context);
        init(null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs){

        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TestView,
                0, 0);

        try {
            mCustomColorEmpty = a.getInteger(R.styleable.TestView_custom_color, Color.DKGRAY);
            mCustomColorShip = a.getInteger(R.styleable.TestView_custom_color, Color.BLACK);
        } finally {
            a.recycle();
        }
        createPaint();
        setOnTouchListener(this);
    }

    private void createPaint() {
        mPaintEmpty = new Paint();
        mPaintEmpty.setColor(mCustomColorEmpty);
        mPaintEmpty.setStrokeWidth(getContext().getResources().getDimension(R.dimen.line_stroke));
        mPaintEmpty.setStyle(Paint.Style.STROKE);

        mPaintShip = new Paint();
        mPaintShip.setColor(mCustomColorShip);
        mPaintShip.setStyle(Paint.Style.FILL);
        mPaintShipStroke = new Paint();
        mPaintShipStroke.setColor(mCustomColorEmpty);
        mPaintShipStroke.setStyle(Paint.Style.STROKE);

    }

    public void logSize() {
        Log.d(TAG, "onMeasure: width: "+getWidth()+"\n"+"height: "+getHeight());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d(TAG, "onTouch: "+ event.getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                float x = event.getX();
                float y = event.getY();
                Log.d(TAG, "onTouch coordinates: "+x+y);
                columnWidth = getWidth()/10;
                int column = (int) (x/columnWidth+1);
                int row = (int) (y/columnWidth+1);
                char ch = (char) (64+row);
                Log.d(TAG, "cell: " + column+" "+ch);

                break;
        }
        return true;
    }
}
