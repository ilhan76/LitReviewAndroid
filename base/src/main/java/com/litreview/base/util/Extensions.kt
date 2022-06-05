package com.litreview.base.util

import android.content.res.Resources

//convert pixels to dp
fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

//convert dp tp pixels
fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

//convert pixels to sp
fun Int.toSp(): Int = (this * Resources.getSystem().displayMetrics.scaledDensity).toInt()