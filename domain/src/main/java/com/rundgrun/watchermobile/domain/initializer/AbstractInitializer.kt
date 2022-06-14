package com.rundgrun.watchermobile.domain.initializer

import com.rundgrun.watchermobile.domain.connections.Connection
import com.rundgrun.watchermobile.domain.connections.LOGIN
import com.rundgrun.watchermobile.domain.connections.PASSWORD
import com.rundgrun.watchermobile.domain.models.cisco.SG3XX
import java.io.IOException

abstract class AbstractInitializer(private val connection: Connection) {

    @Throws(IOException::class)
    open fun authorization(device: SG3XX) {
        var result: String = connection.readToPoints("Name:")
        connection.send(LOGIN)
        result += connection.readToPoints("Password")
        connection.send(PASSWORD)
        result += connection.readToPoints(device.name, "(Y/N)")
        if (result.contains("password has exceeded")) {
            connection.send(NOT)
            result += connection.readToPoints("K" + device.name)
            device.data.put(SG3XX.ERROR_MESSAGE, "Требуется смена пароля")
        }
        device.data.put(SG3XX.AUTHORIZATION, result)
    }



}