import java.util.*;
import java.io.*;

public class Main {

    static int numPeople, numConnections;
    static int[][] distanceMatrix;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        numPeople = Integer.parseInt(tokenizer.nextToken());
        numConnections = Integer.parseInt(tokenizer.nextToken());

        distanceMatrix = new int[numPeople + 1][numPeople + 1];

        for (int i = 1; i <= numPeople; i++) {
            for (int j = 1; j <= numPeople; j++) {
                distanceMatrix[i][j] = 999999999;
                if (i == j) {
                    distanceMatrix[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < numConnections; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int personA = Integer.parseInt(tokenizer.nextToken());
            int personB = Integer.parseInt(tokenizer.nextToken());

            distanceMatrix[personA][personB] = 1;
            distanceMatrix[personB][personA] = 1;
        }

        for (int k = 1; k <= numPeople; k++) {
            for (int i = 1; i <= numPeople; i++) {
                for (int j = 1; j <= numPeople; j++) {
                    distanceMatrix[i][j] = Math.min(distanceMatrix[i][j], distanceMatrix[i][k] + distanceMatrix[k][j]);
                }
            }
        }

        int minSumOfDistances = 999999999;
        int personWithMinSum = 0;

        for (int i = 1; i <= numPeople; i++) {
            int sumOfDistances = 0;
            for (int j = 1; j <= numPeople; j++) {
                sumOfDistances += distanceMatrix[i][j];
            }

            if (minSumOfDistances > sumOfDistances) {
                minSumOfDistances = sumOfDistances;
                personWithMinSum = i;
            }
        }

        writer.write(personWithMinSum + "");
        writer.flush();
        writer.close();
    }
}
