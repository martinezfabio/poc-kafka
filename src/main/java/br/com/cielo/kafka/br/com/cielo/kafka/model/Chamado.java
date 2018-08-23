package br.com.cielo.kafka.br.com.cielo.kafka.model;

public class Chamado {

    private Long idChamado;

    private String nome;

    private String situacaoChamado;

    public Chamado (){

    }

    public Chamado(Long idChamado, String nome, String situacaoChamado) {
        this.idChamado = idChamado;
        this.nome = nome;
        this.situacaoChamado = situacaoChamado;
    }

    public Long getIdChamado() {
        return idChamado;
    }

    public void setIdChamado(Long idChamado) {
        this.idChamado = idChamado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSituacaoChamado() {
        return situacaoChamado;
    }

    public void setSituacaoChamado(String situacaoChamado) {
        this.situacaoChamado = situacaoChamado;
    }

    @Override
    public String toString() {
        return "chamado{" +
                "id = " + idChamado +
                "name = '" + nome + "  -  " +
                "situacao = '" + situacaoChamado +
                '}';
    }

}