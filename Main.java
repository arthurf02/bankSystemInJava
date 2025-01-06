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
        String usuario = "";
        Scanner newValue = new Scanner(System.in);
        System.out.println("Banco Agiota\nSeu dinheiro, nossa vida!");
        while (true){
            System.out.println("\nMENU\n1- Criar conta\n2- Ver saldo\n3- Ver dados da conta\n4- Sacar\n5- Depositar\n0- Sair");
            usuario = newValue.next();
            if (Integer.parseInt(usuario) == 0){
                break;
            } else if (Integer.parseInt(usuario) == 1){
                System.out.println("Qual tipo de conta você quer criar:\n1- Conta Corrente\n2- Conta Poupança\n0- Voltar");
                usuario = newValue.next();
                while (true){
                    if (Integer.parseInt(usuario) == 0){
                        break;
                    } else if (Integer.parseInt(usuario) == 1){
                        ContaCorrente cc = new ContaCorrente(1,contasC.size()+10000,"","",0.0);
                        cc.criarConta(newValue);
                        contasC.add(cc);
                        break;
                    } else if (Integer.parseInt(usuario) == 2){
                        ContaPoupanca cp = new ContaPoupanca(2, contasP.size()+10000, "", "", 0.0);
                        cp.criarConta(newValue);
                        contasP.add(cp);
                        break;
                    } else {
                        System.out.println("Opção inválida!");
                        usuario = "1";
                        break;
                    }
                }
            } else if (Integer.parseInt(usuario) == 2){
                while (true){
                    System.out.println("Informe o id da conta (1 para Conta Corrente / 2 para Conta Poupança / 0 para sair): ");
                    String idConta = newValue.next();
                    if (Integer.parseInt(idConta) == 0){
                        break;
                    } else if (isNumeric(idConta) == true && Integer.parseInt(idConta) == 1 && contasC.size() != 0){
                        System.out.println("Informe o número da conta: ");
                        String usuarioConta = newValue.next();
                        if (isNumeric(usuarioConta) == true){
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
                    }else if (isNumeric(idConta) == true && Integer.parseInt(idConta) == 2 && contasP.size() != 0){
                        System.out.println("Informe o número da conta: ");
                        String usuarioConta = newValue.next();
                        if (isNumeric(usuarioConta) == true){
                            int i = 0;
                            int conta = Integer.parseInt(usuarioConta);
                            int contaEncontrada = 0;
                            while (i < contasP.size()){
                                contaEncontrada = contasP.get(i).getNumeroConta();
                                if (conta==contaEncontrada){
                                    System.out.println("Olá, " + contasP.get(i).getNomeCliente() + "!");
                                    System.out.println("O saldo da sua conta é de: R$" + contasP.get(i).getSaldo());
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
                    } else{
                        System.out.println("Id sem contas cadastradas ou inválido!");
                    }
                }
            } else if (Integer.parseInt(usuario) == 3){
                while (true){
                    System.out.println("Informe o id da conta: ");
                    String idConta = newValue.next();
                    if (isNumeric(idConta) == true && Integer.parseInt(idConta) == 1 && contasC.size() != 0){
                        System.out.println("Informe o número da conta:");
                        String usuarioConta = newValue.next();
                        if (isNumeric(usuarioConta) == true){
                            int i = 0;
                            int conta = Integer.parseInt(usuarioConta);
                            int contaEncontrada = 0;
                            while (i < contasC.size()){
                                contaEncontrada = contasC.get(i).getNumeroConta();
                                if (conta==contaEncontrada){
                                    contasC.get(i).verDadosConta();
                                    break;
                                } else {
                                    System.out.println("Número de conta inválido!");
                                    break;
                                }
                            }
                        } break;
                    } else if (isNumeric(idConta) == true && Integer.parseInt(idConta) == 2 && contasP.size() != 0){
                        System.out.println("Informe o número da conta:");
                        String usuarioConta = newValue.next();
                        if (isNumeric(usuarioConta) == true){
                            int i = 0;
                            int conta = Integer.parseInt(usuarioConta);
                            int contaEncontrada = 0;
                            while (i < contasC.size()){
                                contaEncontrada = contasP.get(i).getNumeroConta();
                                if (conta==contaEncontrada){
                                    contasP.get(i).verDadosConta();
                                    break;
                                } else {
                                    System.out.println("Número de conta inválido!");
                                    break;
                                }
                            }
                        } break;
                    } else {
                        System.out.println("Id inválido ou não existem contas cadastradas!");
                        break;
                    }
                }
            } else if (Integer.parseInt(usuario) == 4){
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