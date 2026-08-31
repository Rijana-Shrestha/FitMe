package com.rijana.fitme.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.rijana.fitme.R

class OnboardingGoalFragment : Fragment() {

    private lateinit var cardLoseWeight: LinearLayout
    private lateinit var cardGetFitter: LinearLayout
    private lateinit var cardGainMuscles: LinearLayout

    private var selectedGoal: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(
            R.layout.fragment_onboarding_goal,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardLoseWeight = view.findViewById(R.id.cardLoseWeight)
        cardGetFitter = view.findViewById(R.id.cardGetFitter)
        cardGainMuscles = view.findViewById(R.id.cardGainMuscles)

        // Lose Weight
        cardLoseWeight.setOnClickListener {
            selectedGoal = "Lose Weight"
            updateSelectedCard(cardLoseWeight)
        }

        // Get Fitter
        cardGetFitter.setOnClickListener {
            selectedGoal = "Get Fitter"
            updateSelectedCard(cardGetFitter)
        }

        // Gain Muscles
        cardGainMuscles.setOnClickListener {
            selectedGoal = "Gain Muscles"
            updateSelectedCard(cardGainMuscles)
        }
    }

    private fun updateSelectedCard(selectedCard: LinearLayout) {

        // Unselect all cards
        cardLoseWeight.isSelected = false
        cardGetFitter.isSelected = false
        cardGainMuscles.isSelected = false

        // Select the clicked card
        selectedCard.isSelected = true
    }

    fun getSelectedGoal(): String? {
        return selectedGoal
    }
}