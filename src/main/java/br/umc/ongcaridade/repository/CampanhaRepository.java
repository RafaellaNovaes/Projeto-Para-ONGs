package br.umc.ongcaridade.repository;

import br.umc.ongcaridade.entity.Campanha;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository

public class CampanhaRepository {
    public void cadastrarCampanha(Campanha campanha) throws Exception {
        Firestore db = FirestoreClient.getFirestore();

        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", campanha.getNome());
        dados.put("status", campanha.getStatus());
        dados.put("id", campanha.getId());
        dados.put("descricao", campanha.getDescricao());
        dados.put("dataInicio", campanha.getDataInicio().toString());
        dados.put("dataFim", campanha.getDataFim().toString());
        dados.put("responsavelLocal", campanha.getResponsavelLocal());
        dados.put("contatoResponsavel", campanha.getContatoResponsavel());
        dados.put("enderecoId", campanha.getEndereco().getId());
        dados.put("organizadorId", campanha.getOrganizador().getId());
        dados.put("capacidadePessoas", campanha.getCapacidadePessoas());
        db.collection("campanhas").add(dados).get();
    }

    public Campanha buscarPorId(String id) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        DocumentSnapshot documento = db.collection("campanhas").document(id).get().get();
        if (documento.exists()) {
            return documento.toObject(Campanha.class);
        }
        return null;
    }

    public void deletar(String id) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        DocumentSnapshot documento = db.collection("campanhas").document(id).get().get();
        if (documento.exists()) {
            db.collection("campanhas").document(id).delete().get();
        }
    }

    public List<Campanha> listarTodas() throws Exception {
        Firestore db = FirestoreClient.getFirestore();

        QuerySnapshot resultado = db.collection("campanhas").get().get();

        List<Campanha> campanhas = new ArrayList<>();

        for (QueryDocumentSnapshot documento : resultado) {
            campanhas.add(documento.toObject(Campanha.class));
        }

        return campanhas;
    }

    public void atualizar(String id, Campanha campanha) throws Exception {
        Firestore db = FirestoreClient.getFirestore();

        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", campanha.getNome());
        dados.put("status", campanha.getStatus());
        dados.put("id", campanha.getId());
        dados.put("descricao", campanha.getDescricao());
        dados.put("dataInicio", campanha.getDataInicio().toString());
        dados.put("dataFim", campanha.getDataFim().toString());
        dados.put("responsavelLocal", campanha.getResponsavelLocal());
        dados.put("contatoResponsavel", campanha.getContatoResponsavel());
        dados.put("enderecoId", campanha.getEndereco().getId());
        dados.put("organizadorId", campanha.getOrganizador().getId());
        dados.put("capacidadePessoas", campanha.getCapacidadePessoas());
        db.collection("campanhas").document(id).set(dados).get();
    }


}
