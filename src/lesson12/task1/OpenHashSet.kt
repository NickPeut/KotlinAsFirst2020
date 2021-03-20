@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

/**
 * Класс "хеш-таблица с открытой адресацией"
 *
 * Общая сложность задания -- сложная, общая ценность в баллах -- 20.
 * Объект класса хранит данные типа T в виде хеш-таблицы.
 * Хеш-таблица не может содержать равные по equals элементы.
 * Подробности по организации см. статью википедии "Хеш-таблица", раздел "Открытая адресация".
 * Методы: добавление элемента, проверка вхождения элемента, сравнение двух таблиц на равенство.
 * В этом задании не разрешается использовать библиотечные классы HashSet, HashMap и им подобные,
 * а также любые функции, создающие множества (mutableSetOf и пр.).
 *
 * В конструктор хеш-таблицы передаётся её вместимость (максимальное количество элементов)
 */
class OpenHashSet<T>(val capacity: Int) {

    /**
     * Массив для хранения элементов хеш-таблицы
     */
    internal val elements = Array<Any?>(capacity) { null }

    /**
     * Число элементов в хеш-таблице
     */

    var size: Int = 0
        private set

    /**
     * Признак пустоты
     */
    fun isEmpty(): Boolean = size == 0

    /**
     * Добавление элемента.
     * Вернуть true, если элемент был успешно добавлен,
     * или false, если такой элемент уже был в таблице, или превышена вместимость таблицы.
     */
    fun add(element: T): Boolean {
        if (size == capacity) return false
        var start = hash(element)
        while (elements[start] != null && elements[start] != element) {
            start = (start + 1) % capacity
        }
        return if (elements[start] == null) {
            elements[start] = element
            size++
            true
        } else
            false
    }

    private fun hash(element: T): Int = (element.hashCode() and 0x7fffffff) % capacity
    /**
     * Проверка, входит ли заданный элемент в хеш-таблицу
     */
    operator fun contains(element: T): Boolean {
        val start = hash(element)
        var ind = start
        while (elements[ind] != element && elements[ind] != null) {
            ind = (ind + 1) % capacity
            if (ind == start)
                return false
        }
        return elements[ind] == element
    }

    /**
     * Таблицы равны, если в них одинаковое количество элементов,
     * и любой элемент из второй таблицы входит также и в первую
     */
    override fun equals(other: Any?): Boolean {
        if (other === this)
            return true
        if (other == null || other !is OpenHashSet<*> || other.size != size)
            return false
        for (i in other.elements) {
            if (!contains(i as T))
                return false
        }
        return true
    }

    override fun hashCode(): Int = elements.sumBy { it.hashCode() }
}