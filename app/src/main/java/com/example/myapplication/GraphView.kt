package com.example.myapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.view.View

class GraphView(context: Context, private val nodePositions: List<PointF>) : View(context) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = 0xff0000ff.toInt()  // Color of nodes
        style = Paint.Style.FILL
    }

    private val radius = 10f  // Radius of nodes

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (nodePosition in nodePositions) {
            canvas.drawCircle(nodePosition.x, nodePosition.y, radius, paint)
        }
    }
}
