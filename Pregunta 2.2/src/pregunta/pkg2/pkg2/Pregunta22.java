
package pregunta.pkg2.pkg2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Graph {

// No. of vertices
private int V;

// Adjacency Lists
private LinkedList<Integer> adj[];
private int Time;

// Constructor
@SuppressWarnings("unchecked") Graph(int v)
{
V = v;
adj = new LinkedList[v];

for (int i = 0; i < v; ++i)
adj[i] = new LinkedList();

Time = 0;
}

// Function to add an edge into the graph
void addEdge(int v, int w) { adj[v].add(w); }
// A recursive function that finds and prints strongly
// connected components using DFS traversal
// u --> The vertex to be visited next
// disc[] --> Stores discovery times of visited vertices
// low[] -- >> earliest visited vertex (the vertex with
//             minimum discovery time) that can be
//             reached from subtree rooted with current
//             vertex
// st -- >> To store all the connected ancestors (could
// be part
//         of SCC)
// stackMember[] --> bit/index array for faster check
//                   whether a node is in stack
void SCCUtil(int u, int low[], int disc[],
boolean stackMember[], Stack<Integer> st)
{

// Initialize discovery time and low value
disc[u] = Time;
low[u] = Time;
Time += 1;
stackMember[u] = true;
st.push(u);
int n;
// Go through all vertices adjacent to this
Iterator<Integer> i = adj[u].iterator();
while (i.hasNext()) {
n = i.next();

if (disc[n] == -1) {
SCCUtil(n, low, disc, stackMember, st);

// Check if the subtree rooted with v
// has a connection to one of the
// ancestors of u
// Case 1 (per above discussion on
// Disc and Low value)
low[u] = Math.min(low[u], low[n]);
}
else if (stackMember[n] == true) {

// Update low value of 'u' only if 'v' is
// still in stack (i.e. it's a back edge,
// not cross edge).
// Case 2 (per above discussion on Disc
// and Low value)
low[u] = Math.min(low[u], disc[n]);
}
}
// head node found, pop the stack and print an SCC
// To store stack extracted vertices
int w = -1;
if (low[u] == disc[u]) {
while (w != u) {
w = (int)st.pop();
System.out.print(w + " ");
stackMember[w] = false;
}
System.out.println();
}
}

// The function to do DFS traversal.
// It uses SCCUtil()
void SCC()
{
// Mark all the vertices as not visited
// and Initialize parent and visited,
// and ap(articulation point) arrays
int disc[] = new int[V];
int low[] = new int[V];
for (int i = 0; i < V; i++) {
disc[i] = -1;
low[i] = -1;
}
boolean stackMember[] = new boolean[V];
Stack<Integer> st = new Stack<Integer>();
for (int i = 0; i < V; i++) {
if (disc[i] == -1)
SCCUtil(i, low, disc, stackMember, st);
}
}
}



public class Pregunta22 {
    
    public static class trainer{
        String name;
        int id;
        ArrayList<String> contacts;
        boolean confianza;
        public trainer(){
            contacts = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String name = "src\\pregunta\\pkg2\\pkg2\\ProfesoraEncina.txt";
        File archivo = new File(name);
        FileReader fr = new FileReader (archivo);
        BufferedReader br = new BufferedReader(fr);
        int numTrainers = Integer.valueOf(br.readLine());
        Graph a = new Graph(numTrainers);
        ArrayList<trainer> Trainers = new ArrayList<>();
        
        for (int i = 0; i < numTrainers; i++) {
            trainer auxT = new trainer();
            String aux = br.readLine();
            String[] line;
            line = aux.split(" ", 3);
            auxT.name = line[0];
            String[] dates;
            auxT.id = i;
            int nContacts = Integer.valueOf(line[1]);
            dates = line[2].split(" ",nContacts+1);
            int total = dates.length;
            for (int j = 0; j < total - 1; j++) {
                auxT.contacts.add(dates[j]);
            }
            if("yes".equals(dates[total-1])){
                auxT.confianza = true;
            }else{
                auxT.confianza = false;
            }
            Trainers.add(auxT);
        }
        
        for (int i = 0; i < numTrainers; i++) {
            for (int j = 0; j < Trainers.get(i).contacts.size(); j++) {
                int n = 0;
                boolean careful = false;
                for (int k = 0; k < numTrainers; k++) {
                    if(Trainers.get(k).name.equals(Trainers.get(i).contacts.get(j))){
                        n = Trainers.get(k).id;
                        if(Trainers.get(k).confianza == true && Trainers.get(i).confianza == true){
                            careful = true;
                        }
                    }
                }
                a.addEdge(Trainers.get(j).id, n);
                if(careful == true){
                    a.addEdge(n,Trainers.get(j).id);
                }
            }
        }
        a.SCC();
    }
}
