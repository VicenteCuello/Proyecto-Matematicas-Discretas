
package problema2discretas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.Math.min;
import java.util.*;

class CodigoTarjan{
    private int n;
     private List<List<Integer>> grafo;
    private boolean res;
    private int cont;
    private int num;
    private boolean[] vis;
    private int[] id;
    private int[] b;
    private int[] v;
    private Deque<Integer> con;
    private static final int nvis = -1;
    
    public CodigoTarjan(List<List<Integer>> grafo){
        n = grafo.size();
        this.grafo = grafo;
    }
    public void resuelto(){
        id = new int[n];
        b = new int[n];
        v = new int[n];
        vis = new boolean[n];    
        con = new ArrayDeque<>();
        Arrays.fill(id, nvis);
        for(int i=0;i<n;i++){
            if(id[i]==nvis){
                iniciar(i);
            }
        }
        res = true;
    }
    public int contador(){
        if(res== false){
            resuelto();
        }
        return cont;
    }
    public int[] obtenerVertice(){
        if(res== false){
            resuelto();
        }
        return v;
    }
    public void iniciar(int a){
        b[a]= num++;
        id[a] = b[a];
        con.push(a);
        for(int i : grafo.get(a)){
            if(id[i]==nvis){
                iniciar(i);
            }
            if(vis[i]){
                b[a]= min(b[a],b[i]);
            }
        }
        if(id[a]==b[a]){
            for(int i=con.pop(); ;i=con.pop()){
                vis[i] = false;
                v[i] = cont;
                if(i==a){
                    break;
                }
            }
            cont++;
        }
    }
    
    
}
class Grafo{
    public Grafo(){
        
    }
    public static List<List<Integer>> crearGrafo(int n){
        List<List<Integer>> grafo = new ArrayList<>(n);
        for(int i=0; i<n; i++){
            grafo.add(new ArrayList<>());
        }
        return grafo;
    }
    public static void añadirVertice(List<List<Integer>> grafo, int n, int a){
        grafo.get(n).add(a);
    }

}


public class Problema2discretas {

    
    public static void main(String[] args) throws IOException {
        File arc = new File("C:\\Users\\jorge\\Downloads\\Problema2discretas\\ProfesoraEncina.txt");
        Scanner sc = new Scanner(arc);
        while(sc.hasNextLine()){
            System.out.println(sc.nextLine());
        }
        int n = 8;
        Grafo a = new Grafo();
        List<List<Integer>> grafo = a.crearGrafo(n);
        
        int Cheren = 0;
        int Elesa = 1;
        int Iris = 2;
        int Liza = 3;
        int Lucho = 4;
        int Bianca = 5;
        int Cilan = 6;
        int Alder = 7;
        
        a.añadirVertice(grafo,Cheren,Elesa);
        a.añadirVertice(grafo,Elesa,Cheren);
        a.añadirVertice(grafo,Iris,Liza);
        a.añadirVertice(grafo,Iris,Lucho);
        a.añadirVertice(grafo,Liza,Bianca);
        a.añadirVertice(grafo,Liza,Iris);
        a.añadirVertice(grafo,Lucho,Cilan);
        a.añadirVertice(grafo,Lucho,Cilan);
        a.añadirVertice(grafo,Bianca,Alder);
        a.añadirVertice(grafo,Bianca,Iris);
        a.añadirVertice(grafo,Bianca,Liza);
        a.añadirVertice(grafo,Cilan,Iris);
        a.añadirVertice(grafo,Cilan,Lucho);
        a.añadirVertice(grafo,Alder,Iris);
        a.añadirVertice(grafo,Alder,Lucho);
        
        
        

 

        CodigoTarjan x = new CodigoTarjan(grafo);

        int[] ar = x.obtenerVertice();
        Map<Integer, List<Integer>> mapa = new HashMap<>();
        for (int i=0; i<n; i++) {
          if (!mapa.containsKey(ar[i])) mapa.put(ar[i], new ArrayList<>());
          mapa.get(ar[i]).add(i);
        }
        System.out.printf("Numero de componentes fuertemente conectados: %d\n", x.contador());
        for (List<Integer> scc : mapa.values()) {
          System.out.println("Entrenador: " + scc + " recibe el mensaje.");
        }
        }
            
    }

