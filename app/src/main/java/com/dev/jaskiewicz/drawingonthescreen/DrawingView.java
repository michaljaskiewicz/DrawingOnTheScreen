package com.dev.jaskiewicz.drawingonthescreen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class DrawingView extends View {

    private static final int DEFAULT_PAINT_COLOR = 0xFF000000;
    private static final int SMALL_CIRCLE_RADIUS = 15;
    private Canvas canvas;
    private Bitmap canvasBitmap;
    private Path pathToDraw;
    private Paint paint;


    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    private void setupDrawing() {
        createPathToDraw();
        setupPaint();
    }

    private void createPathToDraw() {
        pathToDraw = new Path();
    }

    private void setupPaint() {
        paint = new Paint();
        paint.setColor(DEFAULT_PAINT_COLOR);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
    }

    /**
     * Metoda wywoywana jest za każdym razem, gdy zmieni się rozmiar widoku
     * Wywoływana jest także po utworzeniu widoku
     *
     * Tworzę tutaj bitmapę oraz powiązany z nią obiekt Canvas
     */
    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        canvasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(canvasBitmap);
    }

    /**
     * Metoda wywoływana po każdym wystąpieniu invalidate()
     * Rysuje nową ścieżkę na istniejącej już bitmapie
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(canvasBitmap, 0, 0, null);
        canvas.drawPath(pathToDraw, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pathToDraw.moveTo(touchX, touchY);
                drawCircleOnBitmap(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                pathToDraw.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                canvas.drawPath(pathToDraw, paint);
                pathToDraw.reset();
                drawCircleOnBitmap(touchX, touchY);
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    private void drawCircleOnBitmap(float positionX, float positionY) {
        canvas.drawCircle(positionX, positionY, SMALL_CIRCLE_RADIUS, paint);
    }

    public void setPaintColor(int color) {
        paint.setColor(color);
    }

    public void clear() {
        drawWhiteBackgroundOnCanvas();
    }

    private void drawWhiteBackgroundOnCanvas() {
        canvas.drawARGB(255, 255, 255, 255);
        invalidate();
    }
}
