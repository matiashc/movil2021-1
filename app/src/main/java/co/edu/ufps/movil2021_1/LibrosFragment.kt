package co.edu.ufps.movil2021_1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LibrosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LibrosFragment : Fragment() {
    lateinit var contenedorLibros: RecyclerView
    lateinit var adapterLibro: AdapterLibro
    lateinit var addLibro: FloatingActionButton
   
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_libros, container, false)
        contenedorLibros = view.findViewById(R.id.contenedorLibro)
        addLibro = view.findViewById(R.id.addlibro)
        val linearLayout = LinearLayoutManager(context)
        linearLayout.orientation = LinearLayoutManager.VERTICAL
        contenedorLibros.layoutManager = linearLayout
        adapterLibro = AdapterLibro(context,cargarDatosFireBase(),R.id.card)
        contenedorLibros.adapter = adapterLibro
        addLibro.setOnClickListener{
            irRegistrarLibro()
        }
        return view
    }

    private fun irRegistrarLibro() {
        val intent = Intent(context,RegistrarLibroActivity::class.java)
        startActivity(intent)
    }

    private fun cargarDatos(): ArrayList<LibroEntity> {
        val libros: ArrayList<LibroEntity> =  java.util.ArrayList<LibroEntity>()
        libros.add(
            LibroEntity(
            "1",
            "JAVA FUNDAMENTALS",
        "PROGRAMACIÓN EN JAVA",
            "RDE432",
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1455925344l/29227081._SY475_.jpg"
        )
        )
        libros.add(
            LibroEntity(
            "2",
            "ORACLE",
            "BASE DE DATOS EN ORACLE",
            "REY56",
        "https://olcovers2.blob.core.windows.net/coverswp/2018/05/OracleDatabaseNotesForProfessionals-OpenLibra.png"
        )
        )
    return libros
    }

    fun cargarDatosFireBase(): ArrayList<LibroEntity>{
        val libros = ArrayList<LibroEntity>()
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference()
        myRef.child("libros").addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    libros.clear()
                    for (data in snapshot.children){
                        val libro = data.getValue(LibroEntity::class.java)
                        libros.add(libro as LibroEntity)
                        adapterLibro.notifyDataSetChanged()
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("LibrosFragment","Carga Cancelada",error.toException())
            }
        })

    return libros
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LibrosFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LibrosFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}