package com.rundgrun.watchermobile.data.repository

import android.content.Context
import com.google.gson.GsonBuilder
import com.rundgrun.watchermobile.domain.models.fluidmesh.FmIP
import com.rundgrun.watchermobile.domain.repository.FluidMeshRepository
import com.rundgrun.watchermobile.domain.repository.Line


class FluidMeshRepositoryImpl(private val context: Context?) : FluidMeshRepository {

    override fun getFMListByLines(line: Line): List<FmIP> {
        when(line){
            Line.Line1-> {
                return getFMListFromFile("fm_ip/line_1_fm.json")
            }
            Line.Line2->{
                return getFMListFromFile("fm_ip/line_2_fm.json")
            }
            Line.Line3->{
                return getFMListFromFile("fm_ip/line_3_fm.json")
            }
            Line.Line4->{
                return getFMListFromFile("fm_ip/line_4_fm.json")
            }
            Line.Line5->{
                return getFMListFromFile("fm_ip/line_5_fm.json")
            }
        }
    }

    private fun getFMListFromFile(fileName: String):List<FmIP>{
        val jsonStringBuilder = StringBuilder()
        context?.assets?.open(fileName)
            ?.bufferedReader()
            .use { br -> br?.forEachLine { jsonStringBuilder.append(it) } }
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()
        val array = gson.fromJson(jsonStringBuilder.toString(), Array<FmIP>::class.java)
        return array.toList()
    }
}