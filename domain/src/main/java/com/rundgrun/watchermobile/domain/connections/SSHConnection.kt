package com.rundgrun.watchermobile.domain.connections

import com.jcraft.jsch.*
import java.io.IOException

class SSHConnection(private val ip: String): AbstractConnection() {

    private lateinit var session: Session
    private lateinit var channel: Channel

    init{
        connect()
    }

    override fun connect() {
        val jsch = JSch()
        session = jsch.getSession(LOGIN, ip, 22)
        session.setPassword(PASSWORD)
        val ui: UserInfo = object : UserInfo {
            override fun getPassphrase(): String {
                return null!!
            }

            override fun getPassword(): String {
                return null!!
            }

            override fun promptPassword(s: String): Boolean {
                return false
            }

            override fun promptPassphrase(s: String): Boolean {
                return false
            }

            override fun promptYesNo(s: String): Boolean {
                return true
            }

            override fun showMessage(s: String) {}
        }

        session.userInfo = ui
        session.connect(SSH_TIME_OUT_CONNECTION)
        channel = session.openChannel("shell")
        input = channel.inputStream
        output = channel.outputStream
        channel.connect()
    }

    override fun close() {
        channel.disconnect()
        session.disconnect()
    }

}