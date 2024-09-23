import java.util.Scanner;
import java.util.Arrays;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        int[] abilities = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            abilities[i] = sc.nextInt();
        }
        
        TeamFormation teamFormation = new TeamFormation(abilities);
        int result = teamFormation.findMinSum();
        
        System.out.println(result);
    }
}
 
class TeamFormation {
    private int[] abilities; 
    
    public TeamFormation(int[] abilities) {
        this.abilities = abilities;
        Arrays.sort(this.abilities);
    }
    
    public int findMinSum() {
        int n = abilities.length / 2; 
        int minSum = Integer.MAX_VALUE; 
        for (int i = 0; i < n; i++) {
            int sum = abilities[i] + abilities[abilities.length - i - 1];
            minSum = Math.min(minSum, sum);
        }
        return minSum;
    }
}