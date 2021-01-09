package com.study.androidstudy.screens.timeline

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.study.androidstudy.screens.base.BaseFragment
import com.study.androidstudy.R
import com.study.androidstudy.screens.timeline.adapter.TimelineAdapter
import com.study.androidstudy.screens.timeline.add_timeline.AddTimelineFragment
import kotlinx.android.synthetic.main.fragment_timeline.*
import org.koin.android.viewmodel.ext.android.viewModel

class TimelineFragment : BaseFragment() {

    override val layoutResource: Int = R.layout.fragment_timeline

    private val timelineAdapter by lazy {
        TimelineAdapter {
            viewModel.openDetailTimeline(it)
        }
    }

    private val viewModel: TimelineViewModel by viewModel()

    companion object {
        fun newInstance() = TimelineFragment()
    }

    override fun setupViews() {
        timelineRecycler.apply {
            adapter = timelineAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    override fun setupListeners() {
        with(viewModel) {
            timelineData.subscribe {
                timelineAdapter.set(it.sortedBy { it.date }.toList())
            }

            toastData.subscribe {
                Toast.makeText(requireContext(), "Поздравляем! Задача выполнена.", Toast.LENGTH_SHORT).show()
            }
        }

        addTimer.setOnClickListener {
            if (activity != null) {
                val manager = activity!!.supportFragmentManager
                AddTimelineFragment.getInstance().show(manager, "atf")
            }
        }
    }
}