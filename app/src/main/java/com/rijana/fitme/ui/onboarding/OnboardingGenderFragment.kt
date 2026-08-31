package com.rijana.fitme.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.rijana.fitme.R

class OnboardingGenderFragment : Fragment() {

    private lateinit var cardFemale: LinearLayout
    private lateinit var cardMale: LinearLayout
    private lateinit var cardOther: LinearLayout

    private var selectedGender: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(
            R.layout.fragment_onboarding_gender,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardFemale = view.findViewById(R.id.cardFemale)
        cardMale = view.findViewById(R.id.cardMale)
        cardOther = view.findViewById(R.id.cardOther)

        // Female
        cardFemale.setOnClickListener {
            selectedGender = "Female"
            updateSelectedCard(cardFemale)
        }

        // Male
        cardMale.setOnClickListener {
            selectedGender = "Male"
            updateSelectedCard(cardMale)
        }

        // Other
        cardOther.setOnClickListener {
            selectedGender = "Other"
            updateSelectedCard(cardOther)
        }
    }

    private fun updateSelectedCard(selectedCard: LinearLayout) {

        // Unselect all cards
        cardFemale.isSelected = false
        cardMale.isSelected = false
        cardOther.isSelected = false

        // Select the clicked card
        selectedCard.isSelected = true
    }

    fun getSelectedGender(): String? {
        return selectedGender
    }
}