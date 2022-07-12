package br.com.testeuol.feature.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.testeuol.databinding.FragmentGalleryPhotoProfileBinding
import br.com.testeuol.feature.home.viewmodel.GalleryPhotoProfileViewModel
import org.koin.android.ext.android.inject

class GalleryPhotoProfileFragment : Fragment(){

    private lateinit var binding: FragmentGalleryPhotoProfileBinding
    private val viewModel by inject<GalleryPhotoProfileViewModel>()
    private val args : GalleryPhotoProfileFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGalleryPhotoProfileBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uniUi()
    }

    private fun uniUi() {

        setViewModelInit()
    }

    private fun setViewModelInit() {
        viewModel.setImageViewProfile(binding.imageProfile, args.urlImage.toString())
    }

}