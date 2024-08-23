package com.aurora.interpolacao;


import java.io.IOException;
import com.aurora.imagem.*;

/**
 * A classe Interpolacao oferece uma funcionalidade para duplicar a resolução
 * de uma imagem através de interpolação simples, replicando os pixels da imagem original.
 * 
 * Esta classe é aplicável para os formatos de imagem PPM, PGM e PBM.
 * 
 * @autor ligiaurora@gmail.com
 */


public class Interpolacao {

    public void interpolarImagem(Imagem imagem, String outputFilePath) throws IOException {
        int[][] pixelsOriginais = imagem.getPixels();
        int alturaOriginal = pixelsOriginais.length;
        int larguraOriginal = pixelsOriginais[0].length;

        int novaAltura = alturaOriginal * 2;
        int novaLargura = larguraOriginal * 2;
        int[][] pixelsInterpolados = new int[novaAltura][novaLargura];

        for (int i = 0; i < novaAltura; i++) {
            for (int j = 0; j < novaLargura; j++) {
                pixelsInterpolados[i][j] = pixelsOriginais[i / 2][j / 2];
            }
        }

        imagem.setPixels(pixelsInterpolados);
        imagem.salvarImagem(imagem.getDestino()+ outputFilePath);
    }
}