package com.example.national_hackathon_it_amnesia.ui.activity


import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.national_hackathon_it_amnesia.R
import com.example.national_hackathon_it_amnesia.App
import com.example.national_hackathon_it_amnesia.mvp.BaseActivity
import com.example.national_hackathon_it_amnesia.mvp.BaseFragment
import com.itamnesia.hackaton.contract.MessageServiceGrpc
import com.itamnesia.hackaton.contract.MessageServiceOuterClass
import com.example.national_hackathon_it_amnesia.ui.chat.MessageItemAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.grpc.android.AndroidChannelBuilder
import io.grpc.stub.StreamObserver

class MainActivity : BaseActivity<MainPresenterImpl>(), MainView {

    private val messageList: MutableList<MessageServiceOuterClass.MessageResponse> = mutableListOf()

    private lateinit var messageRequest: MessageServiceGrpc.MessageServiceStub

    private lateinit var bottomNavigationView: BottomNavigationView

    val messages: MutableLiveData<MutableList<MessageServiceOuterClass.MessageResponse>> =
        MutableLiveData(mutableListOf())

    interface OnBackPressedListener {
        fun onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.view = this
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val mainGraph = navController.navInflater.inflate(R.navigation.navigation_graph)
        navController.graph = mainGraph
        bottomNavigationView = findViewById(R.id.clientBottomNavigationView)
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.logoFragment -> hideBotNav()
                R.id.authFragment -> hideBotNav()
                R.id.registrationFragment -> hideBotNav()
                else -> showBotNav()
            }
        }
    }

    private fun hideBotNav() {
        bottomNavigationView.animate().alpha(0.0f).duration = 300
        bottomNavigationView.visibility = View.GONE
    }

    private fun showBotNav() {
        bottomNavigationView.animate()
            .alpha(1.0f).duration = 1000
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        bottomNavigationView.visibility = View.VISIBLE
    }


    override fun onBackPressed() {
        if (BaseFragment.backPressedListener != null) {
            BaseFragment.backPressedListener!!.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createMainActivity()
            .inject(this)
    }
}