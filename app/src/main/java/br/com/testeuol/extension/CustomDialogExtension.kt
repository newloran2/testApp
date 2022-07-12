package br.com.testeuol.extension

import android.content.Context
import br.com.testeuol.widget.CustomDialog

fun Context.showDialog(
    title : String,
    message : String,
    listenerDialog : ((CustomDialog)-> Unit)
){
    val dialog = CustomDialog(this).apply {
        this.title = title
        this.message = message
        this.customDialogListener = listenerDialog

    }

    dialog.setOnCustomDialogListener{
        dialog.customDialogListener = listenerDialog
        dialog.dismiss()
    }

    dialog.show()


}