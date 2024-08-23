package Testes;



import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;
import com.aurora.imagem.*;


public class JunitTestePGM extends TestCase {

    private ImagemPGM imagemPGM;
    private Manipulacao manipulacao;

    @Before
    public void setUp() throws IOException {
        imagemPGM = new ImagemPGM("D:\\Eclipse\\IMGLIB\\exemplo_cinza.pgm", "", 298, 696, 255);
        imagemPGM.carregarImagem("D:\\Eclipse\\IMGLIB\\exemplo_cinza.pgm");
        manipulacao = new Manipulacao(imagemPGM);
    }

    @Test
    public void testInverterImagem() throws IOException {
        manipulacao.inverterImagem("D:\\Eclipse\\IMGLIB\\teste_img_invertida.pgm");
        int[][] pixels = imagemPGM.getPixels();
     
        assertEquals(255 - pixels[0][0], pixels[0][0]);
    }

    @Test
    public void testClarearImagem() throws IOException {
        manipulacao.clarearImagem("D:\\Eclipse\\IMGLIB\\teste_img_clarear.pgm");
        int[][] pixels = imagemPGM.getPixels();
        
        assertTrue(pixels[0][0] <= 255 && pixels[0][0] >= 30);
    }

}
