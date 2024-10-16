import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        List<ContaCorrente> contas = new ArrayList<>();
        int usuario = 1;
        Scanner newValue = new Scanner(System.in);
        System.out.println("Banco Agiota\nSeu dinheiro, nossa vida!");
        while (usuario != 0){
            System.out.println("MENU\n1-Criar conta\n2-Ver saldo\n3-Ver dados da conta");
            usuario = newValue.nextInt();
            newValue.nextLine();
            if (usuario == 1){
                System.out.println("Qual tipo de conta você quer criar:\n1- Conta Corrente\n2- Conta Poupança");
                usuario = newValue.nextInt();
                newValue.nextLine();
                if (usuario == 1){
                    ContaCorrente cc = new ContaCorrente(1,contas.size()+10000,"","",0.0);
                    cc.criarCC(newValue);
                    contas.add(cc);
                }
            } else if (usuario == 3){
                System.out.println("Informe o número da conta:");
                int usuarioConta = newValue.nextInt();
                newValue.nextLine();
                contas.get(usuarioConta).verDadosConta();
            }
        }
        newValue.close();
    }
}