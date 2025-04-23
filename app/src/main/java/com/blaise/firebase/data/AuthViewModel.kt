package com.blaise.firebase.data

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.blaise.firebase.model.User
import com.blaise.firebase.navigation.ROUTE_HOME
import com.blaise.firebase.navigation.ROUTE_LOGIN
import com.blaise.firebase.navigation.ROUTE_REGISTER
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel(var navController: NavHostController, var context: Context){
    val mAuth: FirebaseAuth

    init{
        mAuth = FirebaseAuth.getInstance()
    }

    fun signup(email:String,pass:String,confpass:String) {
      if (email.isBlank() || pass.isBlank() || confpass.isBlank()) {

        Toast.makeText(context, "Please input your email and password", Toast.LENGTH_LONG).show()
        return
      } else if (pass != confpass) {
        Toast.makeText(context, "Password does not match", Toast.LENGTH_LONG).show()
        return
      } else {
        mAuth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val userdata = User(email, pass, mAuth.currentUser!!.uid)
                    val regRef = FirebaseDatabase.getInstance().getReference()
                        .child("Users/"+mAuth.currentUser!!.uid)
                    regRef.setValue(userdata).addOnCompleteListener{
                        if (it.isSuccessful){
                            Toast.makeText(context,"Registered Successfully",Toast.LENGTH_LONG).show()
                            navController.navigate(ROUTE_LOGIN)
                        }else{
                            Toast.makeText(context,"${it.exception!!.message}",Toast.LENGTH_LONG).show()
                            navController.navigate(ROUTE_LOGIN)
                        }
                    }
                }else{
                    navController.navigate(ROUTE_REGISTER)
                }
            }
    }
  }

    fun login(email: String, pass: String){

        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(context,"Successfully Logged in",Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_HOME)
            }else{
                Toast.makeText(context,"&{it.exception!!.message}",Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_LOGIN)
            }
        }
    }



fun logout(){
    mAuth.signOut()
    navController.navigate(ROUTE_LOGIN)
}

fun isloggedin():Boolean{
    return mAuth.currentUser !=null
}
}
