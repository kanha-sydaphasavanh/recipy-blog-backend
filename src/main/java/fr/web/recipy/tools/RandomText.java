package fr.web.recipy.tools;

import java.util.Random;

public class RandomText {
    public static String getRandomText() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        // Genere un mot de passe aleatoire de 0 à 9 et de A à Z(Majuscule/minuscule compris) en caractere ASCII
        // appendCodePoint => recupere le String en code ASCII pour le dechiffrer en charactere alphanumerique

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }
}
