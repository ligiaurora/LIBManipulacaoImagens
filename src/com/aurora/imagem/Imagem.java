package com.aurora.imagem;
import com.aurora.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


/**
 * A classe Imagem serve como uma classe abstrata que define a estrutura 
 * básica para carregar e salvar imagens em diferentes formatos.
 * 
 * Cada formato de imagem (PPM, PGM, PBM) possui uma estrutura diferente, 
 * exigindo a implementação de subclasses ( que no caso, vão ser as classes ImagemPBM, ImagemPGM e ImagemPPM),
 * específicas para tratar a leitura e escrita de cada formato de imagem.
 * 
 * Esta classe fornece métodos abstratos que devem ser implementados 
 * pelas subclasses para manipular as diferentes representações de imagem.
 * 
 * Além disso, a classe Imagem oferece métodos utilitários para leitura e 
 * escrita de arquivos, que são utilizados pelas subclasses durante a 
 * manipulação dos arquivos de imagem.
 * 
 * @author Ligia Aurora (ligiaurora@gmail.com)
 * @version 1 versão da classe
 */

public abstract class Imagem {
    private String caminho;
    private String destino;
    protected ExtensoesImagens extensao;

    public Imagem(String caminho, String destino, ExtensoesImagens extensao) {
        this.caminho = caminho;
        this.destino = destino;
        this.extensao = extensao;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getDestino() {
        return destino;
    }

    public ExtensoesImagens getExtensao() {
        return extensao;
    }

    public void setExtensao(ExtensoesImagens extensao) {
        this.extensao = extensao;
    }

    public abstract int[][] getPixels();

    public abstract void setPixels(int[][] pixels);

    public abstract void carregarImagem(String caminhoArquivo) throws IOException;

    public abstract void salvarImagem(String caminhoArquivo) throws IOException;

    protected List<String> lerArquivo(String caminhoArquivo) throws IOException {
        return Files.readAllLines(Paths.get(caminhoArquivo));
    }

    protected void escreverArquivo(String caminhoArquivo, List<String> conteudo) throws IOException {
        Files.write(Paths.get(caminhoArquivo), conteudo);
    }

    @Override
    public String toString() {
        return "Imagem [caminho=" + caminho + ", destino=" + destino + ", extensao=" + extensao + "]";
    }
}