public class AVLTree extends BinarySearchTree {
   private int height(TreeNode node) {
       if (node == null) return 0;
       if (node.getLeftChild() == null && node.getRightChild() == null) return 1;
       if (node.getRightChild() == null) return height(node.getLeftChild()) + 1;
       if (node.getLeftChild() == null) return height(node.getRightChild()) + 1;
       return Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1;
   }

   private TreeNode balance(TreeNode node) {
       int left = height(node.getLeftChild());
       int right = height(node.getRightChild());

       if (left - right == -2) {
           left = height(node.getRightChild().getLeftChild());
           right = height(node.getRightChild().getRightChild());
           if (left < right) {
               node = lRotation(node);
           } else {
               node = rlRotation(node);
           }
       } else if (left - right == 2){
           left = height(node.getLeftChild().getLeftChild());
           right = height(node.getLeftChild().getRightChild());
           if (left > right) {
               node = rRotation(node);
           } else {
               node = lrRotation(node);
           }
       }

       return node;
   }

   private TreeNode lRotation(TreeNode node) {
       TreeNode temp = node.getRightChild();
       node.setRightChild(null);
       temp.setLeftChild(node);
       return temp;
   }

   private TreeNode rRotation(TreeNode node) {
        TreeNode temp = node.getLeftChild();
        node.setLeftChild(null);
        temp.setRightChild(node);
        return temp;
   }

   private TreeNode rlRotation(TreeNode node) {
        node.setRightChild(rRotation(node.getRightChild()));
        return lRotation(node);
   }

   private TreeNode lrRotation(TreeNode node) {
       node.setLeftChild(lRotation(node.getLeftChild()));
       return rRotation(node);
   }

   @Override
   protected TreeNode insertNode(TreeNode root, TreeNode newNode) {
       return balance(super.insertNode(root, newNode));
   }
}
