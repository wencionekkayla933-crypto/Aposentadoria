package com.example.aposentadoria

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.aposentadoria.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val generos = arrayOf("Selecione", "Masculino", "Feminino")

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            generos
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGenero.adapter = adapter

        binding.bntCalcular.setOnClickListener {

            val idadeTexto = binding.edtIdade.text.toString()

            if (idadeTexto.isEmpty()) {
                binding.edtIdade.error = getString(R.string.erro_idade)
                return@setOnClickListener
            }

            val idade = idadeTexto.toInt()
            val genero = binding.spinnerGenero.selectedItem.toString()

            if (genero == "Selecione") {
                Snackbar.make(
                    binding.root,
                    getString(R.string.erro_genero),
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val idadeAposentadoria = if (genero == "Masculino") {
                65
            } else {
                62
            }

            val anosRestantes = idadeAposentadoria - idade

            if (anosRestantes < 0) {
                binding.txtResultado.text =
                    "Você já deveria estar aposentado."
            } else if (anosRestantes == 0) {
                binding.txtResultado.text =
                    "Você já pode se aposentar."
            } else {
                binding.txtResultado.text =
                    "Faltam $anosRestantes anos para você se aposentar."
            }
        }
    }
}

