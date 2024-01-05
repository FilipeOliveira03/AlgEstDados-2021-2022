package pt.ulusofona.deisi.aed.deisiflix;
import java.util.*;

public class TopMoviesWithGenderBias {

//    Quais os N filmes do ano Y com
//    maior discrepância percentual
//    entre os géneros dos seus
//    actores?

//    Apenas filmes com mais de 10
//    actores/actrizes devem ser
//    considerados.

//    O output deve ser uma string
//    com a seguinte sintaxe:
//            “<Título do
//    Filme>:<Género>:<Percentagem>”

//    O <Género> apresentado deve
//    ser aquele que mais vezes
//    aparece.

//    Os vários resultados devem ser
//    separados por “\n”.

//    A ordem deve ser decrescente
//    pela percentagem.

//    TOP_MOVIES_WITH_GENDER_BIAS 6 1970

    static QueryResult function (String interessa) {

        long start, end, tempo;
        start = System.currentTimeMillis();

        String[] dados = interessa.split(" ");
        HashMap<Integer, ArrayList<String>> guarda = new HashMap<>();
        ArrayList<String> arrayGuarda;

        StringBuilder result = new StringBuilder();
        int minimo = Integer.parseInt(dados[2]);
        int maximo = Integer.parseInt(dados[3]);


        for (int countMovies = 0; countMovies < Main.arrayMovies1.size(); countMovies++) {

            Filme posicao = Main.arrayMovies1.get(countMovies);
            int idFilme = posicao.idFilme;
            String data = posicao.dataDeLancamento;
            String ano = data.substring(6);
            String tituloFilme = posicao.titulo;

            if(Objects.equals(ano, dados[1])){
                ArrayList<Filme> array = Main.arrayPeople2.get(String.valueOf(idFilme));
                int nrAtoresTotal = 0;
                if(array != null){
                    for (int countAtores = 0; countAtores < array.size(); countAtores++) {
                        if(Objects.equals(array.get(countAtores).tipoPessoa, "ACTOR")){
                            nrAtoresTotal++;
                        }
                    }

                    if(nrAtoresTotal <= maximo && nrAtoresTotal >= minimo && minimo < maximo){

                        int countM = 0;
                        int countF = 0;
                        TreeSet<String> usados = new TreeSet<>();

                        for (Filme pos : array) {

                            String ator = pos.atores;
                            if(!usados.contains(ator)){
                                char tipo = pos.genero;
                                if (tipo == 'M') {
                                    countM++;
                                } else if (tipo == 'F') {
                                    countF++;
                                }
                                usados.add(ator);
                            }

                        }

                        int nrAtores = countM + countF;
                        if(countM < countF){
                            String texto = "";
                            double valor = (countF * 100D) / nrAtores;
                            int value = (int)Math.round(valor);
                            texto += tituloFilme + ":F:" + value;

                            if(guarda.get(value) == null){
                                arrayGuarda = new ArrayList<>();
                                arrayGuarda.add(texto);
                                guarda.put(value, arrayGuarda);
                            }else{
                                guarda.get(value).add(texto);
                            }



                        }else if(countM > countF){
                            String texto = "";
                            double valor = (countM * 100D) / nrAtores;
                            int value = (int)Math.round(valor);
                            texto += tituloFilme + ":M:" + value;

                            if(guarda.get(value) == null){
                                arrayGuarda = new ArrayList<>();
                                arrayGuarda.add(texto);
                                guarda.put(value, arrayGuarda);
                            }else{
                                guarda.get(value).add(texto);
                            }


                        }else{
                            String texto = "";
                            double valor = (countM * 100D) / nrAtores;;
                            int value = (int)Math.round(valor);
                            texto += tituloFilme + ":F:" + value;

                            if(guarda.get(value) == null){
                                arrayGuarda = new ArrayList<>();
                                arrayGuarda.add(texto);
                                guarda.put(value, arrayGuarda);
                            }else{
                                guarda.get(value).add(texto);
                            }

                        }

                    }else{
                        end = System.currentTimeMillis();
                        tempo = (end - start);

                        result.append("Erro");
                        return new QueryResult(result.toString(), tempo);
                    }
                }

            }
        }

        int tamanhoAtual = 0;
        for (int count = 100; count > 0 ; count--) {
            ArrayList<String> array = guarda.get(count);
            if(array != null){
                for (int count2 = 0; count2 < array.size(); count2++) {
                    String output = array.get(count2);
                    if(tamanhoAtual < Integer.parseInt(dados[0]) - 1){
                        result.append(output).append("\n");
                        tamanhoAtual++;
                    }else if(tamanhoAtual == Integer.parseInt(dados[0]) - 1){
                        result.append(output);
                        tamanhoAtual++;
                    }
                }
            }
        }

        end = System.currentTimeMillis();
        tempo = (end - start);

        return new QueryResult(result.toString(), tempo);
    }
}
