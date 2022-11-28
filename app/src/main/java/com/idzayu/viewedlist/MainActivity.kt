package com.idzayu.viewedlist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.idzayu.kinoline.model.movies.Repository.room.AppDataBase
import com.idzayu.kinoline.ui.detail.AddFilmFragment
import com.idzayu.kinoline.ui.exit.ExitDialogFragment
import com.idzayu.viewedlist.databinding.ActivityMainBinding
import com.idzayu.viewedlist.model.repo.MovieList
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MovieList.addMovieDb(this)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.addBut -> {

                val dialog = AddFilmFragment()
                dialog.show(supportFragmentManager, "dialog")


            }
        }
        return true
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            val dialog = ExitDialogFragment()
            dialog.show(supportFragmentManager, "dialog")
        }

    }


    override fun onStop() {
        Executors.newSingleThreadExecutor().execute {
            Runnable {
                val appDb = AppDataBase.getInstance(this)?.getMovieDao()
                appDb?.insert(MovieList.getMovieEntity())
            }.run()
        }
        super.onStop()
    }

}