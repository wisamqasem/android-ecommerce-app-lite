package com.example.shop.messges

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.shop.R
import com.example.shop.activities.User
import com.example.shop.activities.mypref
import com.example.shop.views.ChatFromItem
import com.example.shop.views.ChatToItem
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chatlog.*

class chatlog : AppCompatActivity() {
    companion object {
        val TAG = "ChatLog"

    }

    val adapter = GroupAdapter<ViewHolder>()

    var toUser: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatlog)

        recyclerview_chat_log.adapter = adapter

        toUser = intent.getParcelableExtra<User>(NewMessages.USER_KEY)

        supportActionBar?.title = toUser?.first_name

//    setupDummyData()
        listenForMessages()

        send_button_chat_log.setOnClickListener {
            Log.d(TAG, "Attempt to send message....")
           performSendMessage()
        }
    }

    private fun listenForMessages() {
        println("fuck1")
      //  val fromid = FirebaseAuth.getInstance().uid
        val mypreference = mypref(this)
        val fromid =mypreference.getstr("id")
        val toid = toUser?.id
        val ref = FirebaseDatabase.getInstance().getReference("/Messages/$fromid/$toid")
       // val ref = FirebaseDatabase.getInstance().getReference("Messages")

        ref.addChildEventListener(object: ChildEventListener {

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                println("fucko")
                val chatMessage = p0.getValue(ChatMessage::class.java)

if (chatMessage == null)
    println("fuck")
                if (chatMessage != null) {
                    Log.d("tag3", chatMessage.text)

                    //mypreference.setstr("username",username.text.toString())
                    Log.d("tag4", mypreference.getstr("id"))
                    Log.d("tag6", chatMessage.fromid)
                    if (chatMessage.fromid == mypreference.getstr("id") ) {

                      //  val currentUser = LatestMesages.currentUser ?: return
                        val currentUser = p0.getValue(User::class.java) ?: return
                        Log.d("tag5", chatMessage.text)
                        adapter.add(ChatFromItem(chatMessage.text, currentUser))

                    } else {
                        adapter.add(ChatToItem(chatMessage.text, toUser!!))
                    }
                }

                recyclerview_chat_log.scrollToPosition(adapter.itemCount - 1)

            }

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }

        })

    }

    private fun performSendMessage() {
        // how do we actually send a message to firebase...
        val text = edittext_chat_log.text.toString()
        val mypreference = mypref(this)
        val fromid = mypreference.getstr("id")
       // val fromid = "1"
        val user = intent.getParcelableExtra<User>(NewMessages.USER_KEY)
        val toid = user.id

        if (fromid == null) return
        println(fromid)
        println(toid)

//    val reference = FirebaseDatabase.getInstance().getReference("/messages").push()
        val reference = FirebaseDatabase.getInstance().getReference("/Messages/$fromid/$toid").push()

        val toReference = FirebaseDatabase.getInstance().getReference("/Messages/$toid/$fromid").push()

        val chatMessage = ChatMessage(reference.key!!, text, fromid, toid, System.currentTimeMillis() / 1000)

        reference.setValue(chatMessage)
                .addOnSuccessListener {
                    Log.d("tag2", "Saved our chat message: ${reference.key}")
                    edittext_chat_log.text.clear()
                    recyclerview_chat_log.scrollToPosition(adapter.itemCount - 1)
                }

       toReference.setValue(chatMessage)

        val latestMessageRef = FirebaseDatabase.getInstance().getReference("/latest-messages/$fromid/$toid")
        latestMessageRef.setValue(chatMessage)

        val latestMessageToRef = FirebaseDatabase.getInstance().getReference("/latest-messages/$toid/$fromid")
        latestMessageToRef.setValue(chatMessage)
    }





}
