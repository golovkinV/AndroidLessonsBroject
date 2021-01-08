package com.study.androidstudy.screens.timeline.add_timeline

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.study.androidstudy.R
import com.study.androidstudy.screens.timeline.adapter.formatMonth
import com.study.androidstudy.screens.timeline.model.TimelineData
import com.study.androidstudy.widget.DatePickerFragment
import kotlinx.android.synthetic.main.fragment_add_timeline.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class AddTimelineFragment: AppCompatDialogFragment() {

    private val viewModel: AddTimelineViewModel by viewModel()

    companion object {
        fun getInstance() = AddTimelineFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_timeline, container)

    override fun onStart() {
        super.onStart()
        dialog?.apply {
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCanceledOnTouchOutside(true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()

        val c = Calendar.getInstance()
        listOf(Calendar.HOUR,
            Calendar.MINUTE,
            Calendar.SECOND,
            Calendar.MILLISECOND).forEach { c.set(it, 0) }

        viewModel.date = c.timeInMillis
        dateEditText.setText(c.timeInMillis.formatMonth())
    }

    private fun setupListeners() {
        dateEditText.setOnClickListener {
            if (activity != null) {
                DatePickerFragment {
                    dateEditText.setText(it.formatMonth())
                    viewModel.date = it
                }.show(activity!!.supportFragmentManager, "datePicker")
            }
        }

        addBtn.setOnClickListener {
            viewModel.addNewItem(TimelineData(
                titleEditText.text.toString(),
                descEditText.text.toString(),
                viewModel.date
            ))
            dismiss()
        }

        titleEditText.addTextChangedListener ( object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    addBtn.isEnabled = s.isNotEmpty()
                }
            }
        })
    }
}