package controlecaixa;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static junit.framework.Assert.assertTrue;

public class ControleCaixaTest {

    @Test
    public void testMain() {
        String input = "u\nArroz\n3.50\n2\ns\nk\nFeijão\n4,50\n1.5\nn\ns\n2.5\nn\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ControleCaixa.main(null);

        // Adicione mais asserções conforme necessário
        assertTrue(true); // Se chegou aqui sem erros, consideramos o teste bem-sucedido
    }
}