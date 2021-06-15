package co.edu.ufps.movil2021_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class HomeActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_home)
       tabLayout = findViewById(R.id.home)
       viewPager = findViewById(R.id.viewpager)
       tabLayout.tabGravity = TabLayout.GRAVITY_FILL
       val adapter = MyAdapter(this,supportFragmentManager,
       tabLayout.tabCount)
       viewPager.adapter = adapter
       viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
       tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
           override fun onTabSelected(tab: TabLayout.Tab) {
               viewPager.currentItem = tab.position
           }

           override fun onTabUnselected(tab: TabLayout.Tab) { }
           override fun onTabReselected(tab: TabLayout.Tab) { }

       })


    }
}