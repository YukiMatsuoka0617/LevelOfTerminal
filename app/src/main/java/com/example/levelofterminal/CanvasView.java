package com.example.levelofterminal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CanvasView extends View {
    private final Paint paint;

    private float SensorX;
    private float SensorY;
    private float SensorZ;

    public CanvasView(Context context) {
        super(context);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.argb(255, 255, 255, 255));
        setDraw(canvas);
    }

    void setDraw(Canvas canvas) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = 100;
        float positionX = transformMaxAndMin(SensorX, 10, -10, 0, getWidth() - radius);
        float positionY = transformMaxAndMin(SensorY, 10, -10, getHeight() - radius, 0);

        drawCircle(paint, canvas, 128, 255, 0, 0, 10, positionX, positionY, radius);
        drawCross(paint, canvas, 128, 255, 0, 0, 10, positionX, positionY);

        drawCircle(paint, canvas, 255, 0, 0, 255, 10, centerX, centerY, radius);
        drawCross(paint, canvas, 255, 0, 0, 255, 10, centerX, centerY);

        drawLetter(paint, canvas, 255, 0, 0, 0, 1, 0, 100);

    }

    void drawCircle(Paint paint, Canvas canvas, int alpha, int r, int g, int b, int StrokeWidth, float x, float y, int radius) {
        paint.setColor(Color.argb(alpha, r, g, b));
        paint.setStrokeWidth(StrokeWidth);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(x, y, radius, paint);

    }

    void drawCross(Paint paint, Canvas canvas, int alpha, int r, int g, int b, int StrokeWidth, float x, float y) {
        paint.setColor(Color.argb(alpha, r, g, b));
        paint.setStrokeWidth(StrokeWidth);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        int length = 50;
        canvas.drawLine(x - length, y, x + length, y, paint);
        canvas.drawLine(x, y - length, x, y + length, paint);

    }

    void drawLetter(Paint paint, Canvas canvas, int alpha, int r, int g, int b, int StrokeWidth, float x, float y) {
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(StrokeWidth);
        paint.setTextSize(50);
        paint.setColor(Color.argb(alpha, r, g, b));
        canvas.drawText("Sensorx: " + String.valueOf(SensorX), x, y, paint);
        canvas.drawText("Sensory: " + String.valueOf(SensorY), x, y + 50, paint);

    }

    public void invalidate(float sensorX, float sensorY, float sensorZ) {
        SensorX = sensorX;
        SensorY = sensorY;
        SensorZ = sensorZ;

        invalidate();
    }

    public float transformMaxAndMin(float input, int inputMax, int inputMin, int transMax, int transMin) {
        return ((input - inputMin) / (inputMax - inputMin)) * (transMax - transMin) + transMin;
    }

}
