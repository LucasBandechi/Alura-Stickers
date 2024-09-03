import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App 
{
    public static void main(String[] args) throws Exception 
    {

        // fazer uma conexao HTTP e buscar os top 250 filmes

        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoImdb();
        
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        
        var http = new ClienteHttp();
        String json = http.buscaDados(url);
        List<Conteudo> conteudos = extrator.extraiConteudos(json);
        
        var geradora = new GeradoraDeFigurinhas();

        for (Conteudo conteudo : conteudos) 
        {
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println("\u001b[1mTitulo:\u001b[m "+ conteudo.getTitulo());
            System.out.println("\u001b[1mImagem:\u001b[m "+ conteudo.getUrlImagem());
            System.out.println();
        }
    }
}
