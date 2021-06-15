package co.edu.ufps.movil2021_1

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
@Suppress("DEPRECATION")
class MyAdapter(
        var context: Context,
        fm: FragmentManager,
        var totalTabs: Int
): FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
       return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                LibrosFragment()
            }
            1 -> {
                FavoritosFragment()
            }
            2 -> {
                PerfilFragment()
            }
            else -> getItem(position)
        }
    }

}