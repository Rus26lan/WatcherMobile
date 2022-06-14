package com.rundgrun.watchermobile.domain.usecase

import com.rundgrun.watchermobile.domain.models.fluidmesh.FmModule
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList
import kotlin.jvm.Throws
public class GetFMModuleListUseCase {
    @Throws(IOException::class)
    fun execute(ip: String): ArrayList<FmModule> {
        val baseStationList: ArrayList<FmModule> = ArrayList()
        var con: HttpURLConnection? = null
        try {
            val url =
                URL("http://" + ip + "/get_local_node_stats.php?rand=" + Random().nextInt(1000000))
            con = url.openConnection() as HttpURLConnection
            con.setRequestProperty("Authorization", "Basic YWRtaW46cmFkaW9tdA==")
            con.connectTimeout = 2000
            con.readTimeout = 5000
            val input = BufferedReader(InputStreamReader(con.inputStream))
            input.forEachLine {
                val listBar = it.split(" ".toRegex())
                val baseStation = FmModule(
                    meshID = listBar[0],
                    level = listBar[1].toInt(),
                    dBm = listBar[3]
                )
                baseStationList.add(baseStation)
            }
            input.close()
            con.disconnect()
        } catch (e: IOException) {
            con?.disconnect()
        } finally {
            con?.disconnect()
        }
        return baseStationList
    }
}
