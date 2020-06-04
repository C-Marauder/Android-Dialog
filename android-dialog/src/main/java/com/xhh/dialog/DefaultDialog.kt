package com.xhh.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.FragmentManager

internal class DefaultDialog(private val builder: AndroidDialog.Builder):AppCompatDialogFragment(),
    AndroidDialog {
    companion object{
        fun getInstance(builder: AndroidDialog.Builder): DefaultDialog {
            return DefaultDialog(builder)
        }
    }
    private var onBackPressed:(()->Boolean)?=null
    private var onHandle:((view:View)->Unit)?=null
    private lateinit var dialogWrapper: DialogWrapper
    override fun onAttach(context: Context) {
        super.onAttach(context)
        dialogWrapper = DialogWrapper.getInstance(this)



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(builder.layoutId,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onHandle?.invoke(view)
    }


    override fun onHandle(handle: View.() -> Unit): AndroidDialog {
        this.onHandle = handle
        return this
    }

    override fun observeOnBackPressed(interrupted: () -> Boolean): AndroidDialog {
        this.onBackPressed = interrupted
        return this
    }


    override val isAlerting: Boolean
        get() = isResumed

    override fun onStart() {
        super.onStart()
        dialogWrapper.init(builder)
        onBackPressed?.let {
            dialogWrapper.interruptOnBackPressed(it)
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        builder.onDismiss?.invoke()
    }

    override fun alert(fm: FragmentManager, tag: String?) {
        show(fm,tag)
    }

    override fun hide() {
        dismiss()
    }

}