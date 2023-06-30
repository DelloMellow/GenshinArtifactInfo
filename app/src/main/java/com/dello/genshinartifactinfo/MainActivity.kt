package com.dello.genshinartifactinfo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvArtifact: RecyclerView
    private val list = ArrayList<Artifact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvArtifact = findViewById(R.id.rv_artifacts)
        rvArtifact.setHasFixedSize(true)

        //buat list dari string.xml
        list.addAll(getListArtifacts())
        showRecyclerList()
    }

    //method getListArtifacts
    private fun getListArtifacts(): ArrayList<Artifact> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listArtifact = ArrayList<Artifact>()
        for (i in dataName.indices) {
            val artifact = Artifact(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listArtifact.add(artifact)
        }
        return listArtifact
    }

    //method utk menampilkan RV
    private fun showRecyclerList() {
        rvArtifact.layoutManager = LinearLayoutManager(this)
        val artifactAdapter = ArtifactAdapter(list)
        rvArtifact.adapter = artifactAdapter

        artifactAdapter.setOnItemClickCallBack(object : ArtifactAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: Artifact) {
                showSelectedArtifact(data)
            }
        })
    }

    //buat tulisan ketika tiap gambar diklik
    private fun showSelectedArtifact(artifact: Artifact) {
        Toast.makeText(this, "Kamu memilih " + artifact.name, Toast.LENGTH_SHORT).show()
    }

    //untuk menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //action ketika menu diklik
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val goToAboutPage = Intent(this@MainActivity, AboutPage::class.java)
        startActivity(goToAboutPage)
        return super.onOptionsItemSelected(item)
        finish()
    }
}