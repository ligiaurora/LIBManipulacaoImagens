package com.aurora.imagem;
import java.io.IOException;
import com.aurora.util.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe ImagemPBM estende a classe abstrata Imagem e implementa
 * a funcionalidade para carregar, salvar e manipular imagens no formato PBM.
 * 
 * O formato PBM armazena imagens binárias, onde cada pixel é representado 
 * por um único bit (0 ou 1), o que torna o formato ideal para imagens 
 * em preto e branco puro.
 * 
 * @autor ligiaurora@gmail.com
 */

public class ImagemPBM extends Imagem {
    private int[][] pixels;

    public ImagemPBM(String caminho, String destino, int largura, int altura) {
        super(caminho, destino, ExtensoesImagens.PBM);
        this.pixels = new int[altura][largura];
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

        if (!linhas.get(0).equals("P1")) {
            throw new IOException("Formato de arquivo PBM inválido");
        }

        String[] dimensoes = linhas.get(1).split(" ");
        int largura = Integer.parseInt(dimensoes[0]);
        int altura = Integer.parseInt(dimensoes[1]);
        this.pixels = new int[altura][largura];

        for (int y = 0; y < altura; y++) {
            String[] valoresPixels = linhas.get(y + 2).split(" ");
            for (int x = 0; x < largura; x++) {
                this.pixels[y][x] = Integer.parseInt(valoresPixels[x]);
            }
        }

        // Validação para garantir que a imagem foi carregada corretamente
        if (pixels == null || pixels.length != altura || pixels[0].length != largura) {
            throw new IllegalStateException("Matriz de pixels não foi inicializada corretamente para a imagem PBM.");
        }
    }

    @Override
    public void salvarImagem(String caminhoArquivo) throws IOException {
        List<String> linhas = new ArrayList<>();
        linhas.add("P1");
        linhas.add(pixels[0].length + " " + pixels.length);

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