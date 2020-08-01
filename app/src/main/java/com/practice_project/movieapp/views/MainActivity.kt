package com.practice_project.movieapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.practice_project.movieapp.R
import com.practice_project.movieapp.dagger.App
import com.practice_project.movieapp.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mainVM: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}