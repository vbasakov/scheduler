package ru.vbasakov.church.scheduler.service

import org.springframework.stereotype.Service
import java.io.File
import java.nio.charset.Charset

@Service
class CalendarService(
    csvParser: CsvParser
) {
    val tasks = File("src/main/resources/init/jobs.csv").readLines(Charset.defaultCharset())

    val units = csvParser.parse(this.tasks)
}