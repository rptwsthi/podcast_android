package com.android.podcast.Fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.android.podcast.MainActivity
import com.android.podcast.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabFragment : Fragment() {
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
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_tab, container, false)
        activity!!.toolbar.title = "Add Media";
        (activity as MainActivity).setBackButton(true)
        val viewpager = view.findViewById<ViewPager>(R.id.viewpager)
        var adapter:VPAdapter = VPAdapter(childFragmentManager,FragmentPagerAdapter.POSITION_NONE)
        adapter.addFragment(CreateFolderFragment(), "FOLDER")
        adapter.addFragment(CreateVideoFragment(), "VIDEO")
        adapter.addFragment(CreateAudioFragment(), "AUDIO")
        adapter.addFragment(CreateTextFragment(), "TEXT")
        viewpager.setAdapter(adapter)

        // Set Tabs inside Toolbar
        var tabs:TabLayout = view.findViewById<TabLayout>(R.id.tabs)
        tabs.setupWithViewPager(viewpager);

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TabFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TabFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    class VPAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {

        private var mFragmentList: ArrayList<Fragment> = ArrayList()
        private var mFragmentTitleList: ArrayList<String> = ArrayList()
        override fun getItem(position: Int): Fragment {
            return mFragmentList.get(position);
        }

        override fun getCount(): Int {
            return mFragmentList.size;
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList.get(position);
        }

        fun addFragment(
            fragment: Fragment,
            title: String
        ) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

    }
}
