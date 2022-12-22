package me.huteri.nopbarchart

import android.content.res.Resources
import android.util.TypedValue
import java.text.DecimalFormat

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

fun Double.toDouble(numberOfDecimals: Int = 2): Double? {
    val str = StringBuilder("0.")
    for(i in 1..numberOfDecimals) str.append("0")
    return DecimalFormat(str.toString()).format(this).toDoubleOrNull()
}

fun Double.toDecimalPlaces(numberOfDecimals: Int = 2): String {
    val str = StringBuilder("0.")
    for(i in 1..numberOfDecimals) str.append("0")
    return DecimalFormat(str.toString()).format(this)
}
