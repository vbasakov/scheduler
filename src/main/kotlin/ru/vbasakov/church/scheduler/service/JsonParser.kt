package ru.vbasakov.church.scheduler.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import ru.vbasakov.church.scheduler.dto.json.SheetDto
import java.io.File

val apiUrl = "https://sheets.googleapis.com/v4/spreadsheets"

val apiKey = "AIzaSyDmXe7UMHKblpgf2MgtzV6jjfPHXsuy8Lk"



//val documentIdMasterNew = "1hvVfxdsuVKjwJF4YmB4LxYSBZ_wAP9rwqUVQbTYHEOg" //original
val documentIdCopyOfNew = "16tqtvAhk6fQsEHEmd_199Jt3WRf9kl4pqqZdNG4TxqU"
val documentIdMaster = "1aMLQaJS3A1VNF3t2shccr13joIsLHGld"
val documentIdWorkingCopy = "1yHuFft0scC1n47Yh-EDb-uAZ8wI_miou8dTL-amI_sE"

//https://docs.google.com/spreadsheets/d/1a6OwSbfUIjJ-8GmshtlA-o25Dn8x-Ljw/edit?usp=sharing&ouid=108343190869350218959&rtpof=true&sd=true

val documentId = documentIdWorkingCopy

val dropdownListNew = "выпадающий%20список"
val dataInput = "Внесение%20данных%20в%20график"

val one = "постоянный%20график"
val dropdownList = "выпадающий%20список"

@Service
class JsonParser(
    @Autowired
    val mapper: ObjectMapper
) {
    /*
    https://console.cloud.google.com/apis/dashboard?project=vbasakov-oauth
https://handsondataviz.org/google-sheets-api-key.html


    */


    val getAllJson = "$apiUrl/$documentId/?key=$apiKey"

    val getDropdownListJson = "$apiUrl/$documentId/values/$dropdownList?key=$apiKey"
    val getFirstListJson = "$apiUrl/$documentId/values/$one?key=$apiKey"


    val dataInputJson = File("src/main/resources/init/dataInput.json").readText()
    fun readData() {

        println(getAllJson)
        println()
        println(getFirstListJson)
        println()
        println(getDropdownListJson)

        val dto = mapper.readValue(dataInputJson, SheetDto::class.java)

//        println(dto)
//        dto.values.filter {
//            it.size > 19 && it[19].contains("Хим.средства в хоз.службе0,6875")
//        }
    }

}