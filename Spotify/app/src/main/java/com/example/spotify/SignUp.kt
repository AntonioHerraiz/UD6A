package com.example.spotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.spotify.databinding.ActivitySingUpBinding
var userList : MutableList<User> = mutableListOf()
class SignUp : AppCompatActivity() {


    private lateinit var binding : ActivitySingUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUp()
    }
    var validator = binding.validate.setOnClickListener {  }
    private fun setUp() {
        var name = binding.nameEditText.text.toString()
        var email = binding.emailEditText.text.toString()
        var password = binding.password.text.toString()
        var password2 = binding.password2.text.toString()
        var result = true

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()){
            Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show()
            result = false
        }

        // Falta aqui utilizar la expesion regular
        if (email.isEmpty() ){
            binding.emailEditText.error = "Completa tu email"
        }
        if (password.isEmpty()){
            binding.nameEditText.error = "Completa la contraseña"
        }
        if (password2.isEmpty()){
            binding.nameEditText.error = "Completa tu nombre"
        }
        if (password.isNotEmpty() && password2.isNotEmpty() && password2 != password){
            result = false
        }
        if (result){

            val user = User(name, email, password)
            userList.add(user)
            val intent : Intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
    }
}