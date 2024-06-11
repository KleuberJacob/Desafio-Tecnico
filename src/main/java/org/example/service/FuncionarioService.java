package org.example.service;

import org.example.model.FuncionarioModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FuncionarioService {

    public static void inserirTodosFuncionarios(List<FuncionarioModel> funcionarioModels) {
        funcionarioModels.add(new FuncionarioModel("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), "Operador"));
        funcionarioModels.add(new FuncionarioModel("João", LocalDate.of(1990, 5, 12), BigDecimal.valueOf(2284.38), "Operador"));
        funcionarioModels.add(new FuncionarioModel("Caio", LocalDate.of(1961, 5, 2), BigDecimal.valueOf(9836.14), "Coordenador"));
        funcionarioModels.add(new FuncionarioModel("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88), "Diretor"));
        funcionarioModels.add(new FuncionarioModel("Alice", LocalDate.of(1995, 1, 5), BigDecimal.valueOf(2234.68), "Recepcionista"));
        funcionarioModels.add(new FuncionarioModel("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72), "Operador"));
        funcionarioModels.add(new FuncionarioModel("Arthur", LocalDate.of(1993, 3, 31), BigDecimal.valueOf(4071.84), "Contador"));
        funcionarioModels.add(new FuncionarioModel("Laura", LocalDate.of(1994, 7, 8), BigDecimal.valueOf(3017.45), "Gerente"));
        funcionarioModels.add(new FuncionarioModel("Heloisa", LocalDate.of(2003, 5, 24), BigDecimal.valueOf(1606.85), "Eletricista"));
        funcionarioModels.add(new FuncionarioModel("Helena", LocalDate.of(1996, 9, 2), BigDecimal.valueOf(2799.93), "Gerente"));
    }

    public static void removerFuncionarioPorNome(List<FuncionarioModel> funcionarioModels, String nome) {
        funcionarioModels.removeIf(funcionarioModel -> nome.equals(funcionarioModel.getNome()));
    }

    public static void imprimirFuncionarios(List<FuncionarioModel> funcionarioModels) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

        System.out.println("Lista de Funcionários:");
        for (FuncionarioModel funcionarioModel : funcionarioModels) {
            System.out.println("Nome: " + funcionarioModel.getNome());
            System.out.println("Data de Nascimento: " + funcionarioModel.getDataNascimento().format(formatter));
            System.out.println("Salário: R$" + decimalFormat.format(funcionarioModel.getSalario()));
            System.out.println("Função: " + funcionarioModel.getFuncao());
            System.out.println();
        }
    }

    public static void aumentarSalario(List<FuncionarioModel> funcionarioModels, BigDecimal aumentoPercentual) {
        for (FuncionarioModel funcionarioModel : funcionarioModels) {
            BigDecimal aumento = funcionarioModel.getSalario().multiply(aumentoPercentual);
            BigDecimal novoSalario = funcionarioModel.getSalario().add(aumento);
            funcionarioModel.setSalario(novoSalario);
        }
    }

    public static Map<String, List<FuncionarioModel>> agruparPorFuncao(List<FuncionarioModel> funcionarioModels) {
        Map<String, List<FuncionarioModel>> funcionariosPorFuncao = new HashMap<>();

        for (FuncionarioModel funcionarioModel : funcionarioModels) {
            String funcao = funcionarioModel.getFuncao();
            List<FuncionarioModel> funcionariosDaFuncao = funcionariosPorFuncao.getOrDefault(funcao, new ArrayList<>());
            funcionariosDaFuncao.add(funcionarioModel);
            funcionariosPorFuncao.put(funcao, funcionariosDaFuncao);
        }

        return funcionariosPorFuncao;
    }

    public static void imprimirFuncionariosPorFuncao(Map<String, List<FuncionarioModel>> funcionariosPorFuncao) {
        System.out.println("Funcionários agrupados por função:");
        for (Map.Entry<String, List<FuncionarioModel>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            imprimirFuncionarios(entry.getValue());
            System.out.println();
        }
    }

    // Método para imprimir funcionários que fazem aniversário no mês especificado
    public static void imprimirAniversariantes(List<FuncionarioModel> funcionarioModels, int mes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Aniversariantes do mês " + mes + ":");
        for (FuncionarioModel funcionarioModel : funcionarioModels) {
            if (funcionarioModel.getDataNascimento().getMonthValue() == mes) {
                System.out.println("Nome: " + funcionarioModel.getNome() + ", Data de Nascimento: " +
                        funcionarioModel.getDataNascimento().format(formatter));
            }
        }
        System.out.println();
    }

    public static void imprimirFuncionarioMaisVelho(List<FuncionarioModel> funcionarioModels) {
        FuncionarioModel funcionarioMaisVelho = encontrarFuncionarioMaisVelho(funcionarioModels);

        if (funcionarioMaisVelho != null) {
            System.out.println("Funcionário mais velho:");
            System.out.println("Nome: " + funcionarioMaisVelho.getNome());
            System.out.println("Idade: " + calcularIdade(funcionarioMaisVelho.getDataNascimento()));
        } else {
            System.out.println("Não há funcionários na lista.");
        }
    }

    private static FuncionarioModel encontrarFuncionarioMaisVelho(List<FuncionarioModel> funcionarioModels) {
        return funcionarioModels.stream()
                .min(Comparator.comparing(FuncionarioModel::getDataNascimento))
                .orElse(null);
    }

    private static int calcularIdade(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now();
        return Period.between(dataNascimento, hoje).getYears();
    }

    public static void imprimirPorOrdemAlfabetica(List<FuncionarioModel> funcionarioModels) {
        Collections.sort(funcionarioModels, (f1, f2) -> f1.getNome().compareToIgnoreCase(f2.getNome()));

        System.out.println("Lista de funcionários por ordem alfabética:");
        for (FuncionarioModel funcionarioModel : funcionarioModels) {
            System.out.println("Nome: " + funcionarioModel.getNome());
        }
        System.out.println();
    }

    public static void imprimirTotalSalarios(List<FuncionarioModel> funcionarioModels) {
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (FuncionarioModel funcionarioModel : funcionarioModels) {
            totalSalarios = totalSalarios.add(funcionarioModel.getSalario());
        }
        System.out.println("Total dos salários dos funcionários: " + totalSalarios);
    }

    public static void imprimirSalariosMinimos(List<FuncionarioModel> funcionarioModels) {
        BigDecimal salarioMinimo = BigDecimal.valueOf(1212.00);
        System.out.println("Quantidade de salários mínimos que cada funcionário ganha:");
        for (FuncionarioModel funcionarioModel : funcionarioModels) {
            BigDecimal salariosMinimos = funcionarioModel.getSalario().divide(salarioMinimo, 2, RoundingMode.DOWN);
            System.out.println(funcionarioModel.getNome() + ": " + salariosMinimos + " salários mínimos");
        }
    }

}
