package com.litreview.i_navigation.providers

import android.os.Bundle
import com.litreview.i_navigation.NavCommand

interface TabsNavCommandProvider {

    fun toBookDetail(args: Bundle) : NavCommand
}