package com.utad.ud2_ej

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Registro.newInstance] factory method to
 * create an instance of this fragment.
 */
class Registro : Fragment() {
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

        return inflater.inflate(R.layout.fragment_registro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*view?.findViewById<Button>(R.id.btn_alta_registro)?.setOnClickListener {
            findNavController().navigate(R.id.action_registro_to_homeFragment2)
        }*/
    /*
        var registroEmail = view.findViewById<EditText>(R.id.id_Reg_email)
        var registroPassword = view.findViewById<EditText>(R.id.id_reg_password)
        var registroNombre = view.findViewById<EditText>(R.id.id_Reg_nombre)
        var registroApellidos = view.findViewById<EditText>(R.id.id_reg_apellidos)
        var registroFechaNac = view.findViewById<EditText>(R.id.id_reg_fechanac)
        view?.findViewById<Button>(R.id.btn_alta_registro)?.setOnClickListener {

            val action = RegistroDirections.actionRegistroToHomeFragment2(
                false,
                registroEmail.text.toString(),
                registroPassword.text.toString(),
                registroNombre.text.toString(),
                registroApellidos.text.toString(),
                registroFechaNac.text.toString()
            )



            var emailValido =false;
            var passwordValido =false;
            var nombreValido =false;
            var apellidosValido =false;
            var fechaNacValido =false;

            if (Validator.isEmailValid(registroEmail.text.toString())) {
                emailValido=true
            }
            else {
                Toast.makeText(context, "Email no válido",Toast.LENGTH_SHORT).show()
            }
            if (registroPassword.text.toString().length>=4){
                passwordValido = true
            }
            else {
                Toast.makeText(context, "Contraseña demasiado corta",Toast.LENGTH_SHORT).show()
            }
            if (registroNombre.text.toString().length>=2){
                nombreValido = true
            }
            else {
                Toast.makeText(context, "Nombre demasiado corto",Toast.LENGTH_SHORT).show()
            }
            if (registroApellidos.text.toString().length>=2){
                apellidosValido = true
            }
            else {
                Toast.makeText(context, "Apellidos demasiado corto",Toast.LENGTH_SHORT).show()
            }

            if (Validator.isDateValid(registroFechaNac.text.toString())) {
                fechaNacValido=true
            }
            else {
                Toast.makeText(context, "Fecha no válida",Toast.LENGTH_SHORT).show()
            }

            if (emailValido && passwordValido && nombreValido && apellidosValido && fechaNacValido) {
                Toast.makeText(context, "Registro válido",Toast.LENGTH_SHORT).show()
                findNavController().navigate(action)
            }


        }*/
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Registro.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Registro().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}