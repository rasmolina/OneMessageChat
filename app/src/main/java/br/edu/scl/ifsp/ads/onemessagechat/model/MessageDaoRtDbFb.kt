package br.edu.scl.ifsp.ads.onemessagechat.model

import br.edu.scl.ifsp.ads.onemessagechat.model.Message
import com.google.firebase.Firebase
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.database
import com.google.firebase.database.ValueEventListener


class MessageDaoRtDbFb: MessageDao {

    companion object {
        private const val MESSAGE_LIST_ROOT_NODE = "messageList"
    }

    private val messageRtDbFbReference = Firebase.database.getReference(MESSAGE_LIST_ROOT_NODE)

    //Simula uma consulta ao RtDB.
    private val messageList: MutableList<Message> = mutableListOf()



    init{
        messageRtDbFbReference.addChildEventListener(object: ChildEventListener {

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val message: Message? = snapshot.getValue<Message>()

                message?.also { cont ->
                    if (!messageList.any { it.id == cont.id }) {
                        messageList.add(cont)
                    }
                }
            }


            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val message: Message? = snapshot.getValue<Message>()

                message?.also { editedMessage ->
                    messageList.apply {
                        this[indexOfFirst { editedMessage.id == it.id }] = editedMessage
                    }
                }
            }


            override fun onChildRemoved(snapshot: DataSnapshot) {
                //NSA
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                //NSA
            }

            override fun onCancelled(error: DatabaseError) {
                //NSA
            }
        })

        //É chamado uma única vez qdo o app é aberto
        messageRtDbFbReference.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val messageMap = snapshot.getValue<Map<String,Message>>()

                messageList.clear()
                messageMap?.values?.also{
                    messageList.addAll(it)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                //NSA
            }
        })
    }

    override fun createMessage(message: br.edu.scl.ifsp.ads.onemessagechat.model.Message): Int {
        createOrUpdateMessage(message)
        return 1
    }

    override fun retrieveMessage(id: Int): Message? {
        return messageList[messageList.indexOfFirst { it.id == id }]
    }

    override fun retrieveMessages(): MutableList<Message> = messageList

    override fun updateMessage(message: Message): Int {
        createOrUpdateMessage(message)
        return 1
    }

    private fun createOrUpdateMessage(message:Message) = messageRtDbFbReference.child(message.id.toString()).setValue()
}