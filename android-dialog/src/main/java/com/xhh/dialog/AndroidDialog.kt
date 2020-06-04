package com.xhh.dialog

import android.view.View
import androidx.annotation.AnimRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentManager

interface AndroidDialog{
    companion object{
        const val DEFAULT:Int = 1
        const val FULL_SCREEN:Int = 2
        const val BOTTOM_SHEET:Int = 3

        fun builder(@LayoutRes layoutId: Int,dialogType:Int?=null,init: Builder.()->Unit)= Builder(layoutId,dialogType).apply(init).build()
    }
    val isAlerting:Boolean
    fun observeOnBackPressed(interrupted:()->Boolean):AndroidDialog
    fun onHandle(handle:View.()->Unit):AndroidDialog
    fun alert(fm:FragmentManager,tag:String?)
    fun hide()

    class Builder(@LayoutRes val layoutId:Int,internal val style:Int?= DEFAULT){
        internal var dWidth:Int = -2
        internal var dHeight:Int = -2
        internal var dCancel:Boolean? = null
        internal var amount:Float?=null
        internal var cornerRadius:FloatArray?=null
        internal var onDismiss:(()->Unit)?=null
        @AnimRes
        internal var anim:Int?=null
        fun setSize(width:Int,height:Int){
            this.dWidth = width
            this.dHeight= height
        }
        fun setCanceledOnTouchOutside(cancel:Boolean){
            this.dCancel = cancel
        }
        fun setDimAmount(init: () -> Float){
            this.amount = init()
        }
        fun setAnim(init: () -> Int){
            this.anim = init()
        }
        fun setCornerRadius(init: () ->FloatArray){
            this.cornerRadius = init()
        }
        fun onDismiss(init: () -> Unit){
            this.onDismiss = init
        }
        internal fun build(): AndroidDialog =when(style){
            BOTTOM_SHEET -> BottomDialog.getInstance(this)
            else -> DefaultDialog.getInstance(this)
        }
    }
    
}