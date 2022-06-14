package com.rundgrun.watchermobile.domain.models.cisco.components

import com.rundgrun.watchermobile.domain.models.Status

class Port(val name: String) {

    var checkStatus: Status = Status.UNCHECKED

    val inUcastPkts = 0
    val outUcastPkts = 0

    var alEr = Integer.MAX_VALUE
    var fcs = Integer.MAX_VALUE
    var singCol = Integer.MAX_VALUE
    var multCol = Integer.MAX_VALUE
    var sqe = Integer.MAX_VALUE
    var transmis = Integer.MAX_VALUE
    var lateCol = Integer.MAX_VALUE
    var exCol = Integer.MAX_VALUE
    var carEr = Integer.MAX_VALUE
    var oversize = Integer.MAX_VALUE
    var macEr = Integer.MAX_VALUE
    var symEr = Integer.MAX_VALUE
    var rePausFrame = Integer.MAX_VALUE
    var trPausFrame = Integer.MAX_VALUE

    var enabled = false
    var speed = false
    var state = false
}