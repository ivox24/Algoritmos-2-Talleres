package aed;



public class Heap{
        
    private Router[] heap; // array que almacena el heap
    private int size; // tamaño actual del heap
    private int capacidad; // Capacidad máxima del heap

    // public Heap (int capacidad){ //Constructor de Heap
    //     this.capacidad = capacidad; // Asignar la capacidad
    //     this.size = 0; // Inicializar el tamaño
    //     this.heap = new int[capacidad]; // Crear el arreglo           
    // }

    public Heap (Router[] router){ //Constructor de Heap que recibe listas
        this.capacidad = router.length; // Asignar la capacidad
        this.size = 0; // Inicializar el tamaño
        this.heap = new Router[capacidad];// Crear el arreglo 

        
        for (int i = 0; i < router.length; i++) {
            heap[i] = router[i]; 
            // heap[i] = router[i].getTrafico(); DUDOSO, ME ESTOY GUARDANDO SOLO EL TRAFICO Y NO EL ID
        }

        heapificar(heap);
    }
// encolar es n.log n
// floyd es n
    public int obtenerLargo() {
        return size;
    }

    private int padre(int i) {
        return (i - 1) / 2;
    }

    private int hijoIzq(int i) {
        return 2 * i + 1;
    }

    private int hijoDer(int i) {
        return 2 * i + 2;
    }

    private void swap(int indice, int padre) {
        Router temp = heap[indice];
        heap[indice] = heap[padre];
        heap[padre] = temp;
    }
    

    private void heapifyUp(int indice) { // Cada vez que encolemos algo al final queremos que si es mayor vaya subiendo.
    while (indice > 0 && heap[indice].compareTo(heap[padre(indice)]) >0) {
        swap(indice, padre(indice));
        indice = padre(indice);
    }
    }

//     private void heapifyDown(int indice) { // Despues de desencolar la raiz tenemos que rebalancear el arbol
//         while (true) {
//             int izq = hijoIzq(indice);
//             int der = hijoDer(indice);
//             int padre = indice;

//             if (izq < size && (heap[izq].compareTo(heap[padre]))>0) {
//                 padre = izq;
//             }
// //heap[der] > heap[padre]
//             if (der < size && (heap[der].compareTo(heap[padre]))>0) {
//                 padre = der;
//             }

//             if (padre != indice) { //Si se hicieron cambios de indice, hacer el swap
//                 swap(indice, padre);
//                 indice = padre;

//             } else { 
//                 break; // Cuando ya este ordenado va a parar
//             }
//         }
//     }
private void heapifyDown(int indice) {
    while (true) {
        int izq = hijoIzq(indice);
        int der = hijoDer(indice);
        int padre = indice;

        if (izq < size && heap[izq].compareTo(heap[padre]) > 0) {
            padre = izq;
        }

        if (der < size && heap[der].compareTo(heap[padre]) > 0) {
            padre = der;
        }

        if (padre != indice) {
            swap(indice, padre);
            indice = padre;
        } else {
            break;
        }
    }
}


    private void heapificar(Router[] array){
        size = array.length;
        int inicio= (size / 2) - 1;
        while(0 <= inicio){
            heapifyDown(inicio);
            inicio--;
        }
    }
    

    public void encolar(Router elem) {
        if(size < capacidad - 1){
            size++;
            heap[size] = elem; //el indice es el tamaño actual
            heapifyUp(size);
        }
        else {
            System.out.println("El heap está lleno. No se puede encolar más elementos.");
            return;
        }
    } 


    public Router desencolar() {
        if(0 < size){
            Router max = heap[0];
            heap[0] = heap[size -1]; //subimos el ultimo
            size--;
            heapifyDown(0);
            return max;
        }
        else { //if (size == 0)
        System.out.println("El heap está vacío. No se puede desencolar.");
                    return null;
        }
    }
}
