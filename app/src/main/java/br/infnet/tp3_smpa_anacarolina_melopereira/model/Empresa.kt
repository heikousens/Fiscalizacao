package br.infnet.tp3_smpa_anacarolina_melopereira.model

import com.google.firebase.firestore.DocumentId

class Empresa(
    @DocumentId
    val nomeEmpresa: String? = null,
    val bairro: String? = null

)