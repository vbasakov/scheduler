package ru.vbasakov.church.scheduler.dto

class Worker(
    var name: String,
    val units : MutableList<Task> = mutableListOf()
)