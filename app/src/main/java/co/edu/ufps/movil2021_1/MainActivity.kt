package co.edu.ufps.movil2021_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    val TAG: String = "MainActivity";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth =FirebaseAuth.getInstance() ;
        val usuario: TextInputEditText = findViewById(R.id.usuario);
        val clave: TextInputEditText = findViewById(R.id.clave);
        val sesion: Button = findViewById(R.id.sesion);
        val crearUsuario: TextView = findViewById(R.id.crearusuario);
        val recuperarClave: TextView = findViewById(R.id.recuperarclave);
        sesion.setOnClickListener {
           iniciar(usuario.text.toString(),clave.text.toString())
        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            //reload();
        }
    }

    public fun iniciar(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        Toast.makeText(baseContext, "Authentication Exitosa.",
                                Toast.LENGTH_SHORT).show()
                        val user = auth.currentUser
                        irHome1();
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        //updateUI(null)
                    }
                }
    }

    fun irHome(){
        val intent = Intent(this,HomeActivity::class.java);
        startActivity(intent);

    }
    fun irHome1(){
        val intent = Intent(this,BaseDatosActivity::class.java);
        startActivity(intent);

    }
}