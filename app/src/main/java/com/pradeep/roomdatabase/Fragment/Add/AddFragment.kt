package com.pradeep.roomdatabase.Fragment.Add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pradeep.roomdatabase.R
import com.pradeep.roomdatabase.Model.User
import com.pradeep.roomdatabase.ViewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)

        view.btn.setOnClickListener{
            inserDataToDatabase()
        }

        return view
    }

    private fun inserDataToDatabase(){
        val fname=name.text.toString()
        val lname=lastname.text.toString()
        val age= age.text.toString()

        if (Inputcheck(fname,lname, age)){

            val user= User(0,fname,lname,age)
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"successful",Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment2)
        }else{
            Toast.makeText(requireContext(),"fail",Toast.LENGTH_LONG).show()
        }

    }

    private fun Inputcheck(fname:String, lname:String, age: String):Boolean{
        return !(TextUtils.isEmpty(fname) && TextUtils.isEmpty(lname) && age.isEmpty())
    }


}