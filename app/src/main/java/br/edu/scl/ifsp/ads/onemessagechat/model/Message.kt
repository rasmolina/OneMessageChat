package br.edu.scl.ifsp.ads.onemessagechat.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.edu.scl.ifsp.ads.onemessagechat.model.Constant.INVALID_CONTACT_ID
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Message (
    @PrimaryKey(autoGenerate = true)
    var id:Int? = INVALID_CONTACT_ID,
    @NonNull
    var identificador: String = "",
    @NonNull
    var message: String = "",
):Parcelable