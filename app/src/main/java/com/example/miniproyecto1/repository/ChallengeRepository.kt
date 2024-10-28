package com.example.miniproyecto1.repository

import android.content.Context
import com.example.miniproyecto1.data.ChallengeDB
import com.example.miniproyecto1.data.ChallengeDao
import com.example.miniproyecto1.model.Challenge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChallengeRepository(val context: Context) {
    private var challengeDao:ChallengeDao = ChallengeDB.getDatabase(context).challengeDao()

    suspend fun saveChallenge(challenge:Challenge){
        withContext(Dispatchers.IO){
            challengeDao.saveChallenge(challenge)
        }
    }

    suspend fun getListChallenge():MutableList<Challenge>{
        return withContext(Dispatchers.IO){
            challengeDao.getListChallenge()
        }
    }
}