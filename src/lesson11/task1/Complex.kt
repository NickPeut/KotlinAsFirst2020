@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import lesson2.task1.rookOrBishopThreatens
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.util.function.BooleanSupplier

/**
 * Класс "комплексное число".
 *
 * Общая сложность задания -- лёгкая, общая ценность в баллах -- 8.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(val re: Double, val im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)

    /**
     * Конструктор из строки вида x+yi
     */


    companion object {
        fun correctString(s: String): List<Double> {
            val ans = Regex("(-?\\d+(?:\\.\\d+)?)(?:([-+]\\d+(?:\\.\\d+)?)i)").matchEntire(s)
                ?: throw IllegalArgumentException()
            return listOf(ans.groupValues[1].toDouble(), ans.groupValues[3].toDouble())
        }
    }

    constructor(s: String) : this(
        correctString(s)[0], correctString(s)[1]
    )

    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex = Complex(re + other.re, im + other.im)

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = Complex(-re, -im)

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = Complex(re - other.re, im - other.im)

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex =
        Complex(re * other.re - im * other.im, re * other.im + im * other.re)

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex = Complex(
        ((re * other.re) + (im * other.im)) / ((other.re * other.re) + (other.im * other.im)),
        ((other.re * im) - (re * other.im)) / ((other.re * other.re) + (other.im * other.im))
    )

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Complex) return false
        return other.im == im && other.re == re
    }

    /**
     * Преобразование в строку
     */
    override fun toString(): String = "" + re + if (im > 0) "+" else {
        ""
    } + im + "i"

    override fun hashCode(): Int = re.hashCode() * 31 + im.hashCode()
}

