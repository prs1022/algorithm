package com.interview.meet;

/**
 * @author rensong.pu
 * @date 2024/8/29
 */
public class EditDistance {

    public static int calculate(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int cost = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), // Deletion or Insertion
                        dp[i - 1][j - 1] + cost); // Substitution
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String str1 = "kitten";
        String str2 = "sitting";
        System.out.println("Edit distance between \"" + str1 + "\" and \"" + str2 + "\" is " + calculate(str1, str2));
    }
}
