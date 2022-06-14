package com.rundgrun.watchermobile.domain.connections

import com.rundgrun.watchermobile.domain.connections.AbstractConnection
import java.io.*
import java.net.InetSocketAddress
import java.net.Socket

class TNConnection(private var ip: String) : AbstractConnection() {

    private lateinit var socket: Socket


    init {
        connect()
    }

    @Throws(IOException::class)
    override fun connect() {
        socket = Socket()
        socket.connect(InetSocketAddress(ip, 23), TN_TIME_OUT_CONNECTION)
        socket.soTimeout = TIME_OUT_READ
        input = socket.getInputStream()
        output = socket.getOutputStream()
    }

    @Throws(IOException::class)
    override fun close() {
        socket.close()
    }
}