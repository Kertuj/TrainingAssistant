package com.example.trainingassistant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingassistant.ExerciseDetailsActivity.Companion.newIntentDetails
import com.example.trainingassistant.databinding.RecyclerviewExerciseListItemBinding

class ExerciseListAdapter(val fragment: TrainingPlanFragment) : RecyclerView.Adapter<ExerciseListAdapter.ExerciseListViewHolder>() {
    private var allExercise: List<String> = emptyList()

    inner class ExerciseListViewHolder(private val binding: RecyclerviewExerciseListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            with(binding){
                exerciseTitle.text = allExercise[position]

                exerciseTitle.setOnClickListener {
                    val text = exerciseTitle.text.toString()
                    fragment.startActivity(newIntentDetails(fragment, text))
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseListViewHolder {
        val binding: RecyclerviewExerciseListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_exercise_list_item, parent, false)
        return ExerciseListViewHolder(binding)
    }

    override fun getItemCount() = allExercise.size

    override fun onBindViewHolder(holder: ExerciseListAdapter.ExerciseListViewHolder, position: Int) {
        holder.bind(position)

    }

    fun setExercise(allExercise: List<String>){
        this.allExercise = allExercise
        notifyDataSetChanged()
    }
}