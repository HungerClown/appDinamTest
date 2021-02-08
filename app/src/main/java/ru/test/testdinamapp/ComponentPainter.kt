package ru.test.testdinamapp

import android.app.DatePickerDialog
import android.text.Layout
import android.view.Gravity.CENTER_HORIZONTAL
import android.view.ViewGroup
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*

class ComponentPainter (val activity: MainActivity,
                        val rootContainer: LinearLayout,
                        val attributeList: AttributeList) {

    private fun createHorizontalLayout(attribute: Attribute): LinearLayout{
        val horizontalLayout = LinearLayout(activity)
        horizontalLayout.orientation = LinearLayout.HORIZONTAL
        // Создаем текст вью. Просмотр текста
        val textView = TextView(activity)
        textView.setText(attribute.name)
        textView.width = 300
        horizontalLayout.addView(textView)
        return horizontalLayout
    }
//создание Boolean элемента
    private fun createBooleanEditor(horizontalLayout: LinearLayout){
        val checkBox = CheckBox(activity)
        checkBox.width = 600
        horizontalLayout.addView(checkBox)
    }
//создание числового элемента
    private fun createIntegerEditor(horizontalLayout: LinearLayout){
        val textView = TextView(activity)
        textView.width = 600
        textView.text = "Int"
        horizontalLayout.addView(textView)
    }
//создание строкового элемента
    private fun createStringEditor(horizontalLayout: LinearLayout){
        val editorText = EditText(activity)
        editorText.width = 600
        horizontalLayout.addView(editorText)
    }
//создание элемента даты
    private fun createDateEditor(horizontalLayout: LinearLayout){

        var cal = Calendar.getInstance()
        val textView = TextView(activity)
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            textView.text = sdf.format(cal.time)

        }
    }


    // Рисование атрибута
    private fun createEditor(attribute: Attribute){
        var horizontalLayout = createHorizontalLayout(attribute)
        when (attribute.type) {
            AttributeType.BOOLEAN -> createBooleanEditor(horizontalLayout)
            AttributeType.INTEGER -> createIntegerEditor(horizontalLayout)
            AttributeType.STRING  -> createStringEditor(horizontalLayout)
            AttributeType.DATE    -> createDateEditor(horizontalLayout)
        }
        rootContainer.addView(horizontalLayout)

    }


    fun drawAttributes() {
        // Устанавливаем параметры
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        // Перебираем атрибуты и добавляем элементы в список
        for (i in 0..attributeList.count() - 1) {
            val attribute = attributeList.get(i)
            createEditor(attribute)
        }
    }

}