@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import java.util.zip.DeflaterOutputStream
import kotlin.math.*

// Урок 3: циклы
// Максимальное количество баллов = 9
// Рекомендуемое количество баллов = 7
// Вместе с предыдущими уроками = 16/21

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая (2 балла)
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    if (n == 0) return 1
    var ans = 1
    var num = abs(n / 10)
    while (num > 0) {
        ans++
        num /= 10
    }
    return ans
}

/**
 * Простая (2 балла)
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var a = 1
    var b = 2
    var c = 2
    if (n < 3) return 1
    if (n == 3) return 2
    for (k in 3 until n) {
        c = a + b
        a = b
        b = c
    }
    return c
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    if (n % 2 == 0) return 2
    for (i in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % i == 0) return i
    }
    return n
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)

/**
 * Простая (2 балла)
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    if (x == 1) return 0
    if (x % 2 == 0)
        return collatzSteps(x / 2) + 1
    return collatzSteps(3 * x + 1) + 1
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun gcd(m: Int, n: Int): Int {
    var a = n
    var b = m
    while (a * b != 0) {
        b %= a
        val k = a
        a = b
        b = k
    }
    return a + b
}

fun lcm(m: Int, n: Int): Int = n / gcd(n, m) * m

/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = gcd(n, m) == 1

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val l = sqrt(m.toDouble()).toInt()
    val r = sqrt(n.toDouble()).toInt()
    for (i in l..r) {
        if (sqr(i) in m..n) return true
    }
    return false
}

/**
 * Средняя (3 балла)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var ans = 0
    var k = 10
    while (n >= k / 10) {
        ans = ans * 10 + (n % k) / (k / 10)
        k *= 10
        if (k % 10 != 0) break
        //я не придумала как иначе обработать случай переполнения k так, чтобы не менять идею
    }
    return ans
}

/**
 * Средняя (3 балла)
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var m = 0
    var num = n
    while (num > 0) {
        m = m * 10 + num % 10
        num /= 10
    }
    return m == n
}

/**
 * Средняя (3 балла)
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    if (n < 10) return false
    val k = n % 10
    var num = n / 10
    while (num != 0 && k == num % 10) {
        num /= 10
    }
    return num != 0
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x1: Double, eps: Double): Double {
    var x = abs(x1) % (2 * PI)
    if (x1 < 0) x *= -1
    var ans = 0.0
    var fact = 1
    var k = 1.0
    var xs = x
    while (abs(xs / factorial(fact)) >= eps) {
        ans += k * (xs / factorial(fact))
        k = -k
        xs *= x * x
        fact += 2
    }
    return ans
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x1: Double, eps: Double): Double {
    val x = abs(x1) % (2 * PI)
    var ans = 0.0
    var fact = 0
    var k = 1.0
    var xs = 1.0
    while (xs / factorial(fact) > eps) {
        ans += k * (xs / factorial(fact))
        k = -k
        xs *= x * x
        fact += 2
    }
    return ans
}

/**
 * Сложная (4 балла)
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var k = 0
    var ans = 1
    for (i in 1..n) {
        k += digitNumber(i * i)
        if (k >= n) {
            val tmp = 10.0
            val count = tmp.pow(maxOf(k - n)).toInt()
            ans = (i * i) / count
            return ans % 10
        }
    }
    return ans % 10
}

/**
 * Сложная (5 баллов)
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var a = 1
    var b = 1
    var c: Int
    if (n <= 3) {
        return when (n) {
            3 -> 2
            else -> 1
        }
    }
    var k = 2
    var ans = 1
    for (i in 3..n + 1) {
        c = a + b
        a = b
        b = c
        var t = c
        while (t != 0) {
            k++
            t /= 10
        }
        if (k >= n) {
            val tmp = 10.0
            val count = tmp.pow(maxOf(k - n)).toInt()
            ans = c / count
            return ans % 10
        }
    }
    return ans % 10
}
