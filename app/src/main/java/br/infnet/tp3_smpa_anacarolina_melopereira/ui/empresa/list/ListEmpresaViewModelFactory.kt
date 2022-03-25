package br.infnet.tp3_smpa_anacarolina_melopereira.ui.empresa.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.infnet.tp3_smpa_anacarolina_melopereira.dao.empresa.EmpresaDaoFirestore

class ListEmpresaViewModelFactory(private val empresaDao: EmpresaDaoFirestore):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListEmpresaViewModel::class.java))
            return ListEmpresaViewModel(empresaDao) as T
        throw IllegalArgumentException("ViewModel desconhecida")
    }

}