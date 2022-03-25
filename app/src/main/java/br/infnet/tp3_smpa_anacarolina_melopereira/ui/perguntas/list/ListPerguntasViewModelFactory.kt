package br.infnet.tp3_smpa_anacarolina_melopereira.ui.perguntas.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.infnet.tp3_smpa_anacarolina_melopereira.dao.pergunta.PerguntaDao

class ListPerguntasViewModelFactory(private val perguntaDao: PerguntaDao
)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListPerguntasViewModel::class.java))
            return ListPerguntasViewModel(perguntaDao) as T
        throw IllegalArgumentException("ViewModel desconhecida")
    }
}