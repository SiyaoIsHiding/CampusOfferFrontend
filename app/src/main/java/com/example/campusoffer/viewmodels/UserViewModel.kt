package com.example.campusoffer.viewmodels

import com.example.campusoffer.models.User

class UserViewModel {

    fun applyHardCodeData() : User {

        val user1: User = User(
            "Living in Verano Place. Gonna leave here soon so contact me via siyaoh4@uci.edu for some cheap furniture.",
            "Jane",
            "117864142497404641625",
            "He",
            "siyaoh4@uci.edu"
        )

        val user2: User = User(
            "Hey, contact me via fengnans@uci.edu for some cheap books.",
            "Sonia",
            "117864142497404641625",
            "Sun",
            "fengnans@uci.edu"
        )
//        return listOf(user1, user2)
        return user1
    }
}