package br.infnet.tp3_smpa_anacarolina_melopereira.ui.perguntas.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.infnet.tp3_smpa_anacarolina_melopereira.dao.empresa.EmpresaDaoFirestore
import br.infnet.tp3_smpa_anacarolina_melopereira.dao.pergunta.PerguntaDao
import br.infnet.tp3_smpa_anacarolina_melopereira.model.AppUtil
import br.infnet.tp3_smpa_anacarolina_melopereira.model.Empresa
import br.infnet.tp3_smpa_anacarolina_melopereira.model.Pergunta
import com.google.firebase.firestore.FirebaseFirestore

class CadastroPerguntasViewModel(
    private val perguntaDao: PerguntaDao
) : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String?>()
    val msg: MutableLiveData<String?> = _msg

    private var empresa: Empresa? = null

    private lateinit var empresaSelecionada : EmpresaDaoFirestore

    init {
        _status.value = false
        _msg.value = null
        empresaSelecionada = EmpresaDaoFirestore()

    }


    fun insertPergunta(titulo: String, pontuacao: Int, comentario: String) {

        val empresas = AppUtil.empresaSelecionada?.nomeEmpresa

        if (empresas == null){
            _status.value = true
        }

        var empresaAtual = empresas?.let {
            FirebaseFirestore.getInstance()
                .collection("empresas")
                .document(it)
        }

        val pergunta = Pergunta(titulo, pontuacao, empresaAtual, comentario)

        perguntaDao.insert(pergunta)
            .addOnSuccessListener {
                _status.value = true
                _msg.value = "Persistência realizada com sucesso."
            }
            .addOnFailureListener {
                _msg.value = "${it.message}"
            }
    }

}