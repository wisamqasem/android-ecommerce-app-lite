package com.example.shop.views

import com.example.shop.App
import com.example.shop.R
import com.example.shop.activities.User
import com.example.shop.activities.getUsers
import com.example.shop.messges.ChatMessage
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_latest_message_row.view.*

val aa =App()
class LatestMessageRow(val chatMessage: ChatMessage,val idu:String): Item<ViewHolder>()  {
    var chatPartnerUser: User? = null

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.message_textview_latest_message.text = chatMessage.text

        val chatPartnerId: String


        if (chatMessage.fromid == idu) {
            chatPartnerId = chatMessage.toid
        } else {
            chatPartnerId = chatMessage.fromid
        }
var users : Array<User> = getUsers()

        for (user in users){
            if (user.id==chatPartnerId) {
                chatPartnerUser=user

                viewHolder.itemView.username_textview_latest_message.text = user?.first_name

            }



        }
//        val ref = FirebaseDatabase.getInstance().getReference("/users/$chatPartnerId")
//        ref.addListenerForSingleValueEvent(object: ValueEventListener {
//            override fun onDataChange(p0: DataSnapshot) {
//                chatPartnerUser = p0.getValue(User::class.java)
             //   viewHolder.itemView.username_textview_latest_message.text = chatPartnerUser?.first_name

//                val targetImageView = viewHolder.itemView.imageview_latest_message
//             //   Picasso.get().load(chatPartnerUser?.image).into(targetImageView)
//            }

//            override fun onCancelled(p0: DatabaseError) {
//
//            }
//        })
    }

    override fun getLayout(): Int {
        return R.layout.activity_latest_message_row
    }
}
