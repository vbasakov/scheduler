package ru.vbasakov.church.scheduler.service

import org.springframework.stereotype.Service
import ru.vbasakov.church.scheduler.dto.Task
import ru.vbasakov.church.scheduler.dto.Worker
import ru.vbasakov.church.scheduler.service.Days.*
import java.io.File
import java.nio.charset.Charset

@Service
class CsvParser(
    val tasksDict: Set<String>
) {
    /** Имя, Время начала, время С - ДО, Пн, Вт, Ср, Чт, Пт, Сб, Вс
     * 0: Имя
     * 1: Время начала
     * 2: время С - ДО
     * 3: Пн
     * 4: Вт
     * 5: Ср
     * 6: Чт
     * 7: Пт
     * 8: Сб
     * 9: Вс
     */
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

    /*

    fun parse(fileName: String) {
        val lines = File(fileName).readLines()

        val workers = mutableSetOf<Worker>()

        var worker: Worker? = null
        val iterator = lines.listIterator()

        while (iterator.hasNext()) {
            var line = iterator.next()
            var data = line.split(",")
            var name = data[0]

            assert(data.size == 10) { iterator.nextIndex() }


            if (name == "Имя") {
                line = iterator.next()
                data = line.split(",")
                name = data[0]

                worker = Worker(name = name)
                workers.add(worker)
            }


            worker!!
            assert(name.isEmpty() || name == worker.name)

            (3..9).map { data[it] }
                .filter(String::isNotBlank)
                .filterNot { it in tasksDict }
                .forEach(::println)
        }

//        workers.forEach { println(it.name) }


    }
    */
}

private fun Worker.addTaskIfExists(day: Days, task: String, timePeriod: String) {
    if (task.isNotBlank()) {
        units.add(Task(day, timePeriod, task))
    }
}