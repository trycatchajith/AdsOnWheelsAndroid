package io.adsonwheels.adsonwheels.account

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import io.adsonwheels.adsonwheels.MainActivity
import io.adsonwheels.adsonwheels.R
import io.adsonwheels.adsonwheels.config.AdsOnWheelsPref
import io.adsonwheels.adsonwheels.models.LoginRequest
import io.adsonwheels.adsonwheels.models.LoginResponse
import io.adsonwheels.adsonwheels.utils.APIInterface
import io.adsonwheels.adsonwheels.utils.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    private var textSignup: TextView? = null
    private var btnLogin: Button? = null
    private var userEmail: EditText? = null
    private var userPassword: EditText? = null

    private var prefManager: AdsOnWheelsPref? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        userEmail = findViewById(R.id.input_email)
        userPassword = findViewById(R.id.input_password)
        textSignup = findViewById(R.id.link_signup)

        prefManager = AdsOnWheelsPref(this)

        textSignup!!.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }

        btnLogin = findViewById(R.id.btn_login)
        btnLogin!!.setOnClickListener { doUserLogin() }
    }

    private fun doUserLogin() {
        val apiService = APIClient.getClient(this.applicationContext).create(APIInterface::class.java)
        val loginRequest = LoginRequest()
        loginRequest.user_name = userEmail!!.text.toString()
        loginRequest.user_password = userPassword!!.text.toString()

        val userLogin = apiService.doLogin(loginRequest)
        userLogin.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("login Response", response.toString())
                val loginResponse   = response.body()
                if (loginResponse!!.success == "true") {

                    prefManager!!.loginSessionToken = loginResponse.session_token
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                    Toast.makeText(this@LoginActivity, loginResponse.message, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

            }
        })
    }
}
