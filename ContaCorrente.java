public class ContaCorrente extends Conta{
    public ContaCorrente(int _id, int _numeroConta, String _nomeCliente, String _telefone, Double _saldo){
        super(_id, _numeroConta, _nomeCliente, _telefone, _saldo);
    }
    public void verDadosConta(){
        System.out.println("\n"+getNomeCliente());
        System.out.println(getTelefone());
        System.out.println(getNumeroConta());
        System.out.println(getSaldo());
    }
    @Override
    public void sacar(Double valor){
        if (valor < getSaldo()){
            double saldo = getSaldo();
            saldo-=valor+0.01;
            System.out.println("Valor sacado R$" + valor + "\nSaldo atualizado: R$" + saldo);
        } else{
            System.out.println("Valor inválido ou saldo insuficiente.");
        }
    }
}