package me.huteri.nopbarchart

import android.content.res.Resources
import android.util.TypedValue

class Utils {

    companion object {
        fun convertDpToPx(dp: Int): Int {
            return convertDpToPx(dp.toFloat())
        }
        fun convertDpToPx(dp: Float): Int {
            return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics))
        }
    }

}


fun Float.toPx() = Utils.convertDpToPx(this).toFloat()
fun Int.toPx() = Utils.convertDpToPx(this)