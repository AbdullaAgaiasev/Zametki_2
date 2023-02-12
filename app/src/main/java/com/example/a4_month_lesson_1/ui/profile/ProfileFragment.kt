package com.example.a4_month_lesson_1.ui.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.a4_month_lesson_1.R
import com.example.a4_month_lesson_1.data.Pref
import com.example.a4_month_lesson_1.databinding.FragmentProfileBinding
import com.example.a4_month_lesson_1.utils.loadImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref
    private lateinit var auth: FirebaseAuth

    private val launcher = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK
            && result.data != null
        ) {
            val photoUri: Uri? = result.data?.data
            pref.saveImage(photoUri.toString())
            binding.ivImage.loadImage(photoUri.toString())

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref =Pref(requireContext())

        binding.edName.setText(pref.getName())
        binding.edAge.setText(pref.getAge())
        binding.ivImage.loadImage(pref.getImage())

        binding.save.setOnClickListener {

            pref.saveName(binding.edName.text.toString())
            pref.saveAge(binding.edAge.text.toString())
        }
        binding.btnLogOut.setOnClickListener {

            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.onBoardingFragment)
            auth.currentUser == null
            findNavController().navigate(R.id.authFragment)
            
        }
        binding.ivImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            launcher.launch(intent)
        }

    }
}