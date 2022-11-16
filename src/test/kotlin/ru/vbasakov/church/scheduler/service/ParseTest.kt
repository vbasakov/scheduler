package ru.vbasakov.church.scheduler

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.vbasakov.church.scheduler.service.CsvParser

@SpringBootTest
class ParseTests {

    @Autowired
    lateinit var parser: CsvParser

    @Test
    fun parse() {
        parser.parse("src/main/resources/init/data.csv")
    }

}
