package com.android.podcast.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.podcast.Database.DataSource
import com.android.podcast.MainActivity
import com.android.podcast.Models.Item

import com.android.podcast.R
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateTextFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateTextFragment(parentId : String) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var parentID = parentId
    var add : Button? = null
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
        var view:View = inflater.inflate(R.layout.fragment_create_text, container, false)
        view.findViewById<Button>(R.id.add).setOnClickListener(View.OnClickListener {
            //check for validation
            if(view.findViewById<EditText>(R.id.name).text ==null || view.findViewById<EditText>(R.id.videoName).text.length<1){
                Toast.makeText(activity,"Enter Video name!", Toast.LENGTH_LONG).show()
                return@OnClickListener
            }
            if(view.findViewById<EditText>(R.id.description).text ==null || view.findViewById<EditText>(R.id.description).text.length<1){
                Toast.makeText(activity,"Enter Description!", Toast.LENGTH_LONG).show()
                return@OnClickListener
            }

            addDataToDatabase(view);
        })
        return view
    }

    fun addDataToDatabase(view:View){

        var item = Item()
        item.id = UUID.randomUUID().toString().replace('-', 'S').toUpperCase();
        item.name = view!!.findViewById<EditText>(R.id.name).text.toString()
        item.type = "T"
        item.parentId = parentID
        item.description = view!!.findViewById<EditText>(R.id.description).text.toString()
        item.filePath = ""
        item.updateDate = Calendar.getInstance().timeInMillis
        var ds = DataSource(activity as MainActivity)
        ds.open()
        ds.saveEntity(item)
        ds.close()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateTextFragment.
         */
        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            CreateTextFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }
}
