package br.infnet.tp3_smpa_anacarolina_melopereira.ui.perguntas.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.infnet.tp3_smpa_anacarolina_melopereira.R
import br.infnet.tp3_smpa_anacarolina_melopereira.dao.pergunta.PerguntaDaoFirestore
import br.infnet.tp3_smpa_anacarolina_melopereira.model.AppUtil
import br.infnet.tp3_smpa_anacarolina_melopereira.model.Pergunta
import br.infnet.tp3_smpa_anacarolina_melopereira.ui.empresa.list.ListEmpresaViewModel
import br.infnet.tp3_smpa_anacarolina_melopereira.ui.perguntas.adapter.PerguntaRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.list_perguntas_fragment.*

class ListPerguntasFragment : Fragment() {

    private lateinit var viewModel: ListPerguntasViewModel
    private lateinit var viewModelEmpresa : ListEmpresaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireActivity().application

        val firebaseAuth = FirebaseAuth.getInstance()


        val listPerguntas = ListPerguntasViewModelFactory(PerguntaDaoFirestore())

        viewModel = ViewModelProvider(this, listPerguntas)
            .get(ListPerguntasViewModel::class.java)

        viewModel.perguntas.observe(viewLifecycleOwner) {
            setupListViewEmpresas(it)
        }

        viewModel.atualizarQuantidade()

        return inflater.inflate(R.layout.list_perguntas_fragment, container, false)
    }

    private fun setupListViewEmpresas(perguntas: List<Pergunta>) {
        listPerguntas.adapter = PerguntaRecyclerAdapter(perguntas) {
            AppUtil.perguntaSelecionada = it

        }
        listPerguntas.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabPerguntaCadastro.setOnClickListener {
            findNavController().navigate(R.id.action_listPerguntasFragment_to_cadastroPerguntasFragment)
        }

    }

}