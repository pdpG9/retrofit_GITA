package com.example.retrofit_gita.utils

import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import androidx.viewbinding.ViewBinding

fun EditText.myAddTextChangedListener(block: (String) -> Unit) {
    this.addTextChangedListener {
        it?.let {
            block.invoke(it.toString())
        }
    }
}
fun String.myLog(){
    Log.d("TTT", "myLog: $this")
}
fun EditText.amount() : String = this.text.toString().trim()
fun <T : ViewBinding> T.myApply(block: T.() -> Unit) {
    block(this)
}
fun AppCompatEditText.myAddTextChangedListener(block: (String) -> Unit) {
    this.addTextChangedListener {
        it?.let {
            block.invoke(it.toString())
        }
    }
}
fun Window.changeStatusBarColor(color:Int){
    this.statusBarColor = this.context.getColor(color)
}
fun Window.changeStatusBarTextColor(){
    this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    this.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
}
fun Window.changeNavigationBarColor(color:Int){
    this.navigationBarColor = this.context.getColor(color)
}