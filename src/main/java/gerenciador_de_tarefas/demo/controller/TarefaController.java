package gerenciador_de_tarefas.demo.controller;

import gerenciador_de_tarefas.demo.model.Tarefa;
import gerenciador_de_tarefas.demo.service.TarefaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Tarefa> listar() {
        return service.listar();
    }

    @PostMapping
    public Tarefa criar(@RequestBody Tarefa t) {
        return service.salvar(t);
    }

    @PutMapping("/{id}/status")
    public Tarefa atualizarStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return service.atualizarStatus(id, body.get("status"));
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }
}
