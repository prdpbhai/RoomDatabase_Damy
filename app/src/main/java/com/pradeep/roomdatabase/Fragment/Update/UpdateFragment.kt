package com.pradeep.roomdatabase.Fragment.Update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pradeep.roomdatabase.Model.User
import com.pradeep.roomdatabase.R
import com.pradeep.roomdatabase.ViewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.update_age
import kotlinx.android.synthetic.main.fragment_update.view.update_btn
import kotlinx.android.synthetic.main.fragment_update.view.update_lastname
import kotlinx.android.synthetic.main.fragment_update.view.update_name

class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.update_name.setText(args.currentUser.firstName)
        view.update_lastname.setText(args.currentUser.lastName)
        view.update_age.setText(args.currentUser.age.toString())

        view.update_btn.setOnClickListener {
            UpdateItem()
        }
        setHasOptionsMenu(true)

        return view
    }

    private fun UpdateItem() {
        val firstname = update_name.text.toString()
        val lastname = update_lastname.text.toString()
        val age = update_age.text.toString()

        if (Inputcheck(firstname, lastname, update_age.text)) {
            val updatedUser = User(args.currentUser.id, firstname, lastname, age)
            mUserViewModel.updateUser(updatedUser)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

            Toast.makeText(requireContext(), "Update Successfully", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "Please fill all details", Toast.LENGTH_LONG).show()
        }
    }

    private fun Inputcheck(fname: String, lname: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(fname) && TextUtils.isEmpty(lname) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deletUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deletUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}")

        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "Successfully removed: ${args.currentUser.firstName}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        builder.setNegativeButton("No") { _, _ ->
            // Do nothing, or add any additional actions on cancel
        }

        builder.create().show()
    }
}
