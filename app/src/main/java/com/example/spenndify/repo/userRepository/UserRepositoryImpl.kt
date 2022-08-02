package com.example.spenndify.repo.userRepository

import com.example.spenndify.data.local.UserDao
import com.example.spenndify.model.User

class UserRepositoryImpl(
    private val userDao: UserDao
) :UserRepository{
    override suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
}