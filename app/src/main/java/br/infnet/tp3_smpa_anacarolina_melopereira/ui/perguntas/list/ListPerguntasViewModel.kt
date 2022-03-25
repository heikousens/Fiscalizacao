package br.infnet.tp3_smpa_anacarolina_melopereira.ui.perguntas.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.infnet.tp3_smpa_anacarolina_melopereira.dao.pergunta.PerguntaDao
import br.infnet.tp3_smpa_anacarolina_melopereira.model.Pergunta

class ListPerguntasViewModel(
    private val perguntaDao: PerguntaDao
) : ViewModel() {

    private val _perguntas = MutableLiveData<List<Pergunta>>()
    val perguntas: MutableLiveData<List<Pergunta>> = _perguntas

    fun atualizarQuantidade() {
        perguntaDao.all() // task<>
            .addSnapshotListener { snapshot, error ->
                if (error != null)
                    Log.i("Erro ao atualizar a quantidade", "${error.message}")
                else
                    if (snapshot != null && !snapshot.isEmpty)
                        _perguntas.value = snapshot.toObjects(Pergunta::class.java)
            }
    }
}