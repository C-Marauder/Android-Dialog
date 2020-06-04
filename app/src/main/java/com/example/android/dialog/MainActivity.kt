package com.example.android.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xhh.dialog.AndroidDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showView.setOnClickListener {
//            AndroidDialog.builder(R.layout.dialog_confirm){
//                setCornerRadius {
//                    floatArrayOf(24f,24f,24f,24f,24f,24f,24f,24f)
//                }
//            }.alert(supportFragmentManager,"")
            AndroidDialog.builder(R.layout.dialog_bottom,AndroidDialog.BOTTOM_SHEET){
                setCornerRadius {
                    floatArrayOf(24f,24f,24f,24f,0f,0f,0f,0f)
                }
                onDismiss {
                    Log.e("==","===>>")
                }
               // setSize(-1,300)
            }.alert(supportFragmentManager,"")
        }

    }
}
