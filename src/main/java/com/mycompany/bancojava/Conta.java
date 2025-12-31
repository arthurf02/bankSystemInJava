package com.mycompany.bancojava;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Conta {

    static NumberFormat currency = NumberFormat.getInstance();
    static NumberFormat printCurrency = NumberFormat.getCurrencyInstance(Locale.getDefault());
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
            Double.parseDouble(usuario);
            return true;
        } catch (NumberFormatException e) {
            // Se uma exceção NumberFormatException for lançada,
            // a string não é um double válido.
            return false;
        }
    }
    
    public Conta(){}
    
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

    public void criarConta(JTextField nome, JTextField telefone, JTextField saldo,
            JComboBox<String> comboBoxAccountType, JPanel newAccountMenu, JPanel newAccountMenu2,
            JTextField nameNewAccount, JTextField phoneNewAccount, JTextField moneyNewAccount, JLabel showAccountID, JLabel showAccountNumber) {
        Number number = 0;
        try {
            number = currency.parse(saldo.getText());
        } catch (ParseException ex) {
            System.getLogger(Conta.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        double saldo1 = number.doubleValue();
        String accountType = String.valueOf(comboBoxAccountType.getSelectedItem());
        if (accountType == "1- Corrente") {
            contasC.add(new ContaCorrente(1, contasC.size() + 10000, nome.getText(), telefone.getText(), saldo1));
            showAccountID.setText("1");
            showAccountNumber.setText("" + ((contasC.size() - 1) + 10000));
            newAccountMenu.setVisible(false);
            newAccountMenu2.setVisible(true);
        } else {
            contasP.add(new ContaPoupanca(2, contasP.size() + 10000, nome.getText(), telefone.getText(), saldo1));
            showAccountID.setText("2");
            showAccountNumber.setText("" + ((contasP.size() - 1) + 10000));
            newAccountMenu.setVisible(false);
            newAccountMenu2.setVisible(true);
        }
        nameNewAccount.setText("");
        phoneNewAccount.setText("");
        moneyNewAccount.setText("");
        comboBoxAccountType.setSelectedItem("1- Corrente");
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

    public void sacar(JPanel withdrawMenu, JPanel withdrawMenu2, JComboBox<String> withdrawAccountID, JTextField withdrawAccountNumber, JTextField withdrawValue, JLabel withdrawValue2, JLabel finalBalance) {
        if (withdrawAccountID.getSelectedItem() == "1- Corrente") {
            if (isNumeric(withdrawAccountNumber.getText()) == true) {
                int account = Integer.parseInt(withdrawAccountNumber.getText());
                int i = 0;
                int foundAccount = 0;
                while (i < contasC.size()) {
                    foundAccount = contasC.get(i).getNumeroConta();
                    if (account == foundAccount) {
                        Number number = 0;
                            try {
                                number = currency.parse(withdrawValue.getText());
                            } catch (ParseException ex) {
                                System.getLogger(Conta.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                            }
                        if (isNumeric(String.valueOf(number))) {
                            double withdraw = number.doubleValue();
                            double balance = contasC.get(i).getSaldo();
                            if (balance < withdraw){
                                JOptionPane.showMessageDialog(null, "Valor maior que saldo disponível");
                                break;
                            }
                            double result = balance - withdraw;
                            contasC.get(i).setSaldo(result);
                            withdrawValue2.setText(printCurrency.format(withdraw));
                            finalBalance.setText(printCurrency.format(result));
                            withdrawMenu.setVisible(false);
                            withdrawMenu2.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Digite apenas números!", "Alert", JOptionPane.WARNING_MESSAGE);
                        }
                        break;
                    } else {
                        i += 1;
                    }
                }
                if (account != foundAccount) {
                    JOptionPane.showMessageDialog(null, "Número de conta inválido!", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Número de conta inválido!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            if (isNumeric(withdrawAccountNumber.getText()) == true) {
                int account = Integer.parseInt(withdrawAccountNumber.getText());
                int i = 0;
                int foundAccount = 0;
                while (i < contasP.size()) {
                    foundAccount = contasP.get(i).getNumeroConta();
                    if (account == foundAccount) {
                        Number number = 0;
                            try {
                                number = currency.parse(withdrawValue.getText());
                            } catch (ParseException ex) {
                                System.getLogger(Conta.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                            }
                        if (isNumeric(String.valueOf(number))) {
                            double withdraw = number.doubleValue();
                            double balance = contasP.get(i).getSaldo();
                            if (balance < withdraw){
                                JOptionPane.showMessageDialog(null, "Valor maior que saldo disponível");
                                break;
                            }
                            double result = balance - withdraw;
                            contasC.get(i).setSaldo(result);
                            withdrawValue2.setText(printCurrency.format(withdraw));
                            finalBalance.setText(printCurrency.format(result));
                            withdrawMenu.setVisible(false);
                            withdrawMenu2.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Digite apenas números!", "Alert", JOptionPane.WARNING_MESSAGE);
                        }
                        break;
                    } else {
                        i += 1;
                    }
                }
                if (account != foundAccount) {
                    JOptionPane.showMessageDialog(null, "Número de conta inválido!", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Número de conta inválido!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
        withdrawAccountID.setSelectedItem("1- Corrente");
        withdrawAccountNumber.setText("");
        withdrawValue.setText("");
    }

    public void depositar(JPanel depositMenu, JPanel depositMenu2, JComboBox<String> depositAccountID, JTextField depositAccountNumber, JTextField depositValue, JLabel depositValue2, JLabel finalBalance2) {
        if (depositAccountID.getSelectedItem() == "1- Corrente") {
            if (isNumeric(depositAccountNumber.getText()) == true) {
                int account = Integer.parseInt(depositAccountNumber.getText());
                int i = 0;
                int foundAccount = 0;
                while (i < contasC.size()) {
                    foundAccount = contasC.get(i).getNumeroConta();
                    if (account == foundAccount) {
                        Number number = 0;
                        double value = 0;
                            try {
                                number = currency.parse(depositValue.getText());
                            } catch (ParseException ex) {
                                System.getLogger(Conta.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                            }
                        if (isNumeric(String.valueOf(number))) {
                            double deposit = number.doubleValue();
                            double balance = contasC.get(i).getSaldo();
                            double result = balance + deposit;
                            contasC.get(i).setSaldo(result);
                            depositValue2.setText(printCurrency.format(deposit));
                            finalBalance2.setText(printCurrency.format(result));
                            depositMenu.setVisible(false);
                            depositMenu2.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Digite apenas números!", "Alert", JOptionPane.WARNING_MESSAGE);
                        }
                        break;
                    } else {
                        i += 1;
                    }
                }
                if (account != foundAccount) {
                    JOptionPane.showMessageDialog(null, "Número de conta inválido!", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Número de conta inválido!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            if (isNumeric(depositAccountNumber.getText()) == true) {
                int account = Integer.parseInt(depositAccountNumber.getText());
                int i = 0;
                int foundAccount = 0;
                while (i < contasP.size()) {
                    foundAccount = contasP.get(i).getNumeroConta();
                    if (account == foundAccount) {
                        Number number = 0;
                        double value = 0;
                            try {
                                number = currency.parse(depositValue.getText());
                            } catch (ParseException ex) {
                                System.getLogger(Conta.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                            }
                        if (isNumeric(String.valueOf(number))) {
                            double deposit = number.doubleValue();
                            double balance = contasP.get(i).getSaldo();
                            double result = balance + deposit;
                            contasP.get(i).setSaldo(result);
                            depositValue2.setText(printCurrency.format(deposit));
                            finalBalance2.setText(printCurrency.format(result));
                            depositMenu.setVisible(false);
                            depositMenu2.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Digite apenas números!", "Alert", JOptionPane.WARNING_MESSAGE);
                        }
                        break;
                    } else {
                        i += 1;
                    }
                }
                if (account != foundAccount) {
                    JOptionPane.showMessageDialog(null, "Número de conta inválido!", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Número de conta inválido!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
        depositAccountID.setSelectedItem("1- Corrente");
        depositAccountNumber.setText("");
        depositValue.setText("");
    }

    public static void verSaldo(JComboBox accountBalanceId, JTextField accountBalanceNumber, JLabel accountBalance, JPanel balanceMenu, JPanel balanceMenu2) {
        if (accountBalanceId.getSelectedItem() == "1- Corrente") {
            if (isNumeric(accountBalanceNumber.getText()) == true) {
                int conta = Integer.parseInt(accountBalanceNumber.getText());
                int i = 0;
                int contaEncontrada = 0;
                while (i < contasC.size()) {
                    contaEncontrada = contasC.get(i).getNumeroConta();
                    if (conta == contaEncontrada) {
                        accountBalance.setText(printCurrency.format(contasC.get(i).getSaldo()));
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
        } else if (accountBalanceId.getSelectedItem() == "2- Poupança") {
            if (isNumeric(accountBalanceNumber.getText()) == true) {
                int conta = Integer.parseInt(accountBalanceNumber.getText());
                int i = 0;
                int contaEncontrada = 0;
                while (i < contasP.size()) {
                    contaEncontrada = contasP.get(i).getNumeroConta();
                    if (conta == contaEncontrada) {
                        accountBalance.setText(printCurrency.format(contasP.get(i).getSaldo()));
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
            JOptionPane.showMessageDialog(null, "Número de conta inválido!", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        accountBalanceId.setSelectedItem("1- Corrente");
        accountBalanceNumber.setText("");
    }

    public void verDadosConta(JComboBox accountDataId, JTextField accountDataNumber, JLabel accountDataName, JLabel accountDataPhone, JPanel accountDataMenu, JPanel accountDataMenu2) {
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
        accountDataId.setSelectedItem("1- Corrente");
        accountDataNumber.setText("");
    }
}
