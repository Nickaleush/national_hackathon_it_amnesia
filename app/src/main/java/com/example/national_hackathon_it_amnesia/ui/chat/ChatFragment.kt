package com.example.national_hackathon_it_amnesia.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.example.national_hackathon_it_amnesia.App
import com.example.national_hackathon_it_amnesia.R
import com.example.national_hackathon_it_amnesia.mvp.BaseFragment
import com.itamnesia.hackaton.contract.MessageServiceGrpc
import com.itamnesia.hackaton.contract.MessageServiceOuterClass
import io.grpc.android.AndroidChannelBuilder
import io.grpc.stub.StreamObserver
import kotlinx.android.synthetic.main.chat_fragment.*

const val grpcChatServiceHost = "itamnesia.pad4pets.com"

const val grpcChatServicePort = 443

class ChatFragment: BaseFragment<ChatPresenterImpl>(), ChatView {

    private val messageList: MutableList<MessageServiceOuterClass.MessageResponse> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.chat_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.clearFlags((WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS))
        activity?.window?.statusBarColor = resources.getColor(R.color.white, null)
        val clientChannel =  AndroidChannelBuilder
            .forAddress(grpcChatServiceHost, grpcChatServicePort)
            .context(requireContext())
            .build()

        val asyncStub: MessageServiceGrpc.MessageServiceStub = MessageServiceGrpc.newStub(clientChannel)

       val currentMessage = editTextTextPersonName.text
       send_message_button.setOnClickListener {
            asyncStub.sendMessage(
                MessageServiceOuterClass.MessageRequest.newBuilder().setData(currentMessage.toString()).build(),
                object : StreamObserver<MessageServiceOuterClass.MessageResponse> {
                    override fun onNext(value: MessageServiceOuterClass.MessageResponse?) {
                        if (value != null) {
                            messageList.add(value)
                        }
                    }

                    override fun onError(t: Throwable?) {
                        t?.printStackTrace()
                    }

                    override fun onCompleted() {
                        initRecyclerView()
                    }
                }
            )
        }
    }

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createChatFragment()
            .inject(this)
    }

    private fun initRecyclerView() {
        requireActivity().runOnUiThread {
            messagesRecyclerView
            val adapterMessages = MessageItemAdapter(messageList)
            messagesRecyclerView.adapter = adapterMessages
            messagesRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    override fun onBackPressed() {
        MaterialDialog.Builder(requireContext())
            .content(getString(R.string.ExitConfirm))
            .positiveText(R.string.Yes)
            .contentColor(resources.getColor(R.color.black, null))
            .positiveColor(resources.getColor(R.color.mainColor, null))
            .negativeColor(resources.getColor(R.color.black, null))
            .negativeText(R.string.No)
            .onPositive { materialDialog, _ ->
                materialDialog.dismiss()
                requireActivity().finish()
            }
            .onNegative { materialDialog, _ ->
                materialDialog.dismiss()
            }.show()
    }


}