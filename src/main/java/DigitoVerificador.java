import java.util.Scanner;

public class DigitoVerificador {
    private final static Scanner scanner = new Scanner(System.in);
    private final static int VALOR_MAXIMO = 99999999;
    private final static int VALOR_MINIMO = 1000000;

    public static void main(String[] args) {
        llamarFunciones();
        scanner.close();
    }

    public static String pedirRut() {
        System.out.println("Por favor ingrese su rut.");
        return scanner.nextLine();
    }

    public static String limpiarRut(String input) {
        input = input.replaceAll("\\p{Punct}","");
        return input;
    }

    public static String obtenerRutSinDigito(String rut) {
        return rut.substring(0, rut.length() - 1);
    }

    public static int verificarNumeroRut(String rutSinDigito) {
        int rut;
        try {
            rut = Integer.parseInt(rutSinDigito);
        } catch (NumberFormatException e) {
            rut = 1;
        }

        return rut;
    }

    public static String obtenerDigito(String rut) {
        return rut.substring(rut.length() - 1);
    }

    public static int convertirDigitoAInt(String digitoVerificador) {
        int digitoVerificadorUsuario;

        if(digitoVerificador.equalsIgnoreCase("k")) {
            digitoVerificadorUsuario = 10;
        }else if(digitoVerificador.equals("0")) {
            digitoVerificadorUsuario = 11;
        }else {
            try {
                digitoVerificadorUsuario = Integer.parseInt(digitoVerificador);
            }catch(NumberFormatException e) {
                digitoVerificadorUsuario = 12;
            }
        }
        return digitoVerificadorUsuario;
    }

    public static char [] convertirIntAArreglo(int rut) {
        return String.valueOf(rut).toCharArray();
    }

    public static char [] invertirRut(char[] rutChar) {
        char[] rutInvertido = new char[rutChar.length];

        for (int i = rutChar.length - 1, j = 0; i >= 0; i--, j++) {
            rutInvertido[j] = rutChar[i];
        }

        return rutInvertido;
    }

    public static int [] crearCadenaParaMultiplicar(char[] rutInvertido) {
        int[] cadenaParaMultiplicar = new int[rutInvertido.length];
        int numeroMultiplicador = 2;

        for (int i = 0; i < rutInvertido.length; i++) {
            if (numeroMultiplicador < 8) {
                cadenaParaMultiplicar[i] = numeroMultiplicador;
                numeroMultiplicador++;
            } else {
                numeroMultiplicador = 2;
                i--;
            }
        }

        return cadenaParaMultiplicar;
    }

    public static int multiplicarCadenas(int[] cadenaParaMultiplicar, char[] rutInvertido) {
        int multiplicacionCadenas = 0;

        for (int i = 0; i < rutInvertido.length; i++) {
            int digitoRutAMultiplicar = convertirCharAInt(rutInvertido[i]);
            int auxMultiplicacion = cadenaParaMultiplicar[i] * digitoRutAMultiplicar;
            multiplicacionCadenas += auxMultiplicacion;
        }

        return multiplicacionCadenas;
    }

    public static int calcularDigitoVerificador (int multiplicacionCadenas) {
        int division = multiplicacionCadenas / 11;
        int multiplicacionFinal = division * 11;

        return 11 - (multiplicacionCadenas - multiplicacionFinal);
    }

    public static int convertirCharAInt(char caracterRutInvertido) {
        String digitoString = String.valueOf(caracterRutInvertido);
        return Integer.parseInt(digitoString);
    }

    public static void imprimirDigitoVerificador(int resultadoDigitoVerificador) {

        if (resultadoDigitoVerificador == 11) {
            System.out.println("El dígito verificador es: 0");

        } else if (resultadoDigitoVerificador == 10) {
            System.out.println("El dígito verificador es: k");

        } else {
            System.out.println("El dígito verificador es: " + resultadoDigitoVerificador);
        }
    }

    public static void llamarFunciones() {
        int rut;
        String digitoVerificador;

        do {
            String input = pedirRut();
            input = limpiarRut(input);
            String rutSinDigito = obtenerRutSinDigito(input);
            digitoVerificador = obtenerDigito(input);
            rut = verificarNumeroRut(rutSinDigito);
        }while(rut < VALOR_MINIMO || rut > VALOR_MAXIMO);

        char[] rutChar = convertirIntAArreglo(rut);
        char[] rutInvertido = invertirRut(rutChar);
        int[] cadenaParaMultiplicar = crearCadenaParaMultiplicar(rutInvertido);
        int multiplicacionCadenas = multiplicarCadenas(cadenaParaMultiplicar, rutInvertido);
        int resultadoDigitoVerificador = calcularDigitoVerificador(multiplicacionCadenas);
        int digitoVerificadorUsuario = convertirDigitoAInt(digitoVerificador);

        if(digitoVerificadorUsuario == resultadoDigitoVerificador) {
            imprimirDigitoVerificador(resultadoDigitoVerificador);
        }else {
            System.out.print("Ha ingresado un digito verificador inválido. ");
            imprimirDigitoVerificador(resultadoDigitoVerificador);
        }
    }
}
