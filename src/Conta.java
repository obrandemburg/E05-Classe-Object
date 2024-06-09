public class Conta {

    private int numero;

    private Cliente dono;

    private double saldo;

    private double limite;

    private Operacao[] operacoes;

    private int proximaOperacao;

    private static int totalContas = 0;

    public Conta(int numero, Cliente dono, double saldo, double limite) {
        this.numero = numero;
        this.dono = dono;
        this.saldo = saldo;
        this.limite = limite;

        this.operacoes = new Operacao[10];
        this.proximaOperacao = 0;

        Conta.totalContas++;
    }

    private void redimensionarVetor() {
        Operacao[] newArray = new Operacao[this.operacoes.length * 2];
        for (int i = 0; i < this.operacoes.length; i++) {
            newArray[i] = this.operacoes[i];
        }
        this.operacoes = newArray;
    }

    private void adicionarOperacao(Operacao operacao) {
        if(this.proximaOperacao == this.operacoes.length) {
            redimensionarVetor();
        }
        this.operacoes[this.proximaOperacao] = operacao;
        this.proximaOperacao++;
    }

    public boolean sacar(double valor) {
        if (valor >= 0 && valor <= this.limite) {
            this.saldo -= valor;

            this.adicionarOperacao(new OperacaoSaque(valor));
            return true;
        }

        return false;
    }

    public void depositar(double valor) {
        this.saldo += valor;
        this.adicionarOperacao(new OperacaoDeposito(valor));
    }

    public boolean transferir(Conta destino, double valor) {
        boolean valorSacado = this.sacar(valor);
        if (valorSacado) {
            destino.depositar(valor);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String saida = "===== Conta " + this.numero + " =====\n" +
                "Dono: " + this.dono.nome + "\n" +
                "Saldo: " + this.saldo + "\n" +
                "Limite: " + this.limite + "\n" +
                "====================";
        return saida;
    }

    @Override
    public boolean equals(Object obj) {

        return this.numero == ((Conta)obj).numero;
    }

    public void imprimirExtrato() {
        System.out.println("======= Extrato Conta " + this.numero + "======");
        for(Operacao atual : this.operacoes) {
            if (atual != null) {
                System.out.println(atual.toString());
            }
        }
        System.out.println("====================");
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getDono() {
        return dono;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }

    public static int getTotalContas() {
        return Conta.totalContas;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    public void setLimite(double limite) {
        if (limite < 0)
            limite = 0;

        this.limite = limite;
    }
}
