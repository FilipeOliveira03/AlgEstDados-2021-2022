package pt.ulusofona.deisi.aed.deisiflix;

import java.util.ArrayList;

public class InsertActor {

//    Insere um actor.

//    O actor inserido tem que ser
//    reflectido nas queries que
//    ocorram após o “insert”. No
//    entanto, não tem que ser escrito
//    em ficheiro. Ou seja, quando o
//    programa terminar (QUIT),
//    todas as inserções são
//    “esquecidas”.

//    O output deve apenas
//    responder “OK”, caso a
//    inserção tenha tido sucesso, ou
//    “Erro” caso já exista um actor
//    com esse id ou o filme não
//    exista.

    static QueryResult function (String interessa) {

        long start, end, tempo;
        start = System.currentTimeMillis();

        String[] dados = interessa.split(";");

        int idAtor = Integer.parseInt(dados[0].trim());
        String nomeAtor = dados[1];
        char genero = dados[2].charAt(0);
        int idFilme = Integer.parseInt(dados[3].trim());

        String result = "";
        String output = "";

        ArrayList<Filme> pos = Main.arrayPeople2.get(dados[3]);
        if(pos != null){
            for (int count = 0; count < pos.size(); count++) {
                Filme pos2 = pos.get(count);
                int idAtor2 = pos2.idAtor;
                
                if((idAtor2 == idAtor)){
                    result = "Errado";
                }
            }

            for (int count2 = 0; count2 < pos.size(); count2++) {
                Filme pos2 = pos.get(count2);
                int idFilme2 = pos2.idFilme;

                if(!(idFilme2 == idFilme)){
                    result = "Errado";
                }
            }

            if(result.equals("Errado")){
                output = "Erro";
            }else{
                Filme filme = new Filme("ACTOR", idAtor, nomeAtor, genero, idFilme);
                Main.arrayPeople1.add(filme);

                ArrayList<Filme> array;
                if(Main.arrayPeople2.get(String.valueOf(dados[3])) == null){
                    array = new ArrayList<>();
                    array.add(filme);
                    Main.arrayPeople2.put(String.valueOf(dados[3]), array);
                }else{
                    Main.arrayPeople2.get(String.valueOf(dados[3])).add(filme);
                }

                output = "OK";
            }
        }else{
            output = "Erro";
        }

        end = System.currentTimeMillis();
        tempo = (end - start);

        return new QueryResult(output, tempo);
    }
}
