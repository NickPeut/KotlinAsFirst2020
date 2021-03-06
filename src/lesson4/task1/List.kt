@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.numberRevert
import lesson3.task1.isPrime
import java.lang.StringBuilder
import kotlin.math.pow
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var ans = 0.0
    for (i in v) {
        ans += i * i
    }
    return sqrt(ans)
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty()) return 0.0
    var ans = 0.0
    for (i in list) {
        ans += i
    }
    return ans / list.size
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val k = mean(list)
    for (i in 0 until list.size)
        list[i] -= k
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int = (a zip b).map { (x, y) -> x * y }.sum()

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var ans = 0
    var y = 1
    for (i in p) {
        ans += y * i
        y *= x
    }
    return ans
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    var sum = 0
    for (i in 0 until list.size) {
        sum += list[i]
        list[i] = sum
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val ans = mutableListOf<Int>()
    var number = n
    var k = 2
    while (number > 1) {
        while (number % k == 0) {
            ans.add(k)
            number /= k
        }
        k++
    }
    if (ans.isEmpty()) return listOf(n)
    return ans
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(number: Int, base: Int): List<Int> {
    var n = number
    val ans = mutableListOf<Int>()
    do {
        ans.add(n % base)
        n /= base
    } while (n > 0)
    return ans.reversed()
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(number: Int, base: Int): String =
    convert(number, base).map { if (it > 9) 'a' + it - 10 else '0' + it }.joinToString("")

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var ans = 0
    for (i in digits) ans = ans * base + i
    return ans
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var k = 1
    var ans = 0
    for (i in 1..str.length) {
        ans += if (str[str.length - i] in '0'..'9') {
            (str[str.length - i] - '0') * k
        } else {
            (str[str.length - i] - 'a' + 10) * k
        }
        k *= base
    }
    return ans
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(a: Int): String {
    val number = listOf(
        1000 to "M",
        900 to "CM",
        500 to "D",
        400 to "CD",
        100 to "C",
        90 to "XC",
        50 to "L",
        40 to "XL",
        10 to "X",
        9 to "IX",
        5 to "V",
        4 to "IV",
        1 to "I"
    )
    var num = a
    val ans = StringBuilder()
    for (i in number) {
        while (num >= i.first) {
            ans.append(i.second)
            num -= i.first
        }
        if (num <= 0) break
    }
    return ans.toString()
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999 999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */

fun getNum(m: Int, flag: Boolean): MutableList<String> {
    val ans = mutableListOf<String>()
    if (m % 100 < 10 || m % 100 > 19) {
        when (m % 10) {
            1 -> {
                if (flag) ans.add("одна") else ans.add("один")
            }
            2 -> {
                if (flag) ans.add("две") else ans.add("два")
            }
            3 -> ans.add("три")
            4 -> ans.add("четыре")
            5 -> ans.add("пять")
            6 -> ans.add("шесть")
            7 -> ans.add("семь")
            8 -> ans.add("восемь")
            9 -> ans.add("девять")
        }
        when ((m / 10) % 10) {
            2 -> ans.add("двадцать")
            3 -> ans.add("тридцать")
            4 -> ans.add("сорок")
            5 -> ans.add("пятьдесят")
            6 -> ans.add("шестьдесят")
            7 -> ans.add("семьдесят")
            8 -> ans.add("восемьдесят")
            9 -> ans.add("девяносто")
        }
    } else {
        when (m % 100) {
            10 -> ans.add("десять")
            11 -> ans.add("одиннадцать")
            12 -> ans.add("двенадцать")
            13 -> ans.add("тринадцать")
            14 -> ans.add("четырнадцать")
            15 -> ans.add("пятнадцать")
            16 -> ans.add("шестнадцать")
            17 -> ans.add("семнадцать")
            18 -> ans.add("восемнадцать")
            19 -> ans.add("девятнадцать")
        }
    }
    when ((m / 100) % 10) {
        1 -> ans.add("сто")
        2 -> ans.add("двести")
        3 -> ans.add("триста")
        4 -> ans.add("четыреста")
        5 -> ans.add("пятьсот")
        6 -> ans.add("шестьсот")
        7 -> ans.add("семьсот")
        8 -> ans.add("восемьсот")
        9 -> ans.add("девятьсот")
    }

    return ans
}

fun russian(n: Int): String {
    var m = n
    if (m == 0) return "ноль"
    val ans = getNum(m, false)
    m /= 1000
    if (m != 0) {
        if (m % 100 < 10 || m % 100 > 19)
            when (m % 10) {
                1 -> ans.add("тысяча")
                2, 3, 4 -> ans.add("тысячи")
                5, 6, 7, 8, 9, 0 -> ans.add("тысяч")
            }
        else
            ans.add("тысяч")
        val res = getNum(m, true)
        ans += res
    }
    ans.reverse()
    return ans.joinToString(separator = " ")
}