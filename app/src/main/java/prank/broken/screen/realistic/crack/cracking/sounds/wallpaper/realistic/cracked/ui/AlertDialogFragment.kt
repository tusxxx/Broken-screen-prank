package prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import prank.broken.screen.realistic.crack.cracking.sounds.wallpaper.realistic.cracked.R

class AlertDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = MaterialAlertDialogBuilder(it, R.style.MaterialAlertDialog_rounded)
            val alertDialogView = requireActivity().layoutInflater.inflate(R.layout.dialog_layout, null).apply {
                dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            alertDialogView.findViewById<ImageButton>(R.id.ibAccept).setOnClickListener {
                requestOverlaySetting()
                dismiss()
            }
            alertDialogView.findViewById<ImageButton>(R.id.ibReject).setOnClickListener {
                dismiss()
                requireActivity().finish()
            }
            builder.setView(alertDialogView)

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun requestOverlaySetting() {
        val intent =
            Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:${requireActivity().packageName}"))

        startActivityForResult(intent, 101)
    }
}