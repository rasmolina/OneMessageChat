package br.edu.scl.ifsp.ads.onemessagechat.controller

import br.edu.scl.ifsp.ads.onemessagechat.model.Constant
import br.edu.scl.ifsp.ads.onemessagechat.model.Message
import br.edu.scl.ifsp.ads.onemessagechat.model.MessageDao
import br.edu.scl.ifsp.ads.onemessagechat.model.MessageDaoRtDbFb
import br.edu.scl.ifsp.ads.onemessagechat.view.MainActivity

class MessageRtDbFbController (private val mainActivity: MainActivity) {
    private val messageDaoImpl: MessageDao = MessageDaoRtDbFb()

    fun insertMessage(message: Message){
        Thread{
            messageDaoImpl.createMessage(message)
        }.start()
    }

    fun getMessage(id: Int) = messageDaoImpl.retrieveMessage(id)

    fun getMessages(){
        Thread{
            mainActivity.updateMessageListHandler.apply{
                sendMessage(
                    obtainMessage().apply{
                        data.getParcelableArray(
                            Constant.MESSAGE_ARRAY,
                            messageDaoImpl.retrieveMessages().toTypedArray()
                        )
                    }
                )
            }
        }
    }

    fun editMessage(message: Message){
        Thread{
            messageDaoImpl.updateMessage(message)
            getMessages()
        }.start()
    }


}