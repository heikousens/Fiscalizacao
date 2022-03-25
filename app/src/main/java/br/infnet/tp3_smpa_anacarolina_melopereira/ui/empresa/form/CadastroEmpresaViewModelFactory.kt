package br.infnet.tp3_smpa_anacarolina_melopereira.ui.empresa.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.infnet.tp3_smpa_anacarolina_melopereira.dao.empresa.EmpresaDaoFirestore

class CadastroEmpresaViewModelFactory(private val empresaDao: EmpresaDaoFirestore,
                                      private val application: Application
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CadastroEmpresaViewModel::class.java))
            return CadastroEmpresaViewModel(empresaDao) as T
        throw IllegalArgumentException("ViewModel desconhecida")
    }

}