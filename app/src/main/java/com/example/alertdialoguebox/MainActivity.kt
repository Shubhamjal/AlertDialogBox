package com.example.alertdialoguebox

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

var totalValue: Int = 0//public variable declaration

class MainActivity : AppCompatActivity() {
    private var displayTextView: TextView? = null
    private var openDialogButton: Button? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        displayTextView = findViewById(R.id.displayTextView)
        openDialogButton = findViewById(R.id.openDialogButton)

        openDialogButton?.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialogue)
            val addButton = dialog.findViewById<Button>(R.id.addButton)
            val subtractButton = dialog.findViewById<Button>(R.id.subtractButton)
            val resetButton = dialog.findViewById<Button>(R.id.resetButton)
            val inputEditText = dialog.findViewById<EditText>(R.id.inputEditText)

            addButton.setOnClickListener {
                val inputText = inputEditText.text.toString()
                if (inputText.isNotEmpty()) {
                    val inputValue = inputText.toInt()
                    totalValue += inputValue
                    displayTextView?.text = totalValue.toString()
                }
                dialog.dismiss()
            }

            subtractButton.setOnClickListener {
                val inputText = inputEditText.text.toString()//storing inputed value in input text variable
                if (inputText.isNotEmpty()) {
                    val inputValue = inputText.toInt()
                    totalValue -= inputValue
                    displayTextView?.text = totalValue.toString()
                }
                dialog.dismiss()
            }

            resetButton.setOnClickListener {
                totalValue = 0
                displayTextView?.text = totalValue.toString()
                dialog.dismiss()
            }

            dialog.show()
        }
    }
}
