package com.mycompany.bancojava;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Conta {

    static NumberFormat currency = NumberFormat.getInstance();
    private int id;
    private int numeroConta;
    private String nomeCliente;
    private String telefone;
    private Double saldo;
    static List<ContaCorrente> contasC = new ArrayList<>();
    static List<ContaPoupanca> contasP = new ArrayList<>();

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

    public Conta(int id, int numeroConta, String nomeCliente, String telefone, double saldo) {
        this.id = id;
        this.numeroConta = numeroConta;
        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double valor) {
        saldo = valor;
    }

    public static void criarConta(JTextField nome, JTextField telefone, JTextField saldo, JComboBox<String> comboBoxAccountType) {
        Number number = 0;
        try {
            number = currency.parse(saldo.getText());
        } catch (ParseException ex) {
            System.getLogger(Conta.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        double saldo1 = number.doubleValue();
        String accountType = String.valueOf(comboBoxAccountType.getSelectedItem());
        if (accountType == "1- Corrente") {
            ContaCorrente cc = new ContaCorrente(1, contasC.size() + 10000, nome.getText(), telefone.getText(), saldo1);
            contasC.add(cc);
        } else {
            ContaPoupanca cp = new ContaPoupanca(2, contasP.size() + 10000, nome.getText(), telefone.getText(), saldo1);
            contasP.add(cp);
        }
        /*Usando o método matches com uma expressão regular para validar
            se o input contém apenas letras.
            ^: início da string;
            [a-zA-Z]: Representa qualquer letra maiúscula ou minúscula;
            +: Garante que haja pelo menos uma letra;
            \\s: permite apenas espaços ou tabulações na string;
            $: Indica o fim da string.
            
            if (input.matches("^[a-zA-Z\\s]+$")){
                nomeCliente = input;
                break;
            } else{
                System.out.println("Nome inválido! Digite apenas letras");
            }*/

    }

    public void sacar(Double valor) {
        if (valor < saldo) {
            saldo -= valor;
        }
        System.out.println("Valor sacado: " + currency.format(valor) + "\nO saldo agora é de: " + currency.format(saldo));
    }

    public void depositar(double valor) {
        if (valor >= 1) {
            saldo += valor;
            System.out.println("O valor do depósito é de: " + currency.format(valor) + "\nSaldo atualizado: " + currency.format(saldo));
        } else {
            System.out.println("Valor inferior a R$1,00.");
        }
    }

    public static void verSaldo(JComboBox accountBalanceId, JTextField accountBalanceNumber, JLabel accountBalance, JPanel balanceMenu, JPanel balanceMenu2) {
        if (accountBalanceId.getSelectedItem() == "1") {
            if (isNumeric(accountBalanceNumber.getText()) == true) {
                int conta = Integer.parseInt(accountBalanceNumber.getText());
                int i = 0;
                int contaEncontrada = 0;
                while (i < contasC.size()) {
                    contaEncontrada = contasC.get(i).getNumeroConta();
                    if (conta == contaEncontrada) {
                        accountBalance.setText(String.valueOf(contasC.get(i).getSaldo()));
                        balanceMenu.setVisible(false);
                        balanceMenu2.setVisible(true);
                        break;
                    } else {
                        i += 1;
                    }
                }
                if (conta != contaEncontrada) {
                    JOptionPane.showMessageDialog(null, "Número de conta inválido!", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Número de conta inválido!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            if (isNumeric(accountBalanceNumber.getText()) == true) {
                int conta = Integer.parseInt(accountBalanceNumber.getText());
                int i = 0;
                int contaEncontrada = 0;
                while (i < contasP.size()) {
                    contaEncontrada = contasP.get(i).getNumeroConta();
                    if (conta == contaEncontrada) {
                        accountBalance.setText(String.valueOf(contasP.get(i).getSaldo()));
                        balanceMenu.setVisible(false);
                        balanceMenu2.setVisible(true);
                        break;
                    } else {
                        i += 1;
                    }
                }
            } else {
                System.out.println("Número de conta inválido!");
            }
        }
    }

    public static void verDadosConta(JComboBox accountDataId, JTextField accountDataNumber, JLabel accountDataName, JLabel accountDataPhone, JPanel accountDataMenu, JPanel accountDataMenu2) {
        if (accountDataId.getSelectedItem() == "1- Corrente") {
            if (isNumeric(accountDataNumber.getText()) == true) {
                int conta = Integer.parseInt(accountDataNumber.getText());
                int i = 0;
                int contaEncontrada = 0;
                while (i < contasC.size()) {
                    contaEncontrada = contasC.get(i).getNumeroConta();
                    if (conta == contaEncontrada) {
                        accountDataName.setText(String.valueOf(contasC.get(i).getNomeCliente()));
                        accountDataPhone.setText(String.valueOf(contasC.get(i).getTelefone()));
                        accountDataMenu.setVisible(false);
                        accountDataMenu2.setVisible(true);
                        break;
                    } else {
                        i += 1;
                    }
                }
                if (conta != contaEncontrada) {
                    JOptionPane.showMessageDialog(null, "Número de conta inválido!", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Número de conta inválido!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            if (isNumeric(accountDataNumber.getText()) == true) {
                int conta = Integer.parseInt(accountDataNumber.getText());
                int i = 0;
                int contaEncontrada = 0;
                while (i < contasP.size()) {
                    contaEncontrada = contasP.get(i).getNumeroConta();
                    if (conta == contaEncontrada) {
                        accountDataName.setText(String.valueOf(contasP.get(i).getNomeCliente()));
                        accountDataPhone.setText(String.valueOf(contasP.get(i).getTelefone()));
                        accountDataMenu.setVisible(false);
                        accountDataMenu2.setVisible(true);
                        break;
                    } else {
                        i += 1;
                    }
                }
                if (conta != contaEncontrada) {
                    JOptionPane.showMessageDialog(null, "Número de conta inválido!", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Número de conta inválido!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
        
    }
}