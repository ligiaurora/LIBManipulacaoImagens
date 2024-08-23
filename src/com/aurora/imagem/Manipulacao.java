package com.aurora.imagem;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * A classe Manipulacao tem métodos para manipular imagens como inverter cores,
 * clarear, converter para preto e branco, e eliminar canais RGB.
 * 
 * Esta classe trabalha diretamente com uma instância da classe Imagem e aplica
 * operações de manipulação nos dados dos pixels.
 * 
 * @autor ligiaurora@gmail.com
 */

public class Manipulacao {
    private Imagem imagem;

    public Manipulacao(Imagem imagem) {
        this.imagem = imagem;
    }
    
    //Get e setters.
    public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}
	
	/**
     * Inverte as cores da imagem. Para imagens PPM, inverte cada canal RGB.
     * Para imagens PGM e PBM, inverte a intensidade do pixel.
     */

	public void inverterImagem(String outputFilePath) throws IOException {
	    int[][] pixels = imagem.getPixels();
	    if (pixels == null) {
	        throw new IllegalStateException("A imagem não foi carregada corretamente ou os pixels são nulos.");
	    }

	    if (imagem instanceof ImagemPPM) {
	        int valorMaximoCor = ((ImagemPPM) imagem).getValorMaximoCor();
	        for (int y = 0; y < pixels.length; y++) {
	            for (int x = 0; x < pixels[0].length; x += 3) {
	                pixels[y][x] = valorMaximoCor - pixels[y][x];
	                pixels[y][x + 1] = valorMaximoCor - pixels[y][x + 1];
	                pixels[y][x + 2] = valorMaximoCor - pixels[y][x + 2];
	            }
	        }
	    } else if (imagem instanceof ImagemPGM) {
	        int valorMaximoCor = ((ImagemPGM) imagem).getValorMaximoCor();
	        for (int y = 0; y < pixels.length; y++) {
	            for (int x = 0; x < pixels[0].length; x++) {
	                pixels[y][x] = valorMaximoCor - pixels[y][x];
	            }
	        }
	    } else if (imagem instanceof ImagemPBM) {
	        for (int y = 0; y < pixels.length; y++) {
	            for (int x = 0; x < pixels[0].length; x++) {
	                pixels[y][x] = 1 - pixels[y][x]; // Inverte os valores entre 0 e 1
	            }
	        }
	    }

	    imagem.setPixels(pixels);
	    imagem.salvarImagem(imagem.getDestino() + outputFilePath);
	}
	
	 /**
     * Vai clarear a imagem aumentando a intensidade de cada pixel em um valor fixo.
     */

	public void clarearImagem(String outputFilePath) throws IOException {
	    int[][] pixels = imagem.getPixels();
	    if (pixels == null) {
	        throw new IllegalStateException("A imagem não foi carregada corretamente ou os pixels são nulos.");
	    }

	    if (imagem instanceof ImagemPBM) {
	        throw new UnsupportedOperationException("A operação de clarear não é suportada para imagens PBM.");
	    }

	    for (int y = 0; y < pixels.length; y++) {
	        for (int x = 0; x < pixels[0].length; x++) {
	            pixels[y][x] = Math.min(255, pixels[y][x] + 30);
	        }
	    }

	    imagem.setPixels(pixels);
	    imagem.salvarImagem(imagem.getDestino()+outputFilePath);
	}
	
	

	/**
     * Converte uma imagem PPM para tons de cinza e salva como um arquivo PGM.
     */
	public void converterParaPretoEBranco(String outputFilePath) throws IOException {
        int[][] pixels = imagem.getPixels();
        if (pixels == null) {
            throw new IllegalStateException("A imagem não foi carregada corretamente ou os pixels são nulos.");
        }

        if (imagem instanceof ImagemPPM) {
            int largura = pixels[0].length / 3;  
            int altura = pixels.length;
            int[][] pixelsPretoBranco = new int[altura][largura];

            for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {
                    int r = pixels[y][x * 3];
                    int g = pixels[y][x * 3 + 1];
                    int b = pixels[y][x * 3 + 2];
                    int media = (r + g + b) / 3;
                    pixelsPretoBranco[y][x] = media;  // Grava o valor em tons de cinza
                }
            }

            // Criando o conteúdo do arquivo PGM diretamente aqui
            List<String> linhas = new ArrayList<>();
            linhas.add("P2");  // Cabeçalho do arquivo PGM
            linhas.add(largura + " " + altura);  // Largura e altura
            linhas.add("255");  // Valor máximo de cor

            for (int[] linha : pixelsPretoBranco) {
                StringBuilder sb = new StringBuilder();
                for (int pixel : linha) {
                    sb.append(pixel).append(" ");
                }
                linhas.add(sb.toString().trim());
            }

            // Salvando o arquivo PGM
            Files.write(Paths.get(outputFilePath), linhas);
        }
    }
	
	  /**
     * Elimina os canais de cor especificados (vermelho, verde, azul) em uma imagem PPM.
     */
    public void eliminarCanaisRGB(boolean removerVermelho, boolean removerVerde, boolean removerAzul, String outputFilePath) throws IOException {
        int[][] pixels = imagem.getPixels();
        if (pixels == null) {
            throw new IllegalStateException("A imagem não foi carregada corretamente ou os pixels são nulos.");
        }

        if (imagem instanceof ImagemPPM) {
            for (int y = 0; y < pixels.length; y++) {
                for (int x = 0; x < pixels[0].length; x += 3) {
                    if (removerVermelho) {
                        pixels[y][x] = 0; // vermelho
                    }
                    if (removerVerde) {
                        pixels[y][x + 1] = 0; // verde
                    }
                    if (removerAzul) {
                        pixels[y][x + 2] = 0; // azul
                    }
                }
            }
            imagem.setPixels(pixels);
            imagem.salvarImagem(outputFilePath);
        } else {
            throw new UnsupportedOperationException("Eliminação de canais RGB só é suportada para imagens PPM.");
        }
    }
    
}