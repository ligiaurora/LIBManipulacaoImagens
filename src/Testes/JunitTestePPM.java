package Testes;




import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;
import com.aurora.imagem.*;

public class JunitTestePPM extends TestCase {
	
	
	private ImagemPPM imagemPPM;
    private Manipulacao manipulacao;

    @Before
    public void setUp() throws IOException {
        imagemPPM = new ImagemPPM("D:\\Eclipse\\IMGLIB\\exemplo.ppm", "", 298, 696, 255);
        imagemPPM.carregarImagem("D:\\Eclipse\\IMGLIB\\exemplo.ppm");
        manipulacao = new Manipulacao(imagemPPM);
    }

    @Test
    public void testInverterImagem() throws IOException {
        manipulacao.inverterImagem("D:\\Eclipse\\IMGLIB\\teste_img_invertida.ppm");
        int[][] pixels = imagemPPM.getPixels();
        // verificar se o primeiro pixel foi invertido certo
        assertEquals(255 - pixels[0][0], pixels[0][0]);
    }

    @Test
    public void testClarearImagem() throws IOException {
        manipulacao.clarearImagem("D:\\Eclipse\\IMGLIB\\teste_img_clarear.ppm");
        int[][] pixels = imagemPPM.getPixels();
        // verificar se foi clareado corretamente
        assertTrue(pixels[0][0] <= 255 && pixels[0][0] >= 30);
    }

    @Test
    public void testConverterParaPretoEBranco() throws IOException {
        manipulacao.converterParaPretoEBranco("D:\\Eclipse\\IMGLIB\\teste_img_PB.pgm");
        int[][] pixels = imagemPPM.getPixels();
        // verificar conversão
        assertTrue(pixels[0][0] == pixels[0][1] && pixels[0][1] == pixels[0][2]);
    }

    @Test
    public void testEliminarCanaisRGB() throws IOException {
        manipulacao.eliminarCanaisRGB(true, false, false, "D:\\Eclipse\\IMGLIB\\teste_eliminar_RGB.ppm");
        int[][] pixels = imagemPPM.getPixels();
        // verificar se o vermelho foi removido
        assertEquals(0, pixels[0][0]);
    }

}
