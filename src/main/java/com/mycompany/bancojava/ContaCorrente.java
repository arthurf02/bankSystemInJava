package com.mycompany.bancojava;

import javax.swing.JTextField;


public class ContaCorrente extends Conta{
    public ContaCorrente(int _id, int _numeroConta, String _nomeCliente, String _telefone, double _saldo){
        super(_id, _numeroConta, _nomeCliente, _telefone, _saldo);
    }
    
    /*@Override
    public void sacar(Double valor){
        double saldo = getSaldo();
        saldo-=valor+0.01;
        setSaldo(saldo);
        System.out.println("Valor sacado: " + currency.format(valor) + "\nSaldo atualizado: " + currency.format(saldo));
        System.out.println("Você paga uma taxa de R$0,01 a cada saque");
    }*/
}