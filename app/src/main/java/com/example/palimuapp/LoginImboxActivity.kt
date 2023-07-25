package com.example.palimuapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.util.PatternsCompat
import com.example.palimuapp.databinding.ActivityLoginImboxBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginImboxActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var binding: ActivityLoginImboxBinding
    /* Todo lo comentado es lo que se ahorra con el binding
    private lateinit var txt_layouy_correo: TextInputLayout
     private lateinit var txt_layouy_password: TextInputLayout
     private lateinit var txt_correo: TextInputEditText
     private lateinit var txt_password: TextInputEditText
     private lateinit var btn_inicio_sesion: CardView
     private lateinit var btn_olvido_constraseña: TextView
     private lateinit var btn_atras:ImageButton*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginImboxBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        //initComponents()
        initListener()
    }

    override fun onStart() {
        super.onStart()
        val userLogeado = auth.currentUser
        if (userLogeado!=null){
            reload()
        }
    }

    private fun reload() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    /*private fun initComponents() {
        txt_correo = findViewById(R.id.txt_correo)
        txt_password = findViewById(R.id.txt_password)
        txt_layouy_correo = findViewById(R.id.txt_input_layout_1)
        txt_layouy_password = findViewById(R.id.txt_input_layout_2)
        btn_inicio_sesion = findViewById(R.id.btn_iniciar_sesion)
        btn_olvido_constraseña = findViewById(R.id.btn_olvido_contrasena)
        btn_atras = findViewById(R.id.btn_atras)
    }
*/
    private fun initListener() {
        realFormartMailValidator()
        realFormartPassValidator()
        binding.btnIniciarSesion.setOnClickListener {
            mailValidator(binding.txtCorreo.text.toString())
        }
        binding.btnOlvidoContrasena.setOnClickListener {
            //TODO
        }
        binding.btnAtras.setOnClickListener {
            finish()
        }

    }

    private fun mailValidator(mail: String): Boolean {
        return if (mail.isEmpty()) {
            binding.txtInputLayout1.helperText = "Campo correo vacío"
            false
        } else  {
            passwordValidator(mail,binding.txtPassword.text.toString())
            true
        }
    }

    private fun passwordValidator(email:String,pass: String): Boolean {
        return if (pass.isEmpty()) {
            binding.txtInputLayout1.helperText = "Campo contraseña vacío"
            false
        } else  {
            firebaseValidator(email,pass)
            true
        }

    }

    private fun firebaseValidator(email:String,pass:String){
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
            if (it.isSuccessful){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }else{
                val errorCode = it.exception?.localizedMessage
                when(errorCode){
                    "There is no user record corresponding to this identifier. The user may have been deleted."->{
                        Toast.makeText(this, "Correo no existe en la bd de datos", Toast.LENGTH_SHORT).show()
                    }
                    "The password is invalid or the user does not have a password." -> {
                        Toast.makeText(this, "Contraseña invalida", Toast.LENGTH_SHORT).show()
                }

                }
            }
        }
    }

    private fun realFormartMailValidator() {

        binding.txtCorreo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No se necesita implementar
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val correo = s.toString()
                val isFormatValite: Boolean = formatMail(correo)

                if (!isFormatValite) {
                    binding.txtInputLayout1.error = null
                    binding.txtInputLayout1.helperText = null
                } else {
                    binding.txtInputLayout1.error = "Formato de correo inválido"
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // No se necesita implementar
            }
        })
    }

    private fun realFormartPassValidator() {

        binding.txtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No se necesita implementar
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val correo = s.toString()
                val isFormatValite: Boolean = formatPass(correo)

                if (isFormatValite) {
                    binding.txtInputLayout2.error = null
                    binding.txtInputLayout2.helperText = null
                    binding.btnIniciarSesion.isEnabled = true
                } else {
                    binding.txtInputLayout2.error = "Campo password vacío"
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // No se necesita implementar
            }
        })
    }

    private fun formatMail(mail: String): Boolean {
        return !PatternsCompat.EMAIL_ADDRESS.matcher(mail).matches()
    }

    private fun formatPass(pass: String): Boolean {
        return !pass.isEmpty()
    }
}