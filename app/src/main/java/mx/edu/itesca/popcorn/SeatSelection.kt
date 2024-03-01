package mx.edu.itesca.popcorn

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class SeatSelection : AppCompatActivity() {
    var selectedSeatsCount = 0
    var seatLeft = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        val title: TextView =findViewById(R.id.titleSeats)
        var posMovie=-1

        val bundle=intent.extras
        if(bundle!=null){
            title.setText(bundle.getString( "name"))
            posMovie=bundle.getInt("posMovie")
            seatLeft = bundle.getInt("seatLeft", 0) // Obtener el número de asientos disponibles
        }

        val confirm: Button =findViewById(R.id.confirm)

        val row1: RadioGroup=findViewById(R.id.row1)
        val row2: RadioGroup=findViewById(R.id.row2)
        val row3: RadioGroup=findViewById(R.id.row3)
        val row4: RadioGroup=findViewById(R.id.row4)

        row1.setOnCheckedChangeListener{ group, checkedId->
            if(checkedId>-1){
                selectedSeatsCount++
                row2.clearCheck()
                row3.clearCheck()
                row4.clearCheck()
                group.findViewById<RadioButton>(checkedId).isEnabled = false
                group.findViewById<RadioButton>(checkedId).buttonDrawable = ContextCompat.getDrawable(this, R.drawable.radio_disabled)
            }
        }
        row2.setOnCheckedChangeListener{ group, checkedId->
            if(checkedId>-1){
                selectedSeatsCount++
                row1.clearCheck()
                row3.clearCheck()
                row4.clearCheck()

                row2.check(checkedId)
                group.findViewById<RadioButton>(checkedId).isEnabled = false
                group.findViewById<RadioButton>(checkedId).setTextColor(Color.GRAY)
            }
        }
        row3.setOnCheckedChangeListener{ group, checkedId->
            if(checkedId>-1){
                selectedSeatsCount++
                row2.clearCheck()
                row1.clearCheck()
                row4.clearCheck()

                row3.check(checkedId)
                group.findViewById<RadioButton>(checkedId).isEnabled = false
                group.findViewById<RadioButton>(checkedId).setTextColor(Color.GRAY)
            }
        }
        row4.setOnCheckedChangeListener{ group, checkedId->
            if(checkedId>-1){
                selectedSeatsCount++
                row2.clearCheck()
                row3.clearCheck()
                row1.clearCheck()

                row4.check(checkedId)
                group.findViewById<RadioButton>(checkedId).isEnabled = false
                group.findViewById<RadioButton>(checkedId).setTextColor(Color.GRAY)
            }
        }
        confirm.setOnClickListener{
            Toast.makeText(this,"Enjoy the movie! :D", Toast.LENGTH_LONG).show()
            if (selectedSeatsCount > 0) {
                val returnIntent = Intent()
                returnIntent.putExtra("selectedSeats", selectedSeatsCount)
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            } else {
                Toast.makeText(this, "Debes seleccionar al menos un asiento", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
