import java.util.Scanner;

public class Conta {
    protected int id;
    protected int numeroConta;
    protected String nomeCliente;
    protected String telefone;
    protected Double saldo;

    public Conta(int id, int numeroConta, String nomeCliente, String telefone, double saldo) {
        this.id=id;
        this.numeroConta=numeroConta;
        this.nomeCliente=nomeCliente;
        this.telefone=telefone;
        this.saldo=saldo;
    }
    public void criarCC(Scanner newValue){
        System.out.println("Insira seu nome:");
        nomeCliente = newValue.nextLine();
        System.out.println("Insira seu telefone:");
        telefone = newValue.nextLine();
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
            saldo-=valor;
        }
        System.out.println("O valor do depósito é de R$" + valor +"\nSaldo atualizado: R$" + saldo);
    }
    public void verSaldo(){
        System.out.println("O saldo da conta é de R$" + saldo);
    }
  
}