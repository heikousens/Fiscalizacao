package br.infnet.tp3_smpa_anacarolina_melopereira.dao.empresa

import br.infnet.tp3_smpa_anacarolina_melopereira.model.Empresa
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class EmpresaDaoFirestore : EmpresaDao {

    private val collection = FirebaseFirestore.getInstance()
        .collection("empresas")

    override fun insert(empresa: Empresa): Task<Void> {
        return collection
            .document(empresa.nomeEmpresa!!)
            .set(empresa)
    }
    override fun delete(empresa: Empresa): Task<Void> {
        return collection
            .document(empresa.nomeEmpresa!!)
            .delete()
    }
    override fun get(nomeEmpresa: String): Task<DocumentSnapshot> {
        return collection
            .document(nomeEmpresa)
            .get()
    }

    override fun selectByMarca(bairro: String): Task<QuerySnapshot> {
        return collection
            .whereEqualTo("bairro", bairro)
            .get()
    }

    override fun all(): CollectionReference {
        return collection
    }
}