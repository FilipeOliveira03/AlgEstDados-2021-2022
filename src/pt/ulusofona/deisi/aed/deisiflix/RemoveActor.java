package pt.ulusofona.deisi.aed.deisiflix;

import java.util.ArrayList;
import java.util.Objects;

public class RemoveActor {

//    Remove um actor da base
//    de dados e de todos os
//    filmes onde o mesmo tenha
//    participado.

//    A remoção do actor tem de
//    ser reflectida nas queries
//    que ocorram após o
//    “remove”.

//    O output deve apenas
//    responder “OK”, caso a
//    remoção tenha tido sucesso, ou
//    “Erro” caso não exista um actor
//    com esse id.

    static QueryResult function (String interessa) {

        long start, end, tempo;
        start = System.currentTimeMillis();

        String resultado = "";
        String output = "";
        int count1 = 0;
        int idFilme = 0;
        int count2 = 0;
        ArrayList<Integer> idFilmes = new ArrayList<>();

        for (int count = 0; count < Main.arrayPeople1.size(); count++) {
            Filme pos = Main.arrayPeople1.get(count);
            int idAtor = pos.idAtor;

            String tipo = pos.tipoPessoa;
            if(Objects.equals(tipo, "ACTOR") && Integer.toString(idAtor).equals(interessa)){;
                Main.arrayPeople1.remove(count);
                idFilme = pos.idFilme;

                for (int i = 0; i < Main.arrayMovies1.size(); i++) {
                    if(Main.arrayMovies1.get(i).idFilme == idFilme){
                        count2++;
                    }
                }

                count--;
                count--;
                count1++;
                idFilmes.add(idFilme);
                resultado = "Removeu";
            }
        }

        for (int countPeople = 0; countPeople < idFilmes.size() ; countPeople++) {

            int idAtual = idFilmes.get(countPeople);
            ArrayList<Filme> pos = Main.arrayPeople2.get(Integer.toString(idAtual));

            for (int countPeople2 = 0; countPeople2 < pos.size(); countPeople2++) {

                int idAtor = pos.get(countPeople2).idAtor;
                String tipo = pos.get(countPeople2).tipoPessoa;

                if(Objects.equals(tipo, "ACTOR") && Integer.toString(idAtor).equals(interessa) ){

                    pos.remove(countPeople2);
                    countPeople2--;
                }
            }
        }

        if(resultado.equals("Removeu")){
            output = String.valueOf(count2);
        }else{
            output = "Erro";
        }


        end = System.currentTimeMillis();
        tempo = (end - start);

        return new QueryResult(output, tempo);
    }
}
