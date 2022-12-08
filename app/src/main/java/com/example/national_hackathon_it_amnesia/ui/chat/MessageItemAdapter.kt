package com.example.national_hackathon_it_amnesia.ui.chat

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.national_hackathon_it_amnesia.R
import com.itamnesia.hackaton.contract.MessageServiceOuterClass

class MessageItemAdapter(
    var messages: MutableList<MessageServiceOuterClass.MessageResponse>
) : RecyclerView.Adapter<MessageItemAdapter.MessagesHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessagesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return MessagesHolder(view)
    }

    override fun onBindViewHolder(holder: MessagesHolder, position: Int, ) = holder.bind(messages[position])

    override fun getItemCount(): Int = messages.count()

    inner class MessagesHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        private val messageMessageTextView = view.findViewById<TextView>(R.id.friendMessageTextView)

        fun bind( message: MessageServiceOuterClass.MessageResponse) {
            messageMessageTextView.text = message.data
        }
    }
}
