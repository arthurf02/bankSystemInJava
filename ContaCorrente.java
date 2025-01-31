import java.text.NumberFormat;

public class ContaCorrente extends Conta{
    public ContaCorrente(int _id, int _numeroConta, String _nomeCliente, String _telefone, Double _saldo){
        super(_id, _numeroConta, _nomeCliente, _telefone, _saldo);
    }
    
    @Override
    public void sacar(Double valor){
        double saldo = getSaldo();
        saldo-=valor+0.01;
        setSaldo(saldo);
        System.out.println("Valor sacado: " + NumberFormat.getCurrencyInstance().format(valor) + "\nSaldo atualizado: " + saldo);
        System.out.println("Você paga uma taxa de R$0,01 a cada saque");
    }
}