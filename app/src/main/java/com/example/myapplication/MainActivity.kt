package com.example.myapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.widget.TextView
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.android.parcel.Parcelize
import java.security.Key


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var state: State

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also{setContentView(it.root)}

        binding.tabButton.setOnClickListener{ increment() }

        binding.button.setOnClickListener { decrement() }

        state = savedInstanceState?.getParcelable(KEY_STATE) ?: State(
            counterValue = 0
        )

        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, state)
    }

    fun increment(){
        state.counterValue++
        renderState()
    }

    fun decrement(){
        state.counterValue--
        renderState()
    }

    private fun renderState() = with(binding){
        numberView.setText(state.counterValue.toString())
    }

    @Parcelize
    class State(
        var counterValue: Int,
    ):Parcelable

    companion object{
        @JvmStatic private val KEY_STATE = "STATE"
    }

}


