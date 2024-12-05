package org.example;

import java.util.ArrayList;
import java.util.List;

public class CryptarithmSolver {

    public void solve() {
        char[] letters = {'N', 'O', 'R', 'T', 'H', 'E', 'A', 'S', 'U', 'W'};
        int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<int[]> solutions = new ArrayList<>();

        long startTime = System.nanoTime();

        generatePermutations(digits, 0, letters, solutions);

        long endTime = System.nanoTime();

        System.out.println("Total Solutions Found: " + solutions.size());
        solutions.forEach(solution -> printSolution(solution, letters));
        System.out.printf("Execution time: %.2f ms%n", (endTime - startTime) / 1_000_000.0);
    }

    private void generatePermutations(int[] digits, int index, char[] letters, List<int[]> solutions) {
        if (index == digits.length) {
            if (isValidSolution(digits)) {
                solutions.add(digits.clone());
            }
            return;
        }

        for (int i = index; i < digits.length; i++) {
            if (index == 0 && digits[i] == 0) continue; // Avoid N=0
            if (index == 5 && digits[i] == 0) continue; // Avoid E=0
            if (index == 7 && digits[i] == 0) continue; // Avoid S=0
            if (index == 9 && digits[i] == 0) continue; // Avoid W=0

            swap(digits, index, i);
            generatePermutations(digits, index + 1, letters, solutions);
            swap(digits, index, i); // Backtrack
        }
    }

    private boolean isValidSolution(int[] mapping) {
        int N = mapping[0], O = mapping[1], R = mapping[2], T = mapping[3], H = mapping[4];
        int E = mapping[5], A = mapping[6], S = mapping[7], U = mapping[8], W = mapping[9];

        if (N == 0 || E == 0 || S == 0 || W == 0) return false;

        int north = N * 10000 + O * 1000 + R * 100 + T * 10 + H;
        int east = E * 1000 + A * 100 + S * 10 + T;
        int south = S * 10000 + O * 1000 + U * 100 + T * 10 + H;
        int west = W * 1000 + E * 100 + S * 10 + T;
        int earth = E * 10000 + A * 1000 + R * 100 + T * 10 + H;

        // Short-circuit if intermediate sums exceed EARTH
        if (north + east > earth) return false;
        if (north + east + south > earth) return false;
        if (north + east + south + west > earth) return false;

        return (north + east + south + west) == earth;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void printSolution(int[] mapping, char[] letters) {
        int N = mapping[0], O = mapping[1], R = mapping[2], T = mapping[3], H = mapping[4];
        int E = mapping[5], A = mapping[6], S = mapping[7], U = mapping[8], W = mapping[9];

        int north = N * 10000 + O * 1000 + R * 100 + T * 10 + H;
        int east = E * 1000 + A * 100 + S * 10 + T;
        int south = S * 10000 + O * 1000 + U * 100 + T * 10 + H;
        int west = W * 1000 + E * 100 + S * 10 + T;
        int earth = E * 10000 + A * 1000 + R * 100 + T * 10 + H;

        System.out.printf(
                "Solution: { NORTH=%d, EAST=%d, SOUTH=%d, WEST=%d, EARTH=%d }%n",
                north, east, south, west, earth
        );

        System.out.print("Mapping: { ");
        for (int i = 0; i < mapping.length; i++) {
            System.out.printf("%s=%d", letters[i], mapping[i]);
            if (i < mapping.length - 1) System.out.print(", ");
        }
        System.out.println(" }\n");
    }
}
