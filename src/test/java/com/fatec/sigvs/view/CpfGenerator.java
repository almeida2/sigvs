package com.fatec.sigvs.view;

import java.util.Random;
public class CpfGenerator {

    public static String generateValidCpf() {
        Random random = new Random();
        int[] digits = new int[11];

        // 1. Gera os 9 primeiros dígitos aleatoriamente
        for (int i = 0; i < 9; i++) {
            digits[i] = random.nextInt(10);
        }

        // 2. Calcula o 10º dígito (primeiro verificador)
        digits[9] = calculateCheckDigit(digits, 10);

        // 3. Calcula o 11º dígito (segundo verificador)
        digits[10] = calculateCheckDigit(digits, 11);

        // 4. Concatena tudo em uma String numérica
        StringBuilder cpf = new StringBuilder();
        for (int digit : digits) {
            cpf.append(digit);
        }

        return cpf.toString();
    }

    private static int calculateCheckDigit(int[] digits, int weightStart) {
        int sum = 0;
        int weight = weightStart;
        
        // Multiplica os dígitos pelos pesos decrescentes
        for (int i = 0; i < (weightStart - 1); i++) {
            sum += digits[i] * weight--;
        }

        int remainder = sum % 11;
        return (remainder < 2) ? 0 : (11 - remainder);
    }
}