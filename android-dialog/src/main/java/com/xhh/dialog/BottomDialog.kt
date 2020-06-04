package com.xhh.dialog

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

internal class BottomDialog(private val builder: AndroidDialog.Builder): BottomSheetDialogFragment(),
    AndroidDialog {

    companion object{
        fun getInstance(builder: AndroidDialog.Builder): BottomDialog {
            return BottomDialog(builder)
        }
    }
    private var onHandle:((view:View)->Unit)?=null
    private var onBackPressed:(()->Boolean)?=null
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
        this.onHandle?.invoke(view)
    }


    override val isAlerting: Boolean
        get() = isResumed

    override fun observeOnBackPressed(interrupted: () -> Boolean):AndroidDialog {
        this.onBackPressed = interrupted
        return this
    }

    override fun onHandle(handle: (view:View) -> Unit): AndroidDialog {
        this.onHandle = handle
        return this
    }

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