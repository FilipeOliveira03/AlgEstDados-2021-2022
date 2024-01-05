package pt.ulusofona.deisi.aed.deisiflix;

import java.util.*;

public class CountMoviesWithActors {

//    Devolve o número de filmes
//    onde aparecem os vários
//    actores passados como
//    variáveis.
//
//    Apenas contam os filmes onde
//    todos os actores participem.

//    COUNT_MOVIES_WITH_ACTORS Brad Pitt;Angelina Jolie

    static QueryResult function (String interessa){

        long start, end, tempo;
        start = System.currentTimeMillis();
        int resultado = 0;
        int i = 0;

        while (i < interessa.length() - 1 ){
            if(interessa.charAt(i) == ';'){
                break;
            }
            i++;
        }

        if(interessa.charAt(i) == ';'){
            String[] atores = interessa.split(";");

            if(atores.length >= 2 && atores.length <= 5){

                ArrayList<Integer> usados = new ArrayList<>();
                TreeSet<String> atoresSemDuplicados = new TreeSet<>();

                for (int countAtores = 0; countAtores <= atores.length - 1; countAtores++) {
                    atoresSemDuplicados.add(atores[countAtores]);
                }


                for (int countPeople = 0; countPeople < Main.arrayPeople1.size() ; countPeople++) {

                    Filme posicao = Main.arrayPeople1.get(countPeople);
                    int idFilme = posicao.idFilme;
                    String nomePessoa = posicao.atores;

                    if(Objects.equals(nomePessoa, atoresSemDuplicados.first()) && Objects.equals(posicao.tipoPessoa, "ACTOR")){

                        ArrayList<Filme> posicao2 = Main.arrayPeople2.get(String.valueOf(idFilme));
                        for (int countArray = 0; countArray < posicao2.size(); countArray++){

                            Filme filme = posicao2.get(countArray);
                            int idFilme2 = filme.idFilme;
                            String nomePessoa2 = filme.atores;

                            if(idFilme2 == idFilme){

                                for (int count = 1; count <= atores.length - 1; count++ ) {

                                    if (Objects.equals(nomePessoa2, atoresSemDuplicados.last()) &&
                                            Objects.equals(filme.tipoPessoa, "ACTOR") && !usados.contains(idFilme)){
                                        resultado++;
                                        usados.add(idFilme);

                                    }

                                }
                            }
                        }
                    }
                }


            }
        }else {
            resultado = Integer.parseInt("APENAS SAO PERMITIDOS 2 A 5 ATORES");
        }



        String result = Integer.toString(resultado);

        end = System.currentTimeMillis();
        tempo = (end - start);

        return new QueryResult(result, tempo);
    }
}

