package pt.ulusofona.deisi.aed.deisiflix;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class Top10BestMoviesInCertainYear {

    static QueryResult function (String interessa) {

        long start, end, tempo;
        start = System.currentTimeMillis();

        StringBuilder resultado = new StringBuilder();

        ArrayList<VotosNome> array = new ArrayList<>();
        for (int countMovies = 0; countMovies < Main.arrayMovies1.size() - 1; countMovies++) {

            Filme posicao = Main.arrayMovies1.get(countMovies);
            int idFilme = posicao.idFilme;
            String data = posicao.dataDeLancamento;
            String nomeFilme = posicao.titulo;
            String ano = data.substring(6);

            if(Objects.equals(ano, interessa)){
                ArrayList<Filme> pos2 = Main.arrayMovieVotes2.get(Integer.toString(idFilme));
                for (int countVotes = 0; countVotes < pos2.size(); countVotes++) {
                    Filme pos3 = pos2.get(countVotes);
                    double ratio = pos3.mediaDeVotos;
                    array.add(new VotosNome(ratio, nomeFilme));

                }
            }
        }



        if(array.isEmpty()){
            resultado.append("Não existe");
        }else{
            array.sort(Comparator.comparingDouble((VotosNome votes) -> votes.ratio).reversed());

            for (int count = 0; count < 10; count++) {
                if(count == 9){
                    String nome = array.get(count).nome;
                    Double voto = array.get(count).ratio;
                    resultado.append(nome).append(":").append(voto);

                }else{
                    String nome = array.get(count).nome;
                    Double voto = array.get(count).ratio;
                    resultado.append(nome).append(":").append(voto).append("\n");

                }
            }
        }


        
        end = System.currentTimeMillis();
        tempo = (end - start);

        return new QueryResult(resultado.toString(), tempo);
    }
}
