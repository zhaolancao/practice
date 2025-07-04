package com.lancao.practice.algorithms.leetcode;

import java.util.*;

public class LeetCode {
    /**
     * 4. 寻找两个正序数组的中位数
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] merged = new int[nums1.length + nums2.length];
        int i = 0, j = 0, m = 0;
        while (i < nums1.length && j < nums2.length) {
            while (i < nums1.length && nums1[i] <= nums2[j]) {
                merged[m++] = nums1[i++];
            }
            if (i >= nums1.length) {
                break;
            }
            while (j < nums2.length && nums1[i] > nums2[j]) {
                merged[m++] = nums2[j++];
            }
        }
        if (i < nums1.length) {
            while (i < nums1.length) {
                merged[m++] = nums1[i++];
            }
        }
        if (j < nums2.length) {
            while (j < nums2.length) {
                merged[m++] = nums2[j++];
            }
        }
        int length = merged.length;
        if (length % 2 == 0) {
            return ((double) merged[length / 2] + (double) merged[length / 2 - 1]) / 2;
        } else {
            return merged[length / 2];
        }
    }

    /**
     * 7. 整数反转
     */
    public static int reverse(int x) {
        boolean isNegative = x < 0;
        double result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            x /= 10;
        }
        if (isNegative) {
            return Integer.MIN_VALUE > result ? 0 : (int) result;
        }
        return Integer.MAX_VALUE < result ? 0 : (int) result;
    }

    /**
     * 8. 字符串转换整数 (atoi)
     */
    public static int myAtoi(String s) {
        char zero = '0';
        char nine = '9';
        char[] nums = new char[s.length()];
        int count = 0;
        int i = 0;
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }
        if (i >= s.length()) {
            return 0;
        }
        char c1 = s.charAt(i++);
        int signed = c1 == '-' ? -1 : 1;
        if (c1 > zero && c1 <= nine) {
            nums[count++] = c1;
        } else if (c1 != '+' && c1 != '-' && c1 != zero) {
            return 0;
        }
        if (count == 0) {
            while (i < s.length() && s.charAt(i) == zero) {
                i++;
            }
        }
        while (i < s.length() && s.charAt(i) >= zero && s.charAt(i) <= nine) {
            nums[count++] = s.charAt(i++);
        }
        if (count == 0) {
            return 0;
        }

        double max = Integer.MAX_VALUE;
        double min = Integer.MIN_VALUE;
        double result = nums[0] - zero;
        result = signed * result;
        i = 1;
        while (i < count) {
            int num = nums[i++] - zero;
            result = result * 10 + signed * num;
            if (min > result) {
                return Integer.MIN_VALUE;
            }
            if (max < result) {
                return Integer.MAX_VALUE;
            }
        }
        return (int) result;
    }

    public static int minimumDeletions_unresolved(String word, int k) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            Integer count = countMap.get(ch);
            if (count == null) {
                count = 1;
            } else {
                count++;
            }
            countMap.put(ch, count);
        }
        Map<Integer, Integer> ccMap = new HashMap<>();
        for (Integer count : countMap.values()) {
            Integer cc = ccMap.get(count);
            if (cc == null) {
                cc = 1;
            } else {
                cc++;
            }
            ccMap.put(count, cc);
        }
        int[] sortedCount = new int[ccMap.size()];
        int size = 0;
        for (int chCount : ccMap.keySet()) {
            size = addToSortedArray(chCount, size, sortedCount);
        }
        if (sortedCount.length == 0) {
            return 0;
        }
        int minIndex = 0, maxIndex = size - 1;
        int delCount = 0;
        while (minIndex < maxIndex && sortedCount[maxIndex] - sortedCount[minIndex] > k) {
            int maxCount = sortedCount[maxIndex];
            int minCount = sortedCount[minIndex];
            int delMin = 0;
            int lowIndex = minIndex;
            while (lowIndex < maxIndex && maxCount - sortedCount[lowIndex] > k) {
                int lowCount = sortedCount[lowIndex];
                int lowCc = ccMap.get(lowCount);
                delMin += lowCount * lowCc;
                lowIndex++;
            }
            int delMax = 0;
            int highIndex = maxIndex;
            while (minIndex < highIndex && sortedCount[highIndex] - minCount > k) {
                int highCount = sortedCount[highIndex];
                int highCc = ccMap.get(highCount);
                int dd = highCount - minCount - k;
                delMax += dd * highCc;
                highIndex--;
            }
            if (delMin < delMax) {
                delCount += delMin;
                while (minIndex < lowIndex) {
                    minIndex++;
                }
            } else {
                delCount += delMax;
                while (maxIndex > highIndex) {
                    int highCount = sortedCount[maxIndex];
                    int dd = highCount - minCount - k;
                    sortedCount[maxIndex] = highCount - dd;
                    maxIndex--;
                }
            }
        }
        return delCount;
    }

    private static int addToSortedArray(int count, int size, int[] sortedCount) {
        if (size == 0) {
            sortedCount[size] = count;
            return 1;
        }
        int half = size / 2;
        if (sortedCount[half] > count) {
            while (half > 0 && sortedCount[half] >= count) {
                half--;
            }
        } else if (sortedCount[half] < count) {
            while (half < size && sortedCount[half] < count) {
                half++;
            }
        }
        int i = size;
        while (i > half) {
            sortedCount[i] = sortedCount[i - 1];
            i--;
        }
        sortedCount[half] = count;
        return size + 1;
    }

    /**
     * 32. 最长有效括号
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
     */
    public static int longestValidParentheses(String s) {
        int maxMatched = 0;
        Stack<int[]> lefts = new Stack<>();
        int index = 0;
        while (index < s.length()) {
            int count = 0;
            int currentLength = 0;
            while (index < s.length() && s.charAt(index) == '(') {
                count++;
                index++;
            }
            while (index < s.length() && s.charAt(index) == ')' && count > 0) {
                currentLength += 2;
                count--;
                index++;
            }
            int[] leftAndMatched = new int[]{count, currentLength};
            lefts.add(leftAndMatched);
        }
        while (!lefts.isEmpty()) {
            int currentLength = 0;
            int[] pop = lefts.pop();
            while (pop[0] == 0 && !lefts.isEmpty()) {
                currentLength += pop[1];
                pop = lefts.pop();
            }
            currentLength += pop[1];
            if (maxMatched < currentLength) {
                maxMatched = currentLength;
            }
        }
        return maxMatched;
    }

    /**
     * 3. 无重复字符的最长子串
     */
    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        int start = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            Integer index = charMap.get(ch);
            if (index == null) {
                charMap.put(ch, i);
            } else {
                int current = i - start;
                charMap.put(ch, i);
                if (current > max) {
                    max = current;
                }
                if (start < index + 1) {
                    start = index + 1;
                }
            }
        }
        int last = s.length() - start;
        return Math.max(max, last);
    }

    /**
     * 49. 字母异位词分组
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagramsMap = new HashMap<>();
        for (String str : strs) {
            char[] chars = new char[str.length()];
            for (int i = 0; i < str.length(); i++) {
                int j = i;
                char ch = str.charAt(j);
                while (j > 0 && chars[j - 1] < ch) {
                    chars[j] = chars[j - 1];
                    j--;
                }
                chars[j] = ch;
            }
            String sorted = Arrays.toString(chars);
            List<String> anagrams = anagramsMap.get(sorted);
            if (anagrams == null) {
                anagrams = new ArrayList<>();
            }
            anagrams.add(str);
            anagramsMap.put(sorted, anagrams);
        }
        return anagramsMap.values().stream().toList();
    }

    /**
     * 30. 串联所有单词的子串
     * </p>
     * 示例 ：
     * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
     * 输出：[6,9,12]
     * 解释：因为 words.length == 3 并且 words[i].length == 3，所以串联子串的长度必须为 9。
     * 子串 "foobarthe" 开始位置是 6。它是 words 中以 ["foo","bar","the"] 顺序排列的连接。
     * 子串 "barthefoo" 开始位置是 9。它是 words 中以 ["bar","the","foo"] 顺序排列的连接。
     * 子串 "thefoobar" 开始位置是 12。它是 words 中以 ["the","foo","bar"] 顺序排列的连接。
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (words.length == 0) {
            return result;
        }
        HashMap<String, Integer> wordCountMap = new HashMap<>();
        Arrays.stream(words).forEach(word -> addWordCount(wordCountMap, word));
        HashMap<String, Boolean> matchedMap = new HashMap<>();
        int wn = words.length;
        int wl = words[0].length();
        int sl = wn * wl;
        for (int i = 0; i <= s.length() - sl; i++) {
            int start = i, end = i + sl;
            String subStr = s.substring(start, end);
            if (matchedMap.get(subStr) != null) {
                result.add(i);
                continue;
            }
            HashMap<String, Integer> subWordCountMap = new HashMap<>();
            for (int j = 0; j < wn; j++) {
                addWordCount(subWordCountMap, s.substring(start, start + wl));
                start += wl;
            }
            if (wordCountMap.keySet().containsAll(subWordCountMap.keySet())
                    && subWordCountMap.entrySet().stream().allMatch(entry -> entry.getValue().equals(wordCountMap.get(entry.getKey())))) {
                matchedMap.put(subStr, true);
                result.add(i);
            }

        }
        return result;
    }

    private static void addWordCount(HashMap<String, Integer> subWordCountMap, String subWord) {
        Integer count = subWordCountMap.get(subWord);
        if (count == null) {
            count = 1;
        } else {
            count++;
        }
        subWordCountMap.put(subWord, count);
    }

    /**
     * 27. 移除元素
     * </p>
     * 示例 1：
     * 输入：nums = [3,2,2,3], val = 3
     * 输出：2, nums = [2,2,_,_]
     * 解释：你的函数函数应该返回 k = 2, 并且 nums 中的前两个元素均为 2。
     * 你在返回的 k 个元素之外留下了什么并不重要（因此它们并不计入评测）。
     */
    public static int removeElement(int[] nums, int val) {
        int count = 0;
        for (int i = 0; i < nums.length; ) {
            while (i < nums.length && nums[i] != val) {
                i++;
                count++;
            }
            if (i == nums.length) {
                return count;
            }
            int j = i + 1;
            while (j < nums.length && nums[j] == val) {
                j++;
            }
            if (j == nums.length) {
                return count;
            }
            int temp = nums[count];
            nums[count] = nums[j];
            nums[j] = temp;
            i++;
            count++;
        }
        return count;
    }

    /**
     * LeetCode 1210. Minimum Moves to Reach Target with Rotations
     */
    public static int minimumMoves(int[][] grid) {
        int n = grid.length;
        boolean[][][] visited = new boolean[n][n][2];
        Queue<int[]> queue = new LinkedList<>();
        int[] start = new int[]{0, 0, 0};
        queue.offer(start);
        visited[0][0][0] = true;

        int moves = 0;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int[] current = queue.poll();
                int row = current[0];
                int col = current[1];
                int orientation = current[2];
                if (row == n - 1 && col == n - 2 && orientation == 0) {
                    return moves;
                }
                // 1. move right
                if (col + 1 < n && grid[row][col + 1] == 0) {
                    if (orientation == 0) {
                        if (col + 2 < n && grid[row][col + 2] == 0 && !visited[row][col + 1][0]) {
                            visited[row][col + 1][0] = true;
                            queue.offer(new int[]{row, col + 1, 0});
                        }
                    } else if (row + 1 < n && grid[row + 1][col + 1] == 0 && !visited[row][col + 1][1]) {
                        visited[row][col + 1][1] = true;
                        queue.offer(new int[]{row, col + 1, 1});
                    }
                }
                // 2. move down
                if (row + 1 < n && grid[row + 1][col] == 0) {
                    if (orientation == 0) {
                        if (col + 1 < n && grid[row + 1][col + 1] == 0 && !visited[row + 1][col][0]) {
                            visited[row + 1][col][0] = true;
                            queue.offer(new int[]{row + 1, col, 0});
                        }
                    } else if (row + 2 < n && grid[row + 2][col] == 0 && !visited[row + 1][col][1]) {
                        visited[row + 1][col][1] = true;
                        queue.offer(new int[]{row + 1, col, 1});
                    }
                }
                // 3. rotate clockwise && counter-clockwise
                if (row + 1 < n && grid[row + 1][col] == 0 && col + 1 < n && grid[row][col + 1] == 0 && grid[row + 1][col + 1] == 0) {
                    if (orientation == 0 && !visited[row + 1][col + 1][0]) {
                        visited[row][col][1] = true;
                        queue.offer(new int[]{row, col, 1});
                    } else if (orientation == 1 && !visited[row + 1][col + 1][1]) {
                        visited[row][col][0] = true;
                        queue.offer(new int[]{row, col, 0});
                    }
                }
            }
            moves++;
        }
        return -1;
    }

    public static int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        Map<Integer, Boolean> targetedMap = new HashMap<>();
        int bobPoints = maximumBobPoints(numArrows, aliceArrows, aliceArrows.length - 1, targetedMap);
        for (int i = aliceArrows.length - 1; i > 0; i--) {
            if (targetedMap.getOrDefault(i, false)) {
                aliceArrows[i] = aliceArrows[i] + 1;
                numArrows -= aliceArrows[i];
            } else {
                aliceArrows[i] = 0;
            }
        }
        aliceArrows[0] = numArrows;
        System.out.println("Bob scored " + bobPoints + " points.");
        return aliceArrows;
    }

    private static int maximumBobPoints(int numArrows, int[] aliceArrows, int index, Map<Integer, Boolean> targetedMap) {
        if (numArrows == 0) {
            for (int i = 0; i <= index; i++) {
                targetedMap.put(i, false);
            }
            return 0;
        }
        if (index == 0) {
            targetedMap.put(index, numArrows > 0);
            return 0;
        }
        if (numArrows > aliceArrows[index]) {
            int requiredArrows = aliceArrows[index] + 1;
            int currentTargeted = maximumBobPoints(numArrows - requiredArrows, aliceArrows, index - 1, targetedMap) + index;
            Map<Integer, Boolean> notTargetedMap = new HashMap<>(targetedMap);
            int currentNotTargeted = maximumBobPoints(numArrows, aliceArrows, index - 1, notTargetedMap);
            if (currentTargeted > currentNotTargeted) {
                targetedMap.put(index, true);
                return currentTargeted;
            } else {
                targetedMap.putAll(notTargetedMap);
                targetedMap.put(index, false);
                return currentNotTargeted;
            }
        } else {
            int bobPoints = maximumBobPoints(numArrows, aliceArrows, index - 1, targetedMap);
            targetedMap.put(index, false);
            return bobPoints;
        }
    }

    /**
     * 14. 最长公共前缀
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        StringBuilder commonPrefix = new StringBuilder();
        int length = strs[0].length();
        for (int i = 0; i < length; i++) {
            char ch = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != ch) {
                    return commonPrefix.toString();
                }
            }
            commonPrefix.append(ch);
        }
        return commonPrefix.toString();
    }

    /**
     * 151. 反转字符串中的单词
     */
    public static String reverseWords(String s) {
        StringBuilder reversed = new StringBuilder();
        s = s.trim();
        String space = " ";
        String[] strs = s.split(space);
        for (int i = strs.length - 1; i >= 0; i--) {
            if (!strs[i].isBlank()) {
                reversed.append(strs[i]).append(space);
            }
        }
        if (!reversed.isEmpty()) {
            reversed.deleteCharAt(reversed.length() - 1);
        }
        return reversed.toString();
    }

    /**
     * 2047. 句子中的有效单词数
     * 句子仅由小写字母（'a' 到 'z'）、数字（'0' 到 '9'）、连字符（'-'）、标点符号（'!'、'.' 和 ','）以及空格（' '）组成。每个句子可以根据空格分解成 一个或者多个 token ，这些 token 之间由一个或者多个空格 ' ' 分隔。
     * <p>
     * 给你一个字符串 sentence ，请你找出并返回 sentence 中 有效单词的数目 。
     */
    public static int countValidWords(String sentence) {
        String[] tokens = sentence.split(" ");
        int count = 0;
        for (String token : tokens) {
            if (isValidWord(token)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 如果一个 token 同时满足下述条件，则认为这个 token 是一个有效单词：
     * <p>
     * 仅由小写字母、连字符和/或标点（不含数字）组成。
     * 至多一个 连字符 '-' 。如果存在，连字符两侧应当都存在小写字母（"a-b" 是一个有效单词，但 "-ab" 和 "ab-" 不是有效单词）。
     * 至多一个 标点符号。如果存在，标点符号应当位于 token 的 末尾 。
     * 这里给出几个有效单词的例子："a-b."、"afad"、"ba-c"、"a!" 和 "!" 。
     */
    private static boolean isValidWord(String token) {
        if (token.isBlank()) {
            return false;
        }
        char hyphen = '-';
        int hyphenCount = 0;
        Set<Character> punctuations = new HashSet<>() {{
            add('!');
            add('.');
            add(',');
        }};
        int punctuationCount = 0;
        boolean prevIsLetter = false;
        for (int i = 0; i < token.length(); i++) {
            char ch = token.charAt(i);
            // 至多一个 连字符 '-' 。如果存在，连字符两侧应当都存在小写字母（"a-b" 是一个有效单词，但 "-ab" 和 "ab-" 不是有效单词）。
            if (ch == hyphen) {
                if (hyphenCount > 0 || !prevIsLetter) {
                    return false;
                }
                if (i == token.length() - 1 || !isLowerCaseLetter(token.charAt(i + 1))) {
                    return false;
                }
                hyphenCount++;
            } else if (punctuations.contains(ch)) {
                if (punctuationCount > 0 || i != token.length() - 1) {
                    return false;
                }
                punctuationCount++;
            } else {
                boolean isLetter = isLowerCaseLetter(ch);
                if (!isLetter && ch != '/') {
                    return false;
                }
                prevIsLetter = isLetter;
            }
        }
        return true;
    }

    private static boolean isLowerCaseLetter(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    /**
     * 581. 最短无序连续子数组
     * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     * <p>
     * 请你找出符合题意的 最短 子数组，并输出它的长度。
     */
    public static int findUnsortedSubarray(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);
        int left = 0;
        while (left < nums.length && nums[left] == copy[left]) {
            left++;
        }
        int right = nums.length - 1;
        while (right >= left && nums[right] == copy[right]) {
            right--;
        }
        return right - left + 1;
    }

    /**
     * 1071. 字符串的最大公因子
     */
    public String gcdOfStrings(String str1, String str2) {
        if (str1.equals(str2)) {
            return str1;
        }
        int l1 = str1.length();
        int l2 = str2.length();
        if (l1 > l2) {
            String str = str1;
            str1 = str2;
            str2 = str;
        }
        for (int i = l1; i >= 1; ) {
            if (l1 % i != 0 || l2 % i != 0) {
                continue;
            }
            String subStr = str1.substring(0, i);
            if (isGcdStr(str1, str2, subStr)) {
                return subStr;
            }
            i--;
        }
        return "";
    }

    private static boolean isGcdStr(String str1, String str2, String subStr) {
        return Arrays.stream(str1.split(subStr)).allMatch(String::isEmpty)
                && Arrays.stream(str2.split(subStr)).allMatch(String::isEmpty);
    }

    /**
     * 899. 有序队列
     */
    public static String orderlyQueue(String s, int k) {
        if (k == 1) {
            String smallest = s;
            // 尝试所有可能的循环移位
            for (int i = 0; i < s.length(); i++) {
                // 将字符串向左循环移位i个位置
                String rotated = s.substring(i) + s.substring(0, i);
                // 比较并更新最小字符串
                if (rotated.compareTo(smallest) < 0) {
                    smallest = rotated;
                }
            }
            return smallest;
        }
        // 当 k >= 2 时，可以得到任意排列，返回排序后的字符串
        else {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }
    }

    /**
     * 1111. 有效括号的嵌套深度
     * 示例 1：
     * <p>
     * 输入：seq = "(()())"
     * 输出：[0,1,1,1,1,0]
     * 示例 2：
     * <p>
     * 输入：seq = "()(())()"
     * 输出：[0,0,0,1,1,0,1,1]
     * 解释：本示例答案不唯一。
     * 按此输出 A = "()()", B = "()()", max(depth(A), depth(B)) = 1，它们的深度最小。
     * 像 [1,1,1,0,0,1,1,1]，也是正确结果，其中 A = "()()()", B = "()", max(depth(A), depth(B)) = 1 。
     */
    public static int[] maxDepthAfterSplit(String seq) {
        int[] result = new int[seq.length()];
        int depth = 0;
        for (int i = 0; i < seq.length(); i++) {
            char ch = seq.charAt(i);
            if (ch == '(') {
                result[i] = depth % 2 == 0 ? 0 : 1;
                depth++;
            } else if (ch == ')') {
                depth--;
                result[i] = depth % 2 == 0 ? 0 : 1;
            }
        }
        return result;
    }

    /**
     * 946. 验证栈序列
     */
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length < popped.length) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int ii = 0;
        int io = 0;
        while (ii < pushed.length && io < popped.length) {
            if (pushed[ii] == popped[io]) {
                ii++;
                io++;
            } else if (!stack.isEmpty() && stack.peek() == popped[io]) {
                stack.pop();
                io++;
            } else {
                stack.push(pushed[ii]);
                ii++;
            }
        }
        while (io < popped.length) {
            if (stack.isEmpty() || stack.pop() != popped[io++]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 554. 砖墙
     */
    public static int leastBricks(List<List<Integer>> wall) {
        List<List<Integer>> preSumList = new ArrayList<>();
        HashMap<Integer, Integer> preSumMap = new HashMap<>();
        for (List<Integer> bricks : wall) {
            List<Integer> preSum = new ArrayList<>();
            preSumMap.put(bricks.get(0), preSumMap.getOrDefault(bricks.get(0), 0) + 1);
            preSum.add(bricks.get(0));
            for (int j = 1; j < bricks.size(); j++) {
                int current = bricks.get(j) + preSum.get(j - 1);
                preSum.add(current);
                preSumMap.put(current, preSumMap.getOrDefault(current, 0) + 1);
            }
            preSumList.add(preSum);
        }
        int total = preSumList.get(0).get(preSumList.get(0).size() - 1);
        preSumMap.remove(total);
        if (preSumMap.isEmpty()) {
            return wall.size();
        }
        Integer[] preSumCountArray = preSumMap.values().toArray(new Integer[0]);
        Arrays.sort(preSumCountArray, (o1, o2) -> o2 - o1);
        return wall.size() - preSumCountArray[0];
    }

    /**
     * 11. 盛最多水的容器
     */
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            max = Math.max(max, area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
