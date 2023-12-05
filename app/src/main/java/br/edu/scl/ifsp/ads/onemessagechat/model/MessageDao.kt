package br.edu.scl.ifsp.ads.onemessagechat.model

import br.edu.scl.ifsp.ads.onemessagechat.model.Message

interface MessageDao {

    fun createMessage(message: br.edu.scl.ifsp.ads.onemessagechat.model.Message): Int

    fun retrieveMessage(id: Int): Message?

    fun retrieveMessages(): MutableList<Message>

    fun updateMessage(message: br.edu.scl.ifsp.ads.onemessagechat.model.Message): Int

}