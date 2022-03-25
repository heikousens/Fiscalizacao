package br.infnet.tp3_smpa_anacarolina_melopereira.ui.empresa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import br.infnet.tp3_smpa_anacarolina_melopereira.R
import br.infnet.tp3_smpa_anacarolina_melopereira.model.Empresa
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.list_empresa_recycler.view.*

class EmpresaRecyclerAdapter(private val empresas: List<Empresa>,
                             private val actionClick: (Empresa) -> Unit
): RecyclerView.Adapter<EmpresaRecyclerAdapter.EmpresaViewHolder>() {

    class EmpresaViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        val textNome: TextView = itemView.textViewNomeEmpresa
        val textBairro: TextView = itemView.textViewBairroEmpresa
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpresaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.list_empresa_recycler,
                parent, false
            )
        return EmpresaViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmpresaViewHolder, position: Int) {
        val empresa = empresas[position]
        holder.textNome.text = empresa.nomeEmpresa
        holder.textBairro.text = empresa.bairro
        holder.itemView.btnApagarEmpresa.setOnClickListener {
            apagarEmpresa(empresa)
        }

        holder.itemView.btnAvaliacoes.setOnClickListener {
            Navigation.createNavigateOnClickListener(R.id.action_listEmpresaFragment_to_listPerguntasFragment).onClick(holder.itemView)
        }

        holder.itemView.setOnClickListener {
            actionClick(empresa)

        }
    }

    override fun getItemCount(): Int = empresas.size

    private fun apagarEmpresa(empresa: Empresa): Task<Void> {
        val collection = FirebaseFirestore.getInstance().collection("empresas")

        return collection
            .document(empresa.nomeEmpresa!!)
            .delete()
    }
}