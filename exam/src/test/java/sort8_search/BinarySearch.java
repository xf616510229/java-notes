package sort8_search;

import java.util.Arrays;

/**
 * Java 实现二分法查找
 *
 * @author Feahters
 * @version 1.0
 * @date 2019/3/27
 */
public class BinarySearch {

    public static void main(String[] args) {

        int[] target = {2, 68, 77, 25, 89, 22, 56, 88, 54, 71};
        target = sortArray(target); // 首先进行冒泡排序，从小到大
        System.out.println(Arrays.toString(target));

        int i = binarySearchIndexByEleValue(target, 89);
        int j = binarySearchIndexByEleValueUseRecursive(target, 0, target.length - 1, 89);
        System.out.println(i);
        System.out.println(j);
    }

    private static int[] sortArray(int[] target) {
        for (int i = 0; i < target.length; i++) {
            for (int j = i + 1; j < target.length; j++) { // 从前往后遍历，所以是后移
                if (target[i] > target[j]) { // 大的数据往后移
                    int t = target[i];
                    target[i] = target[j];
                    target[j] = t;
                }
            }
        }
        return target;
    }

    private static int binarySearchIndexByEleValue(int[] array, int targetValue) {
        int result = -1;
        int start = 0;
        int end = array.length - 1;

        while (start <= end) {
            int middleIndex = (end + start) / 2;
            if (array[middleIndex] < targetValue) {
                start = middleIndex + 1;
            } else if (array[middleIndex] > targetValue) {
                end = middleIndex - 1; // 因为middleIndex已经不符合条件，所以下次查找不需要此位置
            } else {
                return middleIndex;
            }
        }
        return result;
    }

    private static int binarySearchIndexByEleValueUseRecursive(int[] arr, int start, int end, int targetValue) {
        if (start > end) {
            return -1;
        }
        int middleIndex = (start + end) / 2;
        if (targetValue > arr[middleIndex]) {
            return binarySearchIndexByEleValueUseRecursive(arr, middleIndex + 1, end, targetValue);
        } else if (targetValue < arr[middleIndex]) {
            return binarySearchIndexByEleValueUseRecursive(arr, start, middleIndex - 1, targetValue);
        } else {
            return middleIndex;
        }
    }
}
