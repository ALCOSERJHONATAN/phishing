import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PhishingScanner {
    private static final Map<String, Integer> keywords = new HashMap<>();

    static {
        // Inicializar las palabras clave y sus valores
        keywords.put("Actualización de la cuenta", 1);
        keywords.put("Confirmación de la contraseña", 1);
        keywords.put("Restablecimiento de la contraseña", 1);
        // ... Agrega las demás palabras clave
    }

    public static void main(String[] args) {
        String filePath = "ruta/del/archivo.txt";  // Reemplaza con la ruta correcta de tu archivo

        try {
            int totalPoints = 0;
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                for (Map.Entry<String, Integer> entry : keywords.entrySet()) {
                    String keyword = entry.getKey();
                    int points = entry.getValue();

                    int occurrences = countOccurrences(line, keyword);
                    totalPoints += occurrences * points;

                    if (occurrences > 0) {
                        System.out.println(keyword + ": " + occurrences + " ocurrencias, " + occurrences * points + " puntos");
                    }
                }
            }

            System.out.println("Total de puntos para todo el mensaje: " + totalPoints);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int countOccurrences(String text, String keyword) {
        int count = 0;
        int index = text.indexOf(keyword);

        while (index != -1) {
            count++;
            index = text.indexOf(keyword, index + 1);
        }

        return count;
    }
}
