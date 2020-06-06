public class Fila {

    private int tamanho;
    private String[] fila;

    public Fila(int capacidade) {
        this.tamanho = 0;
        fila = new String[capacidade];
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        return tamanho == fila.length;
    }

    public void insert(String info) {
        if (isFull()) {
            System.out.println("Fila cheia");
            return;
        }
        fila[tamanho++] = info;
    }

    public String peek() {
        return fila[0];
    }

    public String poll() {
        String primeiro = peek();
        if (!isEmpty()) {
            for (int i = 0; i < tamanho - 1; i++) {
                fila[i] = fila[i + 1];
            }
            fila[tamanho - 1] = null;
            tamanho--;
        }
        return primeiro;
    }

    public void exibe() {
        if (isEmpty()) {
            System.out.println("Fila vazia");
        } else {
            for (int i = 0; i < tamanho - 1; i++) {
                System.out.println(fila[i]);
            }
        }
    }
}
