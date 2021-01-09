package com.study.androidstudy.screens.timeline.detail_timeline

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.study.androidstudy.screens.base.BaseFragment
import com.study.androidstudy.R
import com.study.androidstudy.screens.timeline.adapter.formatMonth
import com.study.androidstudy.screens.timeline.model.TimelineData
import kotlinx.android.synthetic.main.fragment_add_timeline.*
import kotlinx.android.synthetic.main.fragment_add_timeline.dateEditText
import kotlinx.android.synthetic.main.fragment_add_timeline.descEditText
import kotlinx.android.synthetic.main.fragment_add_timeline.titleEditText
import kotlinx.android.synthetic.main.fragment_calculation.*
import kotlinx.android.synthetic.main.fragment_calculation.toolbar
import kotlinx.android.synthetic.main.fragment_detail_timeline.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class DetailTimelineFragment: BaseFragment() {
    override val layoutResource: Int = R.layout.fragment_detail_timeline

    private val viewModel: DetailTimelineViewModel by viewModel()

    companion object {
        private const val TIMELINE = "TIMELINE_DATA"
        fun getInstance(data: TimelineData) = DetailTimelineFragment().apply {
            arguments = Bundle().apply {
                putSerializable(TIMELINE, data)
            }
        }
    }

    private val data: TimelineData by lazy {
        (arguments?.getSerializable(TIMELINE) as? TimelineData) ?: TimelineData()
    }

    override fun setupListeners() {
        doneBtn.setOnClickListener {
            viewModel.doneTimeline(data)
            onBackPressed()
        }
    }

    override fun setupViews() {
        doneBtn.visibility =
            if (data.isDone) View.GONE
            else View.VISIBLE

        toolbar.apply {
            setNavigationOnClickListener { onBackPressed() }
            setNavigationIcon(R.drawable.ic_back)
            title = ""
        }

        titleEditText.setText(data.title)
        descEditText.setText(data.desc)
        dateEditText.setText(data.date.formatMonth())

        val today = System.currentTimeMillis()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = today
        calendar.set(Calendar.HOUR, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        if (data.date < calendar.timeInMillis && !data.isDone) {
            dateEditText.setTextColor(ContextCompat.getColor(requireContext(), R.color.pass_time))
        }
    }
}