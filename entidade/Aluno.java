package entidade;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private List<Double> notas;

    // Construtor da classe Aluno
    public Aluno(String nome, double[] notas) {
        this.nome = nome;
        this.notas = new ArrayList<>();
        for (double nota : notas) {
            this.notas.add(nota);
        }
    }

    // Método para obter o nome do aluno
    public String getNome() {
        return nome;
    }

    // Método para alterar o nome do aluno
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Método para obter as notas do aluno
    public List<Double> getNotas() {
        return notas;
    }

    // Método para editar uma nota específica, dada a posição e o novo valor da nota
    public void editarNota(int index, double novaNota) {
        if (index >= 0 && index < notas.size()) {
            notas.set(index, novaNota);
        }
    }

    // Método para calcular o total das notas
    public double calcularTotal() {
        double total = 0;
        for (double nota : notas) {
            total += nota;
        }
        return total;
    }

    // Método para calcular a média das notas
    public double calcularMedia() {
        return calcularTotal() / notas.size();
    }

    // Método para verificar se o aluno está aprovado (média >= 7)
    public boolean estaAprovado() {
        return calcularMedia() >= 7.0;
    }
}