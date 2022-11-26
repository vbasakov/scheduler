package ru.vbasakov.church.scheduler.service

import org.springframework.stereotype.Service
import ru.vbasakov.church.scheduler.dto.Task
import java.io.File
import java.nio.charset.Charset

@Service
class CsvParser {
    fun parse(jobs: List<String>): List<Task> {
        val rows = File("src/main/resources/init/data.csv").readLines(Charset.defaultCharset())

        println()
        rows.forEachIndexed { index, row ->

                val cells = row.split(",")
                val name = cells[0]
                val timeStart = cells[1]
                val timePeriod = cells[2]
                val monday = cells[3]
                val tuesday = cells[4]
                val wednesday = cells[5]
                val thursday = cells[6]
                val friday = cells[7]
                val saturday = cells[8]
                val sunday = cells[9]
                assert(cells.size == 10)

        }


        return listOf()
    }
}