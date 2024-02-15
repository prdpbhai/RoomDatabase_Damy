package com.pradeep.roomdatabase.Repositary

import androidx.lifecycle.LiveData
import com.pradeep.roomdatabase.Model.User
import com.pradeep.roomdatabase.UserDao

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updatedUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deletUser(user)
    }
    suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }
}