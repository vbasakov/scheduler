package ru.vbasakov.church.scheduler.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.File

@Configuration
class AppConf {
    @Bean
    fun tasksDict(): Set<String> = File("src/main/resources/init/jobs.csv").readLines().toSet()
}