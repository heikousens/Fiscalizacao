package br.infnet.tp3_smpa_anacarolina_melopereira.ui.usuario.cadastro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class CadastroViewModel : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String?>()
    val msg: MutableLiveData<String?> = _msg

    fun salvarCadastro(email: String, senha: String) {
        val firebaseAuth = FirebaseAuth.getInstance()
        val task = firebaseAuth
            .createUserWithEmailAndPassword(email,senha)
        task
            .addOnSuccessListener {
                _status.value = true
                _msg.value = "Usuário cadastrado com sucesso!"
            }
            .addOnFailureListener{
                _msg.value = "${it.message}"
            }
    }

    fun alterarMsg(msg: String) {
        _msg.value = msg
    }
}