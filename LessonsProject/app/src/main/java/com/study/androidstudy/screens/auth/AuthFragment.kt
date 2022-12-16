package com.study.androidstudy.screens.auth

import android.widget.Toast
import com.study.androidstudy.R
import com.study.androidstudy.screens.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.fragment_auth.*

class AuthFragment: BaseFragment() {

    override val layoutResource: Int = R.layout.fragment_auth

    private val viewModel: AuthViewModel by viewModel()

    companion object {
        fun newInstance() = AuthFragment()
    }

    override fun setupListeners() {
        btnAuthEmail.setOnClickListener {
            if (etEmail.text.isNullOrEmpty() || etPassword.text.isNullOrEmpty()) {
                Toast
                    .makeText(context, "Заполните Email и пароль", Toast.LENGTH_SHORT)
                    .show()
            } else {
                viewModel.routeToCalculation()
            }
        }
    }
}