package ru.test.testdinamapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    private fun loadAttributes(): AttributeList{

        // создаем список
        var attributes = AttributeList()
        // генерируем атрибуты, случайное число 1..100
        attributes.generateRandomAttributesWithConstNames(maxCount = 10)
        println("Attributes count" + attributes.count())
        return attributes

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Базовый компонент на который будем размещать остальные комопненты
        var rootContainer =  findViewById<LinearLayout>(R.id.rootContainer)
        //Загружаем атрибуты, которые случайно сгенрированы
        val attributes =  loadAttributes()
        val componentPainter = ComponentPainter(this, rootContainer, attributes)
        // Рисуем все атрибуты на форме
        componentPainter.drawAttributes()

    }
}