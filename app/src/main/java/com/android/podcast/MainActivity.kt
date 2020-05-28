package com.android.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toolbar
import androidx.core.view.OneShotPreDrawListener.add
import com.android.podcast.Fragments.ItemFragment
import com.android.podcast.Fragments.TabFragment
import com.android.podcast.Fragments.dummy.DummyContent

class MainActivity : AppCompatActivity(), ItemFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //SET TOOLBAR
        val toolbar:androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar);
        toolbar.title = "Seasons"
        setSupportActionBar(toolbar);
        setBackButton(false)
        //add Fragment
        var frag = ItemFragment.newInstance(3);
        supportFragmentManager.beginTransaction().add(R.id.container, frag).commit()
    }

    public fun setBackButton(show:Boolean){
        supportActionBar!!.setDisplayHomeAsUpEnabled(show)
        supportActionBar!!.setDisplayShowHomeEnabled(show)
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        //Log here for now
        Log.d("item","clicked")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }
}
