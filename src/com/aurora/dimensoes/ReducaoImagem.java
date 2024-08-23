package com.aurora.dimensoes;
import java.io.IOException;
import com.aurora.imagem.*;

/**
 * A classe ReducaoImagem oferece uma funcionalidade para reduzir
 * a resolução de imagens. O método reduzirImagem reduz a imagem 
 * por um fator especificado, o que significa que a nova imagem terá
 * uma largura e altura reduzidas.
 * 
 * Esta classe é aplicável para os formatos de imagem PPM, PGM e PBM.
 * 
 * @autor ligiaurora@gmail.com
 */

public class ReducaoImagem {

    public void reduzirImagem(Imagem imagem, String outputFilePath, int fatorReducao) throws IOException {
        int[][] pixelsOriginais = imagem.getPixels();
        int alturaOriginal = pixelsOriginais.length;
        int larguraOriginal = pixelsOriginais[0].length;

        int novaAltura = alturaOriginal / fatorReducao;
        int novaLargura;

        int[][] pixelsReduzidos;

        // Verifica se é uma imagem PPM
        if (imagem instanceof ImagemPPM) {
            novaLargura = larguraOriginal / fatorReducao / 3 * 3;  // Mantém a largura múltipla de 3 para os canais RGB
            pixelsReduzidos = new int[novaAltura][novaLargura];

            for (int i = 0; i < novaAltura; i++) {
                for (int j = 0; j < novaLargura; j += 3) {
                    for (int c = 0; c < 3; c++) {  // Itera sobre os 3 canais RGB
                        pixelsReduzidos[i][j + c] = pixelsOriginais[i * fatorReducao][(j / 3) * fatorReducao * 3 + c];
                    }
                }
            }
        } else {
        	
            // Imagens PGM e PBM, não tem canais separados
            novaLargura = larguraOriginal / fatorReducao;
            pixelsReduzidos = new int[novaAltura][novaLargura];

            for (int i = 0; i < novaAltura; i++) {
                for (int j = 0; j < novaLargura; j++) {
                    pixelsReduzidos[i][j] = pixelsOriginais[i * fatorReducao][j * fatorReducao];
                }
            }
        }

        imagem.setPixels(pixelsReduzidos);
        imagem.salvarImagem(imagem.getDestino() + outputFilePath);
    }
}