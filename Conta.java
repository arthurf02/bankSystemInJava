import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
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
        String input;
        while (true){
            System.out.println("Insira seu nome:");
            input = newValue.next();
            /*Usando o método matches com uma expressão regular para validar
            se o input contém apenas letras.
            */
            if (input.matches("^[a-zA-Z]+$") == true){
                nomeCliente = input;
                newValue.nextLine();
                break;
            } else{
                System.out.println("Nome inválido! Digite apenas letras");
            }
        }
        while (true){
            System.out.println("Insira seu telefone:");
            input = newValue.next();
            if (Main.isNumeric(input)==true && input.length() == 11){
                telefone = input;
                newValue.nextLine();
                break;
            } else{
                System.out.println("Telefone inválido! Insira o número DDD e mais 9 números.");
            }
        }
        while (true){
            System.out.println("Deposite um valor inicial:");
            input = newValue.next();
            Locale.setDefault(Locale.forLanguageTag("pt-BR"));
            NumberFormat numberFormat = NumberFormat.getInstance();
            try {
                Number number = numberFormat.parse(input);
                double value = number.doubleValue();
                saldo = value;
                break;
            } catch (ParseException e) {
                System.out.println("Valor inválido. Certifique-se de inserir apenas números e vírgula para separar os centavos.");
            }
        }
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