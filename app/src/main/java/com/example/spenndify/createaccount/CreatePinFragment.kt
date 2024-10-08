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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.spenndify.R
import com.example.spenndify.SharedPreferenceManager
import com.example.spenndify.databinding.CreatePinFragBinding
import com.example.spenndify.model.User
import com.example.spenndify.utils.Status
import com.example.spenndify.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePinFragment:Fragment() {
  private lateinit var binding: CreatePinFragBinding
    private val viewModel:CreateAccountViewModel by viewModels()

  private  val args: CreatePinFragmentArgs by navArgs()

    private var pin = ""

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
        binding = CreatePinFragBinding.inflate(inflater,container,false).apply {
            viewModel
        }
        initUI()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userName = args.fname+" "+args.lname
        binding.usernameTxt.text = userName

        binding.pinBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initUI(){
        binding.createPinTxt.text="Create a new 4-digit pin"

            binding.apply {
                btnOne.setOnClickListener { controlPinPad2("1") }
                btnTwo.setOnClickListener { controlPinPad2("2") }
                btnThree.setOnClickListener { controlPinPad2("3") }
                btnFour.setOnClickListener { controlPinPad2("4") }
                btnFive.setOnClickListener { controlPinPad2("5") }
                btnSix.setOnClickListener { controlPinPad2("6") }
                btnSeven.setOnClickListener { controlPinPad2("7") }
                btnEight.setOnClickListener { controlPinPad2("8") }
                btnNine.setOnClickListener { controlPinPad2("9") }
                btnZero.setOnClickListener { controlPinPad2("0") }
                btnDelete.setOnClickListener { deletePinEntry() }
            }
    }

    private fun controlPinPad2(entry: String) {
        binding.apply {
            when {
                one1 == null -> {
                    pin1.background = context?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.drawable.activestepsbackground
                        )
                    }
                    one1 = entry
                }
                two2 == null -> {
                    pin2.background = context?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.drawable.activestepsbackground
                        )
                    }
                    two2 = entry
                }
                three3 == null -> {
                    pin3.background = context?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.drawable.activestepsbackground
                        )
                    }
                    three3 = entry
                }
                four4 == null -> {
                    pin4.background = context?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.drawable.activestepsbackground
                        )
                    }
                    four4 = entry
                    isDone = true
                    isComplete = true
                }
            }

            if (isDone) {

                pin = one1 + two2 + three3 + four4

                //saveInitialPinEntered(pin)
                SharedPreferenceManager.setInitialPin(context,pin)
                Log.i("pin_login", pin)

                changeToConfirmPinLayout()
                confirmUI()
            }

        }
    }

    private fun  changeToConfirmPinLayout(){
        binding.createPinTxt.text="Confirm Pin"
        resetPin()
    }

    private fun verifyPin(confirmPin:String?){
        val shared = context?.getSharedPreferences("PinSharedPref",Context.MODE_PRIVATE)
        val  initialPin = shared?.getString("initial_pin","")

        val firstName= args.fname.toString()
        val lastName = args.lname.toString()
        val idNum = args.idNo.toString()
        val emailAdd = args.emailAdd.toString()
        val phoneNumber = args.phoneNo.toString()
        val a1 =args.a1.toString()
        val a2 =args.a2.toString()
        val a3 =args.a3.toString()
        val password = confirmPin.toString()


        val user = User(
            firstName = firstName,
            lastName = lastName,
            idNumber = idNum,
            email = emailAdd,
            phone = "+254${phoneNumber}", //TODO work on it
            questionOne = a1,
            questionTwo = a2,
            questionThree = a3,
            password = password
        )

        if (initialPin == confirmPin){
          saveUserDetails(user)
        }else{
            binding.createPinTxt.text = "no match!!"
        }
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private fun deletePinEntry() {

        binding.apply {

            if (mConfirmPin.isNotEmpty()) {

                mConfirmPin = mConfirmPin.substring(0, mConfirmPin!!.length - 1)
            }
            if (four4 != null) {

                pin4.background = resources.getDrawable(R.drawable.inactive_pin_bg)
                four4 = null
                isDone = false
            }
            else if (three3 != null) {

                pin3.background = resources.getDrawable(R.drawable.inactive_pin_bg)
                three3 = null
            }else if (two2 != null) {

                pin2.background = resources.getDrawable(R.drawable.inactive_pin_bg)
                two2 = null
            }else{

                pin1.background = resources.getDrawable(R.drawable.inactive_pin_bg)
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


private fun confirmUI() {

    binding.apply {
        btnOne.setOnClickListener { controlPinPad1("1") }
        btnTwo.setOnClickListener { controlPinPad1("2") }
        btnThree.setOnClickListener { controlPinPad1("3") }
        btnFour.setOnClickListener { controlPinPad1("4") }
        btnFive.setOnClickListener { controlPinPad1("5") }
        btnSix.setOnClickListener { controlPinPad1("6") }
        btnSeven.setOnClickListener { controlPinPad1("7") }
        btnEight.setOnClickListener { controlPinPad1("8") }
        btnNine.setOnClickListener { controlPinPad1("9") }
        btnZero.setOnClickListener { controlPinPad1("0") }
        btnDelete.setOnClickListener { deletePinEntry() }
    }
}


private fun controlPinPad1(entry: String) {
    binding.apply {
        when {
            one1 == null -> {
                pin1.background = context?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.drawable.activestepsbackground
                    )
                }
                one1 = entry
            }
            two2 == null -> {
                pin2.background = context?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.drawable.activestepsbackground
                    )
                }
                two2 = entry
            }
            three3 == null -> {
                pin3.background = context?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.drawable.activestepsbackground
                    )
                }
                three3 = entry
            }
            four4 == null -> {
                pin4.background = context?.let {
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

    private fun saveUserDetails(user: User){
        viewModel.saveUserDetails(user).observe(viewLifecycleOwner){
            binding.apply {
                it.let { resource ->
                    when(resource.status){
                        Status.SUCCESS -> {
                            Toast.makeText(context, "works!!", Toast.LENGTH_SHORT).show()

                            val action = CreatePinFragmentDirections.actionCreatePinFragmentToLoginWelcomeFragment(args.fname,args.lname)
                            findNavController().navigate(action)
                        }

                        Status.ERROR -> {
                            showToast(resource.message.toString())
                        }
                        Status.LOADING ->{
                            Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

}//TODO improve error messages and Api call