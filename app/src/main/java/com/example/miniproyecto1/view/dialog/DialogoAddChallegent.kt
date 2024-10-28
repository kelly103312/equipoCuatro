package com.example.miniproyecto1.view.dialog

import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.miniproyecto1.R
import com.example.miniproyecto1.view.fragment.challengeFragment

class DialogoAddChallegent {
    interface DialogListener {
        fun onInputReceived(input: String)
    }
    companion object{
        fun showDialog(context: Context, listener: challengeFragment): AlertDialog {
            val inflater = LayoutInflater.from(context)
            val dialogView = inflater.inflate(R.layout.dialogadditem, null)

            val builder = AlertDialog.Builder(context)
            builder.setCancelable(false)
                .setView(dialogView)
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton("Guardar") { dialog, _ ->
                    val editTextInput = dialogView.findViewById<EditText>(R.id.dialog_edit_text)
                    val inputText = editTextInput.text.toString()
                    listener.onInputReceived(inputText)
                    dialog.dismiss()
                }
            var dialog = builder.create()
            dialog.setOnShowListener {
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setBackgroundColor(context.getColor(R.color.orange))
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(context.getColor(R.color.white))
            }
            return dialog
        }
    }
}