package audacity

import java.io.File

/**
 * Parse labels from audacity exported file
 * @return list of label timestamps, in milliseconds
 */
fun parseLabels(file: File): List<Long> {
    return file.readLines().map { line ->
        (line.split("\t")[0].toDouble() * 1000).toLong()
    }
}