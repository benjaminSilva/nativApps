package com.example.benjamin.nativapps

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.benjamin.nativapps.fragments.AtividadeFragment
import com.example.benjamin.nativapps.fragments.NegocioFragment
import com.example.benjamin.nativapps.fragments.OrganizacaoFragment
import com.example.benjamin.nativapps.fragments.PessoaFragment
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction().replace(R.id.fragment,HomeFragment()).commit()
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Created by Benjamin Silva", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment,HomeFragment()).commit()
                supportActionBar!!.title = "NativApps"
            }
            R.id.nav_people -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment,PessoaFragment()).commit()
                supportActionBar!!.title = "Pessoas"
            }
            R.id.nav_company -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment,OrganizacaoFragment()).commit()
                supportActionBar!!.title = "Organização"
            }
            R.id.nav_business -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment,NegocioFragment()).commit()
                supportActionBar!!.title = "Negócios"
            }
            R.id.nav_activities -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment,AtividadeFragment()).commit()
                supportActionBar!!.title = "Atividades"
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
