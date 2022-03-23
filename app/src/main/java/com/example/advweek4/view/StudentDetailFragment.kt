package com.example.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.advweek4.R
import com.example.advweek4.model.Student
import com.example.advweek4.util.loadImage
import com.example.advweek4.viewmodel.DetailViewModel
import com.example.advweek4.viewmodel.ListViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.student_list_item.view.*


class StudentDetailFragment : Fragment() {
    private lateinit var detailModel:DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailModel= ViewModelProvider(this).get(DetailViewModel::class.java)
        if(arguments != null){
            var id=StudentDetailFragmentArgs.fromBundle(requireArguments()).idStudent
            detailModel.setIdStudent(id)
        }

        detailModel.fetch()

        ObserveDetailModel()
    }

    fun ObserveDetailModel(){

        detailModel.studentLD.observe(viewLifecycleOwner, Observer {
            txtId.setText(it.id)
            txtBod.setText(it.bod)
            txtName.setText(it.name)
            txtPhone.setText(it.phone)
            imageView2.loadImage(it.photoUrl.toString(),progressBar2)
        })
    }
}