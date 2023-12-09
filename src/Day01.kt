fun main() {
    fun getFirstLastByDigit(line: String): Pair<Pair<Int, Char?>, Pair<Int, Char?>> {

        val lineAsChar = line.toCharArray()

        val firstChar = lineAsChar.firstOrNull { it.isDigit() }
        val firstIndex = firstChar?.let { lineAsChar.indexOf(it) } ?: -1
        val first = Pair(firstIndex, firstChar)

        val lastChar = lineAsChar.lastOrNull { it.isDigit() }
        val lastIndex = lastChar?.let { lineAsChar.lastIndexOf(it) } ?: -1
        val last = Pair(lastIndex, lastChar)
        return Pair(first, last)

    }

    fun part1(input: List<String>): Int {

        val pairs = input
            .map(::getFirstLastByDigit)
            .map {
                val (first, last) = it
                "${first.second}${last.second}".toInt()
            }

        return pairs.sum()
    }


    fun part2(input: List<String>): Int {

        val test = input.map { line ->
            val letters = Digits.entries
                .flatMap {
                    listOf(
                        Pair(line.indexOf(it.name), it.value),
                        Pair(line.lastIndexOf(it.name), it.value)
                    )
                }
                .filter { it.first != -1 }
                .distinct()

            val (firstByDigit, lastByDigit) = getFirstLastByDigit(line)

            val allPairs = buildList {
                add(firstByDigit)
                add(lastByDigit)
                addAll(letters)
            }.filter { it.first != -1 }


            val firstTotal = allPairs.minBy { it.first }.second
            val lastTotal = allPairs.maxBy { it.first }.second

            "${firstTotal}${lastTotal}".toInt()

        }
        return test.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

enum class Digits(val value: Char) {
    one('1'), two('2'), three('3'),
    four('4'), five('5'), six('6'),
    seven('7'), eight('8'), nine('9')
}
