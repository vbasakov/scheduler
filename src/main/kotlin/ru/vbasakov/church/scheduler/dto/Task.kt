package ru.vbasakov.church.scheduler.dto

import ru.vbasakov.church.scheduler.service.Days

data class Task(
    val day: Days,
    val timePeriod: String,
    val task: String
)