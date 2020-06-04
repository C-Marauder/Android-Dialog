package com.xhh.dialog

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
    private lateinit var dialogWrapper: DialogWrapper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogWrapper = DialogWrapper.getInstance(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(builder.layoutId,container,false)
    }

    override fun onStart() {
        super.onStart()
        dialogWrapper.init(builder)
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