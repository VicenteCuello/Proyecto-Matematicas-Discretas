
package pregunta.pkg2.pkg3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Ciudad{
    public String name;
    public int number;
    public Ciudad(String s, int n){
        name = s;
        number = n;
    }
}

public class Pregunta23 {
    

    class Prim{
        
    static class Grafo{
        
        int vertices;
        int mat[][];
   
        public Grafo(int vertices){
            this.vertices = vertices;
            mat = new int[vertices][vertices];
        }
        
        public void addEdge(int ori, int des) {
            mat[ori][des] = 1;
            mat[des][ori] = 1;
        }
        class Resultado {
            int padre;
            int peso;
        }
        int minVert( boolean [] mst, int [] pesos){
            int minPeso = Integer.MAX_VALUE;
            int vertice = -1;

            for (int i = 0; i < vertices; i++) {
                if (mst[i]== false && minPeso > pesos [i]) {
                    minPeso =pesos [i];
                    vertice = i;
                }
            }
            return vertice;
        }
        public void mst() {
            boolean [] mst = new boolean[vertices];
            int [] pesos = new int [vertices];
            Resultado [] resultado = new Resultado [vertices];

            for (int i = 0; i < vertices; i++) {
                pesos[i] = Integer.MAX_VALUE;
                resultado[i] = new Resultado();
            }
            pesos[0]=0;
            resultado[0].padre=-1;
            for (int i = 0; i < vertices; i++) {
                int vertice = minVert(mst, pesos);
                mst[vertice]= true;
                for (int j = 0; j < vertices; j++) {
                    if (mat[vertice][j]>0) {
                        if (mst[j]==false && mat[vertice][j]< pesos[j]) {
                            pesos[j] = mat[vertice][j];
                            resultado[j].padre=vertice;
                            resultado[j].peso = pesos[j];
                        }                        
                    }                                        
                }                
            }
            printMst(resultado);
        }
        
        public void printMst( Resultado [] resultado ) {
            System.out.println("Aristas donde se ubicara un centro Pokémon");
            for (int i = 1; i < vertices; i++) {
                System.out.println("Arista " + i + " - " + resultado[i].padre);
            }
        }
        
    }
}
     
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String name = "src\\pregunta\\pkg2\\pkg3\\EnfermeraJoey.txt";
        File archivo = new File (name);
        FileReader fr = new FileReader (archivo);
        BufferedReader br = new BufferedReader(fr);
        int numCiudades = Integer.valueOf(br.readLine());
        ArrayList<Ciudad> Towns = new ArrayList<>();
        Prim.Grafo grafo = new Prim.Grafo(numCiudades);
        for (int i = 0; i < numCiudades; i++) {
            Towns.add(new Ciudad(br.readLine(),i));
        }
        int Edges = Integer.valueOf(br.readLine());
        for (int i = 0; i < Edges; i++) {
            int n = 0, m = 0;
            String aux = br.readLine();
            String[] line;
            line = aux.split(" ", 2);
            for (int j = 0; j < numCiudades; j++) {
                if(Towns.get(j).name.equals(line[0])){
                    n = Towns.get(j).number;
                }    
            }
            for (int j = 0; j < numCiudades; j++) {
                if(Towns.get(j).name.equals(line[1])){
                    m = Towns.get(j).number;
                }    
            }
            grafo.addEdge(n, m);
        }
	grafo.mst();
    }
}
