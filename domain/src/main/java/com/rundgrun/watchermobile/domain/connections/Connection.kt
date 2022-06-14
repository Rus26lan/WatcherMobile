package com.rundgrun.watchermobile.domain.connections

import java.io.Closeable

interface Connection: Closeable {
   fun read():String
   fun readToPoints(vararg points: String): String
   fun send(command: String)
   fun skip()
}