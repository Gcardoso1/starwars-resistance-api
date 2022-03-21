package br.com.cardoso.gabriel.starwarsapi.starwarsapi.model;

public enum ResourcesEnum {

    WEAPON("weapon", 4),
    BULLET("bullet", 3),
    WATER("water", 2),
    FOOD("food", 1);

    private final String descricao;
    private final int pontos;

    ResourcesEnum(String descricao, int pontos){
        this.descricao = descricao;
        this.pontos = pontos;
    }

    public String getDescricao(){ return descricao;}

    public int getPontos(){ return pontos;}
}
