package ru.test.testdinamapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
        textView.width = 400
        textView.height = 120
        horizontalLayout.addView(textView)
        return horizontalLayout
    }

    private fun formatDate(date: Calendar): String {
        val formatter = SimpleDateFormat("dd . MM . yyyy")
        return formatter.format(date.time)
    }
    private fun formatTime(time: Calendar): String{
        val formatter = SimpleDateFormat("hh : mm")
        return formatter.format(time.time)
    }
    private fun showTimePickerDialog(textView: TextView){
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)
        val bool = true
        val sec = c.get(Calendar.SECOND)

        val tpd = TimePickerDialog(activity, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute   ->
            c.set(hourOfDay, minute)
            textView.setText(formatTime(c))
        }, hour, minute, bool)

        tpd.show()
    }
    private fun showDatePickerDialog(textView: TextView) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            c.set(year, monthOfYear, dayOfMonth)
            textView.setText(formatDate(c))
        }, year, month, day)

        dpd.show()
    }
//создание Boolean элемента
    private fun createBooleanEditor(horizontalLayout: LinearLayout){
        val checkBox = CheckBox(activity)
        checkBox.width = 600
        checkBox.height = 120
        horizontalLayout.addView(checkBox)
    }
//создание числового элемента
    private fun createIntegerEditor(horizontalLayout: LinearLayout){
        val textView = TextView(activity)
        textView.width = 600
        textView.height = 120
        textView.text = "Int"
        horizontalLayout.addView(textView)
    }
//создание строкового элемента
    private fun createStringEditor(horizontalLayout: LinearLayout){
        val stringText = TextView(activity)
        stringText.width = 600
        stringText.height = 120
        stringText.text = "String"
        horizontalLayout.addView(stringText)
    }
//создание элемента даты
    private fun createDateEditor(horizontalLayout: LinearLayout){

        val dateView = TextView(activity)
        val timeView = TextView(activity)
        dateView.setText("Set Date")
        dateView.width = 300
        dateView.height = 120
        dateView.setOnClickListener {
            showDatePickerDialog(dateView)
        }
        timeView.setText("Set Time")
        timeView.height = 120
        timeView.setOnClickListener {
            showTimePickerDialog(timeView)
         }
        horizontalLayout.addView(dateView)
        horizontalLayout.addView(timeView)
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