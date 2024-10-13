import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        int usuario = 1;
        Scanner newValue = new Scanner(System.in);
        ContaCorrente cc = new ContaCorrente(0, 0, null, null, 0.0);
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
                    cc.criarCC(newValue);
                }
            }
            cc.verDadosConta();
        }
        newValue.close();
    }
}