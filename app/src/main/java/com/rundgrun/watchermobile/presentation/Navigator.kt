package com.rundgrun.watchermobile.presentation

interface Navigator {
    fun back()
    fun toMenu()
    fun toFMLevel(ip: String)
    fun toFM()
    fun toOption()
    fun toWagons()
}