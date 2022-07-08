package com.rundgrun.watchermobile.presentation.wagons.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import com.rundgrun.watchermobile.R
import java.lang.Integer.max


class HeadWagonView @JvmOverloads constructor(
    context: Context,
    attributesSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStileRes: Int = 0
) : View(context, attributesSet, defStyleAttr, defStileRes) {

    private var isLeadingWagon = true

    private var wlcStatus: Status = Status.UNKNOWN
    private var apStatus: Status = Status.UNKNOWN
    private var gi1Status: Status = Status.UNKNOWN
    private var gi2Status: Status = Status.UNKNOWN
    private var gi3Status: Status = Status.UNKNOWN
    private var gi4Status: Status = Status.UNKNOWN
    private var gi5Status: Status = Status.UNKNOWN
    private var gi6Status: Status = Status.UNKNOWN
    private var gi7Status: Status = Status.UNKNOWN
    private var gi8Status: Status = Status.UNKNOWN
    private var channelStatus: Status = Status.UNKNOWN


    private val wagonRect = RectF(0f, 0f, 0f, 0f)
    private val wlcRect = RectF(0f, 0f, 0f, 0f)
    private val apRect = RectF(0f, 0f, 0f, 0f)
    private val gi1Rect = RectF(0f, 0f, 0f, 0f)
    private val gi2Rect = RectF(0f, 0f, 0f, 0f)
    private val gi3Rect = RectF(0f, 0f, 0f, 0f)
    private val gi4Rect = RectF(0f, 0f, 0f, 0f)
    private val gi5Rect = RectF(0f, 0f, 0f, 0f)
    private val gi6Rect = RectF(0f, 0f, 0f, 0f)
    private val gi7Rect = RectF(0f, 0f, 0f, 0f)
    private val gi8Rect = RectF(0f, 0f, 0f, 0f)
    private val channelRect = RectF(0f, 0f, 0f, 0f)

    private val wagonPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN
        style = Paint.Style.STROKE
        strokeWidth =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f, resources.displayMetrics)
        pathEffect = CornerPathEffect(15.0f)
    }
    private var wagonPath = Path().apply {
        moveTo(wagonRect.left + (wagonRect.right * 0.18f), wagonRect.top)
        lineTo(wagonRect.right, wagonRect.top)
        lineTo(wagonRect.right, wagonRect.bottom)
        lineTo(wagonRect.left, wagonRect.bottom)
        lineTo(wagonRect.left, wagonRect.top + ((wagonRect.bottom - wagonRect.top) / 2))
        close()
    }

    private val elementPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN
        style = Paint.Style.FILL_AND_STROKE
        strokeWidth =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, resources.displayMetrics)
    }
    private val channelPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, resources.displayMetrics)
    }
    private val wlcApTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.FILL_AND_STROKE
        textSize = 18f
        textAlign = Paint.Align.CENTER
        strokeWidth =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.5f, resources.displayMetrics)
    }
    private val portTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.FILL_AND_STROKE
        textSize = 14f
        textAlign = Paint.Align.CENTER
        strokeWidth =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.5f, resources.displayMetrics)
    }

    init {
        initAttributes(attributesSet, defStyleAttr, defStileRes)
    }

    private fun initAttributes(attributesSet: AttributeSet?, defStyleAttr: Int, defStileRes: Int) {
        val typedArray = context.obtainStyledAttributes(
            attributesSet,
            R.styleable.HeadWagonView,
            defStyleAttr,
            defStileRes
        )
        isLeadingWagon = typedArray.getBoolean(R.styleable.HeadWagonView_isLeadingWagon, true)
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        drawWagon(canvas)
        drawWlc(canvas)
        drawAp(canvas)
        drawPort(canvas, gi1Rect, gi1Status, "Gi1")
        drawPort(canvas, gi2Rect, gi2Status, "Gi2")
        drawPort(canvas, gi3Rect, gi3Status, "Gi3")
        drawPort(canvas, gi4Rect, gi4Status, "Gi4")
        drawPort(canvas, gi5Rect, gi5Status, "Gi5")
        drawPort(canvas, gi6Rect, gi6Status, "Gi6")
        drawPort(canvas, gi7Rect, gi7Status, "Gi7")
        drawPort(canvas, gi8Rect, gi8Status, "Gi8")
        drawChannel(canvas)
    }

    private fun drawWagon(canvas: Canvas) {

        canvas.drawRGB(0, 0, 0)
        canvas.drawPath(wagonPath, wagonPaint)
    }

    private fun drawWlc(canvas: Canvas) {
        elementPaint.color = ContextCompat.getColor(context, wlcStatus.color)
        canvas.drawRoundRect(
            wlcRect,
            5f,
            5f,
            elementPaint
        )
        canvas.drawText(
            "WLC",
            wlcRect.left + (wlcRect.right - wlcRect.left) / 2,
            wlcRect.top + (wlcRect.bottom - wlcRect.top) / 2,
            wlcApTextPaint
        )
    }

    private fun drawAp(canvas: Canvas) {
        elementPaint.color = ContextCompat.getColor(context, apStatus.color)
        canvas.drawRoundRect(
            apRect,
            5f,
            5f,
            elementPaint
        )
        canvas.drawText(
            "AP",
            apRect.left + (apRect.right - apRect.left) / 2,
            apRect.top + (apRect.bottom - apRect.top) / 2,
            wlcApTextPaint
        )
    }

    private fun drawPort(canvas: Canvas, portRect: RectF, status: Status, label: String) {
        elementPaint.color = ContextCompat.getColor(context, status.color)
        canvas.drawRoundRect(
            portRect,
            5f,
            5f,
            elementPaint
        )
        canvas.drawText(
            label,
            portRect.left + (portRect.right - portRect.left) / 2,
            portRect.top + (portRect.bottom - portRect.top) / 2,
            portTextPaint
        )
    }

    private fun drawChannel(canvas: Canvas) {
        channelPaint.color = ContextCompat.getColor(context, channelStatus.color)
        canvas.drawRoundRect(
            channelRect,
            5f,
            5f,
            channelPaint
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val minHeight = suggestedMinimumHeight + paddingTop + paddingBottom
        var currentWidth = MeasureSpec.getSize(widthMeasureSpec)
        var currentHeight = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(
            resolveSize(max(currentWidth, minWidth), widthMeasureSpec),
            resolveSize(max((currentWidth / 5), minHeight), heightMeasureSpec)
        )
    }

    override fun onSizeChanged(width: Int, height: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(width, height, oldw, oldh)

        var realWidth = width
        var realHeight = height

        if (width > height && width / height > 5) {
            realWidth = height * 5
            with(wagonRect) {
                left = ((width - realWidth) / 2) + 5f
                top = 5f
                right = width - ((width - realWidth) / 2) - 5f
                bottom = realHeight - 5f
            }
        } else {
            realHeight = width / 5
            with(wagonRect) {
                left = 5f
                top = 5f + ((height - realHeight) / 2)
                right = realWidth - 5f
                bottom = height - ((height - realHeight) / 2) - 5f
            }
        }
        wagonPaint.pathEffect = CornerPathEffect(realWidth.toFloat() / 30)
        wlcApTextPaint.textSize = realWidth.toFloat() / 30
        portTextPaint.textSize = realWidth.toFloat() / 60
        channelPaint.strokeWidth *= realWidth.toFloat() / 300



        if (isLeadingWagon) {
            wagonPath = Path().apply {
                moveTo(wagonRect.left + ((wagonRect.right - wagonRect.left) * 0.18f), wagonRect.top)
                lineTo(wagonRect.right, wagonRect.top)
                lineTo(wagonRect.right, wagonRect.bottom)
                lineTo(wagonRect.left, wagonRect.bottom)
                lineTo(wagonRect.left, wagonRect.top + ((wagonRect.bottom - wagonRect.top) / 2))
                close()
            }
            with(wlcRect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.72f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.15f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.84f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.85f
            }
            with(apRect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.85f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.15f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.97f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.85f
            }
            with(gi1Rect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.20f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.15f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.30f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.45f
            }
            with(gi2Rect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.32f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.15f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.42f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.45f
            }
            with(gi3Rect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.44f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.15f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.54f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.45f
            }
            with(gi4Rect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.56f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.15f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.66f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.45f
            }
            with(gi5Rect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.20f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.55f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.30f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.85f
            }
            with(gi6Rect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.32f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.55f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.42f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.85f
            }
            with(gi7Rect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.44f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.55f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.54f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.85f
            }
            with(gi8Rect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.56f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.55f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.66f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.85f
            }
            with(channelRect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.44f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.54f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.66f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.85f
            }
        } else {
            wagonPath = Path().apply {
                moveTo(wagonRect.left, wagonRect.top)
                lineTo(
                    wagonRect.right - ((wagonRect.right - wagonRect.left) * 0.18f),
                    wagonRect.top
                )
                lineTo(wagonRect.right, wagonRect.bottom - ((wagonRect.bottom - wagonRect.top) / 2))
                lineTo(wagonRect.right, wagonRect.bottom)
                lineTo(wagonRect.left, wagonRect.bottom)
                close()
            }
            with(wlcRect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.17f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.15f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.29f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.85f
            }
            with(apRect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.02f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.15f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.15f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.85f
            }
            with(gi1Rect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.35f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.15f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.45f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.45f
            }
            with(gi2Rect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.47f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.15f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.57f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.45f
            }
            with(gi3Rect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.59f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.15f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.69f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.45f
            }
            with(gi4Rect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.71f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.15f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.81f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.45f
            }
            with(gi5Rect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.35f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.55f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.45f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.85f
            }
            with(gi6Rect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.47f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.55f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.57f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.85f
            }
            with(gi7Rect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.59f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.55f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.69f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.85f
            }
            with(gi8Rect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.71f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.55f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.81f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.85f
            }
            with(channelRect) {
                left = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.59f
                top = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.54f
                right = wagonRect.left + (wagonRect.right - wagonRect.left) * 0.81f
                bottom = wagonRect.top + (wagonRect.bottom - wagonRect.top) * 0.85f
            }
        }
    }

}