package br.infnet.tp3_smpa_anacarolina_melopereira.ui.empresa.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import br.infnet.tp3_smpa_anacarolina_melopereira.R
import br.infnet.tp3_smpa_anacarolina_melopereira.dao.empresa.EmpresaDaoFirestore
import kotlinx.android.synthetic.main.cadastro_empresa_fragment.*

class CadastroEmpresaFragment : Fragment() {

    private lateinit var viewModel: CadastroEmpresaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireActivity().application

        val cadastroEmpresaViewModelFactory = CadastroEmpresaViewModelFactory(
            EmpresaDaoFirestore(),
            application
        )

        viewModel = ViewModelProvider(this, cadastroEmpresaViewModelFactory)
            .get(CadastroEmpresaViewModel::class.java)

        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
            if (status)
                findNavController().popBackStack()
        })
        viewModel.msg.observe(viewLifecycleOwner, Observer { msg ->
            if (!msg.isNullOrBlank())
                Toast.makeText(
                    requireContext(),
                    msg,
                    Toast.LENGTH_LONG
                ).show()
        })

        return inflater.inflate(R.layout.cadastro_empresa_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCadastroEmpresa.setOnClickListener {
            val nomeEmpresa = editTextNomeEmpresa.text.toString()
            val bairro = editTextBairroEmpresa.text.toString()

            val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

            val cryptoNomeEmpresa = EncryptedSharedPreferences.create(
                nomeEmpresa, masterKeyAlias, requireContext(),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )

            val cryptoBairro = EncryptedSharedPreferences.create(
                bairro, masterKeyAlias, requireContext(),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )

            viewModel.insertEmpresa(cryptoNomeEmpresa.toString(),cryptoBairro.toString())
            findNavController().navigate(R.id.listEmpresaFragment)
        }
    }

}