public class ContaCorrente extends Conta{
    public ContaCorrente(int _id, int _numeroConta, String _nomeCliente, String _telefone, Double _saldo){
        super(_id, _numeroConta, _nomeCliente, _telefone, _saldo);
    }
    public void verDadosConta(){
        System.out.println("\n"+nomeCliente);
        System.out.println(telefone);
        System.out.println(numeroConta);
        System.out.println(saldo);
    }
    @Override
    public void sacar(Double valor){
        if (valor < saldo){
            saldo-=valor+0.01;
            System.out.println("Valor sacado R$" + valor + "\nSaldo atualizado: R$" + saldo);
        } else{
            System.out.println("Valor inválido ou saldo insuficiente.");
        }
    }
}