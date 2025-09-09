import java.util.List;
import java.util.Scanner;

public class ToDoListApp {
    private static TarefaServico servico = new TarefaServico();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean sair = false;

        while (!sair) {
            mostrarMenu();
            int opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    criarTarefa();
                    break;
                case 2:
                    listarTarefas();
                    break;
                case 3:
                    editarTarefa();
                    break;
                case 4:
                    removerTarefa();
                    break;
                case 5:
                    marcarTarefaCompleta();
                    break;
                case 0:
                    sair = true;
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n=== Gerenciador de Tarefas ===");
        System.out.println("1 - Cadastrar tarefa");
        System.out.println("2 - Listar tarefas");
        System.out.println("3 - Editar tarefa");
        System.out.println("4 - Remover tarefa");
        System.out.println("5 - Marcar tarefa como concluída");
        System.out.println("0 - Sair");
    }

    private static void criarTarefa() {
        System.out.println("\n--- Cadastrar nova tarefa ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        Tarefa tarefa = servico.criarTarefa(titulo, descricao);
        System.out.println("Tarefa criada com ID: " + tarefa.getId());
    }

    private static void listarTarefas() {
        System.out.println("\n--- Lista de tarefas ---");
        List<Tarefa> tarefas = servico.listarTarefas();
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
            for (Tarefa t : tarefas) {
                System.out.println(t);
            }
        }
    }

    private static void editarTarefa() {
        System.out.println("\n--- Editar tarefa ---");
        Long id = (long) lerInteiro("Informe o ID da tarefa: ");
        Tarefa tarefa = servico.buscarPorId(id);
        if (tarefa == null) {
            System.out.println("Tarefa não encontrada.");
            return;
        }
        System.out.print("Novo título (atual: " + tarefa.getTitulo() + "): ");
        String novoTitulo = scanner.nextLine();
        System.out.print("Nova descrição (atual: " + tarefa.getDescricao() + "): ");
        String novaDescricao = scanner.nextLine();

        boolean atualizado = servico.atualizarTarefa(id, novoTitulo, novaDescricao);
        if (atualizado) {
            System.out.println("Tarefa atualizada com sucesso.");
        } else {
            System.out.println("Erro ao atualizar tarefa.");
        }
    }

    private static void removerTarefa() {
        System.out.println("\n--- Remover tarefa ---");
        Long id = (long) lerInteiro("Informe o ID da tarefa: ");
        boolean removido = servico.removerTarefa(id);
        if (removido) {
            System.out.println("Tarefa removida com sucesso.");
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }

    private static void marcarTarefaCompleta() {
        System.out.println("\n--- Marcar tarefa como concluída ---");
        Long id = (long) lerInteiro("Informe o ID da tarefa: ");
        boolean marcada = servico.marcarComoCompleta(id);
        if (marcada) {
            System.out.println("Tarefa marcada como concluída.");
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }

    private static int lerInteiro(String mensagem) {
        int valor = -1;
        while (true) {
            try {
                System.out.print(mensagem);
                String entrada = scanner.nextLine();
                valor = Integer.parseInt(entrada);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
        return valor;
    }
}