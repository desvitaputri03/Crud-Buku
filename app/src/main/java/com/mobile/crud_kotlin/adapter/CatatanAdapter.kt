package com.mobile.crud_kotlin.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mobile.crud_kotlin.helper.DbHelper
import com.mobile.crud_kotlin.model.ModelCatatan

class CatatanAdapter (

    private var listCatatan : List<ModelCatatan>,
    val context: Context
) : RecyclerView.Adapter<CatatanAdapter.MahasiswaViewHolder>() {

    private val db : DbHelper = DbHelper(context)

    class MahasiswaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtNama: TextView = itemView.findViewById(R.id.txtNama)
        val txtjurusan: TextView = itemView.findViewById(R.id.txtJurusan)
        val txtNim: TextView = itemView.findViewById(R.id.txtNIM)

        val btnEdit : ImageView = itemView.findViewById(R.id.btnEditItem)
        val btnDelete : ImageView = itemView.findViewById(R.id.btnDeleteItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_data_mahasiswa,
            parent, false
        )
        return MahasiswaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCatatan.size
    }

    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        val nMahasiswa = listCatatan[position]
        holder.txtNim.text = nMahasiswa.nim.toString()
        holder.txtNama.text = nMahasiswa.nama
        holder.txtjurusan.text = nMahasiswa.jurusan

        holder.itemView.setOnClickListener{

            val intent = Intent(holder.itemView.context, DetailPage::class.java)
            intent.putExtra("Jurusan", nMahasiswa.jurusan)
            intent.putExtra("Nama", nMahasiswa.nama)
            intent.putExtra("NIM", nMahasiswa.nim.toString())
            holder.itemView.context.startActivity(intent)


        }

        holder.btnDelete.setOnClickListener(){
            db.deleteMahasiswa(nMahasiswa.id)
            refreshData(db.getAllDataMahasiswa())
            Toast.makeText(holder.itemView.context,
                "Berhasil Delete Data ${nMahasiswa.nama}", Toast.LENGTH_LONG
            ).show()
        }

        holder.btnEdit.setOnClickListener(){
            //perlu bikin 1 lagi activity edit
            val intent = Intent(holder.itemView.context, UpdateActivity::class.java)
        }


    }
    //untuk refersh data
    fun refreshData(newMahasiswa: List<ModelMahasiswa>) {
        listMahasiswa = newMahasiswa
        notifyDataSetChanged()



    }
}