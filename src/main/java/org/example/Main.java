package org.example;

import org.example.model.FuncionarioModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.example.service.FuncionarioService.*;

public class Main {

    public static void main(String[] args) {

        // 3.1 - Inserir todos os funcionários
        List<FuncionarioModel> funcionarioModels = new ArrayList<>();
        inserirTodosFuncionarios(funcionarioModels);

        // 3.2 - Remover o funcionário “João”
        removerFuncionarioPorNome(funcionarioModels, "João");

        // 3.3 - Imprimir todos os funcionários
        imprimirFuncionarios(funcionarioModels);

        // 3.4 - Aumentar salário em 10%
        aumentarSalario(funcionarioModels, BigDecimal.valueOf(0.10));

        // Imprimir novamente após o aumento
        System.out.println("Lista de Funcionários após aumento de salário:");
        imprimirFuncionarios(funcionarioModels);

        // 3.5 - Agrupar os funcionários por função em um MAP
        Map<String, List<FuncionarioModel>> funcionariosPorFuncao = agruparPorFuncao(funcionarioModels);

        // 3.6 - Imprimir os funcionários, agrupados por função
        imprimirFuncionariosPorFuncao(funcionariosPorFuncao);

        // 3.8 - Imprimir os funcionários que fazem aniversário nos meses 10 e 12
        imprimirAniversariantes(funcionarioModels, 10);
        imprimirAniversariantes(funcionarioModels, 12);

        // 3.9 - Imprimir o funcionário com a maior idade
        imprimirFuncionarioMaisVelho(funcionarioModels);

        // 3.10 - Imprimir lista de funcionários por ordem alfabética
        imprimirPorOrdemAlfabetica(funcionarioModels);

        // 3.11 - Imprimir o total dos salários dos funcionários
        imprimirTotalSalarios(funcionarioModels);

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        imprimirSalariosMinimos(funcionarioModels);
    }

}