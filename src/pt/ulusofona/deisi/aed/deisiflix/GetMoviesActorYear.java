package pt.ulusofona.deisi.aed.deisiflix;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class GetMoviesActorYear {

//    Quais os filmes em que um
//    actor participou, num certo ano,
//    ordenados por data
//    decrescente?

//    O output devem ser linhas com
//    o seguinte formato
//    “<TítuloFilme> <Data>\n”.

//    A <Data> deve aparecer no
//    formato YYYY-MM-DD.

//    O último elemento não deve ter
//    o “\n”.

//    GET_MOVIES_ACTOR_YEAR Clark Gable 1955

    static QueryResult function (String interessa){

        long start, end, tempo;
        start = System.currentTimeMillis();

        ArrayList<Datas> outputs = new ArrayList<>();
        StringBuilder output = new StringBuilder();
        int caracterReverso = interessa.length() - 1;

        while (caracterReverso > 0 && interessa.charAt(caracterReverso) != ' ') {
            caracterReverso--;
        }

        String nome = interessa.substring(0, caracterReverso);
        String ano = interessa.substring(caracterReverso + 1);

        for (int countPeople = 0; countPeople < Main.arrayPeople1.size(); countPeople++) {

            Filme posicao = Main.arrayPeople1.get(countPeople);
            int idFilme = posicao.idFilme;
            String nomePessoa = posicao.atores;

            if(Objects.equals(nomePessoa, nome)){
                for (int count = 0; count < Main.arrayMovies1.size(); count++){
                    Filme arrayIdfilme = Main.arrayMovies1.get(count);
                    int idFilmeMovies = arrayIdfilme.idFilme;
                    String data = arrayIdfilme.dataDeLancamento;

                    String anoFilme= data.substring(6);

                    if(idFilme == idFilmeMovies){
                        if(ano.equals(anoFilme)){

                            String titulo = arrayIdfilme.titulo;
                            String[] dados = data.split("-");
                            String dataVirada = dados[2] + "-" + dados[1] + "-" + dados[0];

                            outputs.add(new Datas(titulo, dataVirada));
                            //System.out.println(titulo + " (" + dataVirada + ")");
                        }
                    }
                }
            }
        }

        outputs.sort(Comparator.comparing((Datas data1) -> data1.data));

        for (int count = outputs.size() - 1; count >= 0; count--){
            if(count == 0){
                output.append(outputs.get(count).titulo).append(" (");
                output.append(outputs.get(count).data).append(")");
            }else{
                output.append(outputs.get(count).titulo).append(" (");
                output.append(outputs.get(count).data).append(")").append("\n");
            }
        }

        String outputVerdadeiro = String.valueOf(output);
        end = System.currentTimeMillis();
        tempo = (end - start);

        return new QueryResult(outputVerdadeiro, tempo);
    }
}
