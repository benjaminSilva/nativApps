package com.example.benjamin.nativapps.entities

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class Negocio(): RealmObject(){
    var titulo: String = ""
    var descricao: String = ""
    var dataEncerramento: String = ""
    var valor: String = ""
    var organizacao: String = ""
    var pessoa: String=""
}