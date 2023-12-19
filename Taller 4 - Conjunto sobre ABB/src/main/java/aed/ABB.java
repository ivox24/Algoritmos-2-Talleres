package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto
    private Nodo raiz;
    //private Nodo nodoActual;
    private int cantElementos=0;

    

    private class Nodo {
        // Agregar atributos privados del Nodo
        T valor;
        Nodo izq;
        Nodo der;
        Nodo padre;


        // Crear Constructor del nodo
        public Nodo(T v) {
            this.valor = v;
            this.izq = null;
            this.der = null;
            this.padre = null;
            }
            //No es necesario hacer funciones tipo obtenerActual, devolverIzq...



    }

    public ABB() {
        raiz=null;
    }

    public int cardinal() {
        return cantElementos;
    }

    public T minimo(){
        if(raiz==null){
            return null;
        }
        Nodo nodoActual = raiz;
        T min=nodoActual.valor;
        if(cardinal()==1){
            return min;
        } else{
            while(nodoActual!=null){ 
                // elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
                  // Bajar a la izq 
                    if(nodoActual.izq ==null){
                            return min;
                        } else{
                            nodoActual=nodoActual.izq;
                            min= nodoActual.valor;
                        }
            }
            return min;

        }
    }

    public T maximo(){
        if(raiz==null){
            return null;
        }
        Nodo nodoActual = raiz;
        T max=nodoActual.valor;
        if(cardinal()==1){
            return max;
        } else{
            while(nodoActual!=null){ 
                // elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
                  // Bajar a la izq 
                    if(nodoActual.der ==null){
                            return max;
                        } else{
                            nodoActual=nodoActual.der;
                            max= nodoActual.valor;
                        }
            }
            return max;

        }
    }

    public void insertar(T elem){
        if (!pertenece(elem)) {
            Nodo nuevo = new Nodo(elem);
            if (cardinal()==0){
                raiz=nuevo; // El constructor directamente asigna elem como valor.
            } else{
                Nodo nodoActual = raiz;
                while(nodoActual.valor != elem){
    // elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
                    if(nodoActual.valor.compareTo(elem)>0){ //Bajar a la izq
                        if(nodoActual.izq ==null){
                            nodoActual.izq = nuevo;
                            nuevo.padre=nodoActual;
                        }
                        nodoActual=nodoActual.izq; 

                    } else{ //Bajar a la derecha
                        if(nodoActual.der==null){
                            nodoActual.der= nuevo;
                            nuevo.padre=nodoActual;//Lo conecta con el padre, lo une al abb.
                        }
                            nodoActual=nodoActual.der;
                        }
                    }

                    }
                    cantElementos=cantElementos+1;
                }
            }

    
     //Agrego el 5. 8>5, nodoActual es 3. 3>5,no,. nodoActual es 6. 6>5, si,
    // nodoActual es 4. 4>5,no, agregar el 5 a la derecha.(si nodoActual.der==null; then nodoActual.der=5) ...nodoActual es 5 (pero noestá)

    public boolean pertenece(T elem){
        if (cardinal()==0){
            return false;
        } else{
            Nodo actual= raiz;
            while( actual != null){
                if(actual.valor.compareTo(elem)==0){ 
//ANTES HABÍA PUESTO actual.valor==elem. PERO ESTABA MAL. PQ ESTO COMPARA REFERENCIAS EN MEMORIA, NO VALORES REALES.
        //SI QUIERO COMPARAR VALORES HAY QUE USAR COMPARE TO.
                    return true;
                } else{ // elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
                    if(actual.valor.compareTo(elem)>0){
                        actual=actual.izq;
                    } else{
                        actual=actual.der;
                    }
                }
            }
        }
        return false;

    }

    // public void eliminar(T elem){
    //     if(!pertenece(elem)){
    //         Nodo actual=raiz; //no tengo que decir que actual.valor = raiz. porque estoy diciendo que todo el nodo actual es raiz
    //         Nodo reemplazar=raiz;

    //         while(actual.izq.valor != elem || actual.der.valor !=elem){
    //             if(actual.valor.compareTo(elem)>0){ //Bajar a la izq
    //                 actual=actual.izq;
    //             }else{ //Sino, bajar a la derecha
    //                 actual=actual.der;
    //             }
    //         }
    //         if(actual.izq.valor==elem){
    //             actual=actual.izq;
    //         } else{
    //             actual=actual.der;
    //         }

    //         if(actual.valor==elem){ //Buscar el mínimo del derecho
    //             reemplazar=actual; //me guardo el nodo a reemplazar
    //             //Caso 1, no tiene hijos.
    //             if(actual.der==null && actual.izq==null){
    //                 reemplazar=null;
    //                 actual=null;
    //             } 
    //             //Caso 2, solo hijo/s, a la izq:
    //             if(actual.der==null && actual.izq !=null){
    //                 actual.izq.padre=actual.padre;
    //                 reemplazar= actual.izq;
    //                 actual=null;
    //             }
    //             //Caso 3, 2 hijos (lo mismo que tener solo hijo/, a la der)
    //             actual=actual.der;
    //             while(actual.izq!= null){
    //                 actual= actual.izq; //el actual es el mínimo del hijo derecho
    //             }
    //             reemplazar=actual; //ya reemplacé el que quería
    //             //¿Ahora qué hago con el actual? conecto el padre con el hijo (si tiene), y éste pasa a ser null
    //             if (actual.der!=null){
    //                 actual.der.padre=actual.padre;
    //                 actual=null;
    //             } else{
    //                 actual=null;
    //             }
    //             }
    //         }
    //         cantElementos--;
    //     }

    
    public void eliminar(T elem) {
        if (!pertenece(elem)) {
            return; // El elemento no existe en el árbol, no se puede eliminar.
            //Lo que está haciendo es salir de la función inmediatamente sin realizar ninguna otra operación dentro de esa función.
        }
        
        Nodo nodoAEliminar = buscarNodo(raiz, elem);
        
        if (nodoAEliminar != null) {
            eliminarNodo(nodoAEliminar);
            cantElementos--;
        }
    }
    
    private Nodo buscarNodo(Nodo nodo, T elem) {
        if (nodo == null) {
            return null;
        }
        
        int comparacion = elem.compareTo(nodo.valor);
        
        if (comparacion < 0) {
            return buscarNodo(nodo.izq, elem);
        } else if (comparacion > 0) {
            return buscarNodo(nodo.der, elem);
        } else {
            return nodo;
        }
    }
    
    private void eliminarNodo(Nodo nodo) {
        if (nodo.izq == null) {
            reemplazarNodo(nodo, nodo.der);
        } else if (nodo.der == null) {
            reemplazarNodo(nodo, nodo.izq);
        } else {
            Nodo sucesor = encontrarMinimo(nodo.der);
            nodo.valor = sucesor.valor;
            eliminarNodo(sucesor);
        }
    }
    
    private Nodo encontrarMinimo(Nodo nodo) {
        if (nodo.izq == null) {
            return nodo;
        }
        
        return encontrarMinimo(nodo.izq);
    }
    
    private void reemplazarNodo(Nodo nodoViejo, Nodo nodoNuevo) {
        if (nodoViejo.padre == null) {
            raiz = nodoNuevo;
        } else if (nodoViejo == nodoViejo.padre.izq) {
            nodoViejo.padre.izq = nodoNuevo;
        } else {
            nodoViejo.padre.der = nodoNuevo;
        }
        
        if (nodoNuevo != null) {
            nodoNuevo.padre = nodoViejo.padre;
        }
    }
    

    public Nodo minimoNodo(){
            Nodo actual= raiz;
            if(raiz==null){
            return null;
            }
            Nodo min = actual;
            while(min.izq != null){
                min=min.izq;
            }
            return min;
        }

    @Override
    public String toString() {
        String s = "{";
        if (cardinal() == 0) {
            return "{}";
        } else {
            Nodo actual = minimoNodo();
            boolean primerElemento = true; // Para ver si es el primer elemento
    
            while (actual != null) {
                if (!primerElemento) {
                    s += ",";
                } else {
                    primerElemento = false;
                }
    
                s += actual.valor;
    
                if (actual.der != null) {
                    actual = minimoNodo(actual.der);
                } else {
                    while (actual != null) {
                        Nodo padre = actual.padre;
                        if (padre == null) {
                            actual = null; // Llegamos a la raíz y no hay siguiente
                        } else if (padre.izq == actual) {
                            actual = padre;
                            break;
                        } else {
                            actual = padre;
                        }
                    }
                }
            }
        }
        s += "}";
        return s;
    }

    
    private Nodo minimoNodo(Nodo nodo) {
            while (nodo.izq != null) {
                nodo = nodo.izq;
            }
            return nodo;
        }

// public String toString() {
        // if (cardinal()==0){
        //     return "{}";
        // } else{
        //     Nodo actual = minimoNodo();
        //     Nodo sucesor= actual;
        //     if (actual.der!=null){ //Si tiene subarbol derecho, su sucesor es el minimo de su subarbol derecho
        //         sucesor=actual.der;
        //         while(sucesor.izq != null){ //minimo del subarbol derecho
        //             sucesor=sucesor.izq;
        //         }
        //         s=s+sucesor;
        //     } else{ //Si no tiene subarbol derecho, el sucesor es el máximo de un subarbol S, tal que n.izq=S
        //         while(sucesor.padre !=null){
        //            sucesor= sucesor.padre;
        //         }
        //         s=s+sucesor;
        //          }
        //     s=s+"}";
        //     return s;
        //     }
    //}

    
    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public ABB_Iterador(){
            // Inicializar _actual
            _actual= minimoNodo();
        }

        public boolean haySiguiente() {
            return _actual != null;      
            }

        public Nodo minimoNodo(){
            Nodo min = raiz;
            if(raiz==null){
            return null;
            }
            
            while(min.izq != null){
                min=min.izq;
            }
            return min;
        }

        public Nodo maximoNodo(Nodo raiz){ //Devuelvo el maximo nodo para despues ver si es 7.
            Nodo max= raiz;
            while(max.der !=null){ 
                max=max.der;
            }
            return max;
        }
// Otra forma de hacerlo es ver si es un hijo izquierdo. cuando ya llegaste a uno que es hijo izquierdo, el padre es el sucesor.
        
        public T siguiente() {
            // _actual = minimoNodo(); 4 5
            if(!haySiguiente()){
                return null;
            }
            T valor = _actual.valor;
            if (_actual.der != null) {
                // Si el nodo actual tiene un subárbol derecho, el siguiente será el mínimo de ese subárbol derecho
                _actual = minimoNodo(_actual.der);
            } else {
                // Si no tiene subárbol derecho, debemos retroceder para encontrar el siguiente nodo
                while (_actual != null) {
                    Nodo padre = _actual.padre;
                    if (padre == null) {
                        _actual = null; // Llegamos a la raíz y no hay siguiente
                    } else if (padre.izq == _actual) {
                        // Si el actual es el hijo izquierdo de su padre, el siguiente es el padre
                        _actual = padre;
                        break;
                    } else {
                        _actual = padre; // Retrocedemos al padre
                    }
                }
            }
    
            return valor;
        }

        private Nodo minimoNodo(Nodo nodo) {
            while (nodo.izq != null) {
                nodo = nodo.izq;
            }
            return nodo;
        }
            // T valor = _actual.valor;
            // Nodo sucesor= _actual;
            // if (_actual.der!=null){ //Si tiene subarbol derecho, su sucesor es el minimo de su subarbol derecho
            //     sucesor=_actual.der;
            //     while(sucesor.izq != null){ //minimo del subarbol derecho
            //         sucesor=sucesor.izq;
            //     }
            //     _actual=sucesor;

            // } else{ //Si no tiene subarbol derecho, el sucesor es el máximo de un subarbol S, tal que n.izq=S
            //     sucesor = sucesor.padre;
            //     if (sucesor !=null){    // Caso no estoy en la raiz y tengo para recorrer
            //         Nodo max_izq = maximoNodo(sucesor.izq);
            //             while(max_izq.valor.compareTo(valor)!= 0 && sucesor !=null && max_izq != null){
            //                 sucesor= sucesor.padre;
            //                 if(sucesor.izq != null){
            //                     max_izq=maximoNodo(sucesor.izq);
                                
            //                 } else{
            //                     sucesor=sucesor.padre;
            //                 }
            //                 }
            //         }
            //     }
            //     return valor;
            //     }
            

    }
    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }
}

