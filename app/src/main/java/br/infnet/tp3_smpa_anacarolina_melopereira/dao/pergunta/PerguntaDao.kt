package br.infnet.tp3_smpa_anacarolina_melopereira.dao.pergunta

import br.infnet.tp3_smpa_anacarolina_melopereira.model.Pergunta
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

interface PerguntaDao {

    fun insert(pergunta: Pergunta): Task<Void>

    fun delete(pergunta: Pergunta) : Task<Void>

    fun get(titulo: String): Task<DocumentSnapshot>

    fun selectByTitulo(titulo: String): Task<QuerySnapshot>

    fun all(): CollectionReference
}