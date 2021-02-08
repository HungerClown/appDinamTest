package ru.test.testdinamapp

enum class AttributeType {
    INTEGER, BOOLEAN, DATE, STRING
}
// Класс атрибут описывает основное

class Attribute(val id: Int,
                val name: String,
                val type: AttributeType,
                var position: Int,
                private val dictionaryUin: String = "unknown",
                private val dictionaryUinTo: String = "unknown",
                private var filter: String = "unknown"){
    // вывод на консоль
    fun print(){
        print("Атрибут: ")
        print("[ID = $id],")
        print("[name = $name],")
        println("[type = $type]")
    }
}