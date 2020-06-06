public class Teste {
    public static void main(String[] args) {
        FilaObj<Integer> fila;

        fila = new FilaObj<>(3);

        fila.insert(5);
        fila.insert(4);
        fila.insert(3);
        fila.exibe();
        System.out.println("");
        System.out.println(fila.peek());
        fila.poll();
        System.out.println("");
        fila.exibe();
        System.out.println("");
        System.out.println(fila.peek());
    }
}
