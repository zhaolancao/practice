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
        Map<Integer, Integer> indexMap = new HashMap<>();
        int[] countArray = new int[nums.length];
        int maxIndex = 0;
        int zeroIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            Integer index = indexMap.get(n);
            if (index == null) {
                index = maxIndex;
                indexMap.put(n, index);
                nums[maxIndex++] = n;
            }
            countArray[index] = countArray[index] + 1;
            if (n == 0) {
                zeroIndex = index;
            }
        }
        for (int i = 0; i < maxIndex; i++) {
            for (int j = i + 1; j < maxIndex; j++) {
                int remaining = -nums[i] - nums[j];
                Integer countIndex = indexMap.get(remaining);
                if (countIndex == null) {
                    continue;
                }
                if (remaining < nums[i] && remaining < nums[j]) {
                    if (nums[i] > nums[j]) {
                        result.add(buildThreeList(remaining, nums[j], nums[i]));
                    } else {
                        result.add(buildThreeList(remaining, nums[i], nums[j]));
                    }
                } else if (countArray[countIndex] > 1) {
                    if (remaining == nums[i] || remaining == nums[j]) {
                        if (nums[i] > nums[j]) {
                            result.add(buildThreeList(nums[j], remaining, nums[i]));
                        } else {
                            result.add(buildThreeList(remaining, nums[i], nums[j]));
                        }
                    }
                }
            }
        }
        if (zeroIndex != -1 && countArray[zeroIndex] >= 3) {
            result.add(buildThreeList(0, 0, 0));
        }
        return result;
    }

    private static List<Integer> buildThreeList(int min, int middle, int max) {
        List<Integer> valid = new ArrayList<>();
        valid.add(min);
        valid.add(middle);
        valid.add(max);
        return valid;
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

    private static int getFloor(int num, Map<Integer, Integer> numMap, Map<String, Integer> floorMap) {
        int f = 0;
        for (Map.Entry<Integer, Integer> entry : numMap.entrySet()) {
            int preNum = entry.getKey();
            int count = entry.getValue();
            String keyIJ = preNum + "_" + num;
            String keyJI = num + "_" + preNum;
            if (floorMap.get(keyIJ) != null) {
                f = floorMap.get(keyIJ) * count;
            } else if (floorMap.get(keyJI) != null) {
                f = floorMap.get(keyJI) * count;
            } else {
                int floor = preNum > num ? preNum / num : num / preNum;
                floorMap.put(keyIJ, floor);
                f = floor * count;
            }
        }

        return f;
    }
}

