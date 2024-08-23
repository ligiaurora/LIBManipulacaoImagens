package com.aurora.facade;
import java.io.IOException;

import com.aurora.dimensoes.*;
import com.aurora.histograma.*;
import com.aurora.imagem.*;
import com.aurora.interpolacao.*;

/**
 * A classe ImagemFacade implementa o padrão de projeto Facade, fornecendo
 * uma interface simplificada para o uso de várias operações de manipulação
 * de imagem. A classe agrupa diferentes operações em um único ponto de acesso,
 * facilitando o uso das funcionalidades da biblioteca.
 * 
 * As operações disponíveis incluem inversão de cores, clareamento, conversão
 * para preto e branco, eliminação de canais RGB, redução de imagem, geração
 * de histograma e interpolação de imagem.
 * 
 * @autor ligiaurora@gmail.com
 */

public class ImagemFacade {
    private Manipulacao manipulacao;
    private ReducaoImagem reducaoImagem;
    private HistogramaImagem histogramaImagem;
    private Interpolacao interpolacao;

    public ImagemFacade() {
        this.reducaoImagem = new ReducaoImagem();
        this.histogramaImagem = new HistogramaImagem();
        this.interpolacao = new Interpolacao();
    }

    public void carregarImagem(Imagem imagem) throws IOException {
        imagem.carregarImagem(imagem.getCaminho());
        this.manipulacao = new Manipulacao(imagem);
    }

    public void salvarImagem(Imagem imagem, String destino) throws IOException {
        imagem.salvarImagem(destino);
    }

    public void inverterImagem(Imagem imagem, String outputFilePath) throws IOException {
        this.manipulacao = new Manipulacao(imagem);
        manipulacao.inverterImagem(outputFilePath);
    }

    public void clarearImagem(Imagem imagem, String outputFilePath) throws IOException {
        this.manipulacao = new Manipulacao(imagem);
        manipulacao.clarearImagem(outputFilePath);
    }

    public void converterParaPretoEBranco(Imagem imagem, String outputFilePath) throws IOException {
        this.manipulacao = new Manipulacao(imagem);
        manipulacao.converterParaPretoEBranco(outputFilePath);
    }

    public void eliminarCanaisRGB(Imagem imagem, boolean removerVermelho, boolean removerVerde, boolean removerAzul, String outputFilePath) throws IOException {
        this.manipulacao = new Manipulacao(imagem);
        manipulacao.eliminarCanaisRGB(removerVermelho, removerVerde, removerAzul, outputFilePath);
    }

    public void reduzirImagem(Imagem imagem, String outputFilePath, int fatorReducao) throws IOException {
        reducaoImagem.reduzirImagem(imagem, outputFilePath, fatorReducao);
    }

    public void gerarHistograma(Imagem imagem) {
        histogramaImagem.gerarHistograma(imagem);
    }

    public void interpolarImagem(Imagem imagem, String outputFilePath) throws IOException {
        interpolacao.interpolarImagem(imagem, outputFilePath);
    }
}