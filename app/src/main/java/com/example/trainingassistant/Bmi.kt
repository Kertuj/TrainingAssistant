package com.example.trainingassistant

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.trainingassistant.databinding.FragmentBmiBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BmiFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BmiFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentBmiBinding.inflate(inflater, container, false)

        binding.bSubmit.setOnClickListener(){

            var masa = binding.etMasa.text.toString().toDouble()
            var wzrost = binding.etWzrost.text.toString().toDouble()

            var wynik = masa/(wzrost*wzrost)
            //wynik = String.format("%.3f", wynik)

            binding.tvWynik.text = String.format("%.2f", wynik)
            //binding.tvWynik.text = String.format("%.3f", wynik)

            if(wynik <= 24.99 && wynik >= 18.5){

                binding.tvWynik.setTextColor(Color.parseColor("#00FF00"))
                binding.tvOpis.text = getString(R.string.bmi_level_0)

            }else if(wynik < 18.5){

                binding.tvWynik.setTextColor(Color.parseColor("#0000FF"))
                binding.tvOpis.text = getString(R.string.bmi_level_m1)


            }else if(wynik > 24.99 && wynik <= 29.99){

                binding.tvWynik.setTextColor(Color.parseColor("#FFFF00"))
                binding.tvOpis.text = getString(R.string.bmi_level_p1)


            }else if(wynik > 29.99){

                binding.tvWynik.setTextColor(Color.parseColor("#FF0000"))
                binding.tvOpis.text = getString(R.string.bmi_level_p2)

            }

        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BmiFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BmiFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}


