package pt.ulusofona.deisi.aed.deisiflix;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestMain {

    @Test
    public void testeToString1() throws IOException {
        Main.lerFicheiros2();
        String resultEsperado = "603 | The Matrix | 1999-03-30 | 9079 | 7.9 | 2 | 2 | 16 | 20";
        Filme resultReal = new Filme(603);
        String result = String.valueOf(resultReal);
        assertEquals("Falhou no teste1", resultEsperado, result);
    }

    @Test
    public void testeToString2() throws IOException {
        Main.lerFicheiros2();
        String resultEsperado = "95853 | Bunny Drop | 2011-08-20 | 11 | 6.7 | 2 | 1 | 2 | 11";
        Filme resultReal = new Filme(95853);
        String result = String.valueOf(resultReal);
        assertEquals("Falhou no teste2", resultEsperado, result);
    }

    @Test
    public void testCountMoviesActor() throws IOException {
        Main.lerFicheiros2();
        String resultEsperado = "1";
        QueryResult resultReal = Main.perguntar("COUNT_MOVIES_ACTOR Daniel Skowronek");
        assert resultReal != null;
        assertEquals("Falhou no testCountMoviesActor", resultEsperado, resultReal.valor);
    }

    @Test
    public void testGetMoviesActorYear() throws IOException {
        Main.lerFicheiros2();
        String resultEsperado = "The Tall Men (1955-09-22)\nSoldier of Fortune (1955-01-01)";
        QueryResult resultReal = Main.perguntar("GET_MOVIES_ACTOR_YEAR Clark Gable 1955");
        assert resultReal != null;
        assertEquals("Falhou no testGetMoviesActorYear", resultEsperado, resultReal.valor);
    }

    @Test
    public void testCountMoviesWithActors() throws IOException {
        Main.lerFicheiros2();
        String resultEsperado = "1";
        QueryResult resultReal = Main.perguntar("COUNT_MOVIES_WITH_ACTORS Rita Blanco;Rafael Morais");
        assert resultReal != null;
        assertEquals("Falhou no testCountMoviesWithActors", resultEsperado, resultReal.valor);
    }

    @Test
    public void testCountActores3Years() throws IOException {
        Main.lerFicheiros2();
        String resultEsperado = "0";
        QueryResult resultReal = Main.perguntar("COUNT_ACTORS_3_YEARS 1914 1916 1900");
        assert resultReal != null;
        assertEquals("Falhou no testCountActores3Years", resultEsperado, resultReal.valor);
    }

    @Test
    public void testTopMoviesWithGenderBias() throws IOException {
        Main.lerFicheiros2();
        String resultEsperado = "The Seed of Man:F:93";
        QueryResult resultReal = Main.perguntar("TOP_MOVIES_WITH_GENDER_BIAS 1 1969");
        assert resultReal != null;
        assertEquals("Falhou no testeTopMoviesWithGenderBias", resultEsperado, resultReal.valor);
    }

    @Test
    public void testGetTopNMoviesRatio() throws IOException {
        Main.lerFicheiros2();
        String resultEsperado = "Planet Earth:8.8";
        QueryResult resultReal = Main.perguntar("GET_TOP_N_MOVIES_RATIO 1 2006");
        assert resultReal != null;
        assertEquals("Falhou no testeGetTopNMoviesRatio", resultEsperado, resultReal.valor);
    }

    @Test
    public void testInsertActor() throws IOException {
        Main.lerFicheiros2();
        String resultEsperado = "Erro";
        QueryResult resultReal = Main.perguntar("INSERT_ACTOR 18269;Filipe Oliveira;M;77112");
        assert resultReal != null;
        assertEquals("Falhou no testeInsertActor", resultEsperado, resultReal.valor);
    }

    @Test
    public void testRemoveActor() throws IOException {
        Main.lerFicheiros2();
        String resultEsperado = "Erro";
        QueryResult resultReal = Main.perguntar("REMOVE_ACTOR 641556");
        assert resultReal != null;
        assertEquals("Falhou no testeRemoveActor", resultEsperado, resultReal.valor);
    }
}
