package com.rundgrun.watchermobile.domain.repository

import com.rundgrun.watchermobile.domain.models.fluidmesh.FmIP

interface FluidMeshRepository {
    fun getFMListByLines(line: Line): List<FmIP>
}