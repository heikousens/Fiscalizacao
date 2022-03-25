package br.infnet.tp3_smpa_anacarolina_melopereira.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference

class Pergunta(
    @DocumentId
    val titulo: String? = null,
    val pontuacao: Int? = null,
    val empresa: DocumentReference? = null,
    val comentario: String? = "Sem comentários"
)