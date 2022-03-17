package leetcode;

import java.util.*;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/2/26
 * Time:21:17
 * Describe:
 */

class Base{
    public List<Integer> BFS(Queue<TreeNode> queue){
        List<Integer> res = new ArrayList<>();
        int len = queue.size();
        TreeNode node = null;
        while (len--!=0){
            node = queue.poll();
            res.add(node.val);
            if(node.left!=null) queue.offer(node.left);
            if(node.right!=null) queue.offer(node.right);
        }
        return res;
    }
}
class T_1{
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length, res[] = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<len; i++) {
            if (map.containsKey(target - nums[i]))
                return new int[]{map.get(target-nums[i]),i};
            map.put(nums[i],i);
        }
        return res;
    }
}
class T_10{
    public boolean isMatch(String s, String p){
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if(p.charAt(j-1) == '*'){
                    dp[i][j] = dp[i][j-2];
                    if(matchChar(s,p,i,j-1))
                        dp[i][j] = dp[i][j]||dp[i-1][j];
                }else{
                    if(matchChar(s,p,i,j))
                        dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        return dp[m][n];
    }
    public boolean matchChar(String s, String p, int i, int j){
        if(i==0) return false;
        else if(p.charAt(j-1)=='.') return true;
        else return s.charAt(i) == p.charAt(j);
    }
}
class T_11{
    public int maxArea(int[] height) {
        int len = height.length, l=0, r=len-1, max=Integer.MIN_VALUE, min, minIdx, dire=-1;
        while (l<r){
            if(height[l]<height[r]){
                min = height[l];
                dire = -1;
            }else{
                min = height[r];
                dire = 1;
            }
            max = Math.max(max,min*(r-l));
            if(dire==1)
                while (l<r&&height[r]<=min) r--;
            else
                while (l<r&&height[l]<=min) l++;

        }
        return max;
    }
}
class T_32{
    public int longestValidParentheses(String s) {
        int len = s.length(), max=Integer.MIN_VALUE;
        if(len<2) return 0;
        int[] dp = new int[len];
        for (int i = 1; i < len; i++) {
            if(s.charAt(i)==')'){
                if(s.charAt(i-1)=='(')
                    dp[i] = i-2>=0?dp[i-2]+2:2;
                else if(i-dp[i-1]-1>=0&&s.charAt(i-dp[i-1]-1)=='(')
                    dp[i] = dp[i-1]+(i-dp[i-1]-2>=0?dp[i-dp[i-1]-2]:0)+2;
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
}
class T_15{
    public List<List<Integer>> threeSum(int[] nums, int target) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<len; i++) {
            for(int j=i+1; j<len; j++){
                if(map.containsKey(target-nums[i]-nums[j])){
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(map.get(target-nums[i]-nums[j]));
                    tmp.add(i);
                    tmp.add(j);
                    res.add(tmp);
                }
            }
            map.put(nums[i],i);
        }
        return res;
    }
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length, l, r, tmp;
        List<List<Integer>> res = new ArrayList<>();
        if(len<3) return res;
        Arrays.sort(nums);
        for (int i=0;i<len-1;i++){
            if(i>0&&nums[i]==nums[i-1])
                continue;
            l=i+1; r=len-1;
            while(l!=r){
                tmp = nums[i]+nums[l]+nums[r];
                if(tmp!=0){
                    if(tmp>0) r--;
                    else l++;
                }else {
                    res.add(Arrays.asList(nums[i],nums[l],nums[r]));
                    while (l<r&&nums[l]==nums[l+1]) l++;
                    while (l<r&&nums[r]==nums[r-1]) r--;
                    l++; r--;
                }
            }
        }
        return res;
    }
}
class T_33{
    public int search(int[] nums, int target) {
        int len = nums.length, idx=0, l, r, mid=-1;
        for(int i=0;i<len-1;i++){
            if(nums[i+1]<nums[i]) break;
            idx++;
        }
        if(target>=nums[0]){
            l=0; r=idx;
        }else{
            l=idx+1; r=len-1;
        }
        while (l<r){
            System.out.println("l = "+l+" r = "+r);
            mid = (l+r)>>1;
            if(nums[mid]==target) return mid;
            if(nums[mid]>target) l=mid+1;
            else r=mid-1;
        }
        return -1;
    }
}
class T_34{
    public int[] searchRange(int[] nums, int target) {
        int len = nums.length, l=0, r=len-1, mid=0;
        if(len==0) return new int[]{-1,-1};
        int l0=0, r0=0, l1=0, r1=0, mid0=0, mid1=0, res0=-1, res1=-1;
        while (l<=r){
            mid = (l+r)>>1;
            if(nums[mid]==target) break;
            if(nums[mid]<target) l=mid+1;
            else r=mid-1;
        }
        if(mid<0||nums[mid]!=target) return new int[]{-1,-1};
        if(mid==0||nums[mid-1]!=target) res0 = mid;
        if(mid==len-1||nums[mid+1]!=target) res1 = mid;
        if(res0==-1){
            l0=l; r0=mid-1;
            while (l0<r0){
                mid0 = (l0+r0)>>1;
                if(nums[mid0]==target) r0=mid0;
                else l0=mid0+1;
            }
            res0=l0;
        }
        if(res1==-1){
            l1=mid+1; r1=r;
            while (l1<r1){
                mid1=(l1+r1)>>1;
                if(nums[mid1]==target) l1=mid1;
                else r1=mid1-1;
                if(l1==len-1||nums[l1+1]!=target) break;
            }
            res1=l1;
        }
        return new int[]{res0,res1};
    }
}
class T_39{
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp;
        Arrays.sort(candidates);
        int len = candidates.length, l=0, r=0, sum=candidates[0];
        while (l<=r){
            if(sum==target){
                tmp = new ArrayList<>();
                for(int i=l;i<=r;i++)
                    tmp.add(candidates[i]);
                res.add(tmp);
                if(r+1<len)
                    sum+=candidates[++r];
                continue;
            }
            if(sum>target)
                sum-=candidates[l++];
            else
            if(r+1<len)
                sum+=candidates[++r];
            else
                break;
        }
        return res;
    }
}
class T_45{
    public int jump(int[] nums) {
        int len = nums.length, step;
        if(len==1) return 0;
        int steps[] = new int[len];
        steps[len-1]=1;
        for(int i=len-2; i>=0; i--){
            step = nums[i];
            for(int j=1;j<=step&&i+j<len;j++){
                if(steps[i+j]>0){
                    if(steps[i]==0)
                        steps[i] = 1+steps[i+j];
                    else
                        steps[i] = Math.min(steps[i],1+steps[i+j]);
                }
            }
        }
        return steps[0]-1;
    }
}
class T_46{
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        Integer[] numsI = new Integer[len];
        for(int i=0;i<len;i++)
            numsI[i] = nums[i];
        if(nums==null||nums.length==0) return res;
        helper(res, new ArrayList<>(), numsI);
        return res;
    }
    public void helper(List<List<Integer>> res, List<Integer> list, Integer[] numsI){
        int len = numsI.length;
        if(list.size()==len){
            res.add(new ArrayList<>(list));
            return ;
        }
        for(int i=0;i<len;i++){
            if(list.contains(numsI[i])) continue;
            list.add(numsI[i]);
            helper(res, list, numsI);
            list.remove(list.size());
        }
    }
}
class T_55{
    public boolean canJump_ans(int[] nums) {
        int len = nums.length, step;
        if(len==1) return nums[0]>0;
        int jump[] = new int[len];
        jump[len-1]=1;
        for(int i=len-2; i>=0; i--){
            step = nums[i];
            for(int j=0;j<step;j++){
                if(jump[j]==1){
                    jump[i]=1;
                    break;
                }
            }
        }
        return jump[0]==1;
    }
}
class T_56{
    public int[][] merge(int[][] intervals) {
        quickSort(intervals, 0, intervals.length-1);
        for(int i=0;i<intervals.length;i++)
            System.out.println("["+intervals[i][0]+","+intervals[i][1]+"]");
        int l=intervals[0][0], r=intervals[0][1];
        List<Integer> lList = new ArrayList<>();
        List<Integer> rList = new ArrayList<>();
        for(int i=0;i<intervals.length;i++){
            if(intervals[i][0]<r) r=intervals[i][1];
            else{
                lList.add(l); rList.add(r);
                l = intervals[i][0];
                r = intervals[i][1];
            }
        }
        lList.add(l); rList.add(r);
        int num = lList.size();
        int[][] res = new int[num][2];
        for(int i=0; i<num; i++){
            res[i][0] = lList.get(i);
            res[i][1] = rList.get(i);
        }
        return res;
    }
    private static void quickSort(int nums[][], int l, int r){
        if(l>=r) return ;
        int mid = nums[(l+r)>>1][0], i=l-1, j=r+1;
        while(i<j){
            do i++;while (nums[i][0]<mid);
            do j--;while (nums[j][0]>mid);
            if(i<j) {
                int tmp = nums[i][0];
                nums[i][0] = nums[j][0];
                nums[j][0] = tmp;
                tmp = nums[i][1];
                nums[i][1] = nums[j][1];
                nums[j][1] = tmp;
            }
        }
        quickSort(nums, l, j);
        quickSort(nums, j+1, r);
    }
}
class T_64{
    public int minPathSum(int[][] grid) {
        int M = grid.length, N = grid[0].length;
        Integer gridI[][] = new Integer[M][N];
        for(int i=0;i<M;i++)
            for(int j=0;j<N;j++)
                gridI[i][j] = grid[i][j];
        int res = findMin(gridI, M-1, N-1);
        return res;
    }
    public int findMin(Integer[][] grid, int i, int j){
        if(i<0||j<0) return Integer.MAX_VALUE;
        if(i==0&&j==0) return grid[0][0];
        return Math.min(findMin(grid, i-1, j),findMin(grid, i, j-1))+grid[i][j];
    }
    public int minPathSum_dp(int[][] grid){
        int M = grid.length, N = grid[0].length, min;
        int view[][] = new int[M][N];
        view[0][0] = grid[0][0];
        for(int i=0;i<M;i++) {
            for (int j = 1; j < N; j++) {
                min = Integer.MAX_VALUE;
                if (i - 1 >= 0)
                    min = Math.min(min, view[i - 1][j]);
                if (j - 1 >= 0)
                    min = Math.min(min, view[i][j - 1]);
                view[i][j] = min + grid[i][j];
                System.out.print("i = " + i + " j = " + j + " view[i][j] = " + view[i][j]);
            }
            System.out.println();
        }
        return view[M-1][N-1];
    }
}
class T_72{
    public int minDistance(String word1, String word2){
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];
        int l,u,lu;
        for (int i = 0; i <= m; i++)
            dp[i][0] = i;
        for (int i = 0; i <= n; i++)
            dp[0][i] = i;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                l = dp[i][j-1]+1;
                u = dp[i-1][j]+1;
                lu = dp[i-1][j-1];
                if(word1.charAt(i-1)==word2.charAt(j-1))
                    lu++;
                dp[i][j] = Math.min(lu, Math.min(l,u));
            }
        }
        return dp[m][n];
    }
}
class T_94{
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        LTR(root, res);
        return res;
    }
    public void LTR(TreeNode node, List<Integer> list){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode view;
        while(node != null || !stack.isEmpty()){
            while (node!=null){
                stack.push(node);
                node = node.left;
            }
            view = stack.pop();
            list.add(view.val);
            node = view.right;
        }
    }
}

/**
 * 1
 * 12,21
 * 0,2 1,1, 2,2
 */
class T_96{
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0]=1;
        for (int i = 1; i <= n; i++)
            for (int j = 0; j < i; j++)
                dp[i]+=dp[j]*dp[i-1-j];
        return dp[n];
    }
}
class T_98{
    public boolean isValidBST(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null) return true;
        return isValidBST_LTR(root, res);
    }
    public boolean isValidBST_LTR(TreeNode node, List<Integer> list){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode view;
        int pre = Integer.MIN_VALUE;
        while(node != null || !stack.isEmpty()){
            while (node!=null){
                stack.push(node);
                node = node.left;
            }
            view = stack.pop();
            if(list.size()!=0)
                if(view.val<=pre) return false;
            list.add(view.val);
            pre = view.val;
            node = view.right;
        }
        return true;
    }
}
class T_99{
    public void recoverTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode view=null, pre=null, fnode1=null, fnode2=null, fnode1Pre=null, fnode2Pre=null, node=root;
        while(node != null || !stack.isEmpty()){
            while (node!=null){
                stack.push(node);
                node = node.left;
            }
            view = stack.pop();
            if(pre!=null){
                if(view.val<pre.val){
                    fnode2 = fnode1;
                    fnode2Pre = fnode1Pre;
                    fnode1 = view;
                    fnode1Pre = pre;
                }
            }
            if(fnode2!=null) break;
            pre = view;
            node = view.right;
        }
        if(fnode2==null){
            int tmp = fnode1.val;
            fnode1.val = fnode1Pre.val;
            fnode1Pre.val = tmp;
        }else{
            int tmp = fnode2Pre.val;
            fnode2Pre.val = fnode1.val;
            fnode1.val = tmp;
        }
    }
}
class T_102{
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        nodes.offer(root);
        while (!nodes.isEmpty()){
            List<Integer> toAdd;
            toAdd = BFS(nodes);
            res.add(toAdd);
        }
        return res;
    }
    public List<Integer> BFS(Queue<TreeNode> queue){
        List<Integer> res = new ArrayList<>();
        int len = queue.size();
        TreeNode node = null;
        while (len--!=0){
            node = queue.poll();
            res.add(node.val);
            if(node.left!=null) queue.offer(node.left);
            if(node.right!=null) queue.offer(node.right);
        }
        return res;
    }
}
class T_103{
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null) return res;
        Stack<TreeNode> nodes = new Stack<>();
        nodes.push(root);
        int level = 1;
        while (!nodes.isEmpty()){
            List<Integer> toAdd;
            toAdd = zigzagBFS(nodes, level);
            res.add(toAdd);
            level++;
        }
        return res;
    }
    public List<Integer> zigzagBFS(Stack<TreeNode> nodes, int level){
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> qt = new LinkedList<TreeNode>();
        TreeNode node = null;
        if((level&1)==1){
            while (!nodes.isEmpty()){
                node = nodes.pop();
                res.add(node.val);
                if(node.left!=null) qt.offer(node.left);
                if(node.right!=null) qt.offer(node.right);
            }
        }else{
            while (!nodes.isEmpty()){
                node = nodes.pop();
                res.add(node.val);
                if(node.right!=null) qt.offer(node.right);
                if(node.left!=null) qt.offer(node.left);
            }
        }
        while (!qt.isEmpty()) nodes.push(qt.poll());
        return res;
    }
}
class T_107{
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        //
        Base base = new Base();
        //
        List<List<Integer>> res = new ArrayList<>();
        if(root==null) return res;
        Stack<List<Integer>> stack = new Stack<>();
        List<Integer> toAdd;
        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        nodes.offer(root);
        while (!nodes.isEmpty()){
            toAdd = base.BFS(nodes);
            stack.add(toAdd);
        }
        while (!stack.isEmpty())
            res.add(stack.pop());
        return res;
    }
}
class T_121{
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len == 1) return 0;
        Integer[] pricesI = new Integer[len];
        for(int i=0;i<len;i++)
            pricesI[i] = prices[i];
        int res, l=0, r=len-1, max=Math.max(0, prices[r]-prices[l]);
        res = findMax(pricesI, l, r, max);
        return res;
    }
    public int findMax(Integer[] price, int l, int r, int max){
        if(l>=r) return max;
        if (l<r){
            while (l<r&&price[l+1]<price[l])
                l++;
            while (l<r&&price[r-1]>price[r])
                r--;
            max = Math.max(max,price[r]-price[l]);
        }
        max = findMax(price, l+1, r, max);
        max = findMax(price, l, r-1, max);
        return max;
    }

    public int maxProfit_dp(int[] prices) {
        int min = prices[0], max=0;
        for(int i=1; i<prices.length; i++){
            max = Math.max(max,prices[i]-min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }
}
class T_124{
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root){
        findMax(root);
        return max;
    }
    public int findMax(TreeNode node){
         if(node==null) return 0;
         int lmax, rmax;
         lmax = Math.max(findMax(node.left), 0);
         rmax = Math.max(findMax(node.right), 0);
         max = Math.max(max, node.val+lmax+rmax);
         return node.val + Math.max(lmax, rmax);
    }
}

class T_128{
    public int longestConsecutive(int[] nums) {
        if(nums.length<2) return nums.length;
        Set<Integer> sNums = new HashSet<>();
        Set<Integer> view = new HashSet<>();
        int len = nums.length, max=1, l=-1, r=-1, tmp=-1;
        for(int num:nums)
            sNums.add(num);
        for(int i=0;i<len;i++){
            tmp = nums[i];
            if(!view.contains(tmp)){
                view.add(tmp);
                l=tmp-1; r=tmp+1;
                while (sNums.contains(l)){
                    view.add(l);
                    l--;
                }
                while (sNums.contains(r)){
                    view.add(r);
                    r++;
                }
                max = Math.max(max,r-l-1);
            }
        }
        return max;
    }
}

class T_139{
    public boolean wordBreak(String s, List<String> wordDict){
        int len = s.length();
        boolean[] dp = new boolean[len];
        for (int i = 0; i < len; i++) {
            if(wordDict.contains(s.substring(0,i+1))) {
                dp[i]=true;
                continue;
            }
            for (int j = i-1; j >= 0; j--) {
                if(dp[j]){
                    if(wordDict.contains(s.substring(j+1,i+1))) {
                        dp[i]=true;
                        break;
                    }
                }
            }
        }
        return dp[len-1];
    }
}

class T_144{
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        TLR(root, res);
        return res;
    }
    public void TLR(TreeNode node, List<Integer> list){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(node != null || !stack.isEmpty()){
            while (node!=null){
                list.add(node.val);
                stack.add(node);
                node = node.left;
            }
            node = stack.pop().right;
        }
    }
}
class T_145{
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        LRT(root, res);
        return res;
    }
    public void LRT(TreeNode node, List<Integer> list){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode pre = null;
        while(node != null || !stack.isEmpty()){
            while (node!=null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if(node.right==null||node.right == pre){
                list.add(node.val);
                pre = node;
                node = null;
            }else{
                stack.push(node);
                node = node.right;
            }
        }
    }
}
class T_152{
    public int maxProduct(int[] nums) {
        int res = Integer.MIN_VALUE;
        int min = 1, max = 1, len = nums.length, tmp;
        if(len==1) return max;
        for(int i=0;i<len;i++){
            if(nums[i]<0){
                tmp = max;
                max = min;
                min = tmp;
            }
            max = Math.max(nums[i],max*nums[i]);
            min = Math.min(nums[i],min*nums[i]);
            res = Math.max(res,max);
            System.out.println("max = "+max+"  min = "+min);
        }
        return res;
    }
}
class T_198{
    public int rob(int[] nums) {
        int len = nums.length;
        if(len==1) return nums[0];
        if(len==2) return Math.max(nums[0],nums[1]);
        int get = nums[0], disGet = 0, getT, disGetT;
        for (int i=1;i<len;i++){
            getT = get; disGetT = disGet;
            get = disGetT+nums[i];
            disGet = Math.max(disGetT,getT);
        }
        return Math.max(get,disGet);
    }
}
class T_199{
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        int len;
        TreeNode node=null;

        if(root==null) return res;
        queue.offer(root);
        while (!queue.isEmpty()){
            len = queue.size();
            while(len!=0){
                len--;
                node = queue.poll();
                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
            }
            res.add(node.val);
        }
        return res;
    }
}
class T_200{
    public int numIslands(char[][] grid) {
        int M = grid.length, N = grid[0].length, count=0;
        Boolean[][] used = new Boolean[M][N];
        Character[][] cGrid = new Character[M][N];
        for(int i=0;i<M;i++)
            for(int j=0;j<N;j++){
                used[i][j] = false;
                cGrid[i][j] = grid[i][j];
            }
        for(int i=0; i<M; i++)
            for(int j=0; j<N; j++)
                if(grid[i][j]=='1'){
                    if(!used[i][j]) count++;
                    expand(cGrid,i,j,used);
                }
        return count;
    }
    public void expand(Character[][]grid, int i, int j, Boolean[][] view){
        if(i<0||i>=grid.length||j<0||j>=grid[0].length||view[i][j]) return ;
        view[i][j]=true;
        if(grid[i][j]=='1'){
            view[i][j]=true;
            expand(grid,i-1,j,view);
            expand(grid,i,j-1,view);
            expand(grid,i+1,j,view);
            expand(grid,i,j+1,view);
        }
    }
}
class T_230{
    public int kthSmallest(TreeNode root, int k) {
        TreeNode node = root, cur;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (node!=null||!stack.isEmpty()){
            while (node!=null){
                stack.push(node);
                node=node.left;
            }
            cur = stack.pop();
            k--;
            if(k==0) return cur.val;
            node = cur.right;
        }
        return 0;
    }
    public void DFS_Recursion(TreeNode node, List<Integer> list){
        if(node==null) return ;
        list.add(node.val);
        if(node.left!=null) DFS_Recursion(node.left,list);
        if(node.right!=null) DFS_Recursion(node.right,list);
    }
    public List<Integer> DFS(TreeNode node){
        List<Integer> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (node!=null||!stack.isEmpty()){
            while (node!=null){
                stack.push(node);
                list.add(node.val);
                node=node.left;
            }
            node = stack.pop().right;
        }
        return list;
    }
}
class T_257{
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder();
        DFS(root, stringBuilder, res);
        return res;
    }
    public void DFS(TreeNode node, StringBuilder stringBuilder, List<String> list){
        stringBuilder.append(node.val);
        if(node.left==null&&node.right==null){
            list.add(stringBuilder.toString());
            return ;
        }
        if(node.left!=null) DFS(node.left, stringBuilder.append("->"), list);
        if(node.right!=null) DFS(node.right, stringBuilder.append("->"), list);
    }
}
class T_270{
    public int closestValue(TreeNode root, double target) {
        TreeNode node=root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        int pre = Integer.MAX_VALUE;
        while(node!=null || !stack.isEmpty()){
            while (node!=null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if(Math.abs(target- node.val)>Math.abs(target-pre)) return pre;
            pre = node.val;
            node = node.right;
        }
        return 0;
    }
}
class T_272{
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        TreeNode node=root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<Integer> listInt = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int len;
        while(node!=null || !stack.isEmpty()){
            while (node!=null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            listInt.add(node.val);
            node = node.right;
        }
        len = listInt.size();
        int arrNum[] = new int[len], i=0;
        for(int num:listInt)
            arrNum[i++] = num;
        return findClosestElements(arrNum,k, target);
    }
    public List<Integer> findClosestElements(int[] arr, int k, double x) {
        int len = arr.length,minIdx = 0, l, r;
        double min = Double.MAX_VALUE, tmp;
        List<Integer> res = new ArrayList<>();
        for(int i=0;i<len;i++){
            tmp = arr[i]>=x?arr[i]-x:x-arr[i];
            if(tmp<min){
                min = tmp;
                minIdx = i;
            }
        }
        k--;
        l = minIdx-1;
        r = minIdx+1;
        while(k!=0 && l>=0 && r<len){
            if(Math.abs(arr[l]-x)<=Math.abs(arr[r]-x)) l--;
            else r++;
            k--;
        }
        if(r>=len&&k!=0)
            while(k--!=0) l--;
        else if(l<0&&k!=0)
            while(k--!=0) r++;
        for(int i=l+1;i<r;i++)
            res.add(arr[i]);
        return res;
    }
    public List<Integer> closestKValues_PQ(TreeNode root, double target, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> Double.compare(Math.abs(target - o2), Math.abs(target - o1)));
        Stack<Integer> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        PQ_DFS(root, queue, k, target);
        while (!queue.isEmpty())
            stack.add(queue.poll());
        while (!stack.isEmpty())
            res.add(stack.pop());
        return res;
    }
    private void PQ_DFS(TreeNode node, PriorityQueue<Integer> queue, int k, double target) {
        if (node == null) return;
        if (queue.size() < k) {
            queue.offer(node.val);
        } else if (!queue.isEmpty() && Math.abs(target - node.val) < Math.abs(target - queue.peek())) {
            queue.poll();
            queue.offer(node.val);
        }
        System.out.println(queue.toString());
        PQ_DFS(node.right, queue, k, target);
        PQ_DFS(node.left, queue, k, target);
    }
}
class T_283{
    public void moveZeroes(int[] nums) {
        int count = 0, len = nums.length;
        for(int i=0;i<len;i++){
            if(nums[i]==0) count++;
            else nums[i-count]=nums[i];
        }
        while (count!=0){
            nums[len-count]=0;
            count--;
        }
    }
}
class T_285{
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode view=null, pre=null;
        while(node != null || !stack.isEmpty()){
            while (node!=null){
                stack.push(node);
                node = node.left;
            }
            view = stack.pop();
            if(pre==p) return view;
            pre = view;
            node = view.right;
        }
        return null;
    }
}
class T_322{
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int len = coins.length;
        int[] dp = new int[amount];
        int base = coins[0];
        for (int i = 0; i < base; i++)
            dp[i] = -1;
        for (int i = 0; i < len; i++) {
            if(coins[i]<=amount) dp[coins[i]] = 1;
        }
        for (int i = coins[0]+1; i <= amount; i++) {
            if(dp[i]==1) continue;
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < len; j++) {
                if(i-coins[j]<0) break;
                if(dp[i-coins[j]]<0) continue;
                dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
            }
            if(dp[i]==Integer.MAX_VALUE) dp[i] = -1;
        }
        return dp[amount];
    }
}
class T_560{
    public int subarraySum(int[] nums, int k) {
        int len = nums.length, count=0, sum=0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<len;i++) {
            sum += nums[i];
            if(map.containsKey(sum))
                map.put(sum, map.get(sum)+1);
            else
                map.put(sum, 1);
            if(sum==k) count++;
            if(map.containsKey(sum-k))
                count+=map.get(sum-k);
        }
        return count;
    }
}
class T_658{
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int len = arr.length,minIdx = 0, min = Integer.MAX_VALUE, tmp, l, r;
        List<Integer> res = new ArrayList<>();
        for(int i=0;i<len;i++){
            tmp = arr[i]>=x?arr[i]-x:x-arr[i];
            if(tmp<min){
                min = tmp;
                minIdx = i;
            }
        }
        k--;
        l = minIdx-1;
        r = minIdx+1;
        while(k!=0 && l>=0 && r<len){
            if(Math.abs(arr[l]-x)<=Math.abs(arr[r]-x)) l--;
            else r++;
            k--;
        }
        if(r>=len&&k!=0)
            while(k--!=0)
                l--;
        else if(l<0&&k!=0)
            while(k--!=0){
                r++;
            }
        for(int i=l+1;i<r;i++)
            res.add(arr[i]);
        return res;
    }
}
class T_739{
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length, cur, j=0;
        int res[] = new int[len];
        for(int i=0; i<len; i++){
            cur = temperatures[i];
            j=i;
            while (j<len){
                if(temperatures[j]>cur){
                    res[i] = j-i;
                    break;
                }
                j++;
            }
        }
        return res;
    }
}

public class Solution {
    public static void main(String[] args) {

    }
}
