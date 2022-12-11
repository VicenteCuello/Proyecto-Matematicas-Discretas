
package pregunta.pkg2.pkg1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Ciudad{
    String name;
    int number;
    public Ciudad(String s, int n){
        name = s;
        number = n;
    }
}

public class Pregunta21 {

    public static void calculePath(int[][] path, int start, int end, ArrayList<Integer> ruta){
        if(path[start][end] == start){
            return;
        }
        calculePath(path, start, path[start][end], ruta);
        ruta.add(path[start][end]);
    }
    
    public static void FloydWarshall(int[][] weight, ArrayList<Ciudad> Pueblos, int[] mainPath){
        int n = weight.length;
        int[][] cost = new int[n][n];
        int[][] path = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = weight[i][j];
                if(i == j){
                    path[i][j] = 0;
                }else{
                    if(cost[i][j] != Integer.MAX_VALUE){
                        path[i][j] = i;
                    }else{
                        path[i][j] = -1;
                    }
                }
            }
        }
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (cost[i][k] + cost[k][j] < cost[i][j] && cost[i][k] != Integer.MAX_VALUE && cost[k][j] != Integer.MAX_VALUE){
                        cost[i][j] = cost[i][k] + cost[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }
        int size = mainPath.length;
        ArrayList<Integer> result = new ArrayList<>();
        System.out.println("El camino mas corto en orden seria el siguiente:");
        for (int i = 0; i < size-1; i++) {
            for (int j = 0; j < Pueblos.size(); j++) {
                if(mainPath[i] == Pueblos.get(j).number){
                    result.add(Pueblos.get(j).number);
                }
            }
            calculePath(path, mainPath[i], mainPath[i+1], result);
        }
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < Pueblos.size(); j++) {
                if(result.get(i) == Pueblos.get(j).number){
                    System.out.print(Pueblos.get(j).name+"->");
                }
            }
        }
        for (int j = 0; j < Pueblos.size(); j++) {
            if(mainPath[size-1] == Pueblos.get(j).number){
                System.out.print(Pueblos.get(j).name+"\n");
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String name = "src\\pregunta\\pkg2\\pkg1\\Bianca.txt";
        File archivo = new File (name);
        FileReader fr = new FileReader (archivo);
        BufferedReader br = new BufferedReader(fr);
        
        int numCiudades = Integer.valueOf(br.readLine());
        ArrayList<Ciudad> Towns = new ArrayList<>();
        int[][] mundoPokemon = new int[numCiudades][numCiudades];
        ArrayList<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < numCiudades; i++) {
            for (int j = 0; j < numCiudades; j++) {
                if(i == j){
                    mundoPokemon[i][j] = 0;
                }else{
                    mundoPokemon[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        for (int i = 0; i < numCiudades; i++) {
            Towns.add(new Ciudad(br.readLine(),i));
        }
        
        int Edges = Integer.valueOf(br.readLine());
        for (int i = 0; i < Edges; i++){
            int n = 0, m = 0;
            String aux = br.readLine();
            String[] line;
            line = aux.split(" ", 3);
            for (int j = 0; j < numCiudades; j++) {
                if(line[0].equals(Towns.get(j).name)){
                    n = Towns.get(j).number;
                }
            }
            for (int j = 0; j < numCiudades; j++) {
                if(line[1].equals(Towns.get(j).name)){
                    m = Towns.get(j).number;
                }
            }
            mundoPokemon[n][m] = Integer.valueOf(line[2]);
            mundoPokemon[m][n] = Integer.valueOf(line[2]);
        }
       
        int camino = Integer.valueOf(br.readLine());
        int[] mainPath = new int[camino];
        for (int i = 0; i < camino; i++) {
            String aux = br.readLine();
            for (int j = 0; j < numCiudades; j++) {
                if(aux.equals(Towns.get(j).name)){
                    mainPath[i] = Towns.get(j).number;
                }
            }
        }
        FloydWarshall(mundoPokemon, Towns, mainPath);
    }
}
