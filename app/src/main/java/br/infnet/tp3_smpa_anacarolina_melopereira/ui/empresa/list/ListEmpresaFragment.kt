package br.infnet.tp3_smpa_anacarolina_melopereira.ui.empresa.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.infnet.tp3_smpa_anacarolina_melopereira.R
import br.infnet.tp3_smpa_anacarolina_melopereira.dao.empresa.EmpresaDaoFirestore
import br.infnet.tp3_smpa_anacarolina_melopereira.model.AppUtil
import br.infnet.tp3_smpa_anacarolina_melopereira.model.Empresa
import br.infnet.tp3_smpa_anacarolina_melopereira.ui.empresa.adapter.EmpresaRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.list_empresa_fragment.*

class ListEmpresaFragment : Fragment() {

    private lateinit var viewModel: ListEmpresaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireActivity().application

        val firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null)
            findNavController().popBackStack()

        val listEmpresas = ListEmpresaViewModelFactory(EmpresaDaoFirestore())

        viewModel = ViewModelProvider(this, listEmpresas)
            .get(ListEmpresaViewModel::class.java)

        viewModel.empresas.observe(viewLifecycleOwner){
            setupListViewEmpresas(it)
        }

        viewModel.atualizarQuantidade()

        return inflater.inflate(R.layout.list_empresa_fragment, container, false)
    }


    private fun setupListViewEmpresas(empresas: List<Empresa>) {
        empresasList.adapter = EmpresaRecyclerAdapter(empresas) {
            AppUtil.empresaSelecionada = it

            findNavController().navigate(R.id.action_listEmpresaFragment_to_listPerguntasFragment)
        }
        empresasList.layoutManager = LinearLayoutManager(requireContext())
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser = firebaseAuth.currentUser

        fabCadastroEmpresa.setOnClickListener {
            findNavController().navigate(R.id.action_listEmpresaFragment_to_cadastroEmpresaFragment)
        }

        fabLogout.setOnClickListener {
            firebaseAuth.signOut()
            findNavController().navigate(R.id.action_listEmpresaFragment_to_loginFragment)
        }
    }

}