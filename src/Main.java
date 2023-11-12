import javax.swing.tree.TreeNode;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}


// reviewing this, just getting whiteboard concept in my head for now since trying to understand the algorithm conceptually
// and then also the code is too much. so, here's the premise of the algorithm.
// preorder = [3,9,20,15,7]
// inorder = [9,3,15,20,7]
// in order will have its values mapped as keys with index as the value. will then go through pre-order[] and see
// that pre-order will give all the parent nodes 3,9,20. start with value three and retrieve inOrder index of 1
// where [9] will be in L branch and [25,30,7] will be in R branch. again, we see 9 from pre-order will be index 0
// of inOrder and range will be modified to indicate and 9 has no valid nodes to L or right. then to 20 in preorder,
// we see so is index 3 in inOrder and 15 and 7 are valid nodes with clamped range. finally, leaf nodes 15 and 7 added
// this algorithm recursively finds a parent and splits into smaller subtrees until base case
class Solution {
    int preorderIndex;
    Map<Integer, Integer> inorderIndexMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        // build a hashmap to store value -> its index relations
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return arrayToTree(preorder, 0, preorder.length - 1);
    }

    private TreeNode arrayToTree(int[] preorder, int left, int right) {
        // if there are no elements to construct the tree
        if (left > right) return null;

        // select the preorder_index element as the root and increment it
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // build left and right subtree
        // excluding inorderIndexMap[rootValue] element because it's the root
        root.left = arrayToTree(preorder, left, inorderIndexMap.get(rootValue) - 1);
        root.right = arrayToTree(preorder, inorderIndexMap.get(rootValue) + 1, right);
        return root;
    }
}