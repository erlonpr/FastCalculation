package com.github.erlonpr.fastcalculation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.TextView
import com.github.erlonpr.fastcalculation.Extras.EXTRA_SETTINGS
import com.github.erlonpr.fastcalculation.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private val activitySettingsBinding: ActivitySettingsBinding by lazy {
        ActivitySettingsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activitySettingsBinding.root)

        // importa a barra de ferramentas
        setSupportActionBar(activitySettingsBinding.gameTbIn.gameTb)
        // define o título da barra de ferramentas
        supportActionBar?.title = getString(R.string.app_name)
        // define um subtítulo da barra de ferramentas
        supportActionBar?.subtitle = getString(R.string.settings)

        activitySettingsBinding.apply {
            // evento click do botão letsGoBt
            letsGoBt.setOnClickListener {
                val settings = Settings(
                    playerNameEt.text.toString(),
                    (roundsSp.selectedView as TextView).text.toString().toInt(),
                    roundIntervalRg.run {
                        findViewById<RadioButton>(checkedRadioButtonId).text.toString().toLong() * 1000L
                    }
                )
                // transfere os dados do SettingsActivity para o GameActivity
                val gameActivityIntent = Intent(this@SettingsActivity, GameActivity::class.java)
                gameActivityIntent.putExtra(EXTRA_SETTINGS, settings)
                startActivity(gameActivityIntent)
                finish()
            }
        }
    }
}