import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;
public class GeradorPares extends Thread{
    private int chave[];
    private int[] contador;
    private Semaphore mutex;
    private int index[];
    private int e;


    public GeradorPares(int[] chave,Semaphore mutex,int[] index,int[] contador){
       this.chave = chave;
       this.mutex = mutex;
       this.index = index;
       this.contador =contador;

    }

    public boolean bool(){
        boolean valor;
        try {
            mutex.acquire();
            if(index[0] < chave.length){
                valor =true;
                e = index[0];
                index[0] ++;
                contador[0] ++;
            }else{
                valor = false;
            }
            mutex.release();
        }
        catch (Exception e){
            valor=false;
            e.printStackTrace();
        }
        return valor;
    }


    public void run(){
        try {
            Random aleatorio = new Random();
            while (bool()){
                Thread.sleep(aleatorio.nextInt(3000));
                mutex.acquire();
                    if(chave[e] == -1){
                        int valor=aleatorio.nextInt(9);
                        while (valor %2 !=0){
                            valor=aleatorio.nextInt(9);
                        }
                        chave[e] = valor;
                    }
                mutex.release();
                }




        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
