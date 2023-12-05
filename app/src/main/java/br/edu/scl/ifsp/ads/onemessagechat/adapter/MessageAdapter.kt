package br.edu.scl.ifsp.ads.onemessagechat.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.scl.ifsp.ads.onemessagechat.R
import br.edu.scl.ifsp.ads.onemessagechat.databinding.TileMessageBinding
import br.edu.scl.ifsp.ads.onemessagechat.model.Message


class MessageAdapter (context: Context,
    private val messageList: MutableList<Message>
):ArrayAdapter<Message>(context, R.layout.tile_message,messageList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val message = messageList[position]
        var tmb: TileMessageBinding? = null

        var messageTileView = convertView

        if(messageTileView == null) {
            tmb = TileMessageBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            )

            messageTileView = tmb.root

            val tileMessageHolder = TileMessageHolder(tmb.idTextView, tmb.msgTextView)
            messageTileView.tag = tileMessageHolder
        }

        (messageTileView.tag as TileMessageHolder).idTextView.setText(message.identificador)
        (messageTileView.tag as TileMessageHolder).msgTextView.setText(message.message)

        tmb?.idTextView?.setText(message.identificador)
        tmb?.msgTextView?.setText(message.message)

        return messageTileView
    }

    private data class TileMessageHolder(val idTextView: TextView, val msgTextView:TextView){

    }
}