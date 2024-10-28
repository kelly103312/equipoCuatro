package com.example.miniproyecto1.view.viewholder

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import com.example.miniproyecto1.databinding.ItemchallengesBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.miniproyecto1.R
import com.example.miniproyecto1.model.Challenge

class ChallengeViewHolder(binding: ItemchallengesBinding, navController: NavController):
    RecyclerView.ViewHolder(binding.root) {
    val bindingItem = binding
    val navController = navController
    fun setItemChallenge(challenge: Challenge) {
        bindingItem.textDescription.text = challenge.description

        bindingItem.cvChallenge.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("clave", challenge)
            navController.navigate(R.id.action_homeFragment_to_challengeFragment, bundle)
        }

    }

    fun editItemChallenge(challenge: Challenge) {
        bindingItem.iconEdit.setOnClickListener {
            val bundle = Bundle()
            Toast.makeText(bindingItem.root.context, "Editar", Toast.LENGTH_SHORT).show()
        }

    }
}