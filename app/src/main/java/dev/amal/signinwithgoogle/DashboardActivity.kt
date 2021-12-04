package dev.amal.signinwithgoogle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

        id_txt.text = currentUser?.uid
        name_txt.text = currentUser?.displayName
        email_txt.text = currentUser?.email

        Glide.with(this)
            .load(currentUser?.photoUrl)
            .into(profile_image)

        sign_out_btn.setOnClickListener {
            mAuth.signOut()
            val signInIntent = Intent(this, SignInActivity::class.java)
            startActivity(signInIntent)
            finish()
        }
    }
}