package gerenciador_de_tarefas.demo.repository;

import gerenciador_de_tarefas.demo.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}