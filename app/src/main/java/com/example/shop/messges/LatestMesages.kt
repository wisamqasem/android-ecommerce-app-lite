package com.example.shop.messges

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.shop.R
import com.example.shop.activities.User
import com.example.shop.activities.mypref
import com.example.shop.views.LatestMessageRow
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_latest_mesages.*

class LatestMesages : AppCompatActivity() {

    companion object {
        var currentUser: User? = null
        val TAG = "LatestMessages"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latest_mesages)
        recyclerview_latest_messages.adapter = adapter
        recyclerview_latest_messages.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        // set item click listener on your adapter
        adapter.setOnItemClickListener { item, view ->
            Log.d(TAG, "123")
            val intent = Intent(this, chatlog::class.java)

            // we are missing the chat partner user

            val row = item as LatestMessageRow
            intent.putExtra(NewMessages.USER_KEY, row.chatPartnerUser)
            startActivity(intent)
        }

//    setupDummyRows()
        listenForLatestMessages()

        fetchCurrentUser()


    }



    val latestMessagesMap = HashMap<String, ChatMessage>()
    private fun refreshRecyclerViewMessages() {
        adapter.clear()
        val mypreference = mypref(this)
        val fromid = mypreference.getstr("id")
        for (it in latestMessagesMap.values){
            //if for not showing user name
            if (it.toid==fromid)
                continue
            adapter.add(LatestMessageRow(it,fromid))


        }
//        latestMessagesMap.values.forEach {
//            if (it.fromid==fromid)
//                continue
//            adapter.add(LatestMessageRow(it,fromid))
//        }
    }


    private fun listenForLatestMessages() {
       // val fromid = FirebaseAuth.getInstance().uid
        val mypreference = mypref(this)
        val fromid = mypreference.getstr("id")
        val ref = FirebaseDatabase.getInstance().getReference("/latest-messages/$fromid")
        ref.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java) ?: return
                latestMessagesMap[p0.key!!] = chatMessage
                refreshRecyclerViewMessages()
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java) ?: return
                latestMessagesMap[p0.key!!] = chatMessage
                refreshRecyclerViewMessages()
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }
            override fun onChildRemoved(p0: DataSnapshot) {

            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }


    val adapter = GroupAdapter<ViewHolder>()

    private fun fetchCurrentUser() {
        val mypreference = mypref(this)
        val uid = mypreference.getstr("id")

     //   val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
//        ref.addListenerForSingleValueEvent(object: ValueEventListener {
//
//            override fun onDataChange(p0: DataSnapshot) {
//                currentUser = p0.getValue(User::class.java)
//                // Log.d("LatestMessages", "Current user ${currentUser?.profileImageUrl}")
//            }
//
//            override fun onCancelled(p0: DatabaseError) {
//
//            }
//        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {

            R.id.menu_new_message -> {
                val intent = Intent(this, NewMessages::class.java)
                startActivity(intent)
            }
//            R.id.menu_sign_out -> {
//                FirebaseAuth.getInstance().signOut()
//                val intent = Intent(this, RegisterActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
//                startActivity(intent)
//            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }







}
