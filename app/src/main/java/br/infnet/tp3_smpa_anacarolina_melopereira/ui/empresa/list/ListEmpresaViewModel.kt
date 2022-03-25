package br.infnet.tp3_smpa_anacarolina_melopereira.ui.empresa.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.infnet.tp3_smpa_anacarolina_melopereira.dao.empresa.EmpresaDao
import br.infnet.tp3_smpa_anacarolina_melopereira.model.Empresa

class ListEmpresaViewModel(
    private val empresaDao: EmpresaDao
) : ViewModel() {

    private val _empresas = MutableLiveData<List<Empresa>>()
    val empresas: LiveData<List<Empresa>> = _empresas

    fun atualizarQuantidade() {
        empresaDao.all() // task<>
            .addSnapshotListener { snapshot, error ->
                if (error != null)
                    Log.i("Erro ao atualizar a quantidade", "${error.message}")
                else
                    if (snapshot != null && !snapshot.isEmpty)
                        _empresas.value = snapshot.toObjects(Empresa::class.java)
            }
    }

}