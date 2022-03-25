package br.infnet.tp3_smpa_anacarolina_melopereira.ui.perguntas.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.infnet.tp3_smpa_anacarolina_melopereira.R
import br.infnet.tp3_smpa_anacarolina_melopereira.dao.pergunta.PerguntaDaoFirestore
import kotlinx.android.synthetic.main.cadastro_perguntas_fragment.*

class CadastroPerguntasFragment : Fragment() {

    private lateinit var viewModel: CadastroPerguntasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireActivity().application

        val cadastroPerguntaViewModelFactory = CadastroPerguntasViewModelFactory(
            PerguntaDaoFirestore(),
            application
        )

        viewModel = ViewModelProvider(this, cadastroPerguntaViewModelFactory)
            .get(CadastroPerguntasViewModel::class.java)

        viewModel.status.observe(viewLifecycleOwner, { status ->
            if (status)
                findNavController().popBackStack()
        })
        viewModel.msg.observe(viewLifecycleOwner, { msg ->
            if (!msg.isNullOrBlank())
                Toast.makeText(
                    requireContext(),
                    msg,
                    Toast.LENGTH_LONG
                ).show()
        })


        return inflater.inflate(R.layout.cadastro_perguntas_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btCadPergunta.setOnClickListener {

            val titulo = editTextTituloPergunta.text.toString()
            val pontuacao = onRadioButtonClicked()
            val comentario = "Comentário"

            viewModel.insertPergunta(titulo, pontuacao, comentario)
        }

    }

    fun onRadioButtonClicked():Int {

        var pontuacao = 0

        if (rbtnPergunta1SIM.isChecked)
            pontuacao ++
        if (rbtnPergunta2SIM.isChecked)
            pontuacao ++
        if (rbtnPergunta3SIM.isChecked)
            pontuacao ++
        if (rbtnPergunta4SIM.isChecked)
            pontuacao ++
        if (rbtnPergunta5SIM.isChecked)
            pontuacao ++


        return pontuacao
    }

}