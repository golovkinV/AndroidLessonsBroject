package com.study.androidstudy.screens.hello

import com.study.androidstudy.screens.base.BaseFragment
import com.study.androidstudy.R
import kotlinx.android.synthetic.main.fragment_hello.*
import org.koin.android.viewmodel.ext.android.viewModel

class HelloFragment : BaseFragment() {
    override val layoutResource: Int = R.layout.fragment_hello

    companion object {
        fun newInstance() = HelloFragment()
    }

    private val viewModel: HelloViewModel by viewModel()

    override fun setupListeners() {
        with(viewModel) {
            message.subscribe {
                showToast(it)
            }

            keyboard.subscribe {
                if (it) {
                    hideKeyboard()
                }
            }
        }

        helloBtn.setOnClickListener {
            viewModel.showMessage(nameEditText.text.toString())
        }

        calcBtn.setOnClickListener {
            viewModel.routeToCalculation()
        }
    }
}