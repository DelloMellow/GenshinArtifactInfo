package com.dello.genshinartifactinfo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    companion object {
        const val KEY_ARTIFACT = "key_artifact"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataArtifact = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(KEY_ARTIFACT, Artifact::class.java) as Artifact
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(KEY_ARTIFACT)
        }

        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)
        val ivDetailPhoto: ImageView = findViewById(R.id.iv_detail_photo)

        if (dataArtifact != null) {
            tvDetailName.text = dataArtifact.name
            tvDetailDescription.text = dataArtifact.description
            ivDetailPhoto.setImageResource(dataArtifact.photo!!)
        }
    }

    //untuk menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //action ketika menu diklik
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)

        val artf_name = tvDetailName.text.toString().trim()
        val artf_desc = tvDetailDescription.text.toString().trim()

        val share_text = "Artifact Name: \n" + artf_name + "\n\n" + "Description: \n\n" + artf_desc

        //intent buat share
        val action_share = Intent(Intent.ACTION_SEND)
        action_share.type = "text/plain" //type spesifik dari intentnya
        action_share.putExtra(Intent.EXTRA_TEXT, share_text)
        startActivity(action_share)

        return super.onOptionsItemSelected(item)
        finish()
    }
}