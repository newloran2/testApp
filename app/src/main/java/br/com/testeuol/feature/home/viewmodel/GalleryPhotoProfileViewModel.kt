package br.com.testeuol.feature.home.viewmodel

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import br.com.testeuol.R
import com.squareup.picasso.Picasso

class GalleryPhotoProfileViewModel() : ViewModel() {

    fun setImageViewProfile(image : ImageView , url : String) {

        Picasso.get()
            .load(url)
            .error(R.drawable.ic_warning)
            .into(image)
    }
}