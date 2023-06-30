package com.dello.genshinartifactinfo

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArtifactAdapter(private val listArtifact: ArrayList<Artifact>) :
    RecyclerView.Adapter<ArtifactAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallBack: OnItemClickCallBack

    //click call back
    interface OnItemClickCallBack {
        fun onItemClicked(data: Artifact)
    }

    //method click call back
    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_artifact, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listArtifact[position]
        if (photo != null) {
            holder.imgPhoto.setImageResource(photo)
        }
        holder.tvName.text = name
        holder.tvDescription.text = description

        //biar tiap gambar bisa diclick
        holder.itemView.setOnClickListener {
            onItemClickCallBack.onItemClicked(listArtifact[holder.adapterPosition])
        }

        //buat kirim data ke detail activity
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_artifact", listArtifact[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = listArtifact.size

}