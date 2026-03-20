package com.example.memorypuzzlegame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val numRows = 3
    private val numCols = 4
    private var cards = mutableListOf<Int>()
    private lateinit var gridLayout: GridLayout
    private var firstCard: ImageView? = null
    private var secondCard: ImageView? = null
    private var cardImages = intArrayOf(
        R.drawable.card1, R.drawable.card1,
        R.drawable.card2, R.drawable.card2,
        R.drawable.card3, R.drawable.card3,
        R.drawable.card4, R.drawable.card4,
        R.drawable.card5, R.drawable.card5,
        R.drawable.card6, R.drawable.card6,
        R.drawable.card7, R.drawable.card7,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gridLayout = findViewById(R.id.gridLayout)
        initializeGame()
    }

    private fun initializeGame() {
        cards = cardImages.toMutableList()
        cards.shuffle()  // Shuffle the card images

        for (i in 0 until numRows * numCols) {
            val imageView = ImageView(this)
            imageView.setImageResource(R.drawable.card_back) // Set the initial card back image
            imageView.setOnClickListener { onCardClicked(imageView, i) }
            gridLayout.addView(imageView)
        }
    }

    private fun onCardClicked(card: ImageView, position: Int) {
        if (firstCard == null) {
            firstCard = card
            firstCard!!.setImageResource(cards[position])  // Show the card image
        } else if (secondCard == null && card != firstCard) {
            secondCard = card
            secondCard!!.setImageResource(cards[position])

            checkForMatch()  // Check for match
        }
    }

    private fun checkForMatch() {
        Handler().postDelayed({
            if (firstCard!!.drawable.constantState == secondCard!!.drawable.constantState) {
                Toast.makeText(this, "Match Found!", Toast.LENGTH_SHORT).show()
            } else {
                firstCard!!.setImageResource(R.drawable.card_back)  // Hide the card back
                secondCard!!.setImageResource(R.drawable.card_back)
                Toast.makeText(this, "Try Again!", Toast.LENGTH_SHORT).show()
            }
            firstCard = null
            secondCard = null
        }, 1000)
    }
}