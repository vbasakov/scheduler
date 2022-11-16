package ru.vbasakov.church.scheduler.service

import org.springframework.stereotype.Service
import ru.vbasakov.church.scheduler.dto.Worker
import java.io.File

@Service
class CsvParser {


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

    fun parse(fileName: String) {
        val lines = File(fileName).readLines()

        var worker: Worker? = null
        val iterator = lines.listIterator()
        while (iterator.hasNext()) {
            var line = iterator.next()
            var data = line.split(",")

            assert(data.size == 10) { iterator.nextIndex() }

            if (line.startsWith("Имя")) {
                line = iterator.next()
                data = line.split(",")
                worker = Worker(name = data[0])
            }


            worker!!
            assert(data[0].isEmpty() || data[0] == worker.name)
        }
    }
}