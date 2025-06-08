package gerenciador_de_tarefas.demo.service;

import gerenciador_de_tarefas.demo.model.Tarefa;
import gerenciador_de_tarefas.demo.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {
    private final TarefaRepository repo;

    public TarefaService(TarefaRepository repo) {
        this.repo = repo;
    }

    public List<Tarefa> listar() {
        return repo.findAll();
    }

    public Tarefa salvar(Tarefa t) {
        return repo.save(t);
    }

    public void remover(Long id) {
        repo.deleteById(id);
    }

    public Tarefa atualizarStatus(Long id, String status) {
        Tarefa t = repo.findById(id).orElseThrow();
        t.setStatus(status);
        return repo.save(t);
    }
}