import java.awt.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.Border;

public class Main {

    public static boolean isNumeric(String usuario) {
        if (usuario == null) {
            return false;
        }
        try {
            long i = Long.parseLong(usuario);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Banco Agiota");
        frame.setSize(720,480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(0,5));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0;
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        panel.setBorder(lineBorder);

        JLabel mainTitle = new JLabel("Banco Agiota");
        panel.add(mainTitle, c);

        c.gridy = 1;
        JLabel mainTitle2 = new JLabel("Seu dinheiro, nossa vida!");
        panel.add(mainTitle2, c);

        JPanel panel2 = new JPanel(new BorderLayout());
        JLabel mainMenu = new JLabel("MENU", SwingConstants.CENTER);
        panel2.add(mainMenu, BorderLayout.CENTER);
        panel2.setBorder(lineBorder);
        panel2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10));

        JPanel panel3 = new JPanel(new GridBagLayout());
        panel3.setMaximumSize(new Dimension(Integer.MAX_VALUE,300));
        c.insets = new Insets(10, 10, 10, 0);
        c.gridy = 0;
        panel3.add(new JButton("Criar conta"), c.gridy);
        c.gridx = 1;
        panel3.add(new JButton("Ver saldo"), c.insets);
        c.gridx = 2;
        panel3.add(new JButton("Ver dados da conta"), c);
        c.gridx = 0;
        c.gridy = 1;
        panel3.add(new JButton("Sacar"), c);
        c.gridx = 2;
        panel3.add(new JButton("Depositar"), c);
        panel3.setBorder(lineBorder);

        mainPanel.add(panel);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        frame.add(mainPanel);
        frame.setVisible(true);

        Locale localeBR = Locale.forLanguageTag("pt-BR");
        NumberFormat numberFormat = NumberFormat.getInstance();
        NumberFormat currency = NumberFormat.getCurrencyInstance(localeBR);
        List<ContaCorrente> contasC = new ArrayList<>();
        List<ContaPoupanca> contasP = new ArrayList<>();
        String usuario;
        Scanner newValue = new Scanner(System.in);
        System.out.println("Banco Agiota\nSeu dinheiro, nossa vida!");
        while (true) {
            System.out.println("\nMENU\n1- Criar conta\n2- Ver saldo\n3- Ver dados da conta\n4- Sacar\n5- Depositar\n0- Sair");
            System.out.println("Insira o número de uma das opções acima: ");
            usuario = newValue.next();
            if (isNumeric(usuario) == true && Integer.parseInt(usuario) == 0) {
                break;
            } else if (isNumeric(usuario) == true && Integer.parseInt(usuario) == 1) {
                while (true) {
                    System.out.println("\nQual tipo de conta você quer criar:\n1- Conta Corrente\n2- Conta Poupança\n0- Voltar");
                    System.out.println("Insira o número de uma das opções acima:");
                    usuario = newValue.next();
                    if (isNumeric(usuario) == true && Integer.parseInt(usuario) == 0) {
                        break;
                    } else if (isNumeric(usuario) == true && Integer.parseInt(usuario) == 1) {
                        ContaCorrente cc = new ContaCorrente(1, contasC.size() + 10000, "", "", 0.0);
                        cc.criarConta(newValue);
                        contasC.add(cc);
                        System.out.println("Seu id de conta é: 1");
                        System.out.println("Seu número de conta é: " + contasC.get(contasC.size() - 1).getNumeroConta());
                        break;
                    } else if (isNumeric(usuario) == true && Integer.parseInt(usuario) == 2) {
                        ContaPoupanca cp = new ContaPoupanca(2, contasP.size() + 10000, "", "", 0.0);
                        cp.criarConta(newValue);
                        contasP.add(cp);
                        System.out.println("Seu id de conta é: 2");
                        System.out.println("Seu número de conta é: " + contasP.get(contasP.size() - 1).getNumeroConta());
                        break;
                    } else {
                        System.out.println("Opção inválida!");
                    }
                }
            } else if (isNumeric(usuario) == true && Integer.parseInt(usuario) == 2) {
                while (true) {
                    System.out.println("Informe o id da conta (1 para Conta Corrente / 2 para Conta Poupança / 0 para sair): ");
                    String idConta = newValue.next();
                    if (isNumeric(idConta) == true && Integer.parseInt(idConta) == 0) {
                        break;
                    } else if (isNumeric(idConta) == true && Integer.parseInt(idConta) == 1 && contasC.size() != 0) {
                        System.out.println("Informe o número da conta: ");
                        String usuarioConta = newValue.next();
                        if (isNumeric(usuarioConta) == true) {
                            int i = 0;
                            int conta = Integer.parseInt(usuarioConta);
                            int contaEncontrada = 0;
                            while (i < contasC.size()) {
                                contaEncontrada = contasC.get(i).getNumeroConta();
                                if (conta == contaEncontrada) {
                                    contasC.get(i).verSaldo();
                                    break;
                                } else {
                                    i += 1;
                                }
                            }
                            if (conta != contaEncontrada) {
                                System.out.println("Número de conta inválido!");
                            } else {
                                break;
                            }
                        } else {
                            System.out.println("Número de conta inválido!");
                        }
                    } else if (isNumeric(idConta) == true && Integer.parseInt(idConta) == 2 && contasP.size() != 0) {
                        System.out.println("Informe o número da conta: ");
                        String usuarioConta = newValue.next();
                        if (isNumeric(usuarioConta) == true) {
                            int i = 0;
                            int conta = Integer.parseInt(usuarioConta);
                            int contaEncontrada = 0;
                            while (i < contasP.size()) {
                                contaEncontrada = contasP.get(i).getNumeroConta();
                                if (conta == contaEncontrada) {
                                    contasP.get(i).verSaldo();
                                    break;
                                } else {
                                    i += 1;
                                }
                            }
                            if (conta != contaEncontrada) {
                                System.out.println("Número de conta inválido!");
                            } else {
                                break;
                            }
                        } else {
                            System.out.println("Número de conta inválido!");
                        }
                    } else {
                        System.out.println("Id sem contas cadastradas ou inválido!");
                    }
                }
            } else if (isNumeric(usuario) == true && Integer.parseInt(usuario) == 3) {
                while (true) {
                    System.out.println("Informe o id da conta: ");
                    String idConta = newValue.next();
                    if (isNumeric(idConta) == true && Integer.parseInt(idConta) == 1 && contasC.size() != 0) {
                        System.out.println("Informe o número da conta:");
                        String usuarioConta = newValue.next();
                        if (isNumeric(usuarioConta) == true) {
                            int i = 0;
                            int conta = Integer.parseInt(usuarioConta);
                            int contaEncontrada;
                            while (i < contasC.size()) {
                                contaEncontrada = contasC.get(i).getNumeroConta();
                                if (conta == contaEncontrada) {
                                    contasC.get(i).verDadosConta();
                                    break;
                                } else {
                                    System.out.println("Número de conta inválido!");
                                    break;
                                }
                            }
                        }
                        break;
                    } else if (isNumeric(idConta) == true && Integer.parseInt(idConta) == 2 && contasP.size() != 0) {
                        System.out.println("Informe o número da conta:");
                        String usuarioConta = newValue.next();
                        if (isNumeric(usuarioConta) == true) {
                            int i = 0;
                            int conta = Integer.parseInt(usuarioConta);
                            int contaEncontrada;
                            while (i < contasP.size()) {
                                contaEncontrada = contasP.get(i).getNumeroConta();
                                if (conta == contaEncontrada) {
                                    contasP.get(i).verDadosConta();
                                    break;
                                } else {
                                    System.out.println("Número de conta inválido!");
                                    break;
                                }
                            }
                        }
                        break;
                    } else {
                        System.out.println("Id inválido ou não existem contas cadastradas!");
                        break;
                    }
                }
            } else if (isNumeric(usuario) == true && Integer.parseInt(usuario) == 4) {
                while (true) {
                    System.out.println("Informe o id da conta: ");
                    String idConta = newValue.next();
                    if (isNumeric(idConta) == true && Integer.parseInt(idConta) == 1 && contasC.size() != 0) {
                        System.out.println("Informe o número da conta:");
                        String usuarioConta = newValue.next();
                        if (isNumeric(usuarioConta) == true) {
                            int i = 0;
                            int conta = Integer.parseInt(usuarioConta);
                            int contaEncontrada;
                            while (true) {
                                contaEncontrada = contasC.get(i).getNumeroConta();
                                if (conta == contaEncontrada) {
                                    System.out.println("Conta de " + contasC.get(i).getNomeCliente());
                                    System.out.println("Saldo atual: " + currency.format(contasC.get(i).getSaldo()));
                                    System.out.println("Informe o valor a sacar:");
                                    String input = newValue.next();
                                    try {
                                        Number number = numberFormat.parse(input);
                                        double valorSaque = number.doubleValue();
                                        if (valorSaque > 0 && valorSaque <= contasC.get(i).getSaldo()) {
                                            contasC.get(i).sacar(valorSaque);
                                            break;
                                        } else {
                                            System.out.println("Valor inválido ou saldo insuficiente!");
                                        }
                                    } catch (ParseException e) {
                                        System.out.println("Valor inválido. Certifique-se de inserir apenas números e vírgula para separar os centavos.");
                                    }
                                    
                                } else {
                                    System.out.println("Número de conta inválido!");
                                }
                            }
                        break;
                        }
                    } else if (isNumeric(idConta) == true && Integer.parseInt(idConta) == 2 && contasP.size() != 0) {
                        System.out.println("Informe o número da conta:");
                        String usuarioConta = newValue.next();
                        if (isNumeric(usuarioConta) == true) {
                            int i = 0;
                            int conta = Integer.parseInt(usuarioConta);
                            int contaEncontrada;
                            while (true) {
                                contaEncontrada = contasP.get(i).getNumeroConta();
                                if (conta == contaEncontrada) {
                                    System.out.println("Conta de " + contasP.get(i).getNomeCliente());
                                    System.out.println("Saldo atual: " + currency.format(contasP.get(i).getSaldo()));
                                    System.out.println("Informe o valor a sacar:");
                                    String input = newValue.next();
                                    try {
                                        Number number = numberFormat.parse(input);
                                        double valorSaque = number.doubleValue();
                                        if (valorSaque > 0 && valorSaque <= contasP.get(i).getSaldo()) {
                                            contasP.get(i).sacar(valorSaque);
                                            break;
                                        } else {
                                            System.out.println("Valor inválido ou saldo insuficiente!");
                                        }
                                    } catch (ParseException e) {
                                        System.out.println("Valor inválido. Certifique-se de inserir apenas números e vírgula para separar os centavos.");
                                    }
                                } else {
                                    System.out.println("Número de conta inválido!");
                                }
                            }
                        break;
                        }
                    } else {
                        System.out.println("Não há contas existentes. Crie uma primeiro para poder sacar.");
                        break;
                    }
                }
            } else if (isNumeric(usuario) == true && Integer.parseInt(usuario) == 5) {
                while (true) {
                    System.out.println("Informe o id da conta: ");
                    String idConta = newValue.next();
                    if (isNumeric(idConta) == true && Integer.parseInt(idConta) == 1 && contasC.size() != 0) {
                        System.out.println("Informe o número da conta:");
                        String usuarioConta = newValue.next();
                        if (isNumeric(usuarioConta) == true) {
                            int i = 0;
                            int conta = Integer.parseInt(usuarioConta);
                            int contaEncontrada;
                            while (i < contasC.size()) {
                                contaEncontrada = contasC.get(i).getNumeroConta();
                                if (conta == contaEncontrada) {
                                    System.out.println("Informe o valor para depositar: ");
                                    String input = newValue.next();
                                    try {
                                        Number number = numberFormat.parse(input);
                                        double valorDeposito = number.doubleValue();
                                        if (valorDeposito > 0) {
                                            contasC.get(i).depositar(valorDeposito);
                                            break;
                                        } else {
                                            System.out.println("Valor inferior a R$1,00!");
                                        }
                                    } catch (ParseException e) {
                                        System.out.println("Valor inválido. Certifique-se de inserir apenas números e vírgula para separar os centavos.");
                                    }
                                } else {
                                    System.out.println("Número de conta inválido!");
                                    break;
                                }
                            }
                        }
                        break;
                    } else if (isNumeric(idConta) == true && Integer.parseInt(idConta) == 2 && contasP.size() != 0) {
                        System.out.println("Informe o número da conta:");
                        String usuarioConta = newValue.next();
                        if (isNumeric(usuarioConta) == true) {
                            int i = 0;
                            int conta = Integer.parseInt(usuarioConta);
                            int contaEncontrada;
                            while (i < contasP.size()) {
                                contaEncontrada = contasP.get(i).getNumeroConta();
                                if (conta == contaEncontrada) {
                                    System.out.println("Informe o valor para depositar: ");
                                    String input = newValue.next();
                                    try {
                                        Number number = numberFormat.parse(input);
                                        double valorDeposito = number.doubleValue();
                                        if (valorDeposito > 0) {
                                            contasP.get(i).depositar(valorDeposito);
                                            break;
                                        } else {
                                            System.out.println("Valor inferior a R$1,00!");
                                        }
                                    } catch (ParseException e) {
                                        System.out.println("Valor inválido. Certifique-se de inserir apenas números e vírgula para separar os centavos.");
                                    }
                                }
                            }
                        }
                        break;
                    } else {
                        System.out.println("Id inválido ou não existem contas cadastradas!");
                        break;
                    }
                }
            }
        }
        newValue.close();
    }
}
