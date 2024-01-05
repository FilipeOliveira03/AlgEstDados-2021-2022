package pt.ulusofona.deisi.aed.deisiflix;


import java.io.IOException;
import java.util.*;


public class Main {

    static ArrayList<Filme> arrayMovies1;
    static HashMap<String,ArrayList<Filme>> arrayMovies2;
    static HashMap<String, Filme> arrayMovies3;

    static HashMap<String,Filme> arrayMovieVotes1;
    static HashMap<String,ArrayList<Filme>> arrayMovieVotes2;

    static ArrayList<Filme> arrayPeople1;
    static HashMap<String,ArrayList<Filme>> arrayPeople2;

    static HashMap<String,ArrayList<Filme>> arrayGenres;


    static ArrayList<String> linhasIgnoradasMovies;
    static ArrayList<String> linhasIgnoradasPeople;
    static ArrayList<String> linhasIgnoradasGenres;
    static ArrayList<String> linhasIgnoradasVotes;


    static void lerFicheiros() throws IOException {

        arrayMovies1 = new ArrayList<>();
        arrayMovies2 = new HashMap<>();
        arrayMovies3 = new HashMap<>();

        arrayMovieVotes1 = new HashMap<>();
        arrayMovieVotes2 = new HashMap<>();

        arrayPeople1 = new ArrayList<>();
        arrayPeople2 = new HashMap<>();

        arrayGenres = new HashMap<>();

        linhasIgnoradasMovies = new ArrayList<>();
        linhasIgnoradasPeople = new ArrayList<>();
        linhasIgnoradasGenres = new ArrayList<>();
        linhasIgnoradasVotes = new ArrayList<>();

        LerFicheiros.function();
    }

    static void lerFicheiros2() throws IOException {

        arrayMovies1 = new ArrayList<>();
        arrayMovies2 = new HashMap<>();
        arrayMovies3 = new HashMap<>();

        arrayMovieVotes1 = new HashMap<>();
        arrayMovieVotes2 = new HashMap<>();

        arrayPeople1 = new ArrayList<>();
        arrayPeople2 = new HashMap<>();

        arrayGenres = new HashMap<>();

        linhasIgnoradasMovies = new ArrayList<>();
        linhasIgnoradasPeople = new ArrayList<>();
        linhasIgnoradasGenres = new ArrayList<>();
        linhasIgnoradasVotes = new ArrayList<>();

        LerFicheriosTeste.functionTeste();
    }

    static ArrayList<Filme> getFilmes(){
        return arrayMovies1;
    }

    static ArrayList<String> getLinhasIgnoradas(String fileName) {
        if(Objects.equals(fileName, "deisi_movies.txt")){
            return linhasIgnoradasMovies;
        } else if(Objects.equals(fileName, "deisi_people.txt")){
            return linhasIgnoradasPeople;
        } else if(Objects.equals(fileName, "deisi_genres.txt")){
            return linhasIgnoradasGenres;
        } else{
            return linhasIgnoradasGenres;
        }
    }

    static ArrayList<Filme> getFilmesByDuracao(double duracao){

        ArrayList<Filme> b = new ArrayList<>();

        for (int i = 0; i < arrayMovies1.size() ; i++) {
            double a = arrayMovies1.get(i).duracao;
            if(a == duracao){
                b.add(arrayMovies1.get(i));
            }

        }

        return b;
    }


    public static QueryResult perguntar(String pergunta){

        int caracter = 0;

        while (caracter < pergunta.length() && pergunta.charAt(caracter) != ' '){
            caracter++;
        }

        String perguntaVerdadeira = pergunta.substring(0, caracter);
        String interessa = pergunta.substring(caracter + 1);


        switch (perguntaVerdadeira) {
            case "COUNT_MOVIES_ACTOR":

                return CountMoviesActor.function(interessa);

            case "GET_MOVIES_ACTOR_YEAR":

                return GetMoviesActorYear.function(interessa);

            case "COUNT_MOVIES_WITH_ACTORS":

                return CountMoviesWithActors.function(interessa);

            case "COUNT_ACTORS_3_YEARS":

                return CountActores3Years.function(interessa);

            case "TOP_MOVIES_WITH_GENDER_BIAS":

                return TopMoviesWithGenderBias.function(interessa);

            case "INSERT_ACTOR":

                return InsertActor.function(interessa);

            case "REMOVE_ACTOR":

                return RemoveActor.function(interessa);

            case "GET_RECENT_TITLES_SAME_AVG_VOTES_ONE_SHARED_ACTOR":

            case "GET_TOP_N_YEARS_BEST_AVG_VOTES":

            case "DISTANCE_BETWEEN_ACTORS":

            case "GET_TOP_N_MOVIES_RATIO":

                return GetTopNMoviesRatio.function(interessa);

            case "TOP_6_DIRECTORS_WITHIN_FAMILY":

            case "GET_TOP_ACTOR_YEAR":

            case "GET_DUPLICATE_LINES_YEAR":

            case "TOP_10_BEST_MOVIES_IN_CERTAIN_YEAR":

                return Top10BestMoviesInCertainYear.function(interessa);

            default:
                return null;
        }

    }


    public static String getVideoURL(){
        return "https://www.youtube.com/watch?v=KPNFQATUuMA";
    }

    public static String getCreativeQuery(){
        return "TOP_10_BEST_MOVIES_IN_CERTAIN_YEAR";
    }

    public static void main(String[] args) throws IOException {

        lerFicheiros();

        System.out.println("Bem vindo ao DEISIFLIX");

        Scanner in = new Scanner(System.in);
        String texto = in.nextLine();

        while (texto != null && !texto.equals("QUIT")) {

            QueryResult resultado = perguntar(texto);
            if(resultado == null){
                System.out.println("Pergunta desconhecida. Tente novamente.");
            }else{
                System.out.println(resultado.valor);
                System.out.println("(demorou " + resultado.tempo + " ms)");
            }


            texto = in.nextLine();
        }

        System.out.println("Adeus!!!");


    }
}
