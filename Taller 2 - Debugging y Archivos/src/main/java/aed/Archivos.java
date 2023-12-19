package aed;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.PrintStream;

class Archivos {
    float[] leerVector(Scanner entrada, int largo) {
        float[] vec2= new float[largo];
        for(int i=0; i<largo;i++){
            vec2[i]=entrada.nextFloat();
        }
        return vec2;
    }

    float[][] leerMatriz(Scanner entrada, int filas, int columnas) {
        float[][] mat1= new float[filas][columnas];
            for(int i=0;i<filas;i++){
                for(int j=0;j<columnas;j++){
                    mat1[i][j]= entrada.nextFloat();
                }
            }
             return mat1;
        }
       

    void imprimirPiramide(PrintStream salida, int alto) {
        int espacios= alto-1;
        int cantAsteriscos=1;
        String linea="";
        for(int j=0;j<alto;j++){
            for(int i=0;i<espacios;i++){
                linea = linea + " ";
            }
            for(int i=0;i<cantAsteriscos;i++){
                linea = linea + "*";
            }
            for(int i=0;i<espacios;i++){
                linea = linea + " ";
            }
            espacios=espacios-1;
            cantAsteriscos=cantAsteriscos+2;
            salida.println(linea);
            linea="";
        }
        
    }}


/**
 * Quiero que de cada linea imprima los espacios, agregue el asterisco, e imprima los espacios;
 * Luego hacer un /n
 * repetir pero con espacios -1 y asteriscos +1.
 * hasta que espacios=0
             "hola" + " como estas" -> "hola como estas"
 linea = linea + " ";
*/
