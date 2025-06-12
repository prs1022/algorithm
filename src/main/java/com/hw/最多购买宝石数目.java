package com.hw;

/**橱窗里有一排宝石，不同的宝石对应不同的价格，宝石的价格标记为 gems[i],0<=i<n, n = gems.length

 宝石可同时出售0个或多个，如果同时出售多个，则要求出售的宝石编号连续；

 例如客户最大购买宝石个数为m，购买的宝石编号必须为gems[i],gems[i+1]...gems[i+m-1](0<=i<n,m<=n)

 假设你当前拥有总面值为value的钱，请问最多能购买到多少个宝石,如无法购买宝石，则返回 0。

 输入描述
 第一行输入n，参数类型为int，取值范围：[0,10^6]，表示橱窗中宝石的总数量。

 之后n行分别表示从第0个到第n-1个宝石的价格，即gems[0]到gems[n-1]的价格，类型为int，取值范围：(0,1000]。

 之后一行输入v，类型为int，取值范围：[0,10^9]表示你拥有的钱。

 输出描述
 输出int类型的返回值，表示最大可购买的宝石数量。

 作者：code5bug
 链接：https://www.nowcoder.com/discuss/599540710571773952
 来源：牛客网
 * @author rensong.pu
 * @date 2025/6/12
 */
public class 最多购买宝石数目 {

    public int maxGems(int[] gems, int value)
    {
        int left = 0;
        int right = 0;
        int ans = 0;
        while(left < gems.length){
            while(right < gems.length && value >= gems[right]){
                value -= gems[right];
                right++;
            }
            ans = Math.max(ans, right - left);
            value += gems[left];
            left++;
        }
        return ans;
    }


    //一个数组表示股票的多日价格， 找到最大利润
    public int maxProfit(int[] prices){
        int minPrice = prices[0];
        int maxProfit = 0;
        for(int i = 1; i < prices.length; i++){
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }

    public static void main(String[] args)
    {
        最多购买宝石数目 test = new 最多购买宝石数目();
        int[] gems = {1,2,8,9,10,3,4,1,6,7};
        int value = 13;
        System.out.println(test.maxGems(gems, value));
    }

}
