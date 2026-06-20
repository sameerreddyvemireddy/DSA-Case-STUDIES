public class CargoKnapsack {

    public static void main(String[] args) {

        char[] items = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

        int[] weight = {4, 5, 2, 6, 3, 7, 8, 9};
        int[] value  = {30, 25, 20, 45, 25, 40, 50, 55};

        int capacity = 24;
        int n = items.length;

        int[][] dp = new int[n + 1][capacity + 1];

        // Build DP Table
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {

                if (weight[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            value[i - 1] + dp[i - 1][w - weight[i - 1]],
                            dp[i - 1][w]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("Maximum Value = ₹" + dp[n][capacity] + " Thousand");

        // Backtracking
        int w = capacity;

        System.out.print("Selected Items: ");

        for (int i = n; i > 0 && w > 0; i--) {

            if (dp[i][w] != dp[i - 1][w]) {

                System.out.print(items[i - 1] + " ");

                w -= weight[i - 1];
            }
        }

        // Calculate Total Weight
        w = capacity;
        int totalWeight = 0;

        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                totalWeight += weight[i - 1];
                w -= weight[i - 1];
            }
        }

        System.out.println("\nTotal Weight = " + totalWeight + " tons");
        System.out.println("Total Value = ₹" + dp[n][capacity] + " Thousand");
    }
}
