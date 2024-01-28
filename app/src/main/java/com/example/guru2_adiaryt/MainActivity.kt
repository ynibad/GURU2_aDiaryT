package com.example.guru2_adiaryt

import android.view.View
import android.content.Intent
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

    // 액티비티가 생성될 때 호출
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)

        adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter // ViewPager에 어댑터 설정

        // TabLayout과 ViewPager2를 연동
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

    // 마이페이지 화면 전환
    fun onMyPageIconClick(view: View) {
        // 마이페이지 액티비티로 이동하는 인텐트 생성
        val intent = Intent(this, MyPageActivity::class.java)
        startActivity(intent)
    }

    // ViewPager2에 사용될 어댑터 클래스
    class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 5

        // 각 탭에 해당하는 프래그먼트를 생성
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

    // 예시를 위한 임시 프래그먼트
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
