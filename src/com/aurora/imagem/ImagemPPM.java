package com.aurora.imagem;

import com.aurora.util.ExtensoesImagens;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe ImagemPPM estende a classe abstrata Imagem e implementa
 * a funcionalidade para carregar, salvar e manipular imagens no formato PPM.
 * 
 * O formato PPM armazena as cores de cada pixel em três canais: vermelho, 
 * verde e azul (RGB). Por isso, o array de pixels deve considerar três valores
 * para cada pixel.
 * 
 * @autor ligiaurora@gmail.com
 */

public class ImagemPPM extends Imagem {
    private int valorMaximoCor;
    private int[][] pixels;

    public ImagemPPM(String caminho, String destino, int largura, int altura, int valorMaximoCor) {
        super(caminho, destino, ExtensoesImagens.PPM);
        this.valorMaximoCor = valorMaximoCor;
        this.pixels = new int[altura][largura * 3]; //(R, G, B)
    }

    public int getValorMaximoCor() {
        return valorMaximoCor;
    }

    public void setValorMaximoCor(int valorMaximoCor) {
        this.valorMaximoCor = valorMaximoCor;
    }

    public int[][] getPixels() {
        return pixels;
    }

    public void setPixels(int[][] pixels) {
        this.pixels = pixels;
    }

    @Override
    public void carregarImagem(String caminhoArquivo) throws IOException {
        List<String> linhas = lerArquivo(caminhoArquivo);

        if (!linhas.get(0).equals("P3")) {
            throw new IOException("Formato de arquivo PPM inválido");
        }

        String[] dimensoes = linhas.get(1).split(" ");
        int largura = Integer.parseInt(dimensoes[0]);
        int altura = Integer.parseInt(dimensoes[1]);
        this.valorMaximoCor = Integer.parseInt(linhas.get(2));

        this.pixels = new int[altura][largura * 3];
        int linhaAtual = 3;
        int pixelIndex = 0;

        List<Integer> valoresPixels = new ArrayList<>();
        for (int i = linhaAtual; i < linhas.size(); i++) {
            String[] valores = linhas.get(i).trim().split("\\s+");
            for (String valor : valores) {
                valoresPixels.add(Integer.parseInt(valor));
            }
        }

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura * 3; x++) {
                if (pixelIndex < valoresPixels.size()) {
                    this.pixels[y][x] = valoresPixels.get(pixelIndex++);
                } else {
                    throw new IOException("O número de pixels no arquivo PPM é menor do que o esperado.");
                }
            }
        }

        // Validação para garantir que a imagem foi carregada 
        if (pixels == null || pixels.length != altura || pixels[0].length != largura * 3) {
            throw new IllegalStateException("Matriz de pixels não foi inicializada corretamente para a imagem PPM.");
        }
    }

    @Override
    public void salvarImagem(String caminhoArquivo) throws IOException {
        List<String> linhas = new ArrayList<>();
        linhas.add("P3");
        linhas.add(pixels[0].length / 3 + " " + pixels.length);
        linhas.add(String.valueOf(valorMaximoCor));

        for (int y = 0; y < pixels.length; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < pixels[0].length; x++) {
                sb.append(pixels[y][x]).append(" ");
                if (x % 3 == 2) {  // Adiciona quebra de linha após cada pixel completo (RGB)
                    sb.append("\n");
                }
            }
            linhas.add(sb.toString().trim());
        }

        escreverArquivo(caminhoArquivo, linhas);
    }
}