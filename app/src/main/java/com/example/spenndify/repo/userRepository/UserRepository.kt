package com.example.spenndify.repo.userRepository

import com.example.spenndify.model.User

interface UserRepository {
    suspend fun addUser(user: User)

}