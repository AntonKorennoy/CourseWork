package com.example.coursework.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.coursework.R
import com.example.coursework.databinding.FragmentSignInBinding
import com.example.coursework.entities.Users
import com.example.coursework.utilities.EncryptionPassword


class SignIn : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    private lateinit var login: EditText
    private lateinit var password: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSignInBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login = binding.fieldLogin
        password = binding.fieldPassword

        binding.buttonReg.setOnClickListener {
            requireFragmentManager().beginTransaction()
                .add(R.id.frame, SignUp())
                .addToBackStack(null)
                .commit()
        }

        binding.buttonSignup.setOnClickListener {


            if (login.text.isNotEmpty() && password.text.isNotEmpty()) {

                val base = Room.databaseBuilder(
                    requireContext().applicationContext,
                    Users::class.java,
                    "base"
                ).allowMainThreadQueries().build()

                val userDao = base.getUserDao()
                val pass = EncryptionPassword.getHashMD5(password.text.toString())

                if (userDao.listUser(login.text.toString(), pass).isNotEmpty()) {

                    Log.i("debug", "news")

                    requireFragmentManager().beginTransaction()
                        .add(R.id.frame, News())
                        .addToBackStack(null)
                        .commit()

                } else {
                    Log.i("debug", "error")

                    requireFragmentManager().beginTransaction()
                        .add(R.id.frame, Error())
                        .addToBackStack(null)
                        .commit()

                }

            } else {
                Toast.makeText(this.context, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }
    }
}