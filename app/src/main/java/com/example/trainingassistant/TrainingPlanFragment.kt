package com.example.trainingassistant

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainingassistant.databinding.FragmentTrainingPlanBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class TrainingPlanFragment : Fragment() {
    private lateinit var binding: FragmentTrainingPlanBinding
    private val listOfActivity = mutableListOf<String>()
    private val adapter = ExerciseListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTrainingPlanBinding.inflate(inflater, container, false)
        binding.exerciseRecyclerView.adapter = adapter
        binding.exerciseRecyclerView.layoutManager = LinearLayoutManager(inflater.context)

        val database = Firebase.database
        val myRef = database.getReference("/sports")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.children
                for (postSnapshot in dataSnapshot.children) {
                    Log.d("TAG", "Value is: $postSnapshot")
                    listOfActivity.add(postSnapshot.key!!)
                }
                adapter.setExercise(listOfActivity)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
            }

        })



        return binding.root
    }

}
