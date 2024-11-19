import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main{
    public static boolean isNumeric(String usuarioConta){
        if (usuarioConta == null){
            return false;
        }
        try{
            int i = Integer.parseInt(usuarioConta);
        } catch (NumberFormatException nfe){
            return false;
        }
        return true;
    }
    public static void main(String[] args){
        List<ContaCorrente> contasC = new ArrayList<>();
        List<ContaPoupanca> contasP = new ArrayList<>();
        int usuario = 1;
        Scanner newValue = new Scanner(System.in);
        System.out.println("Banco Agiota\nSeu dinheiro, nossa vida!");
        while (usuario != 0){
            System.out.println("\nMENU\n1-Criar conta\n2-Ver saldo\n3-Ver dados da conta\n4-Sacar\n5-Depositar");
            usuario = newValue.nextInt();
            newValue.nextLine();
            if (usuario == 1){
                System.out.println("Qual tipo de conta você quer criar:\n1- Conta Corrente\n2- Conta Poupança");
                usuario = newValue.nextInt();
                newValue.nextLine();
                while (true){
                    if (usuario == 1){
                        ContaCorrente cc = new ContaCorrente(1,contasC.size()+10000,"","",0.0);
                        cc.criarConta(newValue);
                        contasC.add(cc);
                        break;
                    } else if (usuario == 2){
                        ContaPoupanca cp = new ContaPoupanca(2, contasP.size()+10000, "", "", 0.0);
                        cp.criarConta(newValue);
                        contasP.add(cp);
                        break;
                    } else {
                        System.out.println("Opção inválida!");
                        usuario = 1;
                        break;
                    }
                }
            } else if (usuario == 2){
                while (true){
                    System.out.println("Informe o número da conta:");
                    String usuarioConta = newValue.nextLine();
                    if (contasC.size() == 0 && contasP.size() == 0){
                        System.out.println("Não existem contas ainda. Crie uma conta!");
                        break;
                    } else if(isNumeric(usuarioConta)==true){
                        int i = 0;
                        int conta = Integer.parseInt(usuarioConta);
                        int contaEncontrada = 0;
                        while (i < contasC.size()){
                            contaEncontrada = contasC.get(i).getNumeroConta();
                            if (conta==contaEncontrada){
                                System.out.println("Olá, " + contasC.get(i).getNomeCliente() + "!");
                                System.out.println("O saldo da sua conta é de: R$" + contasC.get(i).getSaldo());
                                break;
                            } else {
                                i+=1;
                            }
                        }
                        if (conta!=contaEncontrada){
                            System.out.println("Número de conta inválido!");
                        } else{
                            break;
                        }
                    } else{
                        System.out.println("Número de conta inválido!");
                    }
                }
            }else if (usuario == 3){
                while (true){
                    System.out.println("Informe o número da conta:");
                    int usuarioConta = newValue.nextInt();
                    newValue.nextLine();
                    if (usuarioConta < contasC.size()){
                        contasC.get(usuarioConta).verDadosConta();
                        break;
                    } else{
                        System.out.println("Número de conta inválido!");
                    }
                }
            } else if (usuario == 4){
                while (true){
                    System.out.println("Informe o número da conta:");
                    int usuarioConta = newValue.nextInt();
                    newValue.nextLine();
                    if (contasC.size() > 0){
                        if (contasC.get(usuarioConta).getId() == 1){
                            System.out.println("Conta de " + contasC.get(usuarioConta).getNomeCliente());
                            System.out.println("Saldo atual: R$" + contasC.get(usuarioConta).getSaldo());
                            System.out.println("Informe o valor a sacar:");
                            double valorSaque = newValue.nextDouble();
                            newValue.nextLine();
                            if (valorSaque <= contasC.get(usuarioConta).getSaldo()){
                                contasC.get(usuarioConta).sacar(valorSaque);
                                break;
                            } else {
                                System.out.println("Valor inválido ou saldo insuficiente!");
                            }
                        }
                    } else {
                        System.out.println("Não há contas existentes. Crie uma primeiro para poder sacar.");
                        break;
                    }
                }
            }
        }
        newValue.close();
    }
}