@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth
import java.io.File
import java.util.*
import kotlin.math.max

// Урок 6: разбор строк, исключения
// Максимальное количество баллов = 13
// Рекомендуемое количество баллов = 11
// Вместе с предыдущими уроками (пять лучших, 2-6) = 40/54

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val month = mapOf(
        "января" to 1,
        "февраля" to 2,
        "марта" to 3,
        "апреля" to 4,
        "мая" to 5,
        "июня" to 6,
        "июля" to 7,
        "августа" to 8,
        "сентября" to 9,
        "октября" to 10,
        "ноября" to 11,
        "декабря" to 12
    )
    val date = str.split(' ').toList()
    if (date.size != 3)
        return ""
    val day = isNumber(date[0])
    val year = isNumber(date[2])
    if (day == null || year == null || date[1] !in month) {
        return ""
    }
    val j = month.getValue(date[1])

    if (day > daysInMonth(j, year) || day == 0)
        return ""

    val d = twoDigitStr(day)
    val m = twoDigitStr(j)
    return "$d.$m.${date[2]}"
}

fun isNumber(s: String): Int? = if (s.isNotEmpty() && s[0].isDigit()) s.toIntOrNull() else null


/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val month = listOf(
        "января",
        "февраля",
        "марта",
        "апреля",
        "мая",
        "июня",
        "июля",
        "августа",
        "сентября",
        "октября",
        "ноября",
        "декабря"
    )
    val date = digital.split('.').toMutableList()
    if (date.size != 3)
        return ""
    val day = isNumber(date[0])
    val mnth = isNumber(date[1])
    val year = isNumber(date[2])
    if (day == null || mnth == null || year == null)
        return ""

    if (day > daysInMonth(mnth, year) || day == 0)
        return ""
    val m = month[mnth - 1]
    return "$day $m ${date[2]}"
}

/**
 * Средняя (4 балла)
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String = TODO()

fun checkPhone(phone: String): Boolean =
    phone.count { it.isDigit() || it == ' ' || it == '+' || it == '-' || it == '(' || it == ')' } < phone.length ||
            "()" in phone

/**
 * Средняя (5 баллов)
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    val splitJumps = jumps.split(" ")
    if (!checkRes(splitJumps))
        return -1
    return Collections.max(jumps.split(" ").map { isNumber(it) ?: -1 })
}

fun checkRes(jumps: List<String>): Boolean =
    jumps.all { isNumber(it) != null || it == "-" || it == "%" }


/**
 * Сложная (6 баллов)
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    val check = setOf('+', '%', '-')
    val parts = jumps.split(' ')
    if (parts.size % 2 != 0) return -1
    var ans = -1
    for (i in parts.indices step 2) {
        val tmp = parts[i].toIntOrNull() ?: return -1
        if (!parts[i + 1].all { it in check })
            return -1
        if ('+' in parts[i + 1])
            ans = max(ans, tmp)
    }
    return ans
}

/**
 * Сложная (6 баллов)
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val list = expression.split(" ")
    if (list.isEmpty())
        throw IllegalArgumentException()
    var ans: Int = isNumber(list[0]) ?: throw IllegalArgumentException()

    if ((list.size - 1) % 2 != 0) throw IllegalArgumentException()
    for (i in 1 until list.size - 1 step 2) {
        val tmp = isNumber(list[i + 1]) ?: throw IllegalArgumentException()
        when (list[i]) {
            "+" -> {
                ans += tmp
            }
            "-" -> {
                ans -= tmp
            }
            else -> throw IllegalArgumentException()
        }
    }
    return ans
}

/**
 * Сложная (6 баллов)
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    var index = 0
    val list = str.split(' ')
    for (i in 0..list.size - 2) {
        if (list[i].equals(list[i + 1], ignoreCase = true)) {
            return index
        }
        index += list[i].length + 1
    }
    return -1
}

/**
 * Сложная (6 баллов)
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше нуля либо равны нулю.
 */
fun mostExpensive(description: String): String {
    val tmp = description.split("; ").map { it.split(" ") }
    if (!checkList(tmp)) return ""
    return tmp.map { it[0] to it[1].toDouble() }.maxByOrNull { it.second }?.first ?: ""
}

fun checkList(tmp: List<List<String>>): Boolean = tmp.all { it.size == 2 && it[1].toDoubleOrNull() != null }

/**
 * Сложная (6 баллов)
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная (7 баллов)
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()

/**
 * Имеется список строк todos, в которых перечислены планы на
 * предстоящую неделю, например:
 *
 * “вторник лекции”,
 * “пятница друзья”,
 * “суббота кинотеатр”,
 * “пятница уборка”
 *
 * В качестве результата необходимо вернуть количество дел
 * в каждый из перечисленных дней недели
 *
 * Имя и тип результата функции предложить самостоятельно.
 *
 * Если входные аргументы являются некорректными,
 * бросить IllegalArgumentException
 *
 * Кроме функции, следует написать тесты,
 * подтверждающие её работоспособность.
 */
fun getQuantityByDay(todos: List<String>): Map<String, Int> {
    val todosParse = todos.map { it.split(" ") }
    val days = mutableMapOf(
        "понедельник" to 0,
        "вторник" to 0,
        "среда" to 0,
        "четверг" to 0,
        "пятница" to 0,
        "суббота" to 0,
        "воскресенье" to 0
    )
    for (i in todosParse) {
        if (i.size < 2 || i[1] == "")
            throw IllegalArgumentException()
        days[i[0]] = (days[i[0]] ?: throw IllegalArgumentException()) + 1
    }
    return days
}

fun robotInMaze(inputName: String, commands: String) {
// поиск "робота" + определение размера лабиринта
    val file = mutableListOf<String>()
    var robotX = 0
    var robotY = 0
    var mazeX = 0
    var mazeY = 0
    for (string in File(inputName).readLines()) {
        mazeY++
        file.add(string)
        for (sign in string) {
            if (sign == '*') {
                robotX = mazeX
                robotY = mazeY
            }
            mazeX++
        }
    }
    val cords = robotY to robotX
    var xcoords = 0
    var ycoords = 0
    var yLast = ""
    for (cmd in commands) {
        for (y in file) {
            ycoords++
            if (ycoords == robotY) yLast = y
            for (x in file) {
                xcoords++

                if (ycoords == cords.first && xcoords == cords.second) {
                    if (cmd == 'r') {
                        if (x + 1 == "#" || xcoords + 1 > mazeX - 1) continue
                        else cords.second + 1
                    }

                }
            }
        }
    }
}
