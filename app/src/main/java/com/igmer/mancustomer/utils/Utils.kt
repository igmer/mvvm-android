package com.igmer.mancustomer.utils

import java.math.RoundingMode
import java.text.DecimalFormat

class Utils {
    companion object {
        fun decimalFormat(number: Double): String {
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.HALF_UP
            return df.format(number)
        }
    }
}