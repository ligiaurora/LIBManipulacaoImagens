package Testes;



import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;
import com.aurora.imagem.*;

public class JunitTestePBM extends TestCase {

    private ImagemPBM imagemPBM;
    private Manipulacao manipulacao;

    @Before
    public void setUp() throws IOException {
        imagemPBM = new ImagemPBM("D:\\Eclipse\\IMGLIB\\exemplo_PBM.pbm", "", 298, 696);
        imagemPBM.carregarImagem("D:\\Eclipse\\IMGLIB\\exemplo_PBM.pbm");
        manipulacao = new Manipulacao(imagemPBM);
    }

    @Test
    public void testInverterImagem() throws IOException {
        manipulacao.inverterImagem("D:\\Eclipse\\IMGLIB\\teste_img_invertida.pbm");
        int[][] pixels = imagemPBM.getPixels();
        // verificar se o primeiro pixel foi invertido certo
        assertEquals(1 - pixels[0][0], pixels[0][0]);
    }

}
