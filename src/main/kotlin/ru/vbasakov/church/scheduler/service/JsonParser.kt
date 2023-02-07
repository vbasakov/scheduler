package ru.vbasakov.church.scheduler.service

import org.springframework.stereotype.Service

val apiUrl = "https://sheets.googleapis.com/v4/spreadsheets"

val apiKey = "AIzaSyDmXe7UMHKblpgf2MgtzV6jjfPHXsuy8Lk"

val documentId = "1hvVfxdsuVKjwJF4YmB4LxYSBZ_wAP9rwqUVQbTYHEOg"

val dropdownList = "выпадающий%20список"
val dataInput = "Внесение%20данных%20в%20график"

@Service
class JsonParser {

    val getAllJson  = "$apiUrl/$documentId/?key=$apiKey"

    val getDropdownListJson = "$apiUrl/$documentId/values/$dropdownList?key=$apiKey"
    val getDataInputListJson = "$apiUrl/$documentId/values/$dataInput?key=$apiKey"
}