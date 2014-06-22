package Clases;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author CAC Clase con validaciones de proposito general.
 * 
 */
public class Auxiliar {

	/**
	 * @param fecha
	 *            la fecha debe ser yyyy-MM-dd
	 * @return devuelve TRUE si la fecha es valida
	 */
	public boolean validarFecha(String fecha) {

		if (fecha == null)
			return false;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // anio-mes-dia

		if (fecha.trim().length() != dateFormat.toPattern().length())
			return false;

		dateFormat.setLenient(false);

		try {
			dateFormat.parse(fecha.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	public static String contenarArrayList(ArrayList<String> base) {
		String resultado = "";
		for (int i = 0; i < base.size(); i++)
			resultado = resultado + base.get(i) + "\r\n";
		return resultado;

	}

	/**
	 * @param number
	 *            numero a verificar
	 * @return devuelve TRUE, si el string de entrada es un numero entero
	 */
	public static boolean isInteger(String number) {
		boolean prueba;

		try {
			Long.parseLong(number);
			prueba = true;
		} catch (Exception e) {
			prueba = false;
		}
		return prueba;
	}

	/**
	 * @param number
	 *            Numero a verificar
	 * @return Devuelve TRUE , entrada es un Double
	 */
	public static boolean isDouble(String number) {
		boolean prueba;

		try {
			Double.parseDouble(number);
			prueba = true;
		} catch (Exception e) {
			prueba = false;
		}
		return prueba;
	}

	/**
	 * @param number
	 *            Numero a verificar
	 * @return Devuelve TRUE , entrada es un FLOAT
	 */
	public static boolean isFloat(String number) {
		boolean prueba;

		try {
			Float.parseFloat(number);
			prueba = true;
		} catch (Exception e) {
			prueba = false;
		}
		return prueba;
	}

	// Nota: ademas de validar DNI, tambien valida numero de matricula
	public static boolean isValidDNI(String number) {
		if (number == null)
			return false;
		while (number.contains(".")) {
			number = number.replace(".", "");
		}
		return isInteger(number);

	}

	public static boolean isValidCUIT(String number) {
		if (number == null)
			return false;
		while (number.contains("-")) {
			number = number.replace("-", "");
		}
		return isInteger(number);
	}

	public static boolean isValidTelephone(String number) {
		if (number == null)
			return false;
		while (number.contains(".")) {
			number = number.replace("-", "");
		}
		return isInteger(number);
	}

	/**
	 * @param precioBase
	 *            entrada
	 * @return devuelve un texto de precio
	 */
	public static String FormateaPrecios(Float precio) {
		String precioBase = precio.toString();
		DecimalFormat formateador = new DecimalFormat("###.###,##");
		return "$" + formateador.format(precioBase);
	}

	public static boolean isValidEmail(String email) {
		String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(PATTERN_EMAIL);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();

	}

	/**
	 * @param lineaBase
	 *            linea a recortar
	 * @return Devuelve la linea cortada si el largo es superior al permitido
	 */
	public static String cortarTextoMultilinea(String lineaBase) {
		if (lineaBase.length() > 50)
			return lineaBase.substring(0, 50).trim();
		return lineaBase.trim();
	}

}
