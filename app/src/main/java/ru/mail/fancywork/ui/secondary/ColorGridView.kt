package ru.mail.fancywork.ui.secondary

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.View
import android.widget.GridLayout
import androidx.core.graphics.get

class ColorGridView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : GridLayout(context, attrs, defStyleAttr) {
    companion object {
        private const val TILE_SIZE = 150
    }

    private fun createPixel(color: Int): View {
        val view = View(context)
        view.setBackgroundColor(color)
        // todo ClickListeners or whatever
        return view
    }

    fun setImage(bitmap: Bitmap, scale: Int) {
        setWillNotDraw(false)
        removeAllViews()
        rowCount = bitmap.height
        columnCount = bitmap.width

        for (row in 0 until bitmap.height) {
            val rowSpec = spec(row)
            for (col in 0 until bitmap.width) {
                val colSpec = spec(col)
                val params = LayoutParams(rowSpec, colSpec)
                params.width = TILE_SIZE / scale
                params.height = TILE_SIZE / scale
                val view = createPixel(bitmap[col, row])
                addView(view, params)
            }
        }
    }
}
