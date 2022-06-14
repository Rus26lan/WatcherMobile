package com.rundgrun.watchermobile.domain.models.cisco

import com.rundgrun.watchermobile.domain.models.cisco.components.Port

class SG3XX(var name: String,
            var ip: String) {

    val data = hashMapOf(
        AUTHORIZATION to "",
        SHOW_SYSTEM to "",
        ERROR_MESSAGE to "",
        SHOW_INT_STATUS to "",
        SHOW_INT_COUNTER_GI1 to "",
        SHOW_INT_COUNTER_GI2 to "",
        SHOW_INT_COUNTER_GI3 to "",
        SHOW_INT_COUNTER_GI4 to "",
        SHOW_INT_COUNTER_GI5 to "",
        SHOW_INT_COUNTER_GI6 to "",
        SHOW_INT_COUNTER_GI7 to "",
        SHOW_INT_COUNTER_GI8 to "",
        SHOW_INT_COUNTER_GI9 to "",
        SHOW_INT_COUNTER_GI10 to "",
        SHOW_CDP_EN to "",
    )
    var time = 0
    val ports = Array(10) { i -> Port("Gi$i") }

    companion object{
        const val AUTHORIZATION = "authorization"
        const val SHOW_SYSTEM = "show_system"
        const val SHOW_INT_STATUS = "show_int_status"
        const val SHOW_INT_COUNTER_GI1 = "show_int_counter_GI1"
        const val SHOW_INT_COUNTER_GI2 = "show_int_counter_GI2"
        const val SHOW_INT_COUNTER_GI3 = "show_int_counter_GI3"
        const val SHOW_INT_COUNTER_GI4 = "show_int_counter_GI4"
        const val SHOW_INT_COUNTER_GI5 = "show_int_counter_GI5"
        const val SHOW_INT_COUNTER_GI6 = "show_int_counter_GI6"
        const val SHOW_INT_COUNTER_GI7 = "show_int_counter_GI7"
        const val SHOW_INT_COUNTER_GI8 = "show_int_counter_GI8"
        const val SHOW_INT_COUNTER_GI9 = "show_int_counter_GI9"
        const val SHOW_INT_COUNTER_GI10 = "show_int_counter_GI10"
        const val SHOW_CDP_EN = "show_cdp_ne"
        const val ERROR_MESSAGE = "error_message"
    }
}