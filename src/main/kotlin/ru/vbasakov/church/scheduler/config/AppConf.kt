package ru.vbasakov.church.scheduler.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.util.StdDateFormat
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Configuration
class AppConf {
    @Bean
    fun tasksDict(): Set<String> = File("src/main/resources/init/jobs.csv").readLines().toSet()


    @Bean
    @Primary
    fun jsonMapper(): JsonMapper = initJsonMapper()

    fun initJsonMapper(): JsonMapper {
        val mapper = JsonMapper()
        val javaTimeModule = JavaTimeModule()
        // Hack time module to allow 'Z' at the end of string (i.e. javascript json's)
        javaTimeModule.addDeserializer(
            LocalDateTime::class.java,
            LocalDateTimeDeserializer(
                DateTimeFormatter.ISO_LOCAL_DATE_TIME
            )
        )
        mapper.registerModule(javaTimeModule)
        mapper.registerKotlinModule()
//        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        return commonCfg(mapper) as JsonMapper
    }

    private fun commonCfg(mapper: ObjectMapper): ObjectMapper {
        return mapper
            .enable(SerializationFeature.INDENT_OUTPUT)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)  //todo enable
            .setDateFormat(StdDateFormat())
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
    }

}