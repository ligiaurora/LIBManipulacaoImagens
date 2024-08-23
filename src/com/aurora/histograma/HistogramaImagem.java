package com.aurora.histograma;
import com.aurora.imagem.*;

/**
 * A classe HistogramaImagem oferece a funcionalidade de gerar um histograma
 * para uma imagem. O histograma exibe a distribuição de intensidades de cor
 * na imagem, permitindo uma análise visual dos valores de pixel.
 * 
 * Esta classe é aplicável para os formatos de imagem PPM, PGM e PBM.
 * 
 * @autor ligiaurora@gmail.com
 */

public class HistogramaImagem {

    public void gerarHistograma(Imagem imagem) {
        int[][] pixels = imagem.getPixels();
        int altura = pixels.length;
        int largura = pixels[0].length;

        int maxValorCor = 255; // Pode ser ajustado de acordo com a imagem

        int[] histograma = new int[maxValorCor + 1];

        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                histograma[pixels[i][j]]++;
            }
        }

        exibirHistograma(histograma);
    }

    private void exibirHistograma(int[] histograma) {
        for (int i = 0; i < histograma.length; i++) {
            if (histograma[i] > 0) {
                System.out.println("Intensidade " + i + ": " + histograma[i]);
            }
        }
    }
}