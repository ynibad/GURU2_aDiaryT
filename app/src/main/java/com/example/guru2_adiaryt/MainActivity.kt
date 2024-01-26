package com.example.guru2_adiaryt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.guru2_adiaryt.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)

        adapter = ViewPagerAdapter(this) // Assuming you have 5 tabs
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "배달비 \n게시판"
                1 -> "심부름 \n게시판"
                2 -> "맛집 \n 게시판"
                3 -> "저축 팁 \n게시판"
                4 -> "레시피 \n게시판"
                else -> null
            }
        }.attach()
    }

    class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 5

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> FirstFragment()
                1 -> SecondFragment()
                2 -> ThirdFragment()
                3 -> FourthFragment()
                4 -> FifthFragment()
                else -> throw IllegalArgumentException("Invalid position $position")
            }
        }
    }

    class DummyFragment : Fragment() {
        companion object {
            fun newInstance(position: Int): DummyFragment {
                val fragment = DummyFragment()
                val args = Bundle()
                args.putInt("position", position)
                fragment.arguments = args
                return fragment
            }
        }
    }
}
