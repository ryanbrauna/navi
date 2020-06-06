import java.util.Scanner;

public class PedidosFila {

    public static void main(String[] args) {

        FilaObj<Pedido> filaPedidos = new FilaObj<>(4);

        System.out.println("");
        filaPedidos.insert(new Pedido(1, "Dois sacos de cimento", "Rua Anavilhana, 28, Parque Edu Chaves, São Paulo", 35.00 ));
        filaPedidos.insert(new Pedido(2, "50 azulejos azuis", "Rua Jorge Macedo, 35, Jardim Brasil, São Paulo", 124.45));
        filaPedidos.insert(new Pedido(3, "70 tijolos baianos", "Rua Padre Quevedo, 57, Jardim Continental, Guarulhos", 40.00 ));
        filaPedidos.exibe();
        System.out.println("");
        System.out.println("Preparando: "+filaPedidos.peek());
        System.out.println("");
        System.out.println("Pedido saindo para entrega: "+filaPedidos.poll());
        System.out.println("");
        System.out.println("Pedidos na fila: ");
        filaPedidos.exibe();
        System.out.println("");
        System.out.println("Adicionando novo pedido na fila...");
        filaPedidos.insert(new Pedido(4, "70kg de areia", "Rua Barao de Maua, 79, Pimentas, Guarulhos", 580.97));
        System.out.println("");
        filaPedidos.exibe();


    }

}
