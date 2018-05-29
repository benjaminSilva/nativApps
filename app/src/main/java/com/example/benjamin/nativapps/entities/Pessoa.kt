package com.example.benjamin.nativapps.entities

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class Pessoa(): RealmObject(){
    var nome: String = ""
    var telefone: String = ""
    var email: String = ""
}