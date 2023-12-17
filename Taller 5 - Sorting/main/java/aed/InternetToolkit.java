package aed;


public class InternetToolkit {
    public InternetToolkit() {
    }

    public Fragment[] tcpReorder(Fragment[] fragments) {// Insertion Sort. Como está casi ordenado es el mejor.

            for(int i=0;i<fragments.length; i++){
                int j=i-1;
                Fragment elem= fragments[i];
                while(j>=0 && fragments[j].compareTo(elem)>0){
                    fragments[j+1]=fragments[j];
                    j=j-1;
                }
                fragments[j+1]= elem;
            }
        return fragments;
    }

    public Router[] kTopRouters(Router[] routers, int k, int umbral) { //O(n+k log n) K routers que superan el umbral. n routers de entrada
        Heap heapRouters = new Heap(routers);
        Router[] superanElUmbral = new Router[k];

        for(int i=0; i<k && heapRouters.obtenerLargo()>0; i++){
            Router routerActual = heapRouters.desencolar();

            if (routerActual.getTrafico() > umbral) {
                superanElUmbral[i] = routerActual;
            } 
        }

        return superanElUmbral;
    }

    public IPv4Address[] sortIPv4(String[] ipv4) {
        if (ipv4 == null || ipv4.length == 0) {
            return new IPv4Address[0];
        }

        IPv4Address[] address = new IPv4Address[ipv4.length];

        // Convertir direcciones IPv4 a objetos IPv4Address
        for (int i = 0; i < ipv4.length; i++) {
            address[i] = new IPv4Address(ipv4[i]);
        }

        // Ordenar direcciones IPv4 utilizando el algoritmo de ordenamiento de burbuja
        for (int i = 0; i < address.length - 1; i++) {
            for (int j = 0; j < address.length - i - 1; j++) {
                if (compareIPv4(address[j], address[j + 1]) > 0) {
                    // Intercambiar direcciones si están en el orden incorrecto
                    IPv4Address temp = address[j];
                    address[j] = address[j + 1];
                    address[j + 1] = temp;
                }
            }
        }

        return address;
    }
    
    private static int compareIPv4(IPv4Address a, IPv4Address b) {
        for (int i = 0; i < 4; i++) {
            int octetComparison = Integer.compare(a.getOctet(i), b.getOctet(i));
            if (octetComparison != 0) {
                return octetComparison;
            }
        }
        return 0; // Las direcciones son iguales
    }

}
