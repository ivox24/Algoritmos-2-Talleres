package aed;

class Funciones {
    int cuadrado(int x) {
        int res=x*x;
        return res;
    }

    double distancia(double x, double y) {
        double res= Math.sqrt(x*x+y*y);
        return res;
    }

    boolean esPar(int n) {
        if (n%2==0){
            return true;
        }
        return false;
    }

    boolean esBisiesto(int n) {
        boolean res= ((n%400==0)||(n%4==0 && n%100!=0));
        return res;
    }

    int factorialIterativo(int n) {
        int fact=1;
        if (n==0){
            return 1;
        }
        for(int i=1;i<=n;i++){
            fact=fact*i;
        }
        return fact;
    }

    int factorialRecursivo(int n) {
        if (n==0){
            return 1;
        }
        int fact= n*factorialRecursivo(n-1);
        return fact;
    }

    boolean esPrimo(int n) {
        if (n==0){
            return false;
        }
        if (n==1){
            return false;
        }
        for(int i=2;i<n;i++){
            if(n%i==0){
            return false;
        }
    }
        return true;
    }
    

    int sumatoria(int[] numeros) {
        int res=0;
        for(int i:numeros){
            res=res+i;
        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        int indice=-1;
        for(int i:numeros){
            indice+=1;
            if (i==buscado){
                return indice;
            }
        }
        return indice;
    }

    boolean tienePrimo(int[] numeros) {
        boolean res=false;
        for(int i:numeros){
            if(esPrimo(i)){
                res=true;
            }
        }
        return res;
    }

    boolean todosPares(int[] numeros) {
        boolean res=true;
        for(int i:numeros){
            if(!esPar(i)){
                return false;
            }
        }
        return res;
    }

    boolean esPrefijo(String s1, String s2) {
        boolean res=true;
        if(s1.length()>s2.length()){
            return false;
        } else{
            for (int i=0;i<s1.length();i++){
            if(s1.charAt(i)!=s2.charAt(i)){
                return false;
            }
        }
        }
        
        return res;
    }

    boolean esSufijo(String s1, String s2) {
     boolean res=true;
        if(s1.length()>s2.length()){
            return false;
        } else{
            for (int i=s1.length()-1;0<=i;i--){
            if(s1.charAt(i)!=s2.charAt(i+(s2.length()-s1.length()))){
                return false;
            }
        }
        }
        return res;
    }}

