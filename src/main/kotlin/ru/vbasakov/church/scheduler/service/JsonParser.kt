package ru.vbasakov.church.scheduler.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import ru.vbasakov.church.scheduler.dto.json.SheetDto
import java.io.File

val apiUrl = "https://sheets.googleapis.com/v4/spreadsheets"

val apiKey = "AIzaSyDmXe7UMHKblpgf2MgtzV6jjfPHXsuy8Lk"

//val documentId = "1hvVfxdsuVKjwJF4YmB4LxYSBZ_wAP9rwqUVQbTYHEOg" //original
val documentId = "16tqtvAhk6fQsEHEmd_199Jt3WRf9kl4pqqZdNG4TxqU"

val dropdownList = "выпадающий%20список"
val dataInput = "Внесение%20данных%20в%20график"

@Service
class JsonParser(
    @Autowired
    val mapper: ObjectMapper
) {


    val getAllJson = "$apiUrl/$documentId/?key=$apiKey"

    val getDropdownListJson = "$apiUrl/$documentId/values/$dropdownList?key=$apiKey"
    val getDataInputListJson = "$apiUrl/$documentId/values/$dataInput?key=$apiKey"


    val dataInputJson = File("src/main/resources/init/dataInput.json").readText()
    fun readData() {
        val dto = mapper.readValue(dataInputJson, SheetDto::class.java)

        println(dto)
//        dto.values.filter {
//            it.size > 19 && it[19].contains("Хим.средства в хоз.службе0,6875")
//        }
    }

}