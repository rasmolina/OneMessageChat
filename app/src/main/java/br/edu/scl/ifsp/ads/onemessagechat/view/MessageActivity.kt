package br.edu.scl.ifsp.ads.onemessagechat.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.onemessagechat.databinding.ActivityMsgBinding
import br.edu.scl.ifsp.ads.onemessagechat.model.Constant.EXTRA_MESSAGE
import br.edu.scl.ifsp.ads.onemessagechat.model.Constant.VIEW_MESSAGE
import br.edu.scl.ifsp.ads.onemessagechat.model.Message

//Tela para edição da mensagem
class MessageActivity: AppCompatActivity() {

    private val amb: ActivityMsgBinding by lazy {
        ActivityMsgBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        setSupportActionBar(amb.toolbarIn.toolbar)
        supportActionBar?.subtitle = "Message details"

        val receivedMsg = intent.getParcelableExtra<Message>(EXTRA_MESSAGE)
        receivedMsg?.let { _receivedMsg ->
            with(amb){
                idEditText.setText(_receivedMsg.identificador)
                msgEditText.setText(_receivedMsg.message)
            }
            val viewMessage = intent.getBooleanExtra(VIEW_MESSAGE, false)
            if(viewMessage){
                with(amb){
                    idEditText.isEnabled = false
                    msgEditText.isEnabled = false
                    saveBt.visibility = View.GONE
                }
            }
        }

        with(amb){
            saveBt.setOnClickListener {
                val message: Message = Message(
                    id = receivedMsg?.id,
                    idEditText.text.toString(),
                    msgEditText.text.toString()
                )

                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_MESSAGE, message)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }



    }


}