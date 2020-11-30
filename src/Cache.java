import java.util.ArrayList;

public class Cache{
    private Endereco[][] memoria;
    private int linhas;
    private int palavras;
    private String tipo;
    private ArrayList<String> historico;
    private ArrayList<String> historicoEmHexadecimal;
    private String [] historicoEmHexadecimalCompacto;
    private int countHistHexaComp;
    private int hits;
    private int misses;
    private int count;

    public Cache(int quantidadeDelinhas, int quantidadeDePalavras, String tipoDoCache){
        linhas=quantidadeDelinhas;
        palavras=quantidadeDePalavras;
        memoria = new Endereco[linhas][palavras];
        tipo = tipoDoCache;
        historico = new ArrayList<String>();
        historicoEmHexadecimal = new ArrayList<>();
        hits=0;
        misses=0;
        count=0;
        historicoEmHexadecimalCompacto = new String [196];
        countHistHexaComp = 0;
    }

    public void add(Endereco end){
        if(tipo == "direto"){
            int linha = converteBinInt(end.getLinha());
            int palavra = converteBinInt(end.getPalavra());
            if(memoria[linha][palavra]!=null && memoria[linha][palavra].endereco.equalsIgnoreCase(end.endereco)){
                historico.add(end + " HIT");
                historicoEmHexadecimal.add (converteParaHexadecimal(end.endereco) + " HIT");
                historicoEmHexadecimalCompacto [countHistHexaComp] = converteParaHexadecimal(end.endereco) + " H";
                countHistHexaComp ++;
                hits++;
            }
            else{
                historico.add(end + " MISS");
                misses++;
                historicoEmHexadecimal.add (converteParaHexadecimal(end.endereco) + " MISS");
                historicoEmHexadecimalCompacto [countHistHexaComp] = converteParaHexadecimal(end.endereco) + " M";
                countHistHexaComp ++;
                for(int i=0; i<palavras; i++){
                    String bin = end.getTag() + end.getLinha() + palavraGerada(i) + end.getBitSelecao();
                    memoria[linha][i]=new Endereco(bin,end.tamanhoDaTag,end.tamanhoDaLinha,end.tamanhoDaPalavra,end.quantidadeDeBitsParaSelecao);
                }
            }
        }
        else { //é associativo
            if(hitEndereco(end)){
                historico.add(end + " HIT");
                historicoEmHexadecimal.add (converteParaHexadecimal(end.endereco) + " HIT");
                historicoEmHexadecimalCompacto [countHistHexaComp] = converteParaHexadecimal(end.endereco) + " H";
                countHistHexaComp ++;
                hits++;
            }
            else{
                historico.add(end + " MISS");
                misses++;
                historicoEmHexadecimal.add (converteParaHexadecimal(end.endereco) + " MISS");
                historicoEmHexadecimalCompacto [countHistHexaComp] = converteParaHexadecimal(end.endereco) + " M";
                countHistHexaComp ++;
                if(count==linhas)count=0;
                for(int i=0;i<palavras;i++){
                    String bin = end.getTag() + end.getLinha() + palavraGerada(i) + end.getBitSelecao();
                    memoria[count][i]=new Endereco(bin,end.tamanhoDaTag,end.tamanhoDaLinha,end.tamanhoDaPalavra,end.quantidadeDeBitsParaSelecao);
                }
                count++;
            }
        }

    }

    public void add2(Endereco end){
        if(tipo == "direto"){
            int linha = converteBinInt(end.getLinha());
            int palavra = converteBinInt(end.getPalavra());
            if(memoria[linha][palavra]!=null && memoria[linha][palavra].getTag().equalsIgnoreCase(end.getTag())){
                historico.add(end + " HIT");
                historicoEmHexadecimal.add (converteParaHexadecimal(end.endereco) + " HIT");
                historicoEmHexadecimalCompacto [countHistHexaComp] = converteParaHexadecimal(end.endereco) + " H";
                countHistHexaComp ++;
                hits++;
            }
            else{
                historico.add(end + " MISS");
                misses++;
                historicoEmHexadecimal.add (converteParaHexadecimal(end.endereco) + " MISS");
                historicoEmHexadecimalCompacto [countHistHexaComp] = converteParaHexadecimal(end.endereco) + " M";
                countHistHexaComp ++;
                for(int i=0; i<palavras; i++){
                    String bin = end.getTag() + end.getLinha() + palavraGerada(i) + end.getBitSelecao();
                    memoria[linha][i]=new Endereco(bin,end.tamanhoDaTag,end.tamanhoDaLinha,end.tamanhoDaPalavra,end.quantidadeDeBitsParaSelecao);
                }
            }
        }
        else { //é associativo
            if(hitEndereco2(end)){
                historico.add(end + " HIT");
                historicoEmHexadecimal.add (converteParaHexadecimal(end.endereco) + " HIT");
                historicoEmHexadecimalCompacto [countHistHexaComp] = converteParaHexadecimal(end.endereco) + " H";
                countHistHexaComp ++;
                hits++;
            }
            else{
                historico.add(end + " MISS");
                misses++;
                historicoEmHexadecimal.add (converteParaHexadecimal(end.endereco) + " MISS");
                historicoEmHexadecimalCompacto [countHistHexaComp] = converteParaHexadecimal(end.endereco) + " M";
                countHistHexaComp ++;
                if(count==linhas)count=0;
                for(int i=0;i<palavras;i++){
                    String bin = end.getTag() + end.getLinha() + palavraGerada(i) + end.getBitSelecao();
                    memoria[count][i]=new Endereco(bin,end.tamanhoDaTag,end.tamanhoDaLinha,end.tamanhoDaPalavra,end.quantidadeDeBitsParaSelecao);
                }
                count++;
            }
        }

    }


    public boolean hitEndereco(Endereco a){
        for(int i = 0;i<linhas;i++){
            for (int j=0;j<palavras;j++){
                if(memoria[i][j]!=null && memoria[i][j].endereco.equalsIgnoreCase(a.endereco)) return true;
            }
        }
        return false;
    }

    public boolean hitEndereco2(Endereco a){
        for(int i = 0;i<linhas;i++){
            for (int j=0;j<palavras;j++){
                if(memoria[i][j]!=null && memoria[i][j].getTag().equalsIgnoreCase(a.getTag())) return true;
            }
        }
        return false;
    }



    public String palavraGerada(int n) {
        if (palavras==8){
            if (n==0) return "000";
            else if (n==1) return "001";
            else if (n==2) return "010";
            else if (n==3) return "011";
            else if (n==4) return "100";
            else if (n==5) return "101";
            else if (n==6) return "110";
            else if (n==7) return "111";
            else System.out.println("Tamanho informado de palavra é muito grande para cache. Só comporta 8 palavras.");
        }

        else if(palavras==4) {
            if(n==0) return "00";
            else if(n==1) return "01";
            else if(n==2) return "10";
            else if(n==3) return "11";
            else System.out.println("\"Tamanho informado de palavra é muito grande para cache. Só comporta 4 palavras.");
        }

        else{
            System.out.println("Não existe essa quantidade de palavras no cache.");
        }

        return "";
    }
    public static int converteBinInt(String l){
        int res = 0;
        int tam = l.length()-1;
        for(int i=0;i<l.length();i++){
            res+= Integer.parseInt(""+l.charAt(i))*Math.pow(2,tam--);
        }
        return res;
    }

    public void showHistorico(){
        System.out.println(historico);
        System.out.println(historicoEmHexadecimal);
        System.out.println("Hits: " + hits + " Misses: " + misses + " Total: " + (hits + misses));
    }

    public void showHistoricoEmHexadecimalCompacto(){
        for (int i=0; i<historicoEmHexadecimalCompacto.length; i++){
            System.out.print(historicoEmHexadecimalCompacto[i] + ", ");
            if (i!=0 && (i-9)%10==0) System.out.println("");
        }
        System.out.println("Hits: " + hits + " Misses: " + misses + " Total: " + (hits + misses));
    }

    public void showMemoria(){
        System.out.println("Estado final do cache");
        for (int i=0; i<linhas; i++ ){
            String tag = "não usada";
            if (memoria[i][0]!=null) tag = memoria[i][0].getTag();
            String dados = "";

            for (int j=0; j < palavras; j++){
                if (memoria[i][j]!=null) dados = dados + memoria[i][j].endereco + " ";
                else dados = "não usados";
            }

            System.out.println("Linha " + i + " Tag: " + tag + " Dados: " + dados);
        }
    }

    public void showMemoriaHexadecimal(){
        System.out.println("Estado final do cache");
        for (int i=0; i<linhas; i++ ){
            String tag = "não usada";
            if (memoria[i][0]!=null) tag = memoria[i][0].getTag();
            String dados = "";

            for (int j=0; j < palavras; j++){
                if (memoria[i][j]!=null) dados = dados + converteParaHexadecimal(memoria[i][j].endereco) + " ";
                else dados = "não usados";
            }

            System.out.println("Linha " + i + " Tag: " + tag + " Dados: " + dados);
        }
    }

    public void showMemoriaHexadecimalCompacta(){
        System.out.println("Estado final do cache");
        for (int i=0; i<linhas; i++ ){
            String tag = "não usada";
            if (memoria[i][0]!=null) tag = memoria[i][0].getTag();
            String dados = "";

            for (int j=0; j < palavras; j++){
                if (memoria[i][j]!=null) dados = dados + converteParaHexadecimal(memoria[i][j].endereco) + " ";
                else dados = "não usados";
            }

            System.out.println("Linha-" + i + " " + tag + " " + dados);
        }
    }

    public double percentAcertos(){
        return hits*100.0/historico.size();
    }

    public void showHitsMisses(){
        System.out.println("Hits: " + hits + " Misses: " + misses + " Total: " + (hits + misses));
        System.out.println("Percentual de acertos: " + percentAcertos() + "%");
    }

    public String converteParaHexadecimal (String enderecoEmBinario){

            String enderecoEmHexadecimal = "";

            for (int i=0; i<16; i=i+4) {
                String parteAnalisada = enderecoEmBinario.substring(i,i+4);
                if      (parteAnalisada.equalsIgnoreCase("0000")) enderecoEmHexadecimal += "0";
                else if (parteAnalisada.equalsIgnoreCase("0001")) enderecoEmHexadecimal += "1";
                else if (parteAnalisada.equalsIgnoreCase("0010")) enderecoEmHexadecimal += "2";
                else if (parteAnalisada.equalsIgnoreCase("0011")) enderecoEmHexadecimal += "3";
                else if (parteAnalisada.equalsIgnoreCase("0100")) enderecoEmHexadecimal += "4";
                else if (parteAnalisada.equalsIgnoreCase("0101")) enderecoEmHexadecimal += "5";
                else if (parteAnalisada.equalsIgnoreCase("0110")) enderecoEmHexadecimal += "6";
                else if (parteAnalisada.equalsIgnoreCase("0111")) enderecoEmHexadecimal += "7";
                else if (parteAnalisada.equalsIgnoreCase("1000")) enderecoEmHexadecimal += "8";
                else if (parteAnalisada.equalsIgnoreCase("1001")) enderecoEmHexadecimal += "9";
                else if (parteAnalisada.equalsIgnoreCase("1010")) enderecoEmHexadecimal += "a";
                else if (parteAnalisada.equalsIgnoreCase("1011")) enderecoEmHexadecimal += "b";
                else if (parteAnalisada.equalsIgnoreCase("1100")) enderecoEmHexadecimal += "c";
                else if (parteAnalisada.equalsIgnoreCase("1101")) enderecoEmHexadecimal += "d";
                else if (parteAnalisada.equalsIgnoreCase("1110")) enderecoEmHexadecimal += "e";
                else if (parteAnalisada.equalsIgnoreCase("1111")) enderecoEmHexadecimal += "f";
            }
        return enderecoEmHexadecimal;
    }

}
