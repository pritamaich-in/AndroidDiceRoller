package com.pritamaich.diceroller

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logging()

        val dice1 = Dice(6)
        val dice2 = Dice(6)
        val counter = StaticVariable(0)

        findViewById<TextView>(R.id.clickCounterUpdate).text = "Click Count : ${counter.value}"

        rollDice(dice1, findViewById(R.id.imageView))
        rollDice(dice2, findViewById(R.id.imageView2))

        findViewById<Button>(R.id.button).setOnClickListener {
            increaseClickCount(counter)
            rollDice(dice1, findViewById(R.id.imageView))
            rollDice(dice2, findViewById(R.id.imageView2))
        }
    }

    private fun increaseClickCount(counter: StaticVariable) {
        findViewById<TextView>(R.id.clickCounterUpdate).text = "Click Count : ${++counter.value}"
    }

    private fun rollDice(dice: Dice, diceImage: ImageView) {
        val diceRoll = dice.roll()
        val imageResource =when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
//        val diceImage: ImageView =
        diceImage.setImageResource(imageResource)
        diceImage.contentDescription = diceRoll.toString()

    }

    fun logging() {
        Log.e(TAG, "ERROR: a serious error like an app crash")
        Log.w(TAG, "WARN: warns about the potential for serious errors")
        Log.i(TAG, "INFO: reporting technical information, such as an operation succeeding")
        Log.d(TAG, "DEBUG: reporting technical information useful for debugging")
        Log.v(TAG, "VERBOSE: more verbose than DEBUG logs")
    }
}

class Dice(private val sides: Int) {

    fun roll(): Int {
        return (1..sides).random()
    }
}

class StaticVariable(var value: Int)