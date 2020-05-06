package com.example.trainingassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.trainingassistant.databinding.ActivityExerciseDetailsBinding
import com.example.trainingassistant.databinding.ActivityExerciseDetailsBindingImpl
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ExerciseDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseDetailsBinding
    private lateinit var exercise1: String
    private lateinit var exercise2: String
    private lateinit var exercise3: String
    private lateinit var exercise4: String
    private lateinit var description: String
    private val listOfCategory = mutableListOf<String>()
    private val listOfDescription = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exercise_details)
        val exerciseCategory = intent.getStringExtra(EXERCISE_CATEGORY)

        val database = Firebase.database
        val myRef = database.getReference("/sports/$exerciseCategory")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.children
                for (postSnapshot in dataSnapshot.children) {
                    Log.d("TAG", "Value is: $postSnapshot")
                    listOfCategory.add(postSnapshot.key.toString())
                    listOfDescription.add(postSnapshot.value.toString())
                    if(postSnapshot.value == "Opis") description = postSnapshot.value.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
            }

        })

        listOfCategory.forEach {
            if (it == "Opis"){
                binding.descriptionLabel.text = it
            }

        }

    }

    companion object{
        private const val EXERCISE_CATEGORY = "category"

        fun newIntentDetails(
            fragment: TrainingPlanFragment,
            exerciseCategory: String
        ): Intent? {
            val intent = Intent(fragment.context, ExerciseDetailsActivity::class.java)
            intent.putExtra(EXERCISE_CATEGORY, exerciseCategory)
            return intent
        }

    }
}
