package com.example.benjamin.nativapps.entities

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class Atividade(): RealmObject(){
    var descricao: String = ""
    var tipo: String = ""
    var organizacao: String = ""
    var pessoas: String = ""
    var negocio: String=""
    var data: String=""
    var horario: String=""
}