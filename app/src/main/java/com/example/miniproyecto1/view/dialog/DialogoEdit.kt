package com.example.miniproyecto1.view.dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.miniproyecto1.R
import com.example.miniproyecto1.model.Challenge
import com.example.miniproyecto1.view.fragment.ChallengeFragment

class DialogoEdit {
    interface DialogListener {
        fun onInputReceived(input: String)
    }
    companion object{
        fun showDialog(context: Context, listener: ChallengeFragment, bundle: Bundle): AlertDialog {
            val inflater = LayoutInflater.from(context)
            val dialogView = inflater.inflate(R.layout.dialogo_edit, null)

            val builder = AlertDialog.Builder(context)
            builder.setCancelable(false)
                .setView(dialogView)
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton("Guardar") { dialog, _ ->
                    val editTextInput = dialogView.findViewById<EditText>(R.id.dialog_edit_input)
                    val inputText = editTextInput.text.toString()
                    var challenge = bundle?.getSerializable("clave") as? Challenge
                    challenge.let {
                        if (it != null) {
                            it.description=inputText
                            listener.onInputEdit(it)
                            dialog.dismiss()
                        }
                    }

                }
            var dialog = builder.create()
            dialog.setOnShowListener {
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setBackgroundColor(context.getColor(R.color.orange))
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(context.getColor(R.color.white))
                dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setBackgroundColor(context.getColor(R.color.orange))
                dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(context.getColor(R.color.white))
            }
            return dialog
        }
    }
}