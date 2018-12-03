package tecent.s2017;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 腾讯2017年校园招聘笔试题
 */
public class Q1质数的和等于新的质数 {
    
    /*
    给定一个正整数，编写程序计算有多少对质数和等于输入的这个正整数，并输出结果。
    输入值小于1000，比如，输入10，程序输出结果为2. （共有两对质数的和为10 分别为 5,5 和 3,7）
    */

    /**
     * 超级蠢的做法
     */
    @Test
    public void answer1() {
        // 1. 在(1,n)之间寻找质数
        // 2. 遍历结果，每遍历一个结果，就从所有结果中寻找一个与它相加和为n的数
        // 3. 每找到一次，就将计数器加1

        int n = 1000;

        List<Integer> primeNumbers = new ArrayList<>();

        for (int i = 2; i < n; i++) {
            if (isPrimeNumber(i)) {
                primeNumbers.add(i);
            }
        }

        int count = 0;

        Set<Integer> black = new HashSet<>();

        for (int j = 0; j < primeNumbers.size(); j++) {
            for (int i = 0; i < primeNumbers.size(); i++) {
                // 如果数字使用过，就不能再次使用，避免重复
                if (!black.contains(primeNumbers.get(i)) && primeNumbers.get(i) + primeNumbers.get(j) == n) {
                    count++;
                    black.add(primeNumbers.get(i));
                    black.add(primeNumbers.get(j));
                    System.out.println(count + ". (" + primeNumbers.get(i) + ", " + primeNumbers.get(j) + ") ");
                }
            }
        }
    }

    /**
     * 网络上的解法
     */
    @Test
    public void answer2() {
        int n = 1000;
        int count = 0;
        for (int i = 2; i < n / 2; i++) {
            if (isPrimeNumber(i) && isPrimeNumber(n - i)) {
                System.out.println("(" + i + "," + (n - i) + ")");
                count++;
            }
        }
        System.out.println(count);
    }

    
    public static boolean isPrimeNumber(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimeNumberWithSqrt(int number) {
        if (number == 2) return true;
        if (number < 2 || number % 2 == 0) return false;
        for (int i = 3; i < Math.sqrt(number); i += 2) {
            if (number % i == 0)
                return false;
        }
        return true;
    }

    @Test
    public void testIsPrimeNumber() {
        Assert.assertTrue(isPrimeNumber(2));
        Assert.assertTrue(isPrimeNumber(3));
        Assert.assertTrue(isPrimeNumber(5));
        Assert.assertFalse(isPrimeNumber(4));
    }

    @Test
    public void testIsPrimeNumberWithSqrt() {
        Assert.assertTrue(isPrimeNumberWithSqrt(2));
        Assert.assertTrue(isPrimeNumberWithSqrt(3));
        Assert.assertTrue(isPrimeNumberWithSqrt(5));
        Assert.assertFalse(isPrimeNumberWithSqrt(4));
    }
}


/*
质数判断 开根号法的数学证明：

如果合数 a ，则有 i*j=a
所以，i和j中必有一个 >=根号a  ，一个<=根号a
所以，只要小于或等于根号n的数不能整除n，则n一定是质数
 */