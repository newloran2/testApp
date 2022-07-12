package br.com.testeuol.feature.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.testeuol.R
import br.com.testeuol.databinding.FragmentProfileLinkWebBinding
import br.com.testeuol.feature.home.viewmodel.GalleryLinkWebViewModel
import br.com.testeuol.feature.home.viewmodel.GalleryPhotoProfileViewModel
import org.koin.android.ext.android.inject

class GalleryLinkWebViewFragment : Fragment() {

    private lateinit var binding: FragmentProfileLinkWebBinding
    private val viewModel by inject<GalleryLinkWebViewModel>()
    private val args : GalleryLinkWebViewFragmentArgs by navArgs()
    private lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileLinkWebBinding.inflate(inflater, container, false)
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
        viewModel.showUrlWebView(binding.webViewProfile, args.urlProfile.toString())
    }
}