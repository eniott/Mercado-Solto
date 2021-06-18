package br.iesb.mobile.mercado_solto.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.iesb.mobile.mercado_solto.R
import br.iesb.mobile.mercado_solto.view.adapter.ChatbotAdapter
import br.iesb.mobile.mercado_solto.viewmodel.ChatbotViewModel
import kotlinx.android.synthetic.main.fragment_chatbot.*
import java.util.*
import kotlin.random.Random

class ChatbotFragment(private val b: Button) : Fragment() {
    private lateinit var adapter: ChatbotAdapter

    private val viewModelC: ChatbotViewModel by lazy {
        ViewModelProvider(this).get(ChatbotViewModel::class.java)
    }

    private lateinit var inputText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chatbot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputText = mensagemUser

        initRecyclerView()
        enviarMensagem.setOnClickListener { enviar() }

        close.setOnClickListener {
            val f = activity?.supportFragmentManager
                ?.findFragmentByTag("chatbot")

            if (f != null) {
                b.visibility = View.VISIBLE

                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.remove(f)
                    ?.commit()
            }
        }
    }

    private fun initRecyclerView() {
        adapter = activity?.applicationContext?.let { ChatbotAdapter(it) }!!
        adapter.addMessage("Oi! Sou seu assistente virtual!", "BOT")
        recyclerViewChat.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recyclerViewChat.adapter = adapter
    }

    private fun enviar() {
        val message = mensagemUser.text.toString()
        adapter.addMessage(message, "USER")
        inputText.text = ""

        viewModelC.verifyEmpty(message) { r ->
            if (r == "OK") {
                val data = Date().toString().substring(0, 10).replace(" ", "")
                val random = Random.nextInt(10000000, 1000000000)
                val sessionId = data + random

                viewModelC.sendText(
                    message,
                    "mercado@pago.com",
                    sessionId
                ) { chatMessage ->
                    adapter.addMessage(chatMessage, "BOT")
                }
            }
        }
    }
}