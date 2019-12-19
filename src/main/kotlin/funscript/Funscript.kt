package funscript

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

data class Action(
    val at: Long,
    val pos: Int
)

data class Funscript (
    val actions: List<Action>
)

private val objectMapper = ObjectMapper().apply {
    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
}

fun parsePositions(file: File): List<Action> {
    val funscript = objectMapper.readValue<Funscript>(file)
    return funscript.actions
}