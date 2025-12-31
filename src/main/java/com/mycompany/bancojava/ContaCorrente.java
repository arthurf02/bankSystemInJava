package com.mycompany.bancojava;

import java.text.ParseException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ContaCorrente extends Conta{
    public ContaCorrente(){}
    
    public ContaCorrente (int id, int numeroConta, String nomeCliente, String telefone, double saldo){
        super(id, numeroConta, nomeCliente, telefone, saldo);
    }
    
    @Override
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
                            double result = balance - withdraw - 0.10;
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
}