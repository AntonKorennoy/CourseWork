package com.example.coursework.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.coursework.databinding.FragmentSignUpBinding
import com.example.coursework.entities.User
import com.example.coursework.entities.Users
import com.example.coursework.utilities.EncryptionPassword


class SignUp : Fragment() {

    private lateinit var binging: FragmentSignUpBinding

    private lateinit var login: EditText
    private lateinit var password: EditText
    private lateinit var repeatPassword: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binging = FragmentSignUpBinding.inflate(inflater, container, false)
        return binging.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login = binging.fieldLogin
        password = binging.fieldPassword
        repeatPassword = binging.fieldRepeatPassword

        val base = Room.databaseBuilder(
            requireContext().applicationContext,
            Users::class.java,
            "base"
        ).allowMainThreadQueries().build()


        binging.buttonReg.setOnClickListener {

            if (login.text.isNotEmpty()
                && password.text.isNotEmpty()
                && repeatPassword.text.isNotEmpty()
            ) {

                if (password.text.toString() == repeatPassword.text.toString()) {

                    val userDao = base.getUserDao()
                    val pass = EncryptionPassword.getHashMD5(password.text.toString())

                    if (userDao.listLogin(login.text.toString()).isEmpty()) {

                        userDao.insert(User(login.text.toString(), pass))
                        Toast.makeText(this.context, "Пользователь зарегистрирован", Toast.LENGTH_SHORT).show()

                        requireFragmentManager().beginTransaction()
                            .remove(this@SignUp)
                            .commit()
                    }else{
                        Toast.makeText(this.context, "Такой пользователь уже существует", Toast.LENGTH_SHORT).show()
                    }


                } else {
                    Toast.makeText(this.context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this.context, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }
    }
}