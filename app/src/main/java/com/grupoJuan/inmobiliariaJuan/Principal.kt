package com.grupoJuan.inmobiliariaJuan

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView

class Principal : AppCompatActivity() {
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        listView = findViewById(R.id.cajon)
// 1
        val casaLista = Casa.getCasasDelFichero("casas.json", this)
        val adapter = AdaptadorCasa(this, casaLista)
        listView.adapter = adapter
// 2
        val listItems = arrayOfNulls<String>(casaLista.size)
// 3
        for (i in 0 until casaLista.size) {
            val recipe = casaLista[i]
            listItems[i] = recipe.title
        }

        val context = this
        listView.setOnItemClickListener { _, _, position, _ ->
            // 1
            val selectedRecipe = casaLista[position]
            // 2
            val ventanaPisos= InfoCasa.newIntent(context, selectedRecipe)
            // 3
            startActivity(ventanaPisos)
        }

    }
    }

