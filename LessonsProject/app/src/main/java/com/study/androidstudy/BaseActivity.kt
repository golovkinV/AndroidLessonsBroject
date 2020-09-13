package com.study.androidstudy

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // метод для установки отображаемого представления
        setContentView(layoutResource)

        setupViews()
        setupListeners()
    }

    /**
     *  В данной функции производится настройка представлений
     *  (Button, TextView и т.д.)
     */
    open fun setupViews() {}

    /**
     *  В данной функции определяем слушатели на определенные события представлений
     *  (Button, TextView и т.д.)
     */
    open fun setupListeners() {}

    /**
     *  Вывод стандартного всплывающего окна
     *
     *  @param content String передаем текст для отображения
     */
    fun showToast(content: String) {

        /*
        * context - позволяет получить доступ к ресурсам и классам для конкретных приложений, а также
        * предварительные вызовы для операций на уровне приложений, таких как запуск действий,
        * намерения вещания и приема и т. д. По сути, наш контейнер с ресурсами проекта
        *
        * content - наше сообщение
        *
        * Toast.LENGTH_LONG - указываем время отображения окна
        * (так же можно использовать Toast.LENGTH_SHORT)
        * */
        Toast.makeText(this, content, Toast.LENGTH_LONG).show()
    }

    fun hideKeyboard() {
        val inputManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        currentFocus?.let {
            inputManager?.hideSoftInputFromWindow(
                it.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}