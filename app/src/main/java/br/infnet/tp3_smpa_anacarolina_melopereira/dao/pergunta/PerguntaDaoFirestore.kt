package br.infnet.tp3_smpa_anacarolina_melopereira.dao.pergunta

import br.infnet.tp3_smpa_anacarolina_melopereira.model.Pergunta
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class PerguntaDaoFirestore : PerguntaDao {

    private val collection = FirebaseFirestore.getInstance()
        .collection("perguntas")

    override fun insert(pergunta: Pergunta): Task<Void> {
        return collection
            .document(pergunta.titulo!!)
            .set(pergunta)
    }

    override fun delete(pergunta: Pergunta): Task<Void> {
        return collection
            .document(pergunta.titulo!!)
            .delete()
    }

    override fun get(titulo: String): Task<DocumentSnapshot> {
        return collection
            .document(titulo)
            .get()
    }

    override fun selectByTitulo(titulo: String): Task<QuerySnapshot> {
        return collection
            .whereEqualTo("titulo", titulo)
            .get()
    }

    override fun all(): CollectionReference {
        return collection
    }
}