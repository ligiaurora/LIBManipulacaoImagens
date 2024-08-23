package com.aurora.imagem;


import java.io.IOException;
import com.aurora.util.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe ImagemPGM estende a classe abstrata Imagem e implementa
 * a funcionalidade para carregar, salvar e manipular imagens no formato PGM.
 * 
 * O formato PGM armazena imagens em tons de cinza, onde cada pixel
 * possui um único valor que representa a intensidade da cor (de 0 a 255).
 * 
 * @autor ligiaurora@gmail.com
 */

public class ImagemPGM extends Imagem {
    private int valorMaximoCor;
    private int[][] pixels;

    public ImagemPGM(String caminho, String destino, int largura, int altura, int valorMaximoCor) {
        super(caminho, destino, ExtensoesImagens.PGM);
        this.valorMaximoCor = valorMaximoCor;
        this.pixels = new int[altura][largura];
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

        if (!linhas.get(0).equals("P2")) {
            throw new IOException("Formato de arquivo PGM inválido");
        }

        String[] dimensoes = linhas.get(1).split(" ");
        int largura = Integer.parseInt(dimensoes[0]);
        int altura = Integer.parseInt(dimensoes[1]);
        this.valorMaximoCor = Integer.parseInt(linhas.get(2));
        this.pixels = new int[altura][largura];

        for (int y = 0; y < altura; y++) {
            String[] valoresPixels = linhas.get(y + 3).split(" ");
            for (int x = 0; x < largura; x++) {
                this.pixels[y][x] = Integer.parseInt(valoresPixels[x]);
            }
        }

        // Validação para garantir que a imagem foi carregada corretamente
        if (pixels == null || pixels.length != altura || pixels[0].length != largura) {
            throw new IllegalStateException("Matriz de pixels não foi inicializada corretamente para a imagem PGM.");
        }
    }

    @Override
    public void salvarImagem(String caminhoArquivo) throws IOException {
        List<String> linhas = new ArrayList<>();
        linhas.add("P2");
        linhas.add(pixels[0].length + " " + pixels.length);
        linhas.add(String.valueOf(valorMaximoCor));

        for (int[] linha : pixels) {
            StringBuilder sb = new StringBuilder();
            for (int pixel : linha) {
                sb.append(pixel).append(" ");
            }
            linhas.add(sb.toString().trim());
        }

        escreverArquivo(caminhoArquivo, linhas);
    }
}