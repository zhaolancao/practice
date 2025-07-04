package com.lancao.practice.algorithms.leetcode;

import java.util.*;

public class NSum {
    private static final int[] signs = new int[]{-1, 1};

    public static List<List<Integer>> solution(int[] elements, int target) {
        return signedSequences(new ArrayList<>(), 0, 0, elements, target);
    }

    private static List<List<Integer>> signedSequences(List<Integer> prefix, int sum, int index, int[] elements, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (prefix.size() == elements.length) {
            if (sum == target) {
                result.add(prefix);
            }
            return result;
        }

        for (int sign : signs) {
            List<Integer> curPrefix = new ArrayList<>(prefix);
            curPrefix.add(sign);
            result.addAll(signedSequences(curPrefix, sum + elements[index] * sign, index + 1, elements, target));
        }
        return result;
    }

    /**
     * 15. 三数之和
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。
     * 请你返回所有和为 0 且不重复的三元组。
     * </p>
     * 注意：答案中不可以包含重复的三元组。
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // 如果数组为空或长度小于3，直接返回空列表
        if (nums == null || nums.length < 3) {
            return result;
        }

        // 对数组进行排序，便于使用双指针法和去重
        Arrays.sort(nums);

        // 遍历数组，固定第一个数，然后使用双指针寻找另外两个数
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            // 如果当前数大于0，由于数组已排序，后面的数也都大于0，三数之和必然大于0，所以结束循环
            if (nums[i] > 0) {
                break;
            }

            // 跳过重复的第一个数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 双指针，分别指向当前元素之后的第一个元素和最后一个元素
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                // 计算当前三数之和
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // 找到一个符合条件的三元组，添加到结果列表
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 跳过重复的左指针
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }

                    // 跳过重复的右指针
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    // 移动左右指针
                    left++;
                    right--;
                } else if (sum < 0) {
                    // 如果和小于0，说明左指针的值太小，左指针右移
                    left++;
                } else {
                    // 如果和大于0，说明右指针的值太大，右指针左移
                    right--;
                }
            }
        }

        return result;
    }

    /**
     * 1862. 向下取整数对和
     */
    public static int sumOfFlooredPairs_unresolved(int[] nums) {
        int mode = 1000000007;
        int sum = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        Set<Integer> dataSet = new HashSet<>();
        List<Integer> dataList = new ArrayList<>();
        int[][] countArray = new int[100001][1];
        for (int num : nums) {
            if (!dataSet.contains(num)) {
                dataList.add(num);
                dataSet.add(num);
            }
            countArray[num][0]++;
        }
        for (int i = 0; i < dataList.size(); i++) {
            Integer n1 = dataList.get(i);
            int c1 = countArray[n1][0];
            sum += c1 * c1;
            for (int j = i + 1; j < dataList.size(); j++) {
                Integer n2 = dataList.get(j);
                int c2 = countArray[n2][0];
                int floor = n1 > n2 ? n1 / n2 : n2 / n1;
                sum += c1 * c2 * floor;
            }
        }


        for (Integer n1 : dataSet) {
            int c1 = countArray[n1][0];
            sum += c1 * c1;
            for (Integer n2 : dataSet) {
                int c2 = countArray[n2][0];
                int floor = n1 > n2 ? n1 / n2 : n2 / n1;
                sum += c1 * c2 * floor;
            }
        }

        // count numbers
        for (int num : nums) {
            if (countMap.containsKey(num)) {
                countMap.compute(num, (k, count) -> count + 1);
            } else {
                countMap.put(num, 1);
            }
        }
        // compute floors
        int size = countMap.size();


        for (int num : nums) {
            for (Map.Entry<Integer, Integer> ce : countMap.entrySet()) {
                Integer existNum = ce.getKey();
                Integer existCount = ce.getValue();
                if (existNum == num) {
                    // add same number floors
                    sum += 2 * existCount;
                } else {
                    // add different number floors
                    int floor = existNum > num ? existNum / num : num / existNum;
                    sum += floor * existCount;
                }
            }
            // add self floor: (nums[x],nums[x])
            sum++;

            // add count
            if (countMap.containsKey(num)) {
                countMap.compute(num, (k, count) -> count + 1);
            } else {
                countMap.put(num, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {

        }


        // Map<num1, Map<num2, floor>>
        Map<Integer, Set<Integer>> indexSetMap = new HashMap<>();
        for (int num : nums) {
            Integer count = countMap.get(num);
            if (count == null) {
                countMap.put(num, 1);
            } else {
                count++;
                countMap.put(num, count);
            }
            if (indexSetMap.containsKey(num)) {
                continue;
            }
            Set<Integer> currentSet = new HashSet<>();
            for (Map.Entry<Integer, Set<Integer>> entry : indexSetMap.entrySet()) {
                int existNum = entry.getKey();
                Set<Integer> existSet = entry.getValue();
                if (existNum < num) {
                    currentSet.add(existNum);
                } else {
                    existSet.add(num);
                }
            }
            indexSetMap.put(num, currentSet);
        }
        for (Map.Entry<Integer, Set<Integer>> entry : indexSetMap.entrySet()) {
            Integer n1 = entry.getKey();
            int n1Count = countMap.get(n1);
            int sameNumResult = n1Count * n1Count;
            sum += sameNumResult;
            for (Integer n2 : entry.getValue()) {
                int n2Count = countMap.get(n2);
                sum += n1Count * n2Count * n1 / n2;
            }
        }


        for (Map.Entry<Integer, Integer> e1 : countMap.entrySet()) {
            int sameNumResult = e1.getValue() * e1.getValue();
            sum += sameNumResult;
            for (Map.Entry<Integer, Integer> e2 : countMap.entrySet()) {
                Integer n1 = e1.getKey();
                Integer n2 = e2.getKey();
                if (n1.equals(n2)) {
                    continue;
                }
                if (n1 > n2) {
                    int differentNumResult = (n1 / n2) * e1.getValue() * e2.getValue();
                    sum += differentNumResult;
                }
            }
        }

        for (int num : nums) {
            for (int i : nums) {
                if (i < num) {
                    continue;
                }
                if (i == num) {
                    sum++;
                } else {
                    int current = (int) ((double) (num / i));
                    sum += current;
                }
            }
        }

        Map<String, Integer> floorMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum++;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    sum += 2;
                } else {
                    String keyIJ = nums[i] + "_" + nums[j];
                    String keyJI = nums[j] + "_" + nums[i];
                    if (floorMap.get(keyIJ) != null) {
                        sum += floorMap.get(keyIJ);
                    } else if (floorMap.get(keyJI) != null) {
                        sum += floorMap.get(keyJI);
                    } else {
                        int floor = nums[i] > nums[j] ? nums[i] / nums[j] : nums[j] / nums[i];
                        floorMap.put(keyIJ, floor);
                        sum += floor;
                    }
                }
            }
        }

        return sum > mode ? sum % mode : sum;
    }

    /**
     * 16. 最接近的三数之和
     * 给你一个长度为 n 的整数数组 nums 和一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
     * 返回这三个数的和。
     * 假定每组输入只存在恰好一个解。
     */
    public static int threeSumClosest(int[] nums, int target) {
        // 如果数组长度小于等于3，直接返回所有元素的和
        if (nums.length <= 3) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            return sum;
        }

        // 对数组进行排序，便于使用双指针法
        Arrays.sort(nums);

        // 初始化最接近的和为前三个元素的和
        int closestSum = nums[0] + nums[1] + nums[2];
        // 遍历数组，固定第一个数，然后使用双指针寻找另外两个数
        for (int i = 0; i < nums.length - 2; i++) {
            // 避免重复计算
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 双指针，分别指向当前元素之后的第一个元素和最后一个元素
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                // 计算当前三数之和
                int sum = nums[i] + nums[left] + nums[right];

                // 如果当前和等于目标值，直接返回
                if (sum == target) {
                    return sum;
                }

                // 更新最接近的和
                if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }

                // 根据当前和与目标值的大小关系，移动指针
                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return closestSum;
    }
}

