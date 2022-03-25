package br.infnet.tp3_smpa_anacarolina_melopereira.ui.perguntas.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.infnet.tp3_smpa_anacarolina_melopereira.dao.pergunta.PerguntaDao

class CadastroPerguntasViewModelFactory(private val perguntaDao: PerguntaDao,
                                        private val application: Application
)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CadastroPerguntasViewModel::class.java))
            return CadastroPerguntasViewModel(perguntaDao) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }
}