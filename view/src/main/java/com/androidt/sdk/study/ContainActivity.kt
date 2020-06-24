package com.androidt.sdk.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.androidt.sdk.study.textview.TextViewFragment

class ContainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contain)

        val args = intent.getStringExtra(ARGS)

        when (args) {
            "TextView" -> {
                supportFragmentManager.beginTransaction().replace(R.id.content, TextViewFragment()).commit()
            }
            "EditView" -> {

            }
        }
    }

    companion object{
        const val ARGS = "args"
    }
}
