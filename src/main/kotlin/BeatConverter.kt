import timerotor.Row
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class BeatConverter(
    private val shape: Shape
) {
    private val sampleRateMs = 10L

    private fun getTimestamps(lengthMs: Long): LongProgression {
        return 0 .. lengthMs step 10
    }

    data class Shape(
        val topWidth: Long,
        val bottomWidth: Long
    )

    private fun getStrength(distance: Long): Int {
        val x1 = shape.topWidth / 2.0
        val x2 = shape.bottomWidth / 2.0
        val y1 = 100.0
        val y2 = 0.0
        val m = (y2 - y1) / (x2 - x1)
        val b = y1 - m * x1
        val f = { x: Double -> m * x + b }
        return max(min(f(distance.toDouble()).toInt(), 100), 0)
    }

    private fun getRow(timestamp: Long, beats: List<Long>): Row {
        var closest = beats[0]
        for (beat in beats) {
            val distance = abs(timestamp - beat)
            closest = min(distance, closest)
        }
        return Row(
            millis = timestamp,
            strength = getStrength(closest) * 10
        )
    }

    fun convert(beats: List<Long>): List<Row> {
        val timestamps = getTimestamps(beats.last())
        val percent = (beats.last() / 10) / 100
        return timestamps.mapIndexed { i, timestamp ->
            if ((i.rem(percent)) == 0L) {
                println("Processed ${i / percent}")
            }
            getRow(timestamp, beats)
        }
    }
}