package ru.test.testdinamapp

import android.app.DatePickerDialog
import android.view.View
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

    private fun formatDate(date: Calendar): String {
        val formatter = SimpleDateFormat("dd MM yyyy")
        return formatter.format(date.time)
    }

    private fun showDatePickerDialog(textView: TextView) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { vi, year, monthOfYear, dayOfMonth ->
            c.set(year, monthOfYear, dayOfMonth)
            textView.setText(formatDate(c))
        }, year, month, day)

        dpd.show()
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

        val textView = TextView(activity)
        textView.setText("10.12.2021 12:23:37");
        textView.width = 600

        textView.setOnClickListener {
            showDatePickerDialog(textView)
        }
        horizontalLayout.addView(textView)
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

        // Перебираем атрибуты и добавляем элементы в список
        for (i in 0..attributeList.count() - 1) {
            val attribute = attributeList.get(i)
            createEditor(attribute)
        }
    }

}