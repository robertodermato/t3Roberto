public class Main {
    public static void main(String[] args) {

        // Coloque aqui seus endereços na forma de String
        String [] enderecosEmHexadecimal = {"0000", "0002", "0004", "0006", "0008", "000a",
                "000c", "000e", "0010", "0030", "0032", "7ffc", "0034", "0036", "7ffa",
                "0038", "003a", "003c", "0056", "003e", "0040", "0042", "0044", "0046",
                "0048", "004a", "003a", "003c", "0057", "003e", "0040", "0042", "0044",
                "0046", "0048", "004a", "003a", "003c", "0058", "003e", "0040", "0042",
                "0044", "0046", "0048", "004a", "004c", "7ffa", "004e", "0050", "7ffc",
                "0052", "0054", "0012", "0014", "0016", "0018", "001a", "001c", "001e",
                "0030", "0032", "7ffc", "0034", "0036", "7ffa", "0038", "003a", "003c",
                "005a", "003e", "0040", "0042", "0044", "0046", "0048", "004a", "003a",
                "003c", "005b", "003e", "0040", "0042", "0044", "0046", "0048", "004a",
                "003a", "003c", "005c", "003e", "0040", "0042", "0044", "0046", "0048",
                "004a", "003a", "003c", "005d", "003e", "0040", "0042", "0044", "0046",
                "0048", "004a", "004c", "7ffa", "004e", "0050", "7ffc", "0052", "0054",
                "0020", "0022", "0024", "0026", "0028", "002a", "002c", "0030", "0032",
                "7ffc", "0034", "0036", "7ffa", "0038", "003a", "003c", "005e", "003e",
                "0040", "0042", "0044", "0046", "0048", "004a", "003a", "003c", "005f",
                "003e", "0040", "0042", "0044", "0046", "0048", "004a", "003a", "003c",
                "0060", "003e", "0040", "0042", "0044", "0046", "0048", "004a", "003a",
                "003c", "0061", "003e", "0040", "0042", "0044", "0046", "0048", "004a",
                "003a", "003c", "0062", "003e", "0040", "0042", "0044", "0046", "0048",
                "004a", "003a", "003c", "0063", "003e", "0040", "0042", "0044", "0046",
                "0048", "004a", "004c", "7ffa", "004e", "0050", "7ffc", "0052", "0054", "002e"};

        // Converte os endereços para binário
        String [] enderecosEmBinario = converteParaBinario (enderecosEmHexadecimal);

        // Configuração 1 - Mapeamento direto, com 9 bits para tag, 3 bits para linha, 3 bits
        // para palavra e 1 bit para seleção do byte em uma palavra (cache
        // com 8 linhas, 8 palavras por linha).
        Cache config1 = new Cache(8,8, "direto");
        adicionaEnderecosAoCache (config1, enderecosEmBinario, 9, 3, 3, 1);
        System.out.println("\n--- Configuração 1 ---");
        config1.showMemoria();
        config1.showHistorico();
        System.out.println("Percentual de acertos: " + config1.percentAcertos() + "%");

        // Configuração 2 - Mapeamento direto, com 9 bits para tag, 4 bits para linha, 2 bit
        // para palavra e 1 bit para seleção do byte em uma palavra (cache
        // com 16 linhas, 4 palavras por linha).
        Cache config2 = new Cache(16,4, "direto");
        adicionaEnderecosAoCache (config2, enderecosEmBinario, 9, 4, 2, 1);
        System.out.println("\n--- Configuração 2 ---");
        config2.showMemoria();
        config2.showHistorico();
        System.out.println("Percentual de acertos: " + config2.percentAcertos() + "%");

        // Configuração 3 - Mapeamento associativo, com 12 bits para tag, 3 bits para palavra
        // e 1 bit para seleção do byte em uma palavra (cache com 8 linhas,
        // 8 palavras por linha).
        Cache config3 = new Cache(8,8, "associativo");
        adicionaEnderecosAoCache (config3, enderecosEmBinario, 12, 0, 3, 1);
        System.out.println("\n--- Configuração 3 ---");
        config3.showMemoria();
        config3.showHistorico();
        System.out.println("Percentual de acertos: " + config3.percentAcertos() + "%");

        // Configuração 4 - Mapeamento associativo, com 13 bits para tag, 2 bits para palavra
        // e 1 bit para seleção do byte em uma palavra (cache com 16 linhas,
        // 4 palavras por linha).
        Cache config4 = new Cache(16,4, "associativo");
        adicionaEnderecosAoCache (config4, enderecosEmBinario, 13, 0, 2, 1);
        System.out.println("\n--- Configuração 4 ---");
        config4.showMemoria();
        config4.showHistorico();
        System.out.println("Percentual de acertos: " + config4.percentAcertos() + "%");
    }

    public static void adicionaEnderecosAoCache (Cache config, String [] enderecos, int tamanhoDaTag,
                                                 int tamanhoDaLinha, int tamanhoDaPalavra, int bitSelecao){
        for (int i=0; i < enderecos.length; i++){
            Endereco end = new Endereco(enderecos[i], tamanhoDaTag, tamanhoDaLinha, tamanhoDaPalavra, bitSelecao);
            config.add(end);
        }
    }

    public static String [] converteParaBinario (String [] enderecosEmHexadecimal){

            String [] enderecosEmBinario = new String [enderecosEmHexadecimal.length];

            for(int i = 0; i < enderecosEmHexadecimal.length; i++){
                String enderecoEmHexadecimal = enderecosEmHexadecimal[i];
                String enderecoEmBinario = "";

                for (int j=0; j<4; j++) {
                    char a = enderecoEmHexadecimal.charAt(j);
                    if      (a == '0') enderecoEmBinario += "0000";
                    else if (a == '1') enderecoEmBinario += "0001";
                    else if (a == '2') enderecoEmBinario += "0010";
                    else if (a == '3') enderecoEmBinario += "0011";
                    else if (a == '4') enderecoEmBinario += "0100";
                    else if (a == '5') enderecoEmBinario += "0101";
                    else if (a == '6') enderecoEmBinario += "0110";
                    else if (a == '7') enderecoEmBinario += "0111";
                    else if (a == '8') enderecoEmBinario += "1000";
                    else if (a == '9') enderecoEmBinario += "1001";
                    else if (a == 'a') enderecoEmBinario += "1010";
                    else if (a == 'b') enderecoEmBinario += "1011";
                    else if (a == 'c') enderecoEmBinario += "1100";
                    else if (a == 'd') enderecoEmBinario += "1101";
                    else if (a == 'e') enderecoEmBinario += "1110";
                    else if (a == 'f') enderecoEmBinario += "1111";
                }
                enderecosEmBinario[i] = enderecoEmBinario;
        }
        return enderecosEmBinario;
    }
}
