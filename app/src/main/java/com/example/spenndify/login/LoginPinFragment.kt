package com.example.spenndify.createaccount

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.spenndify.R
import com.example.spenndify.SharedPreferenceManager
import com.example.spenndify.databinding.CreatePinFragBinding

class LoginPinFragment:Fragment() {
    private lateinit var binding: CreatePinFragBinding
    private  val args: CreatePinFragmentArgs by navArgs()

    private var one1: String? = null
    private var two2: String? = null
    private var three3: String? = null
    private var four4: String? = null
    private var isDone = false

    private  var mConfirmPin= ""
    private var isComplete = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CreatePinFragBinding.inflate(inflater,container,false)
        changeToConfirmPinLayout()
        confirmUI()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO change name
        val userName = args.fname+" "+args.lname
        binding.usernameTxt.text = userName

        binding.forgotpinBtn.setOnClickListener {
            val action = LoginPinFragmentDirections.actionLoginPinFragmentToForgotPinFragment()
            findNavController().navigate(action)
        }
        binding.pinBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun  changeToConfirmPinLayout(){
        binding.createPinTxt.text="Enter Pin"
        binding.forgotpinBtn.visibility = View.VISIBLE
        resetPin()
    }

    private fun verifyPin(confirmPin:String?){

        //TODO compare with pin in backend instead
        val shared = context?.getSharedPreferences("PinSharedPref",Context.MODE_PRIVATE)
        val  initialPin = shared?.getString("initial_pin","")

        if (initialPin == confirmPin){
            //TODO navigate to next
            binding.createPinTxt.text = "Match!!"
            val action = LoginPinFragmentDirections.actionLoginPinFragmentToDashboardFragment()
            findNavController().navigate(action)
        }else{
            binding.createPinTxt.text = "No Match!!"
        }
    }

    private fun confirmUI() {

        binding.apply {
            binding.btnOne.setOnClickListener { controlPinPad1("1") }
            binding.btnTwo.setOnClickListener { controlPinPad1("2") }
            binding.btnThree.setOnClickListener { controlPinPad1("3") }
            binding.btnFour.setOnClickListener { controlPinPad1("4") }
            binding.btnFive.setOnClickListener { controlPinPad1("5") }
            binding.btnSix.setOnClickListener { controlPinPad1("6") }
            binding.btnSeven.setOnClickListener { controlPinPad1("7") }
            binding.btnEight.setOnClickListener { controlPinPad1("8") }
            binding.btnNine.setOnClickListener { controlPinPad1("9") }
            binding.btnZero.setOnClickListener { controlPinPad1("0") }
            binding.btnDelete.setOnClickListener { deletePinEntry() }
        }
    }


    private fun controlPinPad1(entry: String) {
        binding.apply {
            when {
                one1 == null -> {
                    binding.pin1.background = context?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.drawable.activestepsbackground
                        )
                    }
                    one1 = entry
                }
                two2 == null -> {
                    binding.pin2.background = context?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.drawable.activestepsbackground
                        )
                    }
                    two2 = entry
                }
                three3 == null -> {
                    binding.pin3.background = context?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.drawable.activestepsbackground
                        )
                    }
                    three3 = entry
                }
                four4 == null -> {
                    binding.pin4.background = context?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.drawable.activestepsbackground
                        )
                    }
                    four4 = entry
                    isComplete = true
                }
            }

            if(isComplete) {

                mConfirmPin = one1 + two2 + three3 + four4
                Log.i("pin_con", mConfirmPin)

                verifyPin(mConfirmPin)

            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun deletePinEntry() {

        binding.apply {

            if (mConfirmPin.isNotEmpty()) {

                mConfirmPin = mConfirmPin.substring(0, mConfirmPin!!.length - 1)
            }
            if (four4 != null) {

                binding.pin4.background = resources.getDrawable(R.drawable.inactive_pin_bg)
                four4 = null
                isDone = false
            }
            else if (three3 != null) {

                binding.pin3.background = resources.getDrawable(R.drawable.inactive_pin_bg)
                three3 = null
            }else if (two2 != null) {

                binding.pin2.background = resources.getDrawable(R.drawable.inactive_pin_bg)
                two2 = null
            }else{

                binding.pin1.background = resources.getDrawable(R.drawable.inactive_pin_bg)
                one1 = null
            }

        }

    }

    private fun resetPin() {
        one1 = null
        two2 = null
        three3 = null
        four4 = null


        isDone = false

        binding.pin4.background = resources.getDrawable(R.drawable.inactive_pin_bg)
        binding.pin3.background = resources.getDrawable(R.drawable.inactive_pin_bg)
        binding.pin2.background = resources.getDrawable(R.drawable.inactive_pin_bg)
        binding.pin1.background = resources.getDrawable(R.drawable.inactive_pin_bg)


    }
}