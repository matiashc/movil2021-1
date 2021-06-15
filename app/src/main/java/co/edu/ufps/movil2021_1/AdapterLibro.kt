package co.edu.ufps.movil2021_1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdapterLibro(var contex: Context?, val dataSet: ArrayList<LibroEntity>, recurso: Int): RecyclerView.Adapter<AdapterLibro.LibroViewHolder>() {
    class LibroViewHolder(view: View): RecyclerView.ViewHolder(view){
        val titulo: TextView
        val descripcion: TextView
        val isbn: TextView
        val imagen: ImageView
        init {
            titulo = view.findViewById(R.id.titulo)
            descripcion = view.findViewById(R.id.descripcion)
            isbn = view.findViewById(R.id.isbn)
            imagen = view.findViewById(R.id.imagen)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_libro, parent, false)

        return LibroViewHolder(view)
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        holder.titulo.text = dataSet[position].titulo
        holder.descripcion.text = dataSet[position].descripcion
        holder.isbn.text = dataSet[position].isbn
        Picasso.get().load(dataSet[position].imagen).into(holder.imagen);
    }

    override fun getItemCount() = dataSet.size
}