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
import com.example.benjamin.nativapps.HomeFragment

import com.example.benjamin.nativapps.R
import com.example.benjamin.nativapps.entities.Organizacao
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.fragment_organizacao.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [OrganizacaoFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [OrganizacaoFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class OrganizacaoFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_organizacao, container, false)

    }

    private lateinit var realm: Realm

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        realm = Realm.getDefaultInstance()
        buttonOrganizacao.setOnClickListener {
            realm.beginTransaction()
            val empresa = realm.createObject(Organizacao::class.java)
            if(isValidAdress(orgEdEnd.text.toString()) &&
                    isValidPhone(orgEdTel.text.toString()) &&
                    isValidName(orgEdNome.text.toString())){

                empresa.nome = orgEdNome.text.toString()
                empresa.telefone = orgEdTel.text.toString()
                empresa.endereco = orgEdEnd.text.toString()
                Snackbar.make(view, empresa.nome + " adicionada", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                fragmentManager!!.beginTransaction().replace(R.id.fragment, HomeFragment()).commit()
            } else {
                Snackbar.make(view, "Alguma informação está errada", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            }
            realm.commitTransaction()
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun isValidAdress(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target)
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
