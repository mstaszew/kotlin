package com.example.myapplication

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Point : RealmObject() {

    var x: Int = 0
    var y: Int = 0

    @PrimaryKey
    var id = x.toString() + "_" + y.toString()

}