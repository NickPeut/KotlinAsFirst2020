@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson9.task1

import java.lang.IllegalArgumentException

// Урок 9: проектирование классов
// Максимальное количество баллов = 40 (без очень трудных задач = 15)

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая (2 балла)
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> = MatrixImpl(height, width, e)


/**
 * Средняя сложность (считается двумя задачами в 3 балла каждая)
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {
    private val data = List(height) { MutableList(width) { e } }

    init {
        require(height > 0 && width > 0)
    }

    override fun get(row: Int, column: Int): E {
        return if (check(row, column)) data[row][column]
        else
            throw IllegalArgumentException()
    }

    private fun check(row: Int, column: Int): Boolean = row in 0 until height && column in 0 until width

    override fun get(cell: Cell): E = get(cell.row, cell.column)

    override fun set(row: Int, column: Int, value: E) {
        if (check(row, column))
            data[row][column] = value
        else
            throw IllegalArgumentException()
    }

    override fun set(cell: Cell, value: E) {
        set(cell.row, cell.column, value)
    }

    override fun equals(other: Any?) =
        other is MatrixImpl<*> && height == other.height && width == other.width && data == other.data


    override fun toString(): String =
        data.joinToString(
            prefix = "{Matrix h=${height}, w=${width}:\n", separator = ",\n", postfix = "}"
        ) {
            it.joinToString(prefix = " [", separator = ", ", postfix = "]")
        }

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + width
        result = 31 * result + data.hashCode()
        return result
    }
}

