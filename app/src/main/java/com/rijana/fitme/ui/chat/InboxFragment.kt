package com.rijana.fitme.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rijana.fitme.R

class InboxFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inbox, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Attach  RecyclerView adapter here.
        // When a conversation row is clicked, call openChatDetail with the item's conversationId:
    }

    fun openChatDetail(conversationId: String) {
        val bundle = bundleOf("conversationId" to conversationId)
        findNavController().navigate(
            R.id.action_inbox_to_chatDetail,
            bundle
        )
    }
}