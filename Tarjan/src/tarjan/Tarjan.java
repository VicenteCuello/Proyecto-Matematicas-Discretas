
package tarjan;

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

public class Tarjan {
    public static void main(String[] args) {
        int n = 8;
        Grafo a = new Grafo();
        List<List<Integer>> grafo = a.crearGrafo(n);
        
        a.añadirVertice(grafo,0,1);
        a.añadirVertice(grafo,1,2);
        a.añadirVertice(grafo,2,0);
        a.añadirVertice(grafo,3,7);
        a.añadirVertice(grafo,3,4);
        a.añadirVertice(grafo,3,7);
        a.añadirVertice(grafo,4,5);
        a.añadirVertice(grafo,5,0);
        a.añadirVertice(grafo,5,6);
        a.añadirVertice(grafo,6,0);
        a.añadirVertice(grafo,6,2);
        a.añadirVertice(grafo,6,4);
        a.añadirVertice(grafo,7,3);
        a.añadirVertice(grafo,7,5);
        

 

    CodigoTarjan x = new CodigoTarjan(grafo);

    int[] ar = x.obtenerVertice();
    Map<Integer, List<Integer>> mapa = new HashMap<>();
    for (int i=0; i<n; i++) {
      if (!mapa.containsKey(ar[i])) mapa.put(ar[i], new ArrayList<>());
      mapa.get(ar[i]).add(i);
    }
    System.out.printf("Número de componentes fuertemente conectados: %d\n", x.contador());
    for (List<Integer> scc : mapa.values()) {
      System.out.println("Vertices: " + scc + " que forman un componente fuertemente conectado.");
    }
    }
    
}
