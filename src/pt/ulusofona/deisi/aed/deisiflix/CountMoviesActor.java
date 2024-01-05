package pt.ulusofona.deisi.aed.deisiflix;

import java.util.Objects;

public class CountMoviesActor {

//    Devolve o número de filmes
//    em que uma pessoa
//    participou na condição de
//    actor.


//    Se não existir nenhum actor
//    com este nome, deve
//    retornar o valor numérico
//    zero.

//    COUNT_MOVIES_ACTOR Emily Blunt


    static QueryResult function (String interessa){

        long start, end, tempo;
        start = System.currentTimeMillis();

        int filmesParticipou = 0;

        for (int count = 0; count < Main.arrayPeople1.size() - 1; count++) {

            Filme arrayFilme = Main.arrayPeople1.get(count);
            String verificar = arrayFilme.tipoPessoa;
            String pessoa = arrayFilme.atores;

            if (Objects.equals(verificar, "ACTOR")) {
                if (Objects.equals(pessoa, interessa)) {
                    filmesParticipou++;
                }
            }
        }

        String result = Integer.toString(filmesParticipou);

        end = System.currentTimeMillis();
        tempo = (end - start);

        return new QueryResult(result, tempo);
    }
}
