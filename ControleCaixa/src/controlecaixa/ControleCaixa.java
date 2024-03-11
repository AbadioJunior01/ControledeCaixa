package controlecaixa;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ControleCaixa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US); // Configura o scanner para aceitar ponto como separador decimal

        double total = 0.0;
        String resposta = null;
        boolean primeiroProduto = true;

        List<Produto> listaProdutos = new ArrayList<>();

        System.out.println("Lançamento de Produto:");

        do {
            if (!primeiroProduto) {
                System.out.println("Novo Produto:");
            } else {
                primeiroProduto = false;
            }

            System.out.print("O produto é vendido por quilo ou por unidade? (k/u): ");
            String tipoProduto = scanner.next();

            Produto produto = new Produto();

            if (tipoProduto.equalsIgnoreCase("k")) {
                produto.setTipo("Kilo");
                System.out.print("Nome do produto: ");
                scanner.nextLine(); // Limpa o buffer
                produto.setNome(scanner.nextLine());

                System.out.print("Preço do produto por quilo: ");
                produto.setPrecoUnitario(lerPreco(scanner));

                System.out.print("Quantidade de quilos: ");
                double quantidadeQuilos = Double.parseDouble(scanner.next().replace(',', '.'));
                produto.setQuantidade(quantidadeQuilos);

                double totalProduto = produto.getPrecoUnitario() * quantidadeQuilos;
                produto.setTotal(totalProduto);

                listaProdutos.add(produto);
                total += totalProduto;

                // Exibir o valor total do produto
                System.out.printf("Total do produto: R$ %.2f\n", totalProduto);
            } else if (tipoProduto.equalsIgnoreCase("u")) {
                produto.setTipo("Unidade");
                System.out.print("Nome do produto: ");
                scanner.nextLine(); // Limpa o buffer
                produto.setNome(scanner.nextLine());

                System.out.print("Preço do produto por unidade: ");
                produto.setPrecoUnitario(lerPreco(scanner));

                System.out.print("Quantidade de unidades: ");
                int quantidadeUnidades = scanner.nextInt();
                produto.setQuantidade(quantidadeUnidades);

                double totalProduto = produto.getPrecoUnitario() * quantidadeUnidades;
                produto.setTotal(totalProduto);

                listaProdutos.add(produto);
                total += totalProduto;

                // Exibir o valor total do produto
                System.out.printf("Total do produto: R$ %.2f\n", totalProduto);
            } else {
                System.out.println("Opção inválida. Tente novamente.");
                continue;
            }

            System.out.print("Deseja lançar outro produto? (s/n): ");
            resposta = scanner.next();
        } while (resposta.equalsIgnoreCase("s"));

        System.out.println("Relação de Produtos Lançados:");
        System.out.println("-------------------------------");
        for (Produto p : listaProdutos) {
            System.out.println("Produto: " + p.getNome() + " | Tipo: " + p.getTipo() +
                    " | Quantidade: " + p.getQuantidade() +
                    " | Preço Unitário: R$" + p.getPrecoUnitario() +
                    " | Total: R$" + p.getTotal());
        }

        System.out.printf("Total: R$ %.2f\n", total);

        System.out.print("Deseja aplicar desconto? (s/n): ");
        resposta = scanner.next();

        if (resposta.equalsIgnoreCase("s")) {
            System.out.print("Digite o valor do desconto (máximo 3%): ");
            double desconto = Double.parseDouble(scanner.next().replace(',', '.'));

            if (desconto > 3) {
                System.out.println("Desconto máximo permitido é de 3%.");
            } else {
                double valorDesconto = total * (desconto / 100);
                total -= valorDesconto;
                System.out.printf("Desconto aplicado: R$ %.2f\n", valorDesconto);
            }
        }

        System.out.printf("Total com desconto: R$ %.2f\n", total);
        System.out.println("Obrigado por utilizar nosso sistema!");
    }

    private static double lerPreco(Scanner scanner) {
        String precoStr = scanner.next().replace(',', '.');
        return Double.parseDouble(precoStr);
    }

   static class Produto {
        private String nome;
        private String tipo;
        private double precoUnitario;
        private double quantidade;
        private double total;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public double getPrecoUnitario() {
            return precoUnitario;
        }

        public void setPrecoUnitario(double precoUnitario) {
            this.precoUnitario = precoUnitario;
        }

        public double getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(double quantidade) {
            this.quantidade = quantidade;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }
    }
}