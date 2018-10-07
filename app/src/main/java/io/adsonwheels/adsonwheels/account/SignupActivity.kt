package io.adsonwheels.adsonwheels.account

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import io.adsonwheels.adsonwheels.R
import io.adsonwheels.adsonwheels.models.SignupRequest
import io.adsonwheels.adsonwheels.models.SignupResponse
import io.adsonwheels.adsonwheels.utils.APIClient
import io.adsonwheels.adsonwheels.utils.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.EditText



class SignupActivity : AppCompatActivity() {
    private var userFirstName: EditText? = null
    private var userLastName: EditText? = null
    private var userEmail: EditText? = null
    private var userPassword: EditText? = null
    private var textLogin: TextView? = null
    private var btnSignup: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        userFirstName = findViewById(R.id.input_first_name)
        userLastName = findViewById(R.id.input_last_name)
        userEmail = findViewById(R.id.input_email)
        userPassword = findViewById(R.id.input_password)

        textLogin = findViewById(R.id.link_login)

        textLogin!!.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        btnSignup = findViewById(R.id.btn_signup)
        btnSignup!!.setOnClickListener { doUserSignup() }



    }

    private fun doUserSignup() {
        val apiService = APIClient.getClient(this.applicationContext).create(APIInterface::class.java)
        val signupRequest = SignupRequest()
        signupRequest.first_name = userFirstName!!.text.toString()
        signupRequest.last_name = userLastName!!.text.toString()
        signupRequest.user_name = userEmail!!.text.toString()
        signupRequest.password = userPassword!!.text.toString()
        signupRequest.role_id = "1"
        val userSignup = apiService.doSignup(signupRequest)
        userSignup.enqueue(object : Callback<SignupResponse> {
            override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {
                Log.d("login Response", response.toString())
                val SignupResponse   = response.body()
                if (SignupResponse!!.getSuccess().equals("true")) {

                } else {
                    Toast.makeText(this@SignupActivity, SignupResponse.message, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {

            }
        })

    }
}
