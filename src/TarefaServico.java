import java.util.ArrayList;
import java.util.List;

public class TarefaServico {
    private List<Tarefa> tarefas = new ArrayList<>();
    private Long contadorId = 1L;

    public Tarefa criarTarefa(String titulo, String descricao) {
        Tarefa tarefa = new Tarefa(titulo, descricao);
        tarefa.setId(contadorId++);
        tarefas.add(tarefa);
        return tarefa;
    }

    public List<Tarefa> listarTarefas() {
        return tarefas;
    }

    public Tarefa buscarPorId(Long id) {
        for (Tarefa t : tarefas) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    public boolean atualizarTarefa(Long id, String novoTitulo, String novaDescricao) {
        Tarefa tarefa = buscarPorId(id);
        if (tarefa != null) {
            tarefa.setTitulo(novoTitulo);
            tarefa.setDescricao(novaDescricao);
            return true;
        }
        return false;
    }

    public boolean removerTarefa(Long id) {
        Tarefa tarefa = buscarPorId(id);
        if (tarefa != null) {
            tarefas.remove(tarefa);
            return true;
        }
        return false;
    }

    public boolean marcarComoCompleta(Long id) {
        Tarefa tarefa = buscarPorId(id);
        if (tarefa != null) {
            tarefa.setCompleta(true);
            return true;
        }
        return false;
    }
}