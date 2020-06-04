package com.example.android.dialog

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.xhh.dialog.AndroidDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var dialog:AndroidDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showView.setOnClickListener {
//            dialog = AndroidDialog.builder(R.layout.dialog_confirm){
//                setAnim {
//                    R.style.DialogAnim
//                }
//
//                setCanceledOnTouchOutside(false)
//                setDimAmount {
//                    1f
//                }
//                setSize(300,400)
//
//
//                setCornerRadius {
//                    floatArrayOf(24f,24f,24f,24f,24f,24f,24f,24f)
//                }
//            }
//            dialog.alert(supportFragmentManager,"")
//            dialog.hide()
            dialog = AndroidDialog.builder(
                R.layout.dialog_full_screen,
                AndroidDialog.FULL_SCREEN
            ) {
                setCanceledOnTouchOutside(false)

                // setSize(-1,300)
            }.onHandle {

                Log.e("=onHandle=", "==111==")

            }.observeOnBackPressed {
                Toast.makeText(this,"拦截物理返回键",Toast.LENGTH_LONG).show()
                true
            }
            dialog.alert(supportFragmentManager, "")
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}

class MyFragment:AppCompatDialogFragment(){
    companion object{
        fun getInstance():MyFragment{
            return MyFragment()
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(this,
            object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                Log.e("===","==MyFragment=")
            }

        })
    }
}
