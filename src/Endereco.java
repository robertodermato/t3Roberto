public class Endereco {
    public String endereco;
    public int tamanhoDaTag;
    public int tamanhoDaLinha;
    public int tamanhoDaPalavra;
    public int quantidadeDeBitsParaSelecao;

    public Endereco(String endereco, int tamanhoTag, int tamanhoLinha, int tamanhoPalavra, int bitsSelecao) {
        tamanhoDaTag = tamanhoTag;
        tamanhoDaLinha = tamanhoLinha;
        tamanhoDaPalavra = tamanhoPalavra;
        quantidadeDeBitsParaSelecao = bitsSelecao;
    }

    public String tag(){
        return endereco.substring(0, tamanhoDaTag);
    }
    public String linha(){
        return endereco.substring(tamanhoDaTag, tamanhoDaTag + tamanhoDaLinha);
    }
    public String palavra(){
        return endereco.substring(tamanhoDaTag + tamanhoDaLinha, tamanhoDaTag + tamanhoDaLinha + tamanhoDaPalavra);
    }
    public String selec(){
        return endereco.substring(tamanhoDaTag + tamanhoDaLinha + tamanhoDaPalavra, tamanhoDaTag + tamanhoDaLinha + tamanhoDaPalavra + quantidadeDeBitsParaSelecao);
    }

    @Override
    public String toString() {
        return "Endereço: " + endereco;
    }
}
