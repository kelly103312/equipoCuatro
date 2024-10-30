package com.example.miniproyecto1.view.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.miniproyecto1.R
import com.example.miniproyecto1.model.Challenge
import com.example.miniproyecto1.view.fragment.ChallengeFragment

class DialogoDelete {
    interface DialogListener {
        fun onInputReceived(input: String)
    }

    companion object {
        fun showDialog(context: Context, listener: ChallengeFragment, bundle: Bundle): AlertDialog {
            val inflater = LayoutInflater.from(context)
            val dialogView = inflater.inflate(R.layout.dialogo_delete, null)

            // Obtén la referencia al TextView para mostrar el nombre del reto
            val dialogTextView = dialogView.findViewById<TextView>(R.id.text_description_delete)
            val challenge = bundle.getSerializable("clave") as? Challenge

            // Si el challenge no es nulo, muestra su nombre en el TextView
            challenge?.let {
                dialogTextView.text = "¿Desea eliminar el reto: ${it.description}?"
            }

            val builder = AlertDialog.Builder(context)
            builder.setCancelable(false)
                .setView(dialogView)
                .setNegativeButton("NO") { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton("SI") { dialog, _ ->
                    // Llama a la función del listener para realizar la acción de eliminar
                    listener.onInputDelete(challenge!!)
                    dialog.dismiss()
                }

            val dialog = builder.create()
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
