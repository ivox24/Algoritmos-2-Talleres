package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    // Completar atributos privados
    private int largo=0;
    private Nodo nodoActual;
    private Nodo primerNodo;
    private Nodo ultimoNodo;


    private class Nodo {
        // Completar
        private T dato;
        private Nodo siguiente;
        private Nodo anterior;

        //Esta parte es como cuando creás un TAD
        //Es el constructor porque tiene el mismo nombre que la clase
        public Nodo (T dato){
            this.dato=dato;
            this.siguiente=null; //en el momento de la creación de un nodo, generalmente no se sabe cuál será su nodo anterior o siguiente
            this.anterior=null; // lo mismo
        } //this se usa para indicar que estás accediendo a las variables de instancia (observadores) de la clase  y no a las variables locales del constructor.

        public T obtenerDato(){
            return dato;
        }
        public Nodo nodoSiguiente(){
            return siguiente;
        }
        public Nodo nodoAnterior(){
            return anterior;
        }
        public void establecerSiguiente(Nodo siguiente){
            this.siguiente= siguiente; //establece la referencia del nodo siguiente 
        }
        public void establecerAnterior(Nodo anterior){
            this.anterior= anterior;  //establece la referencia del nodo anterior 
        }

    }

    public ListaEnlazada(T dato) {
        Nodo nodo = new Nodo(dato);
        nodoActual= nodo;
        largo=1;
        primerNodo=nodoActual;
        ultimoNodo=nodoActual;
    }

    public int longitud() {
        return largo;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo= new Nodo(elem);
        if (longitud()==0){
            nodoActual= nuevo;
            primerNodo= nuevo;
            ultimoNodo= nuevo;
        } else{ 
            if(nodoActual !=null){
            nuevo.establecerSiguiente(primerNodo);
            primerNodo= nuevo;
        }
        }
        largo=largo+1;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo= new Nodo(elem);
        if (longitud()==0){
            nodoActual= nuevo;
            primerNodo= nuevo;
            ultimoNodo= nuevo;
        } else{
            if (nodoActual!=null){
                ultimoNodo.establecerSiguiente(nuevo);
                nuevo.establecerAnterior(ultimoNodo);
                ultimoNodo=nuevo;
            }
        }
        largo=largo+1;
    }

    public T obtener(int i) {
        nodoActual= primerNodo;
        for(int j=0; j<i ; j++){
            nodoActual=nodoActual.nodoSiguiente();
        }
            if(nodoActual != null){
              return nodoActual.obtenerDato();
            } else{
                return null;
            }
        }

    public void eliminar(int i) {
        Nodo previo= primerNodo;
        Nodo nodoActual = primerNodo; //Antes escribí actual, pero tenía que referirme a nodoActual; pq sino después nodoActual se convierte en Null, y le haces nodoSiguiente a un null, no se puede.
//actual apunta al nodo que deseas eliminar.
//Para hacer esto tengo que saltear al nodo que quiero eliminar
        for (int j=0;j<i;j++){
            previo= nodoActual;
            nodoActual= nodoActual.nodoSiguiente();
            //salgo de acá con actual apuntando al que quiero eliminar.
            //Si tenia 1 2 3 4 5, y quiero eliminar el 3, previo es 2, actual es 3
        }
        if (i==0){
            primerNodo=nodoActual.nodoSiguiente();
        } else{ //no podía hacer previo.nodoSiguiente() = actual.nodoSiguiente();
            previo.establecerSiguiente(nodoActual.nodoSiguiente());
        } //previo ahora apunta a 4, es decir, el siguiente de previo ahora es 4, salteándonos el 3, como queríamos.
        largo=largo-1;
        nodoActual= primerNodo;
    }

    public void modificarPosicion(int indice, T elem) {
        nodoActual= primerNodo;
        int j=0;
        while(j<indice && nodoActual!= null){
            nodoActual=nodoActual.nodoSiguiente();
            j++;
        }
        nodoActual.dato=elem;
    }

    public ListaEnlazada<T> copiar(){
        ListaEnlazada<T> copia = new ListaEnlazada<T>();
        Nodo nodoActual = primerNodo;
        while (nodoActual != null) {
            copia.agregarAtras(nodoActual.obtenerDato());
            nodoActual = nodoActual.nodoSiguiente();
        }
        return copia;
    }
  
//ESTA LA CREE YO (para que no tire error la de arriba, ya que no había hasta el momento
//ningún constructor que no reciba argumentos (pero es necesaria):
    public ListaEnlazada(){
        largo = 0;
        nodoActual = null;
        primerNodo = null;
        ultimoNodo = null;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        //longitud = lista.longitud();
        if(lista.longitud()==0){
            primerNodo = null;
        }
        else {
            for(int i=0;i < lista.longitud() ; i++){
                agregarAtras(lista.obtener(i));
            }
        }
    }

    @Override
    public String toString() {
        Nodo nodoActual = primerNodo;
        String s ="[";
        while (nodoActual != null) {
            if(nodoActual.nodoSiguiente() != null){
                s= s + nodoActual.obtenerDato();
                s= s + ", ";
            } else{
                s= s+ nodoActual.obtenerDato();
                s= s + "]";
            }
            nodoActual= nodoActual.nodoSiguiente();
        }
        return s;
    }

    private class ListaIterador implements Iterador<T> {
    	// Completar atributos privados
        int dedito;
        ListaIterador(){
            dedito = 0;
        }

        public boolean haySiguiente() {
	        return dedito < largo;
        }
        
        public boolean hayAnterior() {
	        return dedito > 0;
        }

        public T siguiente() {
            int i = dedito;
            dedito = dedito + 1;
            return obtener(i);
        }
        

        public T anterior() {
            dedito = dedito -1;
            return obtener(dedito);
        }

        // public boolean haySiguiente() {
        //     return (nodoActual != null && nodoActual.nodoSiguiente() != null);
        // } //La paja es que para que no se rompa el siguiente tampoco tiene que ser null
        
        // public boolean hayAnterior() {
	    //     return (nodoActual != null && nodoActual.nodoAnterior() != null);
        // }

        // //Acá no es nodoActual=nodoActual.siguiente, sino que tengo que llamar a lo que esté en Nodo,
        // // nodoActual=nodoActual.nodoSiguiente, como con los observadores
        
        // public T siguiente() {
	    //     if (haySiguiente()){
        //         nodoActual=nodoActual.nodoSiguiente(); // Acá podría crear un Nodo siguienteNodo=nodoActual.nodoSiguiente();
        //         if(nodoActual!=null){
        //             return nodoActual.obtenerDato();// y acá no es return nodoActual, sino que nodoActual.obtenerDato
        //         }
        //     }
        //     return null;
        // }
        
        // public T anterior() {
	    //     if (hayAnterior()){
        //         nodoActual=nodoActual.nodoAnterior();// Acá podría crear un Nodo anteriorNodo=nodoActual.nodoSiguiente();
        //         if(nodoActual !=null){
        //             return nodoActual.obtenerDato();
        //         }
        //     }
        //     return null;
        // }
        
    }
   
//Creo una nueva instancia de la clase ListaIterador. Esto está bien, siempre y cuando ListaIterador sea una clase que implementa la interfaz Iterador<T>.
    public Iterador<T> iterador() {
        return new ListaIterador(); 
    }
//El iterador debe ser una instancia de una clase que implementa la interfaz Iterador<T>, y este caso, esa clase es ListaIterador.
//cuando llamas a return new ListaIterador();, estás creando una instancia de ListaIterador, que es lo que necesitas para tener un iterador válido para tu lista enlazada.
}
