package pt.ulusofona.deisi.aed.deisiflix;

import java.util.*;

public class CountActores3Years {

//    Quantos actores distintos
//    participaram em filmes nos anos
//    M, N, e O?

//    Só devem ser contabilizados os
//    actores que participem em pelo
//    menos um filme de cada ano
//    indicado.

//    COUNT_ACTORS_3_YEARS 1970 2010 2015

    static QueryResult function (String interessa) {

        long start, end, tempo;
        start = System.currentTimeMillis();

        String[] anos = interessa.split(" ");
        int resultado = 0;

        HashMap<String, TreeSet<String>> ordenacao = new HashMap<>();
        TreeSet<String> array;
        ArrayList<String> array2 = new ArrayList<>();
        TreeSet<String> array3 = new TreeSet<>();

        for (int countAnos = 0; countAnos <= anos.length - 1; countAnos++) {

            for (int countMovies = 0; countMovies < Main.arrayMovies1.size(); countMovies++) {

                Filme posicao = Main.arrayMovies1.get(countMovies);
                int idFilme = posicao.idFilme;
                String data = posicao.dataDeLancamento;
                String ano = data.substring(6);

                if (Objects.equals(ano, anos[countAnos])){

                    if(Main.arrayPeople2.get(String.valueOf(idFilme)) != null){

                        ArrayList<Filme> pos = Main.arrayPeople2.get(String.valueOf(idFilme));

                        for (int countArray2 = 0; countArray2 < pos.size() - 1; countArray2++){

                            Filme filme = pos.get(countArray2);
                            String ator = filme.atores;

                            if(ordenacao.get(ano) == null){
                                array = new TreeSet<>();
                                array.add(ator);
                                ordenacao.put(ano, array);
                            }else{
                                ordenacao.get(ano).add(ator);
                            }

                            array2.add(ator);


                        }
                    }
                }
            }
        }

        TreeSet<String> pos1 = ordenacao.get(anos[0]);
        TreeSet<String> pos2 = ordenacao.get(anos[1]);
        TreeSet<String> pos3 = ordenacao.get(anos[2]);

        for (String ator : array2) {
            if (pos1.contains(ator) && pos2.contains(ator) && pos3.contains(ator) && !array3.contains(ator)) {
                resultado++;
                array3.add(ator);
            }

        }

        String result = Integer.toString(resultado);

        end = System.currentTimeMillis();
        tempo = (end - start);

        return new QueryResult(result, tempo);
    }
}
