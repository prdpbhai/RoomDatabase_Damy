package com.pradeep.roomdatabase.Fragment.List

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.pradeep.roomdatabase.Fragment.Update.UpdateFragment
import com.pradeep.roomdatabase.R
import com.pradeep.roomdatabase.Model.User
import kotlinx.android.synthetic.main.custom_row.view.*



class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.idd.text = currentItem.id.toString()
        holder.itemView.name.text = currentItem.firstName
        holder.itemView.lname.text = currentItem.lastName
        holder.itemView.age.text = currentItem.age.toString()

        holder.itemView.rowLayout.setOnClickListener {
            // Using Navigation component to navigate to UpdateFragment with currentUser argument
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment3(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }
}



