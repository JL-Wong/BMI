package my.edu.tarc.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageViewBMI: ImageView = findViewById(R.id.imageViewBMI)
        val textViewBMI: TextView = findViewById(R.id.textViewBMI)
        val textViewStatus: TextView = findViewById(R.id.textViewStatus)
        val weight: EditText = findViewById(R.id.weight)
        val height: EditText = findViewById(R.id.height)
        val cal: Button = findViewById(R.id.cal)
        val reset: Button = findViewById(R.id.reset)


        cal.setOnClickListener {
            if(weight.text.isBlank()){
                weight.setError(getString(R.string.value_required))
                return@setOnClickListener//exit the code
            }

            if(height.text.isBlank()){
                height.setError(getString(R.string.value_required))
                return@setOnClickListener
            }

            var inputW= weight.text.toString().toFloat()//make the input from string to number
            var inputH= height.text.toString().toFloat()
            var bmi = inputW/(inputH/100).pow(2)

            if(bmi<18.5){
                imageViewBMI.setImageResource(R.drawable.under)
                textViewBMI.text=String.format("%s : %.2f",getString(R.string.bmi),bmi)
                textViewStatus.text=String.format("%s : %s",getString(R.string.status),getString(R.string.under))

            }else if(bmi>=25){
                imageViewBMI.setImageResource(R.drawable.over)
                textViewBMI.text=String.format("%s : %.2f",getString(R.string.bmi),bmi)
                textViewStatus.text=String.format("%s : %s",getString(R.string.status),getString(R.string.over))
            }else if(bmi in 18.5..24.9){
                imageViewBMI.setImageResource(R.drawable.normal)
                textViewBMI.text=String.format("%s : %.2f",getString(R.string.bmi),bmi)
                textViewStatus.text=String.format("%s : %s",getString(R.string.status),getString(R.string.normal))
            }

        }

        reset.setOnClickListener {
            weight.text.clear()
            height.text.clear()
            imageViewBMI.setImageResource(R.drawable.empty)
            textViewBMI.text=String.format("%s %s",getString(R.string.bmi),"")
            textViewStatus.text=String.format("%s %s",getString(R.string.status),"")
        }
    }
}