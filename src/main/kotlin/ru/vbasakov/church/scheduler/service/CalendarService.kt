package ru.vbasakov.church.scheduler.service

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import java.io.File
import java.nio.charset.Charset

@Service
class CalendarService(
    val csvParser: CsvParser
) {
    val jobs = File("src/main/resources/init/jobs.csv").readLines(Charset.defaultCharset())

    val tasks = csvParser.parse(jobs)
}