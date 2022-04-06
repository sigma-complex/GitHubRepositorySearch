package com.hiddendimension.githubrepositorysearch.feature_github_repository_search.domain.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

data class Owner(val avatar_url: String?)

class TypeConverterOwner {
    val gson : Gson = Gson()
    @TypeConverter
    fun stringToSomeObjectList(data: String?) : Owner? {
        if(data == null)return null
        val listType: Type = object :TypeToken<Owner?>() {}.type
        return gson.fromJson<Owner?>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someobject: Owner?): String?
    {
        return gson.toJson(someobject)
    }
}
