package com.example.fargmenty.data.models.handlers

import com.example.fargmenty.data.models.State
import com.example.fargmenty.idState
import io.realm.Realm

fun updateState(logged:Boolean)
{
   val realm = Realm.getDefaultInstance()
    var state:State = State()
    state.logged = logged
    state.id = 1
    realm.beginTransaction()
    realm.insertOrUpdate(state)
    realm.commitTransaction()
}