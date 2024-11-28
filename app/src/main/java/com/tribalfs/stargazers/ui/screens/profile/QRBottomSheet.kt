package com.tribalfs.stargazers.ui.screens.profile

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.BundleCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tribalfs.stargazers.data.model.Stargazer
import com.tribalfs.stargazers.databinding.ViewQrBottomsheetBinding
import com.tribalfs.stargazers.ui.core.util.loadImageFromUrl
import com.tribalfs.stargazers.ui.core.util.toast
import com.tribalfs.stargazers.ui.screens.profile.ProfileActivity.Companion.KEY_STARGAZER

class QRBottomSheet : BottomSheetDialogFragment() {

    private  var _binding: ViewQrBottomsheetBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(stargazer: Stargazer): QRBottomSheet {
            return QRBottomSheet().apply{
                arguments = Bundle().apply {
                    putParcelable(KEY_STARGAZER, stargazer)
                }
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return (super.onCreateDialog(savedInstanceState) as BottomSheetDialog).apply {
            behavior.skipCollapsed = true
            setOnShowListener {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ViewQrBottomsheetBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stargazer = BundleCompat.getParcelable(
            requireArguments(),
            KEY_STARGAZER,
            Stargazer::class.java
        )!!
        binding.sgName.text = stargazer.getDisplayName()
        binding.noteTv.text =
            "Scan this QR code on another device to view ${stargazer.getDisplayName()}'s profile."

        binding.qrCode.apply {
            setContent(stargazer.html_url)
            loadImageFromUrl(stargazer.avatar_url)
            invalidate()
        }

        binding.quickShareBtn.setOnClickListener {
            //TODO
            toast("Todo()")
        }

        binding.saveBtn.setOnClickListener {
            //TODO
            toast("Todo()")
        }
    }
}
