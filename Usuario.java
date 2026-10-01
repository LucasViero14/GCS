public class Usuario{
    private String nome;
    private String cpf;
    private String iniciais;
    private int id;

    public Usuario(String nome, String cpf, String iniciais, int id){
        this.nome = nome;
        this.cpf = cpf;
        this.iniciais = iniciais;
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public String getCPF(){
        return cpf;
    }

    public String getIniciais(){
        return iniciais;
    }
}