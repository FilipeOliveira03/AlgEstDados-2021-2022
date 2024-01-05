package pt.ulusofona.deisi.aed.deisiflix;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;


public class GetTopNMoviesRatio {

    static QueryResult function (String interessa) {

        long start, end, tempo;
        start = System.currentTimeMillis();

        StringBuilder resultado = new StringBuilder();
        int caracterReverso = interessa.length() - 1;

        while (caracterReverso > 0 && interessa.charAt(caracterReverso) != ' ') {
            caracterReverso--;
        }

        String numero = interessa.substring(0, caracterReverso);
        String ano = interessa.substring(caracterReverso + 1);

        ArrayList<VotosNome> array = new ArrayList<>();

        for (int countMovies = 0; countMovies < Main.arrayMovies1.size(); countMovies++) {
            Filme pos = Main.arrayMovies1.get(countMovies);
            String data = pos.dataDeLancamento;
            String nome = pos.titulo;
            int idFilme = pos.idFilme;
            String anoFilme = data.substring(6);

            if(anoFilme.equals(ano)){
                ArrayList<Filme> pos2 = Main.arrayMovieVotes2.get(Integer.toString(idFilme));

                for (int countVotes = 0; countVotes < pos2.size(); countVotes++) {
                    Filme pos3 = pos2.get(countVotes);
                    double ratio = pos3.mediaDeVotos;

                    ArrayList<Filme> array2 = Main.arrayPeople2.get(String.valueOf(idFilme));
                    int nrAtoresTotal = 0;
                    if(array2 != null){
                        for (int countAtores = 0; countAtores < array2.size() ; countAtores++) {
                            if(Objects.equals(array2.get(countAtores).tipoPessoa, "ACTOR")){
                                nrAtoresTotal++;
                            }
                        }
                    }
                    if(nrAtoresTotal > 0){
                        double result = ratio / nrAtoresTotal ;
                        array.add(new VotosNome(result, nome));
                    }

                }
            }
        }

        if(!(array.isEmpty())){

            array.sort(Comparator.comparingDouble((VotosNome votes) -> votes.ratio).reversed());


            if(Integer.parseInt(numero) < array.size() - 1){

                for (int count = 0; count < Integer.parseInt(numero); count++) {
                    if(count == Integer.parseInt(numero) - 1){
                        String nome = array.get(count).nome;
                        Double voto = array.get(count).ratio;
                        resultado.append(nome).append(":").append(voto);

                    }else{
                        String nome = array.get(count).nome;
                        Double voto = array.get(count).ratio;
                        resultado.append(nome).append(":").append(voto).append("\n");


                    }
                }

            }else{

                int countAtual = 0;
                while (countAtual < Integer.parseInt(numero)){

                    if(countAtual < array.size() - 1){
                        String nome = array.get(countAtual).nome;
                        Double voto = array.get(countAtual).ratio;
                        resultado.append(nome).append(":").append(voto).append("\n");

                    }else if(countAtual == array.size() - 1){

                        String nome = array.get(countAtual).nome;
                        Double voto = array.get(countAtual).ratio;
                        resultado.append(nome).append(":").append(voto);

                    }
                    countAtual++;
                }
            }



        }else{
            resultado.append("zerop");
        }


        end = System.currentTimeMillis();
        tempo = (end - start);

        return new QueryResult(resultado.toString(), tempo);
    }
}
