package br.infnet.tp3_smpa_anacarolina_melopereira.dao.empresa

import br.infnet.tp3_smpa_anacarolina_melopereira.model.Empresa
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

interface EmpresaDao {

    fun insert(empresa: Empresa): Task<Void>

    fun delete(empresa: Empresa) : Task<Void>

    fun get(nomeEmpresa: String): Task<DocumentSnapshot>

    fun selectByMarca(bairro: String): Task<QuerySnapshot>

    fun all(): CollectionReference
}