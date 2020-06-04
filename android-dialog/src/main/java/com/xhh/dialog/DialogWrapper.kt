package com.xhh.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment.STYLE_NO_TITLE
import com.xhhandroid.dialog.R

internal class DialogWrapper private constructor(
    private var dialogFragment: AppCompatDialogFragment
) {
    companion object {
        private lateinit var dialogWrapper: DialogWrapper
        fun getInstance(dialogFragment: AppCompatDialogFragment): DialogWrapper {
            dialogFragment.setStyle(STYLE_NO_TITLE, 0)
            if (!::dialogWrapper.isInitialized) {
                dialogWrapper = DialogWrapper(dialogFragment)
            } else {
                dialogWrapper.dialogFragment = dialogFragment
            }
            return dialogWrapper
        }
    }

    private fun setCornerRadius(
        builder: AndroidDialog.Builder,
        end: (drawable: GradientDrawable) -> Unit
    ) {
        builder.cornerRadius?.let { radius ->
            val gradientDrawable = GradientDrawable().apply {
                setColor(Color.WHITE)
                cornerRadii = radius
            }
            end(gradientDrawable)

        }
    }

    fun init(builder: AndroidDialog.Builder) {
        dialogFragment.dialog?.let { d ->
            builder.dCancel?.let { cancel ->
                d.setCanceledOnTouchOutside(cancel)

            }
            d.window?.let { win ->
                builder.amount?.let { amount ->
                    win.setDimAmount(amount)
                }

                when (builder.style) {
                    AndroidDialog.FULL_SCREEN -> {
                        if (builder.anim == null){
                            win.setWindowAnimations(R.style.FullScreenDialogAnim)

                        }else{
                            win.setWindowAnimations(builder.anim!!)
                        }

                        win.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(dialogFragment.context!!,android.R.color.background_light)))
                        win.setLayout(-1,-1)
                    }
                    AndroidDialog.BOTTOM_SHEET -> {
                        win.setWindowAnimations(R.style.Animation_Design_BottomSheetDialog)
                        setCornerRadius(builder) {
                            (dialogFragment.view?.parent as View).background = it

                        }
                        win.setLayout(-1, builder.dHeight)
                    }
                    AndroidDialog.DEFAULT -> {
                        setCornerRadius(builder) {
                            win.setBackgroundDrawable(it)
                        }
                        if (builder.anim == null) {
                            win.setWindowAnimations(R.style.DialogAnim)
                        } else {
                            win.setWindowAnimations(builder.anim!!)
                        }

                        if (builder.dWidth == -2) {
                            builder.dWidth =
                                (0.85 * d.context.resources.displayMetrics.widthPixels).toInt()
                        }
                        win.setLayout(builder.dWidth, builder.dHeight)
                    }
                }


            }

        }
    }
}

