package com.mycompany.bancojava;

import java.text.ParseException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ContaPoupanca extends Conta{
    public ContaPoupanca(){}
    
    public ContaPoupanca (int id, int numeroConta, String nomeCliente, String telefone, double saldo){
        super(id, numeroConta, nomeCliente, telefone, saldo);
    }
    
    @Override
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
                            try {
                                number = currency.parse(depositValue.getText());
                            } catch (ParseException ex) {
                                System.getLogger(Conta.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                            }
                        if (isNumeric(String.valueOf(number))) {
                            double deposit = number.doubleValue();
                            double balance = contasP.get(i).getSaldo();
                            double result = balance + deposit + 1;
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
}
