package br.iesb.mobile.mercado_solto.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.iesb.mobile.mercado_solto.R
import kotlinx.android.synthetic.main.bot_message_layout.view.*
import kotlinx.android.synthetic.main.user_message_layout.view.*

private const val USER = 1
private const val ASSISTANT = 2

class ChatbotAdapter(val context: Context) : RecyclerView.Adapter<MessageViewHolder>() {
    private val allMessages = mutableListOf<Array<String>>()

    fun addMessage(message: String, type: String) {
        val m = arrayOf(message, type)
        allMessages.add(m)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        val type = allMessages[position][1]

        return if (type == "BOT") {
            ASSISTANT
        } else {
            USER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return if (viewType == USER) {
            UserMessageViewHolder(
                LayoutInflater.from(context).inflate(R.layout.user_message_layout, parent, false)
            )
        } else {
            AssistantMessageViewHolder(
                LayoutInflater.from(context)
                    .inflate(R.layout.bot_message_layout, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return allMessages.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = allMessages[position][0]

        holder.bind(message)
    }

    inner class UserMessageViewHolder(view: View) : MessageViewHolder(view) {
        private var messageText: TextView = view.textMessageSend

        override fun bind(message: String) {
            messageText.text = message
        }
    }

    inner class AssistantMessageViewHolder(view: View) : MessageViewHolder(view) {
        private var messageText: TextView = view.assistantMessage

        override fun bind(message: String) {
            messageText.text = message
        }
    }
}

open class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    //Discover import for message
    open fun bind(message: String) {}
}
