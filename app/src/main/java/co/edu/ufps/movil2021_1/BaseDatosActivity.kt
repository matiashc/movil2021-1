package co.edu.ufps.movil2021_1

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class BaseDatosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_datos)

        val id=findViewById<EditText>(R.id.txtid)
        val titulo=findViewById<EditText>(R.id.txttitulo)
        val descripcion=findViewById<EditText>(R.id.txtdescripcion)
        val isbn=findViewById<EditText>(R.id.txtisbn)
        val imagen=findViewById<EditText>(R.id.txtimagen)

        val guardar=findViewById<Button>(R.id.btnguardar)
        val consultar=findViewById<Button>(R.id.btnconsultar)
        val consultarNombre=findViewById<Button>(R.id.btnconsultarnombre)
        val eliminar=findViewById<Button>(R.id.btneliminar)
        val modificar=findViewById<Button>(R.id.btnmodificar)

        guardar.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this,"app", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            val libro = LibroEntity(
                id.text.toString(),
                titulo.text.toString(),
                descripcion.text.toString(),
                isbn.text.toString(),
                imagen.text.toString()
            )
            registro.put("id", libro.id)
            registro.put("titulo", libro.titulo)
            registro.put("descripcion", libro.descripcion)
            registro.put("isbn",libro.isbn)
            registro.put("imagen",libro.imagen)
            bd.insert("libros", null, registro)
            bd.close()
            id.setText("")
            titulo.setText("")
            descripcion.setText("")
            isbn.setText("")
            Toast.makeText(this, "Se cargaron los datos del libro", Toast.LENGTH_SHORT).show()
        }

        consultar.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "app", null, 1)
            val bd = admin.writableDatabase
            val fila = bd.rawQuery("select titulo, descripcion, isbn, imagen  from libros where id=${id.text.toString()}", null)
            if (fila.moveToFirst()) {
                titulo.setText(fila.getString(0))
                descripcion.setText(fila.getString(1))
                isbn.setText(fila.getString(2))
                imagen.setText(fila.getString(3))
            } else
                Toast.makeText(this, "No existe un libro con dicho código",  Toast.LENGTH_SHORT).show()
            bd.close()
        }

        consultarNombre.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "app", null, 1)
            val bd = admin.writableDatabase
            val fila = bd.rawQuery("select id,descripcion,isbn,imagen from libros where titulo='${titulo.text.toString()}'", null)
            if (fila.moveToFirst()) {
                id.setText(fila.getString(0))
                descripcion.setText(fila.getString(1))
                isbn.setText(fila.getString(2))
                imagen.setText(fila.getString(3))
            } else
                Toast.makeText(this, "No existe un libro con ese titulo", Toast.LENGTH_SHORT).show()
            bd.close()
        }

        eliminar.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "app", null, 1)
            val bd = admin.writableDatabase
            val cant = bd.delete("libros", "id=${id.text.toString()}", null)
            bd.close()
            id.setText("")
            titulo.setText("")
            descripcion.setText("")
            isbn.setText("")
            if (cant == 1)
                Toast.makeText(this, "Se borró el libro con dicho id", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "No existe un libro con dicho id", Toast.LENGTH_SHORT).show()
        }

        modificar.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "app", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("titulo", titulo.text.toString())
            registro.put("descripcion", descripcion.text.toString())
            registro.put("isbn",isbn.text.toString())
            registro.put("imagen",imagen.text.toString())

            val cant = bd.update("libros", registro, "id=${id.text.toString()}", null)
            bd.close()
            if (cant == 1)
                Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "no existe un libro con el código ingresado", Toast.LENGTH_SHORT).show()
        }
    }
}