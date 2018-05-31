package com.example.benjamin.nativapps.fragments


import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.benjamin.nativapps.HomeFragment

import com.example.benjamin.nativapps.R
import com.example.benjamin.nativapps.entities.Atividade
import com.example.benjamin.nativapps.entities.Negocio
import com.example.benjamin.nativapps.entities.Organizacao
import com.example.benjamin.nativapps.entities.Pessoa
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_atividade.*
import kotlinx.android.synthetic.main.fragment_negocio.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class AtividadeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_atividade, container, false)
    }

    private lateinit var realm: Realm

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        realm = Realm.getDefaultInstance()
        prepararSpineers(realm)
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        realm = Realm.getDefaultInstance()
        buttonAtividade.setOnClickListener {
            realm.beginTransaction()
            val atividade = realm.createObject(Atividade::class.java)
            if(isEmpty(atvEdDescricao.text.toString()) &&
                    isEmpty(atvEdTipo.text.toString()) &&
                    isEmpty(atvSpNegocio.selectedItem.toString()) &&
                    isEmpty(atvSpNegocio.selectedItem.toString()) &&
                    isEmpty(atvSpPessoas.selectedItem.toString())){

                atividade.descricao = atvEdDescricao.text.toString()
                atividade.tipo = atvEdTipo.text.toString()
                atividade.negocio = atvSpNegocio.selectedItem.toString()
                atividade.organizacao = atvSpOrg.selectedItem.toString()
                atividade.pessoas = atvSpPessoas.selectedItem.toString()
                atividade.data = Calendar.getInstance().getTime().toString()

                Snackbar.make(view,"Atividade Realizada", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                fragmentManager!!.beginTransaction().replace(R.id.fragment, HomeFragment()).commit()
            } else {
                Snackbar.make(view, "Alguma informação está errada ou faltando", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            }
            realm.commitTransaction()
            imm.hideSoftInputFromWindow(view.windowToken, 0)
            realm.where(Atividade::class.java).findAll().forEach {
                atividade -> context!!.toast("${atividade.descricao} ${atividade.tipo} ${atividade.negocio} ${atividade.organizacao} ${atividade.pessoas} ${atividade.data}")
            }

        }
    }

    fun prepararSpineers (realm : Realm) {

        val listOrganizacao : MutableList<String> = mutableListOf()
        val todasEmpresas = realm.where(Organizacao::class.java).findAll()
        todasEmpresas.forEach { organizacao ->
            listOrganizacao.add(organizacao.nome)
        }
        atvSpOrg.adapter = ArrayAdapter(context,android.R.layout.simple_list_item_1,listOrganizacao)
        val listNomes : MutableList<String> = mutableListOf()
        val todasPessoas = realm.where(Pessoa::class.java).findAll()
        todasPessoas.forEach { pessoa ->
            listNomes.add(pessoa.nome)
        }
        atvSpPessoas.adapter = ArrayAdapter(context,android.R.layout.simple_list_item_1,listNomes)
        val listaNegocios : MutableList<String> = mutableListOf()
        val todosNegocios = realm.where(Negocio::class.java).findAll()
        todosNegocios.forEach { negocio ->
            listaNegocios.add(negocio.titulo)
        }
        atvSpNegocio.adapter = ArrayAdapter(context,android.R.layout.simple_list_item_1,listaNegocios)
    }

    fun isEmpty(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun Context.toast(message: CharSequence) =
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()

}
