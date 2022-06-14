package com.rundgrun.watchermobile.domain.models.fluidmesh

import java.util.*

data class FmModule(
    var meshID: String = "-.-.-.-",
    var level: Int = 0,
    var dBm: String = "--"
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FmModule) return false
        return Objects.equals(meshID, other.meshID)
    }

    override fun hashCode(): Int {
        return meshID.hashCode()
    }


}