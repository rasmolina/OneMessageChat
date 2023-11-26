package br.edu.scl.ifsp.ads.onemessagechat.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.onemessagechat.databinding.ActivityMsgBinding

class MessageActivity: AppCompatActivity() {

    private val mmb: ActivityMsgBinding by lazy {
        ActivityMsgBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mmb.root)
    }
}