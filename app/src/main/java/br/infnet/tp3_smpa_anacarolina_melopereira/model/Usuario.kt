package br.infnet.tp3_smpa_anacarolina_melopereira.model

import com.google.firebase.firestore.DocumentId

class Usuario (var nome: String? = null,
               var username: String? = null,
               var dataNascimento: String? = null,
               @DocumentId
               val uid: String? = null)