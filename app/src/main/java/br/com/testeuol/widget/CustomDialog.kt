package br.com.testeuol.widget

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.TextView
import br.com.testeuol.R
import com.google.android.material.button.MaterialButton


class CustomDialog(context : Context) : Dialog(context) {

    var title : String? = null
    var message : String? = null
    var customDialogListener: (CustomDialog) -> Unit={it.dismiss()}

    private lateinit var tvCustomDialog: TextView
    private lateinit var tvCustomMessage: TextView
    private lateinit var btCustomDialog: MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        tvCustomDialog = findViewById(R.id.tvCustomDialogTitle)
        tvCustomMessage = findViewById(R.id.tvCustomDialogMsg)
        btCustomDialog = findViewById(R.id.btCustomDialog)

        if(!title.isNullOrEmpty()) tvCustomDialog.text = title
        if(!message.isNullOrEmpty()) tvCustomMessage.text = message
        btCustomDialog.setOnClickListener {
            customDialogListener(this)
        }

    }

    fun setOnCustomDialogListener(lister: (CustomDialog) -> Unit): CustomDialog {
        this.customDialogListener = lister
        return this
    }
}