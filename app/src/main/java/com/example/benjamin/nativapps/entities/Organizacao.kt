package com.example.benjamin.nativapps.entities

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class Organizacao(): RealmObject(){
    var nome: String = ""
    var endereco: String = ""
    var telefone: String = ""
}