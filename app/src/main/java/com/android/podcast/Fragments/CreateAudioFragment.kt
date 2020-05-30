package com.android.podcast.Fragments

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.android.podcast.Database.DataSource
import com.android.podcast.MainActivity
import com.android.podcast.Models.Item

import com.android.podcast.R
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateAudioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateAudioFragment(parentId : String) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var parentID = parentId
    var thumbImage : ImageView? = null
    var selectImageBtn : Button? = null
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
        var view:View =  inflater.inflate(R.layout.fragment_create_audio, container, false)
        thumbImage = view.findViewById(R.id.image)
        selectImageBtn = view.findViewById(R.id.selectImageBtn)
        add = view.findViewById(R.id.add)

        //set listerners
        //select image
        view.findViewById<Button>(R.id.selectVideo).setOnClickListener(View.OnClickListener {
            if(checkPermission()){
                openPicker()
            }
        })

        //on add button click
        view.findViewById<Button>(R.id.add).setOnClickListener(View.OnClickListener {
            //check for validation
            if(view.findViewById<EditText>(R.id.videoName).text ==null || view.findViewById<EditText>(R.id.videoName).text.length<1){
                Toast.makeText(activity,"Enter Audio name!", Toast.LENGTH_LONG).show()
                return@OnClickListener
            }
            if(view.findViewById<EditText>(R.id.description).text ==null || view.findViewById<EditText>(R.id.description).text.length<1){
                Toast.makeText(activity,"Enter Description!", Toast.LENGTH_LONG).show()
                return@OnClickListener
            }
            if(!IS_AUDIO_SELECTED){
                return@OnClickListener
            }
            addDataToDatabase(view);
        })
        return view
    }

    fun addDataToDatabase(view:View){
        var item = Item()
        item.id = UUID.randomUUID().toString().replace('-', 'S').toUpperCase();
        item.name = view!!.findViewById<EditText>(R.id.videoName).text.toString()
        item.type = "A"
        item.parentId = parentID
        item.description = view!!.findViewById<EditText>(R.id.description).text.toString()
        item.filePath = path!!
        item.updateDate = Calendar.getInstance().timeInMillis
        var ds = DataSource(activity as MainActivity)
        ds.open()
        ds.saveEntity(item)
        ds.close()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISSION_CODE ->
                if(grantResults.size>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openPicker();
                }
                else  Toast.makeText(activity, "Permission denied", Toast.LENGTH_SHORT).show()
        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun openPicker(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "audio/*"
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, AUDIO_PICK_CODE)
    }

    fun checkPermission() : Boolean {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var permissions:ArrayList<String> = ArrayList()
            if(activity!!.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                permissions.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            if(activity!!.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                permissions.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
            if(permissions.size > 0){
                ActivityCompat.requestPermissions(activity as MainActivity,permissions.toTypedArray(),
                    PERMISSION_CODE)
                return false
            }else return true
        } else {
            return true
        }
        return true
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            if (resultCode == Activity.RESULT_OK && requestCode == AUDIO_PICK_CODE) {
                IS_AUDIO_SELECTED = true
                path = data!!.data.path
//                val selectedImageUri: Uri = data!!.data
//
//                // OI FILE Manager
//                filemanagerstring = selectedImageUri.getPath()
//
//                // MEDIA GALLERY
//                selectedImagePath = getPath(selectedImageUri)
//                if (selectedImagePath != null) {
//                    val intent = Intent(
//                        this@HomeActivity,
//                        VideoplayAvtivity::class.java
//                    )
//                    intent.putExtra("path", selectedImagePath)
//                    startActivity(intent)
//                }
            }
        }
    }

    companion object {
        private var IS_AUDIO_SELECTED = false;
        private val AUDIO_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
        var path : String? =null
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateAudioFragment.
         */
        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            CreateAudioFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }
}
