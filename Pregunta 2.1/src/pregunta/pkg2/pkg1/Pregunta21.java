
package pregunta.pkg2.pkg1;

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

public class Pregunta21 {
    
    public static void Floyd_Warshall(int[][] weight,int[][] paths, ArrayList<Ciudad> Pueblos, int[] mainPath){
        int vNumber = weight.length;
        for (int k = 0; k < vNumber; ++k) {
            for (int i = 0; i < vNumber; ++i) {
                for (int j = 0; j < vNumber; ++j) {
                    if (weight[i][k] + weight[k][j] < weight[i][j] && weight[i][k] != Integer.MAX_VALUE && weight[k][j] != Integer.MAX_VALUE){
                        weight[i][j] = weight[i][k] + weight[k][j];
                        paths[i][j] = paths[k][j];
                    }
                }
            }
        }
        int pthVertex = mainPath.length;
        ArrayList<Integer> finalPath = new ArrayList<>();
        System.out.println("El camino mas corto en orden seria el siguiente:");
        for (int i = 0; i < pthVertex-1; i++) {
            ArrayList<Integer> auxPath = new ArrayList<>();
            int start = mainPath[i];
            int end = mainPath[i+1];
            for (int j = 0; j < Pueblos.size(); j++) {
                if(mainPath[i] == Pueblos.get(j).number){
                    finalPath.add(Pueblos.get(j).number);
                }
            }
            while(paths[start][end] != start){
                auxPath.add(paths[start][end]);
                end = paths[start][end];
            }
            for (int k = 0; k < auxPath.size(); k++) {
                finalPath.add(auxPath.get(auxPath.size()-1-k));
            }
        }
        for (int i = 0; i < finalPath.size(); i++) {
            for (int j = 0; j < Pueblos.size(); j++) {
                if(finalPath.get(i) == Pueblos.get(j).number){
                    System.out.print(Pueblos.get(j).name+"->");
                }
            }
        }
        for (int j = 0; j < Pueblos.size(); j++) {
            if(mainPath[pthVertex-1] == Pueblos.get(j).number){
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
        int[][] paths = new int[numCiudades][numCiudades];
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
        
        for (int i = 0; i < numCiudades; i++) {
            for (int j = 0; j < numCiudades; j++) {
                if(i == j){
                    paths[i][j] = 0;
                }else{
                    if(mundoPokemon[i][j] != Integer.MAX_VALUE){
                        paths[i][j] = i;
                    }else{
                        paths[i][j] = -1;
                    }
                }
            }
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
        Floyd_Warshall(mundoPokemon,paths, Towns, mainPath);
    }
}
