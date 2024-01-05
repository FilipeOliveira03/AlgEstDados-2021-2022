package pt.ulusofona.deisi.aed.deisiflix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.util.Objects.hash;

public class LerFicheiros {

    static void function() throws IOException {
        //long start, end;
        String linha;

        //start = System.currentTimeMillis();


        FileReader fr1 = new FileReader("deisi_movies.txt");
        BufferedReader reader1 = new BufferedReader(fr1);

        while ((linha = reader1.readLine()) != null) {
            String[] dados = linha.split(",");

            if(dados.length == 5){
                int idFilme = Integer.parseInt(dados[0].trim());
                String titulo = dados[1];
                double duracao = Double.parseDouble(dados[2]);
                int orcamento = Integer.parseInt(dados[3].trim());
                String dataDeLancamento = dados[4];

                Filme filme = new Filme(idFilme, titulo, duracao, orcamento, dataDeLancamento);

                Main.arrayMovies1.add(filme);

                ArrayList<Filme> array;
                if(Main.arrayMovies2.get(String.valueOf(idFilme)) == null){
                    array = new ArrayList<>();
                    array.add(filme);
                    Main.arrayMovies2.put(String.valueOf(idFilme), array);
                }else{
                    Main.arrayMovies2.get(String.valueOf(idFilme)).add(filme);
                }
                //Main.arrayMovies3.put(String.valueOf(idFilme), filme);
            }else{
                Main.linhasIgnoradasMovies.add(linha);
            }
        }
        reader1.close();

        //end = System.currentTimeMillis();
        //System.out.println("Demora1 " + (end - start) + " ms");

        //start = System.currentTimeMillis();

        FileReader fr2 = new FileReader("deisi_movie_votes.txt");
        BufferedReader reader2 = new BufferedReader(fr2);

        while ((linha = reader2.readLine()) != null) {
            String[] dados = linha.split(",");

            if(dados.length == 3){
                int idFilme = Integer.parseInt(dados[0].trim());
                double mediaDeVotos = Double.parseDouble(dados[1]);
                int nrVotos = Integer.parseInt(dados[2].trim());

                Filme filme = new Filme(idFilme, mediaDeVotos, nrVotos);
                Main.arrayMovieVotes1.put(String.valueOf(idFilme), filme);

                ArrayList<Filme> array;
                if(Main.arrayMovieVotes2.get(String.valueOf(idFilme)) == null){
                    array = new ArrayList<>();
                    array.add(filme);
                    Main.arrayMovieVotes2.put(String.valueOf(idFilme), array);
                }else{
                    Main.arrayMovieVotes2.get(String.valueOf(idFilme)).add(filme);
                }


            }else{
                Main.linhasIgnoradasVotes.add(linha);
            }
        }
        reader2.close();

        //end = System.currentTimeMillis();
        //System.out.println("Demora2 " + (end - start) + " ms");

        //start = System.currentTimeMillis();

        FileReader fr3 = new FileReader("deisi_people.txt");
        BufferedReader reader3 = new BufferedReader(fr3);

        while ((linha = reader3.readLine()) != null) {
            String[] dados = linha.split(",");

            if(dados.length == 5){
                String tipoPessoa = dados[0];
                int idAtor = Integer.parseInt(dados[1].trim());
                String atores = dados[2];
                char genero = dados[3].charAt(0);
                int idFilme = Integer.parseInt(dados[4].trim());

                Filme filme = new Filme(tipoPessoa, idAtor, atores, genero, idFilme);

                Main.arrayPeople1.add(filme);
                ArrayList<Filme> array;
                if(Main.arrayPeople2.get(String.valueOf(idFilme)) == null){
                    array = new ArrayList<>();
                    array.add(filme);
                    Main.arrayPeople2.put(String.valueOf(idFilme), array);
                }else{
                    Main.arrayPeople2.get(String.valueOf(idFilme)).add(filme);
                }
            }else{
                Main.linhasIgnoradasPeople.add(linha);
            }

        }
        reader3.close();

        //end = System.currentTimeMillis();
        //System.out.println("Demora3 " + (end - start) + " ms");

        //start = System.currentTimeMillis();

        FileReader fr4 = new FileReader("deisi_genres.txt");
        BufferedReader reader4 = new BufferedReader(fr4);

        while ((linha = reader4.readLine()) != null) {
            String[] dados = linha.split(",");

            if (dados.length == 2) {
                String nomeGenero = dados[0];
                int idFilme = Integer.parseInt(dados[1].trim());

                Filme filme = new Filme(nomeGenero, idFilme);
                ArrayList<Filme> array;

                if(Main.arrayGenres.get(String.valueOf(idFilme)) == null){
                    array = new ArrayList<>();
                    array.add(filme);
                    Main.arrayGenres.put(String.valueOf(idFilme), array);
                }else{
                    Main.arrayGenres.get(String.valueOf(idFilme)).add(filme);
                }
            }else{
                Main.linhasIgnoradasGenres.add(linha);
            }

        }
        reader4.close();

        //end = System.currentTimeMillis();
        //System.out.println("Demora4 " + (end - start) + " ms");
    }

}
