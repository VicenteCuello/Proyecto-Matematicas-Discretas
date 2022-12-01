#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <stdbool.h>

typedef struct{
    int numeroCiudad;
    char nombre[3];
}pokeCiudad;

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

    fclose(f);
    system("pause");
    free(mundopokemon);
    return 0;
}