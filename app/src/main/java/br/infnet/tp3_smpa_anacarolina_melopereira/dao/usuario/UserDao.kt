package br.infnet.tp3_smpa_anacarolina_melopereira.dao.usuario

import br.infnet.tp3_smpa_anacarolina_melopereira.model.Usuario
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

object UserDao {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val collection = FirebaseFirestore.getInstance().collection("users")



    fun saveRegister(email: String, password: String): Task<AuthResult> {
        return firebaseAuth.createUserWithEmailAndPassword(email, password)
    }

    //Get other user register fields from user by their uid
    fun saveOrUpdateUserProfile(
        uid: String,
        nome: String,
        username: String,
        dataNascimento: String
    ): Task<Void> {
        return collection
            .document(uid)
            .set(Usuario(nome, username, dataNascimento))
    }


    //Search for a user by their uid
    fun selectUserByUid(uid: String): Task<DocumentSnapshot> {
        return collection.document(uid).get()
    }

}