import java.util.*;

public class TowerNetwork {
    static int[] findBestTower(int[][] towers, int radius) {
        int n = towers.length;
        int[] bestTower = null;
        double bestQuality = 0;
        
        for (int i = 0; i < n; i++) {
            int x1 = towers[i][0];
            int y1 = towers[i][1];
            int q1 = towers[i][2];
            
            double quality = 0;
            for (int j = 0; j < n; j++) {
                int x2 = towers[j][0];
                int y2 = towers[j][1];
                int q2 = towers[j][2];
                
                double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
                if (distance <= radius) {
                    double attenuation = 1 + Math.sqrt(distance);
                    quality += q2 / attenuation;
                }
            }
            
            if (bestTower == null || quality > bestQuality || (quality == bestQuality && isSmaller(towers[i], bestTower))) {
                bestTower = towers[i];
                bestQuality = quality;
            }
        }
        
        return new int[] {bestTower[0], bestTower[1]};
    }
    
    static boolean isSmaller(int[] tower1, int[] tower2) {
        int x1 = tower1[0];
        int y1 = tower1[1];
        int x2 = tower2[0];
        int y2 = tower2[1];
        return x1 < x2 || (x1 == x2 && y1 < y2);
    }
    
    public static void main(String[] args) {
        int[][] towers = {{1, 2, 5}, {2, 1, 7}, {3, 1, 9}};
        int radius = 2;
        int[] bestTower = findBestTower(towers, radius);
        System.out.println(Arrays.toString(bestTower));
    }
}