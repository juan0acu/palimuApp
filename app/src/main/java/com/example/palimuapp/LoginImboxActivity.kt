package com.example.palimuapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.util.PatternsCompat
import com.example.palimuapp.databinding.ActivityLoginImboxBinding

class LoginImboxActivity : AppCompatActivity() {

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
        //initComponents()
        initListener()
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
        } else if (mail == "juan@gmail.com") {
            passwordValidator(binding.txtPassword.text.toString())
        } else {
            Toast.makeText(this, "Correo invalido", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun passwordValidator(pass: String): Boolean {
        return if (pass.isEmpty()) {
            binding.txtInputLayout1.helperText = "Campo contraseña vacío"
            false
        } else if (pass == "123456") {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            true
        } else {
            Toast.makeText(this, "Contraseña invalida", Toast.LENGTH_SHORT).show()
            false
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