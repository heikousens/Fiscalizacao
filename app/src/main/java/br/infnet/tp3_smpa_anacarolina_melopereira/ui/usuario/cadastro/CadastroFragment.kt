package br.infnet.tp3_smpa_anacarolina_melopereira.ui.usuario.cadastro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.infnet.tp3_smpa_anacarolina_melopereira.R
import kotlinx.android.synthetic.main.cadastro_fragment.*

class CadastroFragment : Fragment() {

    private lateinit var viewModel: CadastroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(CadastroViewModel::class.java)

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it)
                findNavController().popBackStack()
        })

        viewModel.msg.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrBlank())
                Toast
                    .makeText(
                        requireContext(),
                        it,
                        Toast.LENGTH_LONG
                    ).show()

        })

        return inflater.inflate(R.layout.cadastro_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCadastrar.setOnClickListener {
            val email = editTextCadEmail.text.toString()
            val senha = editTextCadSenha.text.toString()
            val resenha = editTextCadRepetirSenha.text.toString()
            if (senha == resenha){
                viewModel.salvarCadastro(email,senha)
            } else {
                viewModel.alterarMsg("As senhas não são iguais")
            }
        }
    }

}