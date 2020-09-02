package com.survivecoding.tiltsensor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.view.View

class TiltView(context: Context?) : View(context) {

    private var cX: Float = 0f
    private var cY: Float = 0f

    private var xCoord: Float = 0f
    private var yCoord: Float = 0f

    private val blackPaint: Paint = Paint()
    private val greenPaint: Paint = Paint()

    init {
        blackPaint.style = Paint.Style.STROKE
        greenPaint.color = Color.GREEN
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        cX = w / 2f
        cY = h / 2f
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawLine(cX - 20, cY, cX + 20, cY, blackPaint)
        canvas?.drawLine(cX, cY + 20, cX, cY - 20, blackPaint)
        canvas?.drawCircle(cX, cY, 100f, blackPaint)
        canvas?.drawCircle(cX + xCoord, cY + yCoord, 100f, greenPaint)
    }

    fun onSensorEvent(event: SensorEvent) {
        yCoord = event.values[0] * 20
        xCoord = event.values[1] * 20
        invalidate()
    }
}