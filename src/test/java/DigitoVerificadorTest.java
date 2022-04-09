import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DigitoVerificadorTest {
    private String [] rutIngresados;
    private String [] rutEsperados;
    private String [] rutSinDigitosEsperados;

    @BeforeEach
    public void init() {
        rutIngresados = new String [] {"6.49.2-77.1-k", "2.1-029.59.4-1", "23.4.92.543-3"};
        rutEsperados = new String [] {"6492771k", "210295941", "234925433"};
        rutSinDigitosEsperados = new String [] {"6492771", "21029594", "23492543"};
    }

    @Test
    public void limpiarRutTest() {
        boolean rutCorrecto = true;

        for(int i = 0; i < rutIngresados.length; i++) {
            String rutObtenido = DigitoVerificador.limpiarRut(rutIngresados[i]);

            if(!rutObtenido.equalsIgnoreCase(rutEsperados[i])) {
                rutCorrecto = false;
                break;
            }
        }

        assertTrue(rutCorrecto);
    }

    @Test
    public void obtenerRutSinDigitoTest() {
        boolean rutSinDigito = true;

        for(int i = 0; i < rutEsperados.length; i++) {
            String rutSinDigitoObtenido = DigitoVerificador.obtenerRutSinDigito(rutEsperados[i]);

            if(!rutSinDigitoObtenido.equalsIgnoreCase(rutSinDigitosEsperados[i])) {
                rutSinDigito = false;
            }
        }

        assertTrue(rutSinDigito);
    }

    @AfterEach
    void teardown () {
        rutIngresados = null;
        rutEsperados = null;
        rutSinDigitosEsperados = null;
    }
}
