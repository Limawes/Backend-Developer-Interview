package br.com.mobiauto.domain.enumerator;

public enum OportunidadeStatus {
    NOVO("novo","nova oportunidade"),
    EM_ATENDIMENTO("em atendimento","oportunidade em atendimento"),
    CONCLUIDO("concluido","oportunidade concluída");

    String name;
    String label;

    OportunidadeStatus(String name, String label) {
        this.name = name;
        this.label = label;
    }
}
