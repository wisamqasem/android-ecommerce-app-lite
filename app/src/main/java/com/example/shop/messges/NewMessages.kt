package com.example.shop.messges

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.shop.App
import com.example.shop.R
import com.example.shop.activities.User
import com.example.shop.activities.getUsers
import com.example.shop.activities.mypref
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_new_messages.*
import kotlinx.android.synthetic.main.user_row_new_messages.view.*


var aa=App()
class NewMessages : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_messages)
        supportActionBar?.title = "Select User"

        fetchUsers()
    }

    companion object {
        val USER_KEY = "USER_KEY"
    }

    private fun fetchUsers() {
//        val ref = FirebaseDatabase.getInstance().getReference("/users")
//        ref.addListenerForSingleValueEvent(object: ValueEventListener {

      //      override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()
var users:Array<User> = getUsers()
        val mypreference = mypref(this)
        val fromid = mypreference.getstr("id")
                for (user in users){
                    Log.d("NewMessage", user.first_name)
                    println(user.first_name)
                    if (user.id==fromid) continue
                    adapter.add(UserItem(user))



                }
//                aa.users.forEach {
//                    Log.d("NewMessage", it.first_name)
//                    println(it.first_name)
//                  //  val user = it.first_name
//
//                   // if (user != null) {
//                        adapter.add(UserItem(it))
//                   // }
//                }
//                p0.children.forEach {
//                    Log.d("NewMessage", it.toString())
//                    val user = it.getValue(User::class.java)
//                    if (user != null) {
//                        adapter.add(UserItem(user))
//                    }
//                }

                adapter.setOnItemClickListener { item, view ->

                    val userItem = item as UserItem

                    val intent = Intent(view.context, chatlog::class.java)
//          intent.putExtra(USER_KEY,  userItem.user.username)
                    intent?.putExtra(USER_KEY, userItem.user)
                    startActivity(intent)

                    finish()

                }

                recyclerview_newmessage.adapter = adapter
          //  }

          //  override fun onCancelled(p0: DatabaseError) {

          //  }
        //})
    }


}
class UserItem(val user: User): Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.username_textview_new_message.text = user.first_name

//        Picasso.get().load(user.image).into(viewHolder.itemView.imageview_new_message)
    }

    override fun getLayout(): Int {
        return R.layout.user_row_new_messages
    }
}