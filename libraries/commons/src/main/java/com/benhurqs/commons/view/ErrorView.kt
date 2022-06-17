package com.benhurqs.commons.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.benhurqs.commons.R
import com.benhurqs.network.ErrorType
import kotlinx.android.synthetic.main.error_view.view.*

class ErrorView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs){

    private var view: View = inflate(context, R.layout.error_view, null)

    init {
        this.addView(view)
        this.visibility = GONE
    }

    fun setup(type: ErrorType, onClickTryAgain: () -> Unit){
        this.visibility = VISIBLE
        error_message.text = type.value

        try_again.setOnClickListener {
            this.visibility = GONE
            onClickTryAgain.invoke()
        }
    }
}