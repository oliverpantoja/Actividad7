package com.example.scoreboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var tvDeporte: TextView
    private lateinit var tvLocal: TextView
    private lateinit var tvVisitante: TextView
    private lateinit var tvSetLocal: TextView
    private lateinit var tvSetVisitante: TextView

    private var puntos_local: Int = 0
    private var puntos_visitante: Int = 0
    private var deporte: Int = 0
    private var contador_local: Int = 0
    private var contador_visitante: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDeporte = findViewById(R.id.tvDeporte)
        tvLocal = findViewById(R.id.tvLocal)
        tvVisitante = findViewById(R.id.tvVisitante)
        tvSetLocal = findViewById(R.id.tvSetLocal)
        tvSetVisitante = findViewById(R.id.tvSetVisitante)


        val bLocal: Button = findViewById(R.id.bLocal)
        val bVisitante: Button = findViewById(R.id.bVisitante)

        bLocal.setOnClickListener {
            anotacion("local")
        }

        bVisitante.setOnClickListener {
            anotacion("visitante")
        }
    }

    private fun anotacion(jugador: String) {
        if (deporte == 0 || deporte == 2) {
            if (jugador == "local") {
                puntos_local++
            } else {
                puntos_visitante++
            }

            if (deporte == 2 && (puntos_local == 15 || puntos_visitante == 15)) {
                if (jugador == "local"){
                    contador_local++
                } else {
                    contador_visitante++
                }
                recarga()
            }
        } else if (deporte == 1) {
            when {
                jugador == "local" -> {
                    when (puntos_local) {
                        0 -> puntos_local = 15
                        15 -> puntos_local = 30
                        30 -> puntos_local = 40
                        40 -> {
                            contador_local++
                            recarga()
                        }
                    }
                }
                jugador == "visitante" -> {
                    when (puntos_visitante) {
                        0 -> puntos_visitante = 15
                        15 -> puntos_visitante = 30
                        30 -> puntos_visitante = 40
                        40 -> {
                            contador_visitante++
                            recarga()
                        }
                    }
                }
            }
        }
        tvLocal.text = puntos_local.toString()
        tvVisitante.text = puntos_visitante.toString()
        tvSetLocal.text = contador_local.toString()
        tvSetVisitante.text = contador_visitante.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.opc_futbol -> {
                // Se seleccionó fútbol
                tvDeporte.text = getString(R.string.futbol)
                deporte = 0
                recarga()
                resetContadores()
            }
            R.id.opc_tenis -> {
                // Se seleccionó tenis
                tvDeporte.text = getString(R.string.tenis)
                deporte = 1
                recarga()
                resetContadores()
            }
            R.id.opc_voleybol -> {
                // Se seleccionó voleibol
                tvDeporte.text = getString(R.string.voleybol)
                deporte = 2
                recarga()
                resetContadores()
            }
            R.id.opc_recarga -> {
                recarga()
                resetContadores()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun recarga() {
        puntos_local = 0
        puntos_visitante = 0
        tvLocal.text = puntos_local.toString()
        tvVisitante.text = puntos_visitante.toString()
    }

    private fun resetContadores(){
        contador_local = 0
        contador_visitante = 0
        tvSetLocal.text = contador_local.toString()
        tvSetVisitante.text = contador_visitante.toString()
    }
}