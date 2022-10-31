package cn.sinva.remember

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.sinva.remember.databinding.FragmentLoginBinding
import cn.sinva.remember.viewmodels.LoginViewModel
import cn.sinva.remember.viewmodels.LoginViewModelFactory
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

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
        // return inflater.inflate(R.layout.fragment_login, container, false)
        binding = FragmentLoginBinding.inflate(layoutInflater)
        val edtEmail = binding.edtEmail
        val edtPassword = binding.edtPassword
        val btnLogin = binding.btnLogin
        val loading = binding.loading

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)
        loginViewModel.loginFormState.observe(viewLifecycleOwner, Observer {
            val loginFormState = it
            btnLogin.isEnabled = loginFormState.isDataValid
            if (loginFormState.usernameError != null) {
                edtEmail.error = getString(loginFormState.usernameError)
            }
            if (loginFormState.passwordError != null) {
                edtPassword.error = getString(loginFormState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(viewLifecycleOwner) { loginResult ->
            loading.visibility = View.GONE
            if (loginResult.error != null) {
                // Toast.makeText(activity, getString(loginResult.error), Toast.LENGTH_SHORT).show()
                Snackbar.make(binding.root, getString(loginResult.error), Snackbar.LENGTH_SHORT).show()
            } else {
                // Toast.makeText(activity, "Login success", Toast.LENGTH_SHORT).show()
                Snackbar.make(binding.root, "Login success", Snackbar.LENGTH_SHORT).show()
            }
        }


        edtEmail.doAfterTextChanged {
            loginViewModel.loginDataChanged(
                edtEmail.text.toString(),
                edtPassword.text.toString()
            )
        }

        edtPassword.apply {
            doAfterTextChanged {
                loginViewModel.loginDataChanged(
                    edtEmail.text.toString(),
                    edtPassword.text.toString()
                )
            }
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    // 键盘的完成事件
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            edtEmail.text.toString(),
                            edtPassword.text.toString()
                        )
                }
                false
            }
        }

        btnLogin.setOnClickListener {
            loading.visibility = View.VISIBLE
            loginViewModel.login(
                edtEmail.text.toString(),
                edtPassword.text.toString()
            )
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
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

