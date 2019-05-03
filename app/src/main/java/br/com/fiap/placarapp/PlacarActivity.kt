package br.com.fiap.placarapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_placar.*

class PlacarActivity : AppCompatActivity() {

    private lateinit var placarViewModel: PlacarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placar)

        placarViewModel = ViewModelProviders.of(this)
                .get(PlacarViewModel::class.java)

        placarViewModel.goalHome.observe(this, Observer{ goalHome ->
            //tvPlacarCasa.text = "${placarViewModel.goalHome}"
            tvPlacarCasa.text = "$goalHome"
        })

        placarViewModel.goalAway.observe(this, Observer{
            //tvPlacarVisitante.text = "${placarViewModel.goalAway}"
            tvPlacarVisitante.text = "$it"
        })

        /*if(intent.extras.containsKey("TIME_CASA")){
            tvTimeCasa.text = intent.getStringExtra("TIME_CASA")
        }
        if(intent.extras.containsKey("TIME_VISITANTE")){
            tvTimeCasa.text = intent.getStringExtra("TIME_VISITANTE")
        }*/

        tvTimeCasa.text = intent.getStringExtra("TIME_CASA")
        tvTimeVisitante.text = intent.getStringExtra("TIME_VISITANTE")

        tvPlacarCasa.text = "${placarViewModel.goalHome}"
        tvPlacarVisitante.text = "${placarViewModel.goalAway}"

        btnGolCasa.setOnClickListener {
            placarViewModel.goalHome()
        }

        btnGolVisitante.setOnClickListener {
            placarViewModel.goalAway()
        }

        btnRestart.setOnClickListener{
            placarViewModel.restartMatch()
        }

        btnShare.setOnClickListener {
            shareWhatsApp()
        }
    }

    private fun shareWhatsApp() {
        try {
            val whatsAppIntent = Intent(Intent.ACTION_SEND)
            whatsAppIntent.type = "text/plain"
            whatsAppIntent.setPackage("com.whatsapp")

            val message = getString(R.string.message_to_share,
                    tvPlacarVisitante.text,
                    tvPlacarCasa.text,
                    placarViewModel.goalHome.value,
                    placarViewModel.goalAway.value)
            /*val message = "O resultado do jogo entre ${tvPlacarCasa.text} X " +
                "${tvPlacarVisitante.text} foi: ${placarViewModel.goalHome.value}" +
                    " a ${placarViewModel.goalAway.value}"*/

            whatsAppIntent.putExtra(Intent.EXTRA_TEXT, message)
            startActivity(whatsAppIntent)

        }catch (e: ActivityNotFoundException) {
            //Toast.makeText(this, "WhatsApp não instalado", Toast.LENGTH_LONG).show()
            val appPackageName = "com.whatsapp"
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
            } catch (anfe: android.content.ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
            }
        }
    }
}
