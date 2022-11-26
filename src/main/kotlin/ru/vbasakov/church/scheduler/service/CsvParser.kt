package ru.vbasakov.church.scheduler.service

import org.springframework.stereotype.Service
import ru.vbasakov.church.scheduler.dto.Task
import ru.vbasakov.church.scheduler.dto.Worker
import ru.vbasakov.church.scheduler.service.Days.*
import java.io.File
import java.nio.charset.Charset

@Service
class CsvParser {
    fun parse(tasks: List<String>): List<Task> {
        val rows = File("src/main/resources/init/data.csv").readLines(Charset.defaultCharset())

        val workers = mutableListOf<Worker>()
        var worker = Worker("")
        rows.forEachIndexed { index, row ->
            val cells = row.trim().split(",")
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
            if (name.startsWith("Имя")) {
                worker = Worker(name)
                workers.add(worker)
            } else {
                assert(worker.name.isNotEmpty())
                if (worker.name == "Имя") {
                    worker.name = name
                } else {
                    assert(name.isEmpty() || name == worker.name)
                }
                worker.addTaskIfExists(MONDAY, monday, timePeriod)
                worker.addTaskIfExists(TUESDAY, tuesday, timePeriod)
                worker.addTaskIfExists(WEDNESDAY, wednesday, timePeriod)
                worker.addTaskIfExists(THURSDAY, thursday, timePeriod)
                worker.addTaskIfExists(FRIDAY, friday, timePeriod)
                worker.addTaskIfExists(SATURDAY, saturday, timePeriod)
                worker.addTaskIfExists(SUNDAY, sunday, timePeriod)
            }
        }

        val units = workers.flatMap { it.units }
        units
            .map { it.task }
            .filterNot { it in tasks }
            .sorted()
            .toSet()
            .forEach {
            println(it)
        }
        return units
    }
}

private fun Worker.addTaskIfExists(day: Days, task: String, timePeriod: String) {
    if (task.isNotBlank()) {
        units.add(Task(day, timePeriod, task))
    }
}