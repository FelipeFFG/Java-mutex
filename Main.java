import java.util.Arrays;
import java.util.concurrent.Semaphore;


public class Main {
    public static void main(String[] args) {
        int[] chave = new int[100];
        int[] index = {0};
        int[] contador_par = {0};
        int[] contador_impar ={0};

        Semaphore mutex = new Semaphore(1);
        Arrays.fill(chave,-1);
        GeradorPares par = new GeradorPares(chave,mutex,index,contador_par);
        GeradorPares par2 = new GeradorPares(chave,mutex,index,contador_par);
        GeradorImpares impar = new GeradorImpares(chave,mutex,index,contador_impar);
        GeradorImpares impar2 = new GeradorImpares(chave,mutex,index,contador_impar);



        par.start();
        par2.start();
        impar.start();
        impar2.start();




        try {
            par.join();
            par2.join();
            impar.join();
            impar2.join();

        }catch (Exception e){
            e.printStackTrace();
        }


        System.out.println("Existem: "+contador_par[0]+ " Numeros Pares");
        System.out.println("Existem: "+contador_impar[0]+ " Numeros Impares");
        System.out.println(Arrays.toString(chave));
    }
}
