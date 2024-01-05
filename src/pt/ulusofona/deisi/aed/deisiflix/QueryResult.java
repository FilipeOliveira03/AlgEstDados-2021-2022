package pt.ulusofona.deisi.aed.deisiflix;

public class QueryResult {
    String valor;
    long tempo;

    QueryResult(){
    }

    QueryResult(String valor, long tempo){
        this.valor = valor;
        this.tempo = tempo;
    }
}
