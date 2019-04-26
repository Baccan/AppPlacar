package br.com.fiap.placarapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_placar.*

class PlacarActivity : AppCompatActivity() {

    private var golCasa = 0
    private var golVisitante = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placar)

        /*if(intent.extras.containsKey("TIME_CASA")){
            tvTimeCasa.text = intent.getStringExtra("TIME_CASA")
        }
        if(intent.extras.containsKey("TIME_VISITANTE")){
            tvTimeCasa.text = intent.getStringExtra("TIME_VISITANTE")
        }*/
        tvTimeCasa.text = intent.getStringExtra("TIME_CASA")
        tvTimeVisitante.text = intent.getStringExtra("TIME_VISITANTE")

        tvPlacarCasa.text = "$golCasa"
        tvPlacarVisitante.text = "$golVisitante"

        btnGolCasa.setOnClickListener {
            golCasa++
            tvPlacarCasa.text = "$golCasa"
        }

        btnGolVisitante.setOnClickListener {
            golVisitante++
            tvPlacarVisitante.text = "$golVisitante"
        }
    }
}
