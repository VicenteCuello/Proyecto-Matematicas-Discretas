package com.mycompany.problema23;

public class Problema23 {
    
    class Prim{
    static class Grafo{
        int vertices;
        int mat[][];
   
        public Grafo(int vertices){
            this.vertices = vertices;
            mat = new int[vertices][vertices];
        }
        public void addEdge(int ori, int des, int peso) {
            mat[ori][des]=peso;
            mat[des][ori]=peso;
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
            int total_coste_min = 0;
            System.out.println("Arbol de recubrimiento minimo");
            for (int i = 1; i < vertices; i++) {
                System.out.println("Arista " + i + " - " + resultado[i].padre + " peso: " + resultado[i].peso);
                total_coste_min +=resultado[i].peso;
            }
            System.out.println("Coste min total = " + total_coste_min);
        }
    }
}
    
    public static void main(String[] args) {
       int vertices = 6;
            Prim.Grafo grafo = new Prim.Grafo(vertices);
                grafo.addEdge(0, 1, 10);
	        grafo.addEdge(0, 2, 25);
	        grafo.addEdge(1, 4, 30);
	        grafo.addEdge(1, 2, 10);
	        grafo.addEdge(2, 5, 5);
	        grafo.addEdge(2, 3, 20);
	        grafo.addEdge(3, 5, 40);
	        grafo.addEdge(5, 4, 12);
	        grafo.mst();
    }
}
