package com.study.androidstudy.screens.timeline

import androidx.lifecycle.MutableLiveData
import com.study.androidstudy.screens.base.BaseViewModel
import com.study.androidstudy.screens.timeline.controllers.TimelineController
import com.study.androidstudy.screens.timeline.model.TimelineData

class TimelineViewModel(
    private val timelineController: TimelineController
): BaseViewModel() {

    val timelineData = MutableLiveData<MutableList<TimelineData>>()
    private val items = mutableListOf(
        TimelineData("Купить продукты", "1) Яйца\n2) Масло\n3) Хлеб\n4) Молоко",  1605812400000L, true),
        TimelineData("Занятия", "1) Лекция по СППР\n2) Практика по мобильной разработке", 1607540400000L),
        TimelineData("Netflix", "Оплатить подписку",  1606762800000L),
        TimelineData("Химчистка", "Забрать пальто", 1606071600000L, true),
        TimelineData("Сериал", "Скачать новые серии перед поездкой", 1609959600000L, true)
    )

    init {
        timelineData.value = items
        timelineController.timelines
            .skip(1)
            .subscribe {
                items.add(it)
                timelineData.value = items
            }
            .untilDestroy()
    }

}