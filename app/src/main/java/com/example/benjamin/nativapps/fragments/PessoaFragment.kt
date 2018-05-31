package com.example.benjamin.nativapps.fragments


import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.benjamin.nativapps.R
import com.example.benjamin.nativapps.entities.Pessoa
import io.realm.Realm
import android.util.Patterns
import android.text.TextUtils
import android.view.inputmethod.InputMethodManager
import com.example.benjamin.nativapps.HomeFragment
import kotlinx.android.synthetic.main.fragment_pessoa.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PessoaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pessoa, container, false)
    }

    private lateinit var realm: Realm

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        realm = Realm.getDefaultInstance()
        buttonPessoa.setOnClickListener {
            realm.beginTransaction()
            val pessoa = realm.createObject(Pessoa::class.java)

            if(isValidEmail(pesEdEmail.text.toString()) && isValidPhone(pesEdTel.text.toString()) && isValidName(pesEdNome.text.toString())){
                pessoa.nome = pesEdNome.text.toString()
                pessoa.telefone = pesEdTel.text.toString()
                pessoa.email = pesEdEmail.text.toString()
                Snackbar.make(view, pesEdNome.text.toString() + " adicionado(a) com sucesso", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                fragmentManager!!.beginTransaction().replace(R.id.fragment, HomeFragment()).commit()

            } else {
                Snackbar.make(view, "Alguma informação está errada", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
            realm.commitTransaction()
            val todasPessoas = realm.where(Pessoa::class.java).findAll()
            todasPessoas.forEach {
                pessoa ->
                println(pessoa.nome)
                println(pessoa.telefone)
                println(pessoa.email)
            }
        }
    }

    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun isValidPhone(number: String): Boolean {
        return !TextUtils.isEmpty(number) && android.util.Patterns.PHONE.matcher(number).matches()
    }

    fun isValidName(name: String): Boolean {
        return !TextUtils.isEmpty(name)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
