package co.edu.ufps.movil2021_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase

class RegistrarLibroActivity : AppCompatActivity() {
    lateinit var titulo: TextInputEditText
    lateinit var descripcion: TextInputEditText
    lateinit var isbn: TextInputEditText
    lateinit var imagen: TextInputEditText
    lateinit var guardar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_libro)
        titulo = findViewById(R.id.txttitulo)
        descripcion = findViewById(R.id.txtdescripcion)
        isbn = findViewById(R.id.txtisbn)
        imagen = findViewById(R.id.txtimagen)
        guardar = findViewById(R.id.btnguardar)
        guardar.setOnClickListener{
            registrarLibro();
        }
    }
    fun registrarLibro(){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.reference
        val libro = LibroEntity(
            myRef.push().key.toString(),
            titulo.text.toString(),
            descripcion.text.toString(),
            isbn.text.toString(),
            imagen.text.toString()
        )
        myRef.child("libros").child(libro.id).setValue(libro)
        finish()

    }
}