package ru.vbasakov.church.scheduler

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import ru.vbasakov.church.scheduler.service.JsonParser

@SpringBootApplication
class Validation

fun main(args: Array<String>) {
    val context =  runApplication<Validation>(*args)

    val jsonParser = context.getBean(JsonParser::class.java)
    jsonParser.readData()
}
