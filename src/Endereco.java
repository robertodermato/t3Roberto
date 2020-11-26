public class Endereco {
    public String endereco;
    public int tamanhoDaTag;
    public int tamanhoDaLinha;
    public int tamanhoDaPalavra;
    public int quantidadeDeBitsParaSelecao;

    public Endereco(String enderecoEmBinario, int tamanhoTag, int tamanhoLinha, int tamanhoPalavra, int bitsSelecao) {
        tamanhoDaTag = tamanhoTag;
        tamanhoDaLinha = tamanhoLinha;
        tamanhoDaPalavra = tamanhoPalavra;
        quantidadeDeBitsParaSelecao = bitsSelecao;
        endereco = enderecoEmBinario;
    }

    public String getTag(){
        return endereco.substring(0, tamanhoDaTag);
    }
    public String getLinha(){
        return endereco.substring(tamanhoDaTag, tamanhoDaTag + tamanhoDaLinha);
    }
    public String getPalavra(){
        return endereco.substring(tamanhoDaTag + tamanhoDaLinha, tamanhoDaTag + tamanhoDaLinha + tamanhoDaPalavra);
    }
    public String getBitSelecao(){
        return endereco.substring(tamanhoDaTag + tamanhoDaLinha + tamanhoDaPalavra, tamanhoDaTag + tamanhoDaLinha + tamanhoDaPalavra + quantidadeDeBitsParaSelecao);
    }

    @Override
    public String toString() {
        return "Endereço: " + endereco;
    }
}
