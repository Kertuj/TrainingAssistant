package com.example.trainingassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.trainingassistant.databinding.ActivityExerciseDetailsBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_exercise_details.*

class ExerciseDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseDetailsBinding
    private lateinit var exercise1: String
    private lateinit var exercise2: String
    private lateinit var exercise3: String
    private lateinit var exercise4: String
    private lateinit var description: String
    private val listOfCategory = mutableListOf<String>()
    private val listOfDescription = mutableListOf<String>()
    private var isDataSet = MutableLiveData<Boolean>().apply { value = false }


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
                    if(postSnapshot.key.toString() == "Opis"){
                        binding.description.text = postSnapshot.value.toString()
                        binding.descriptionLabel.text  = postSnapshot.key.toString()

                    }else{
                        println("SADQWWEQE" + postSnapshot.key.toString())
                        listOfCategory.add(postSnapshot.key.toString())
                        listOfDescription.add(postSnapshot.value.toString())
                    }
                }
                isDataSet.value = true
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
            }

        })
        isDataSet.observe(this, Observer {
            if(it){
                for (i in 0..listOfCategory.size) {
                    println("QWEQQQWEQEWQ " +i)
                    when(i){
                        0 -> {
                            binding.exercise1Label.text = listOfCategory[0]
                            binding.exercise1Strike.text = listOfDescription[0]
                        }
                        1 -> {
                            binding.exercise2Label.text = listOfCategory[1]
                            binding.exercise2Strike.text = listOfDescription[1]
                        }
                        2 ->{
                            binding.exercise3Label.text = listOfCategory[2]
                            binding.exercise3Strike.text = listOfDescription[2]
                        }
                        3 -> {
                            binding.exercise4Label.text = listOfCategory[3]
                            binding.exercise4Strike.text = listOfDescription[3]
                        }
                        else -> println("Niedziała")
                    }
                }
                isDataSet.value = false
            }
        })


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
