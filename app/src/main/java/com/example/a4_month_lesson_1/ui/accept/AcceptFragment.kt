package com.example.a4_month_lesson_1.ui.accept

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.a4_month_lesson_1.R
import com.example.a4_month_lesson_1.databinding.FragmentAcceptBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

@Suppress("UNUSED_EXPRESSION")
class AcceptFragment : Fragment() {

    private lateinit var binding: FragmentAcceptBinding
    private lateinit var args: AcceptFragmentArgs
    private lateinit var auth: FirebaseAuth
//    private var verificationIdGlobal: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAcceptBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args = arguments?.let { AcceptFragmentArgs.fromBundle(it) }!!
        auth = FirebaseAuth.getInstance()
        binding.send.setOnClickListener {
                val credential = PhoneAuthProvider.getCredential(args.verId!!, binding.etCode.text.toString())
                signInWithPhoneAuthCredential(credential)
        }
    }

        private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
            auth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(R.id.navigation_home)
                        val user = task.result?.user
                    } else {
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {

                        }
                    }
                }
        }

}