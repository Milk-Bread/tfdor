package com.example.xiaoyi.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by xiaoyi on 2016/11/15.
 */
public class PieChart extends View {
    private final static String TAG = PieChart.class.getSimpleName();

    private int backgroundColor  = Color.BLUE;
    private int progressColor = Color.DKGRAY;
    private float radius;
    private int gravity;
    private int progress;

    private Paint mPaint;
    private RectF mRectF;//用于定义圆弧的大小形状

    private float contentX = 0;
    private float contentY = 0;

    public static final int LEFT = 0;
    public static final int TOP = 1;
    public static final int CENTER = 2;
    public static final int RIGHT = 3;
    public static final int BOTTOM = 4;


    public PieChart(Context context) {
        super(context);
        initView();
    }

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initParams(context, attrs);
        initView();
    }

    public PieChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(context, attrs);
        initView();
    }

    public void initParams(Context context, @Nullable AttributeSet attrs) {
        initView();
        TypedArray typedArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.PieChart);

        if (typedArray != null) {
            backgroundColor = typedArray.getColor(R.styleable.PieChart_percent_background_color, Color.BLUE);
            progressColor = typedArray.getColor(R.styleable.PieChart_percent_progress_color, Color.DKGRAY);
            radius  = typedArray.getDimension(R.styleable.PieChart_percent_circle_radius, 0);
            gravity  = typedArray.getInt(R.styleable.PieChart_percent_circle_gravity, CENTER);
            progress  = typedArray.getInt(R.styleable.PieChart_percent_circle_progress, 0);
            typedArray.recycle();
        }
    }

    public void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mRectF = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                break;

            case MeasureSpec.EXACTLY:
                break;

            case MeasureSpec.UNSPECIFIED:
                break;

        }

        int width = getWidth();
        int height = getHeight();

        contentX = width/2;
        contentY = width/2;

        switch (gravity){
            case LEFT:
                contentX = radius + getPaddingLeft();
                break;
            case TOP:
                contentY = radius + getPaddingTop();
                break;
            case CENTER:
                break;
            case RIGHT:
                contentX = width - radius - getPaddingRight();
                break;
            case BOTTOM:
                contentY = height - radius - getPaddingBottom();
                break;
        }

        float left = contentX - radius;
        float top = contentY - radius;
        float right = contentX + radius;
        float bottom = contentY + radius;
        mRectF.set(left,top,right,bottom);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("onLayout","onLayout正常输出");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(backgroundColor);

        // FILL填充, STROKE描边,FILL_AND_STROKE填充和描边
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(contentX, contentY, radius, mPaint);
        mPaint.setColor(progressColor);

        double percent = progress * 1.0 / 100;
        int angle = (int) (percent * 360);
        canvas.drawArc(mRectF, 270, angle, true, mPaint);  //根据进度画圆弧
    }
}
