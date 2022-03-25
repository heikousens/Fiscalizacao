package br.infnet.tp3_smpa_anacarolina_melopereira.ui.perguntas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.infnet.tp3_smpa_anacarolina_melopereira.R
import br.infnet.tp3_smpa_anacarolina_melopereira.model.Pergunta
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.list_pergunta_recycler.view.*

class PerguntaRecyclerAdapter(
    private val perguntas: List<Pergunta>,
    private val actionClick: (Pergunta) -> Unit
) : RecyclerView.Adapter<PerguntaRecyclerAdapter.PerguntaViewHolder>() {

    class PerguntaViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        val textComentario: TextView = itemView.textViewPeruntaComentario
        val textPontuacao: TextView = itemView.textViewQtdPontos
        val textTitulo: TextView = itemView.textViewPerguntasTitutlo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerguntaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.list_pergunta_recycler,
                parent, false
            )
        return PerguntaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PerguntaViewHolder, position: Int) {
        val pergunta = perguntas[position]
        holder.textComentario.text = pergunta.comentario.toString()
        holder.textPontuacao.text = pergunta.pontuacao.toString()
        holder.textTitulo.text = pergunta.titulo.toString()
        holder.itemView.btnApagarPergunta.setOnClickListener {
            apagarPergunta(pergunta)
        }

//        holder.itemView.btnAvaliacoes.setOnClickListener {
//            Navigation.createNavigateOnClickListener(R.id.action_listEmpresaFragment_to_listPerguntasFragment).onClick(holder.itemView)
//        }

        holder.itemView.setOnClickListener {
            actionClick(pergunta)

        }
    }

    override fun getItemCount(): Int = perguntas.size

    private fun apagarPergunta(pergunta: Pergunta): Task<Void> {
        val collection = FirebaseFirestore.getInstance().collection("perguntas")

        return collection
            .document(pergunta.titulo!!.toString())
            .delete()
    }
}