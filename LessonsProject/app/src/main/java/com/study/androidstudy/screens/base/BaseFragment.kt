package com.study.androidstudy.screens.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.github.terrakok.cicerone.Router
import org.koin.android.ext.android.inject

abstract class BaseFragment : Fragment() {

    abstract val layoutResource: Int

    private val router by inject<Router>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(layoutResource, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupListeners()
    }

    open fun setupViews() = Unit

    open fun setupListeners() = Unit

    fun <T> MutableLiveData<T>.subscribe(action: (T) -> Unit) {
        observe(this@BaseFragment, Observer { it?.let { action(it) } })
    }

    fun LifecycleObserver.register() = lifecycle.addObserver(this)

    fun onBackPressed() = router.exit()

    fun showToast(content: String) =
        Toast.makeText(requireContext(), content, Toast.LENGTH_LONG).show()

    protected fun hideKeyboard() {
        activity?.also {
            val inputManager =
                it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            it.currentFocus?.let {
                inputManager?.hideSoftInputFromWindow(
                    it.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
    }
}