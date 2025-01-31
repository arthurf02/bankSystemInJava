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
    Locale localeBR = Locale.forLanguageTag("pt-BR");
    NumberFormat currency = NumberFormat.getCurrencyInstance(localeBR);
    NumberFormat numberFormat = NumberFormat.getInstance();

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
        newValue.nextLine();
        String input;
        while (true){
            System.out.println("Insira seu nome:");
            input = newValue.nextLine();
            /*Usando o método matches com uma expressão regular para validar
            se o input contém apenas letras.
            ^: início da string;
            [a-zA-Z]: Representa qualquer letra maiúscula ou minúscula;
            +: Garante que haja pelo menos uma letra;
            \\s: permite apenas espaços ou tabulações na string;
            $: Indica o fim da string.
            */
            if (input.matches("^[a-zA-Z\\s]+$")){
                nomeCliente = input;
                break;
            } else{
                System.out.println("Nome inválido! Digite apenas letras");
            }
        }
        while (true){
            System.out.println("Insira seu telefone:");
            input = newValue.nextLine();
            if (Main.isNumeric(input)==true && input.length() == 11){
                telefone = input;
                break;
            } else{
                System.out.println("Telefone inválido! Insira o número DDD e mais 9 números.");
            }
        }
        while (true){
            System.out.println("Deposite um valor inicial:");
            input = newValue.nextLine();
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
        System.out.println("Valor sacado: " + currency.format(valor) + "\nO saldo agora é de: " + currency.format(saldo));
    }
    public void depositar(double valor){
        if (valor >= 1){
            saldo+=valor;
            System.out.println("O valor do depósito é de: " + currency.format(valor) +"\nSaldo atualizado: " + currency.format(saldo));
        }else{
            System.out.println("Valor inferior a R$1,00.");
        }
    }
    public void verSaldo(){
        System.out.println("Olá, " + getNomeCliente() + "!");
        System.out.println("O saldo da sua conta é de: " + currency.format(getSaldo()));
    }
    public void verDadosConta(){
        System.out.println("\nNome: " + getNomeCliente());
        System.out.println("Telefone: " + getTelefone());
        System.out.println("Número da conta: " + getNumeroConta());
        System.out.print("Saldo: ");
        System.out.println(currency.format(getSaldo()));
    }
}