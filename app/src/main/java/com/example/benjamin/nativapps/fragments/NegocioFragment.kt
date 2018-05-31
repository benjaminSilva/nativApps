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
import com.example.benjamin.nativapps.HomeFragment

import com.example.benjamin.nativapps.R
import com.example.benjamin.nativapps.entities.Negocio
import com.example.benjamin.nativapps.entities.Organizacao
import com.example.benjamin.nativapps.entities.Pessoa
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.fragment_negocio.*
import kotlinx.android.synthetic.main.fragment_organizacao.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [NegocioFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [NegocioFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class NegocioFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_negocio, container, false)
    }

    private lateinit var realm: Realm

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        realm = Realm.getDefaultInstance()

        prepararSpineers(realm)

        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        realm = Realm.getDefaultInstance()
        buttonBusiness.setOnClickListener {
            realm.beginTransaction()
            val negocio = realm.createObject(Negocio::class.java)
            if(isEmpty(negEdTitulo.text.toString()) &&
                    isEmpty(negEdDescricao.text.toString()) &&
                    isEmpty(negEdDataEnc.text.toString()) &&
                    isEmpty(negEdValor.text.toString()) &&
                    isEmpty(negSpOrg.selectedItem.toString())&&
                    isEmpty(negSpPessoas.selectedItem.toString())){

                negocio.titulo = negEdTitulo.text.toString()
                negocio.descricao = negEdTitulo.text.toString()
                negocio.dataEncerramento = negEdTitulo.text.toString()
                negocio.valor = negEdTitulo.text.toString()
                negocio.organizacao = negEdTitulo.text.toString()
                negocio.pessoa = negEdTitulo.text.toString()
                Snackbar.make(view, negocio.titulo + " adicionada", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                fragmentManager!!.beginTransaction().replace(R.id.fragment, HomeFragment()).commit()
            } else {
                Snackbar.make(view, "Alguma informação está errada ou faltando", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            }
            realm.commitTransaction()
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun prepararSpineers (realm : Realm) {

        val listOrganizacao : MutableList<String> = mutableListOf()
        val todasEmpresas = realm.where(Organizacao::class.java).findAll()
        todasEmpresas.forEach { organizacao ->
            listOrganizacao.add(organizacao.nome)
        }
        negSpOrg.adapter = ArrayAdapter(context,android.R.layout.simple_list_item_1,listOrganizacao)
        val listNomes : MutableList<String> = mutableListOf()
        val todasPessoas = realm.where(Pessoa::class.java).findAll()
        todasPessoas.forEach { pessoa ->
            listNomes.add(pessoa.nome)
        }
        negSpPessoas.adapter = ArrayAdapter(context,android.R.layout.simple_list_item_1,listNomes)
    }

    fun isEmpty(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
