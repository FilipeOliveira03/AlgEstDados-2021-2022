package pt.ulusofona.deisi.aed.deisiflix;

import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeSet;

import static java.util.Objects.hash;

public class Filme {
    int idFilme;
    int idAtor;
    String titulo;
    String atores;
    char genero;
    String nomeGenero;
    String dataDeLancamento;
    int orcamento;
    double mediaDeVotos;
    int nrVotos;
    double duracao;
    String tipoPessoa;

    Filme(int idFilme, String titulo, double duracao, int orcamento, String dataDeLancamento){
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.duracao = duracao;
        this.orcamento = orcamento;
        this.dataDeLancamento = dataDeLancamento;
    }

    Filme(int idFilme, double mediaDeVotos, int nrVotos){
        this.idFilme = idFilme;
        this.mediaDeVotos = mediaDeVotos;
        this.nrVotos = nrVotos;
    }

    Filme(String tipoPessoa, int idAtor, String atores, char genero, int idFilme){
        this.tipoPessoa = tipoPessoa;
        this.idAtor = idAtor;
        this.atores = atores;
        this.genero = genero;
        this.idFilme = idFilme;
    }

    Filme(String nomeGenero, int idFilme){
        this.nomeGenero = nomeGenero;
        this.idFilme = idFilme;
    }

    Filme(int idFilme, String titulo, String dataDeLancamento){
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.dataDeLancamento = dataDeLancamento;
    }

    Filme(int idFilme){
        this.idFilme = idFilme;
    }


    public String toString(){

        String dataLancamento = "";
        String titulo = "";
        String dataVirada = "";
        ArrayList<Filme> posicao = Main.arrayMovies2.get(Integer.toString(idFilme));
        if(posicao != null){
            for (int count = 0; count < posicao.size(); count++) {
                int idFilme1 = posicao.get(count).idFilme;
                if(idFilme == idFilme1){
                    titulo = posicao.get(count).titulo;
                    dataLancamento = posicao.get(count).dataDeLancamento;
                    String[] dados = dataLancamento.split("-");
                    dataVirada = dados[2] ;
                }
            }

        }

        Filme posVotes = Main.arrayMovieVotes1.get(String.valueOf(idFilme));

        int nrVotos = 0;
        double mediaDeVotos = 0.0;

        if (posVotes != null){
            nrVotos = posVotes.nrVotos;
            mediaDeVotos = posVotes.mediaDeVotos;
        }

        int nrRealizadores = 0;
        int nrGeneros = 0;
        int countM = 0;
        int countF = 0;
        StringBuilder a = new StringBuilder();


        ArrayList<Filme> array = Main.arrayPeople2.get(Integer.toString(idFilme));
        if(array != null){
            for (int count = 0; count < array.size(); count++) {
                if(Objects.equals(array.get(count).tipoPessoa, "DIRECTOR")){
                    nrRealizadores++;
                    a.append(array.get(count).atores).append(";");

                }
            }

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

        }
        TreeSet<String> usados = new TreeSet<>();
        ArrayList<Filme> array2 = Main.arrayGenres.get(Integer.toString(idFilme));
        if(array2 != null){
            for (int countGenres = 0; countGenres < array2.size(); countGenres++) {
                String genero = array2.get(countGenres).nomeGenero;
                if(!usados.contains(genero)){
                    nrGeneros++;
                }
                usados.add(genero);
            }
        }

        int atorestotal = countM + countF;

        String primeira = idFilme + " | " + titulo + " | " + dataVirada + " | " + nrVotos + " | " + mediaDeVotos;
        String segunda = " | " + nrGeneros  + " | " + atorestotal + " | " + a;

        return primeira + segunda;
    }
}
