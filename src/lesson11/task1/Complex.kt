@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import lesson2.task1.rookOrBishopThreatens

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
        private fun takeRealPart(s: String): Double =
            (if (s[0] == '-') -1.0 else 1.0) * s.split(
                s.findLast { it == '+' || it == '-' }.toString()
            )[0].toDouble()

        private fun takeImaginaryPart(s: String): Double =
            (if (s.findLast { it == '-' } == '-') -1.0 else 1.0) * s.split(
                s.findLast { it == '+' || it == '-' }.toString()
            )[1].filter { it != 'i' }.toDouble()
    }

    constructor(s: String) : this(
        takeRealPart(s),
        takeImaginaryPart(s)
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
}

