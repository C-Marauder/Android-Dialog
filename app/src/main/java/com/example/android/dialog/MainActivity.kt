package com.example.android.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xhh.dialog.AndroidDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var dialog:AndroidDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showView.setOnClickListener {
            dialog = AndroidDialog.builder(R.layout.dialog_confirm){
                setAnim {
                    R.style.DialogAnim
                }

                setCanceledOnTouchOutside(false)
                setDimAmount {
                    1f
                }
                setSize(300,400)


                setCornerRadius {
                    floatArrayOf(24f,24f,24f,24f,24f,24f,24f,24f)
                }
            }
            dialog.alert(supportFragmentManager,"")
            dialog.hide()
            AndroidDialog.builder(
                R.layout.dialog_full_screen,
                AndroidDialog.FULL_SCREEN
            ) {


                // setSize(-1,300)
            }.onHandle {

                Log.e("=onHandle=", "==111==")

            }.alert(supportFragmentManager, "")
        }

    }
}
