package com.cookbook.advance.customComponent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

public class myButton extends Button {
    private Paint mTextPaint, mPaint;
    private String mText;
    private int mAscent;
    private Shader mShader;
    private Matrix  mMatrix = new Matrix();
    private float mStart;
    private float mSweep;
    private float mRotate;
    private static final float SWEEP_INC = 2;
    private static final float START_INC = 15;
    public myButton(Context context) {
        super(context);
        initLabelView();
    }

    public myButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLabelView();
    }

    private final void initLabelView() {
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(16);
        mTextPaint.setColor(0xFF000000);
        setPadding(15, 15, 15, 15);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(4);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mShader = new SweepGradient(this.getMeasuredWidth()/2, this.getMeasuredHeight()/2, new int[] { Color.GREEN,
                Color.RED,                
                Color.CYAN,Color.DKGRAY }, null);
        mPaint.setShader(mShader);
    }

    public void setText(String text) {
        mText = text;
        requestLayout();
        invalidate();
    }

    public void setTextSize(int size) {
        mTextPaint.setTextSize(size);
        requestLayout();
        invalidate();
    }

    public void setTextColor(int color) {
        mTextPaint.setColor(color);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text
            result = (int) mTextPaint.measureText(mText) + getPaddingLeft()
                    + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        mAscent = (int) mTextPaint.ascent();
        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
        	Log.v("Messure Height", "spec Height:"+specSize);
            result = specSize;
        } else {
            // Measure the text (beware: ascent is a negative number)
            result = (int) (-mAscent + mTextPaint.descent()) + getPaddingTop() + getPaddingBottom();
        	Log.v("Messure Height", "result Height:"+result);
            if (specMode == MeasureSpec.AT_MOST) {
            	Log.v("Messure Height", "At most Height:"+specSize);
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
    private void drawArcs(Canvas canvas, RectF oval, boolean useCenter,
            Paint paint) {
    	canvas.drawArc(oval, mStart, mSweep, useCenter, paint);
    }

    @Override protected void onDraw(Canvas canvas) {
    
    	mMatrix.setRotate(mRotate, this.getMeasuredWidth()/2, this.getMeasuredHeight()/2);
        mShader.setLocalMatrix(mMatrix);
        mRotate += 3;
        if (mRotate >= 360) {
            mRotate = 0;
        }
		RectF drawRect = new RectF();
		drawRect.set(this.getWidth()-mTextPaint.measureText(mText),(this.getHeight()-mTextPaint.getTextSize())/2, mTextPaint.measureText(mText), this.getHeight()-(this.getHeight()-mTextPaint.getTextSize())/2);
        drawArcs(canvas,drawRect,false,
                 mPaint);       
        mSweep += SWEEP_INC;
        if (mSweep > 360) {
            mSweep -= 360;
            mStart += START_INC;
            if (mStart >= 360) {
                mStart -= 360;
                
            }
        }
        if(mSweep >180){
        	canvas.drawText(mText, getPaddingLeft(), getPaddingTop() -mAscent, mTextPaint);
        }
        invalidate();
    }

}
