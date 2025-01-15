import java.util.Scanner;

public class Conta {
    private int id;
    private int numeroConta;
    private String nomeCliente;
    private String telefone;
    private Double saldo;

    public Conta(int id, int numeroConta, String nomeCliente, String telefone, double saldo) {
        this.id=id;
        this.numeroConta=numeroConta;
        this.nomeCliente=nomeCliente;
        this.telefone=telefone;
        this.saldo=saldo;
    }
    public int getId(){
        return id;
    }
    public int getNumeroConta(){
        return numeroConta;
    }
    public String getNomeCliente(){
        return nomeCliente;
    }
    public String getTelefone(){
        return telefone;
    }
    public double getSaldo(){
        return saldo;
    }

    public void setSaldo(double valor){
        saldo=valor;
    }
    public void criarConta(Scanner newValue){
        System.out.println("Insira seu nome:");
        nomeCliente = newValue.next();
        System.out.println("Insira seu telefone:");
        telefone = newValue.next();
        System.out.println("Deposite um valor inicial:");
        saldo = newValue.nextDouble();
        newValue.nextLine();
    }
    public void sacar(Double valor){
        if (valor<saldo){
            saldo-=valor;
        }
        System.out.println("Valor sacado: R$" + valor + "\nO saldo agora é de: " + saldo);
    }
    public void depositar(double valor){
        if (valor < saldo){
            saldo+=valor;
        }
        System.out.println("O valor do depósito é de R$" + valor +"\nSaldo atualizado: R$" + saldo);
    }
    public void verSaldo(){
        System.out.println("O saldo da conta de " + nomeCliente + " é de R$" + saldo);
    }
    public void verDadosConta(){
        System.out.println("\nNome: " + getNomeCliente());
        System.out.println("Telefone: " + getTelefone());
        System.out.println("Número da conta: " + getNumeroConta());
        System.out.print("Saldo: ");
        System.out.printf("%.2f" , getSaldo());
        System.out.println("\n");
    }
}