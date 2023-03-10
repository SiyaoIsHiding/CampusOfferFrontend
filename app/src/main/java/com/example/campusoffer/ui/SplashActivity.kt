package com.example.campusoffer.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.example.campusoffer.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException


class SplashActivity : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 2000 // 2 seconds
    private val mHandler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val message = mHandler.obtainMessage()
        mHandler.sendMessageDelayed(message, SPLASH_DELAY)
    }

    override fun onResume() {
        super.onResume()
        mHandler.postDelayed({
            goToMainActivity()
        }, SPLASH_DELAY)
    }

    override fun onPause() {
        super.onPause()
        mHandler.removeCallbacksAndMessages(null)
    }


    private fun goToMainActivity() {
        // Initialize the GoogleSignInOptions object
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Check if the user is already logged in with Google
        val account = GoogleSignIn.getLastSignedInAccount(this)
        Log.d("google account", account.toString())
        if (account != null) {
            // User is already logged in, so start the MainActivity
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            // User is not logged in, so redirect to the Google login screen
            val signInIntent = GoogleSignIn.getClient(this, gso).signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
        // Finish the splash screen activity
        finish()
    }

    companion object {
        private const val RC_SIGN_IN = 123
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // User logged in successfully, start the MainActivity
                val account = task.getResult(ApiException::class.java)
                startActivity(Intent(this, MainActivity::class.java))
            } catch (e: ApiException) {
                // Login failed, show an error message
                Toast.makeText(this, "Login failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}




