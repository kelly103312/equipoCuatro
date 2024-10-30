package com.example.miniproyecto1.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.miniproyecto1.R
import com.example.miniproyecto1.databinding.FragmentABinding


class FragmentA : Fragment() {
    private lateinit var binding: FragmentABinding
    private var isAudioOn = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentABinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationFragmentB()
        setupAudioToggle()
    }

    private fun navigationFragmentB(){
        binding.StarFragmentB.setOnClickListener{
            findNavController().navigate(R.id.action_fragmentA_to_fragmentB)
        }
    }

    private fun setupAudioToggle(){
        binding.iconAudio.setOnClickListener{
            toggleAudio()
        }
    }

    private fun toggleAudio(){
        if (isAudioOn) {
            binding.iconAudio.setImageResource(R.drawable.audiooff)
            pauseAudio()
            Log.d("AudioToggle", "audio pausado")
        } else {
            binding.iconAudio.setImageResource(R.drawable.audio_icon)
            playAudio()
            Log.d("AudioToggle", "audio encendido")
        }
        isAudioOn = !isAudioOn
    }

    private fun playAudio(){

    }

    private fun pauseAudio(){

    }


}