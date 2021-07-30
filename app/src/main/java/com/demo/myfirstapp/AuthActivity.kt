package com.demo.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //Setup
        setup()

    }

    private fun setup(){
        btnregistrar.setOnClickListener {
            if (etEmail.text.isNotEmpty() && etPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(etEmail.text.toString(),
                        etPassword.text.toString()).addOnCompleteListener {
                            if(it.isSuccessful){
                             showHome()
                            }else{
                            showAlert()
                            }
                }
            }
        }

        btnacceder.setOnClickListener {
            if (etEmail.text.isNotEmpty() && etPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(etEmail.text.toString(),
                        etPassword.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        showHome()
                    }else{
                        showAlert()
                    }
                }
            }
        }
    }


    private fun showAlert(){
        val buldier=AlertDialog.Builder(this)
        buldier.setTitle("Error")
        buldier.setMessage("Se ha producido un error autenticando al usuario")
        buldier.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = buldier.create()
        dialog.show()
    }

    private fun showHome(){
        val homeIntent = Intent(this,RecyclerViewActivity::class.java)
        startActivity(homeIntent)
    }

}