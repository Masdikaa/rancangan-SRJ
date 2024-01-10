package com.masdika.tessmartrescuejacket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.masdika.tessmartrescuejacket.databinding.FragmentHistoryBinding

private var _binding: FragmentHistoryBinding? = null
private val binding get() = _binding!!

class HistoryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

}