import java.util.Date;

public class PessoaJuridica extends Cliente{

    String cnpj;
    int Numero_funcionarios;
    String setor;

    PessoaJuridica(String nome, String endereco, String cnpj, int Numero_funcionarios, String setor){
        super(nome, endereco, new Date());
        this.cnpj = cnpj;
        this.Numero_funcionarios = Numero_funcionarios;
        this.setor = setor;
    }
    @Override
    public String toString(){
        String Saida = super.toString() + " CNPJ: " + this.cnpj + " Numero de funcionários: "+ this.Numero_funcionarios +" Setor: "+ this.setor;

        return Saida;
    }
    @Override
    public boolean equals(Object obj){
        return this.cnpj == ((PessoaJuridica)obj).cnpj;
    }
}
