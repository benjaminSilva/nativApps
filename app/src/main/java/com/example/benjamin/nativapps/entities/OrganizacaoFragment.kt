package com.example.benjamin.nativapps.entities

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.benjamin.nativapps.R
import kotlinx.android.synthetic.main.fragment_organizacao.*
import kotlinx.android.synthetic.main.fragment_pessoa.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonOrganizacao.setOnClickListener {
            Snackbar.make(view, "Organização adicionada", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
}
