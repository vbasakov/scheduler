package ru.vbasakov.church.scheduler.dto.json

import java.util.*

data class SheetDto(
    val range: String,
    val majorDimension: String,
    val values: LinkedList<LinkedList<String>>
)