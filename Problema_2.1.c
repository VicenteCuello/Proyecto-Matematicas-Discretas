#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <stdbool.h>

typedef struct{
    int numeroCiudad;
    char nombre[3];
}pokeCiudad;

int dijkstra(int **pokeMatriz, int n, int source, int target){
    int coste[n][n];
    for(int i=0; i< n; i++){
        for(int j=0; j<n;j++){
            if(pokeMatriz[i][j]==-1){
                coste[i][j]=INFINITY;
            }else{
                coste[i][j]=pokeMatriz[i][j];
            }
        }
    }
    int dist[n],prev[n];
    int selected[n];
    int i,m,min,start,d,j;
    char path[n];
    for(i=1;i< n;i++)
    {
        dist[i] = INFINITY;
        prev[i] = -1;
    }
    start = source;
    selected[start]=1;
    dist[start] = 0;
    while(selected[target] ==0){
        min = INFINITY;
        m = 0;
        for(i=1;i< n;i++){
            d = dist[start] +coste[start][i];
            if(d< dist[i]&&selected[i]==0)
            {
                dist[i] = d;
                prev[i] = start;
            }
            if(min>dist[i] && selected[i]==0)
            {
                min = dist[i];
                m = i;
            }
        }
        start = m;
        selected[start] = 1;
    }
    start = target;
    j = 0;
    while(start != -1)
    {
        path[j++] = start+65;
        start = prev[start];
    }
    path[j]='\0';
    strrev(path);
    printf("%s", path);
    return dist[target];

}


int main(){
int numCiudades=0;
FILE *f;
f = fopen("Bianca.txt", "r");
fscanf(f, "%d", &numCiudades);

pokeCiudad *puebloLavanda = malloc(sizeof(pokeCiudad) * numCiudades);
for (int i = 0; i < numCiudades; i++){
	fscanf(f, "%s", puebloLavanda[i].nombre);
	puebloLavanda[i].numeroCiudad = i;
}

int **mundopokemon=malloc(sizeof(int*)*numCiudades);
for(int i=0; i< numCiudades; i++){
    mundopokemon[i]=malloc(sizeof(int)*numCiudades);
    for(int j=0; j<numCiudades;j++){
        if(i==j){
            mundopokemon[i][j]=0;
        }else{
            mundopokemon[i][j]=-1;
        }
        
    }
}
int numRecorridos = 0;
fscanf(f,"%d",&numRecorridos);
for(int i=0; i<numRecorridos; i++){
    int n;
    int m;
    int iv;
    char MT[3];
    char MO[3];
    fscanf(f,"%s %s %d",&MT,&MO,&iv);
    printf("%s %s %d\n",MT,MO,iv);
    bool lector = false;
    while(lector == false){
        for (int i = 0; i < numCiudades; i++){
            if(strcmp(MT, puebloLavanda[i].nombre) == 0){
                n = puebloLavanda[i].numeroCiudad;
                lector = true;
            }
        }
        for (int i = 0; i < numCiudades; i++){
            if(strcmp(MO, puebloLavanda[i].nombre) == 0){
                m = puebloLavanda[i].numeroCiudad;
                lector = true;
            }
        }
        mundopokemon[n][m]= iv;
    }
}
for(int i=0;i<numCiudades;i++){
    for(int j=0;j<numCiudades;j++){
        if(i != j || mundopokemon[i][j] != -1){
            mundopokemon[j][i] = mundopokemon[i][j];
        }
    }
}

int co = dijkstra(mundopokemon,numCiudades,0,19);
printf("\nThe Shortest Path: %d",co);

    fclose(f);
    system("pause");
    free(mundopokemon);
    return 0;
}
