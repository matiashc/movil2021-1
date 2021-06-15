package co.edu.ufps.movil2021_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class SubirImagenActivity : AppCompatActivity() {
    lateinit var subirArchivo: ImageView
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference()
    val file = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subir_imagen)
        subirArchivo = findViewById(R.id.uploadimagen)
        subirArchivo.setOnClickListener{
            fileUpload()
        }

    }
    fun fileUpload(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        startActivityForResult(intent, file)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == file){
            if (resultCode == RESULT_OK){
                val fileUri = data!!.data
                val folder: StorageReference=
                    FirebaseStorage.getInstance().getReference().child("libros")
                val file_name: StorageReference = folder.child("file" + fileUri!!.lastPathSegment)
                file_name.putFile(fileUri).addOnSuccessListener {
                    file_name.downloadUrl.addOnSuccessListener {
                        val hashMap =
                            HashMap<String, String>()
                        hashMap["link"] = java.lang.String.valueOf(it)
                        myRef.setValue(hashMap)
                        Log.d("Mensaje","se subio correctamente")

                    }
               }
            }
        }
    }
}