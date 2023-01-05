package me.huteri.nopbarchart

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat

class NopBarChart(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var viewHeight: Int = 0
    private var viewWidth: Int = 0

    private var markerValue = 0.0
    private var list: List<BarItem> = emptyList()

    private var paint = Paint()
    private var rectF = RectF()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        viewWidth = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        viewHeight = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    fun setData(markerValue: Double, list: List<BarItem>) {
        this.markerValue = markerValue
        this.list = list

        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.let { c ->
            val horizontalPadding = 10f.toPx()

            val totalBarWidth = viewWidth - 2 * horizontalPadding
            val sum = list.sumOf { it.value }

            val barHeight = 20f.toPx()
            val gapWidth = 2.5f.toPx()

            val markerHeight = 20f.toPx()

            var left = horizontalPadding
            val top = markerHeight + 5f.toPx()
            val bottom = top + barHeight

            paint.apply {
                textSize = 16f.toPx()
                color = Color.BLACK
                typeface = ResourcesCompat.getFont(context, R.font.museosans_700)
            }

            var counterSum = 0.0

            var text = counterSum.toDecimalPlaces(2)
            val bounds = Rect()
            paint.getTextBounds(text, 0, text.length, bounds)
            c.drawText(text, left, bounds.height() + bottom + 8f.toPx(), paint)

            var selectedColor = list.lastOrNull()?.color ?: Color.YELLOW

            list.forEachIndexed { index, barItem ->

                if(markerValue > counterSum && markerValue <= counterSum+barItem.value)
                    selectedColor = barItem.color

                counterSum += barItem.value

                val right = left + (barItem.value / sum) * totalBarWidth

                c.drawRoundRect(rectF.apply {
                    set(left, top, right.toFloat(), bottom)
                }, 3f.toPx(), 3f.toPx(), paint.apply {
                    color = barItem.color
                    style = Paint.Style.FILL
                })

                left = if (index != list.size - 1) {
                    (right + gapWidth).toFloat()
                } else {
                    right.toFloat()
                }

                if (index != list.size - 1) {
                    paint.apply {
                        textSize = 16f.toPx()
                        color = Color.BLACK
                        typeface = ResourcesCompat.getFont(context, R.font.museosans_700)
                    }

                    text = counterSum.toDecimalPlaces(2)
                    paint.getTextBounds(text, 0, text.length, bounds)

                    c.drawText(text, left - gapWidth / 2 - bounds.width() / 2, bounds.height() + bottom + 8f.toPx(), paint)
                }
            }

            val iconLeft = Math.min((markerValue / sum * totalBarWidth).toFloat(), totalBarWidth).toInt()
            val icon = ContextCompat.getDrawable(context, R.drawable.ic_marker)
            icon?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(selectedColor, BlendModeCompat.SRC_ATOP)
            icon?.setBounds(iconLeft, 0, iconLeft + 21.toPx(), 19.toPx())
            icon?.draw(c)
        }
    }
}

data class BarItem(
    val value: Double,
    val color: Int
)