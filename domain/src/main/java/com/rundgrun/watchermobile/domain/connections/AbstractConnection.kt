package com.rundgrun.watchermobile.domain.connections

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

abstract class AbstractConnection: Connection {

    lateinit var input: InputStream
    lateinit var output: OutputStream

    abstract fun connect()

    @Throws(IOException::class)
    override fun readToPoints(vararg points: String): String {
        val result = StringBuilder()
        do {
            result.append(read())
        } while (!contains(result, *points))
        return result.toString()
    }

    @Throws(IOException::class)
    override fun read(): String {
        val result = StringBuilder()
        do {
            result.append(input.read().toChar())
        } while (input.available() != 0)
        return result.toString()
    }

    @Throws(IOException::class)
    override fun send(command: String) {
        val cmd = command.toByteArray()
        for (b in cmd) {
            output.write(b.toInt())
        }
        output.write(13)
    }

    @Throws(IOException::class)
    override fun skip() {
        output.write(32)
    }

    fun contains(text: StringBuilder, vararg points: String): Boolean{
        for(s in points){
            if(text.contains(s)){
                return  true
            }
        }
        return false
    }
}