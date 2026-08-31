package com.rijana.fitme.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.rijana.fitme.R

class OnboardingHeightWeightFragment : Fragment() {

    private lateinit var etHeightFt: EditText
    private lateinit var etHeightIn: EditText
    private lateinit var etWeight: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(
            R.layout.fragment_onboarding_height_weight,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etHeightFt = view.findViewById(R.id.etHeightFt)
        etHeightIn = view.findViewById(R.id.etHeightIn)
        etWeight = view.findViewById(R.id.etWeight)
    }

    fun getHeightFeet(): Int? {
        return etHeightFt.text.toString().toIntOrNull()
    }

    fun getHeightInches(): Int? {
        return etHeightIn.text.toString().toIntOrNull()
    }

    fun getWeight(): Double? {
        return etWeight.text.toString().toDoubleOrNull()
    }
}