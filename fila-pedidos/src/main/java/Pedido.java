public class Pedido {

    private int id;
    private String descricao, endereco;
    private Double preco;

    public Pedido(int id, String descricao, String endereco, Double preco) {
        this.id = id;
        this.descricao = descricao;
        this.endereco = endereco;
        this.preco = preco;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getEndereco() { return endereco; }

    public void setEndereco(String endereco) { this.endereco = endereco; }

    public Double getPreco() { return preco; }

    public void setPreco(Double preco) { this.preco = preco; }

    @Override
    public String toString() {
        return "ID DO PEDIDO: "+id+" | DESCRIÇÃO DO PEDIDO: "+descricao+" |  ENDEREÇO DE ENTREGA: "+endereco+" | "+
                " PREÇO TOTAL: "+preco;
    }
}
