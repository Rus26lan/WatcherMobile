package com.rundgrun.watchermobile.domain.models

enum class Status(private val title: String) {
    UNCHECKED(""), NORMAL("норма"), ABNORMAL("ошибка");

    override fun toString(): String {
        return title
    }
}