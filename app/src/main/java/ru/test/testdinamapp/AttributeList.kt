package ru.test.testdinamapp

// Класс который работает со списками атрибутов
class AttributeList(){
    // Обьявляем изменяемый(Mutable) список, элементом списка будет атрибут
    // инициализируем с пустым списком. private ставим, чтобы
    // внешние классы не могли добраться до списк, и работа была только
    // с внутренней функцией

    private val attributes: MutableList<Attribute> =  mutableListOf<Attribute>()
    // Добавление в список нового атрибута
    fun add(new: Attribute){
        attributes.add(new)
    }

    fun get(index: Int): Attribute{
        return attributes.get(index)
    }

    // удалить атрибут из списка
    fun delete(attribute: Attribute){
        attributes.remove(attribute)
    }
    // Количество элементов
    fun count(): Int{
        return attributes.count()
    }
    // Вывести все элементы списка, выведено с помощью лямбда-выражения
    fun print(){
        attributes.forEach{entry -> entry.print()}  // Лямбда заменяет функцию fun print(a: Attribute){ a.print() }
    }

    // генерация случайной строки параметр длина строки, функция внутреняя поэтому директива private(скрыта извне)
    private fun generateRandomString(len: Int = 15): String{
        val alphanumerics = CharArray(26) { it -> (it + 97).toChar() }
            .toSet()
            .union(CharArray(9) { it -> (it + 48).toChar() }.toSet())
        return (0..len-1).map {alphanumerics.toList().random()}.joinToString("")
    }
    // тестовая функция, которая генерирует случайные атрибуты
    // и заполняет наш список рандомными атрибутами
    // параметр максимальное количество атрибутов
    fun generateRandomAttributes(maxCount: Int = 50){
        val randomAttributesCount = (0..maxCount).random()
        // количество случайных атрибутов равно количество сгенерованных случайных чисел
        for (i in 1..randomAttributesCount){
            // генерация случайной строки
            val randomName = generateRandomString()
            // генерация случайного значения перечесления
            val randomTypeIndex = (0..AttributeType.values().size-1).random()
            // создаем атрибут
            val newAttribute = Attribute(i, randomName, AttributeType.values()[randomTypeIndex], i)
            // добавляем в список сгенерирвоанный атрибут
            this.add(newAttribute)
        }
    }
    fun generateRandomAttributesWithConstNames(maxCount: Int = 10){
        for (i in 1..maxCount){
            val randomTypeIndex = (0..AttributeType.values().size-1).random()
            val newAttribute = Attribute(i, "Attribute $i", AttributeType.values()[randomTypeIndex], i)
            this.add(newAttribute)
        }
    }

}