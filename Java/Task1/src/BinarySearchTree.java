public class BinarySearchTree {
    protected TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void insert(TreeNode newNode) {
        root = insertNode(root, newNode);
    }

    protected TreeNode insertNode(TreeNode root, TreeNode newNode) {
        if (root == null) {
            root = new TreeNode(newNode.getKey(), newNode.getValue());
            return root;
        }
        if (root.getKey() == newNode.getKey()) {
            root.setValue(newNode.getValue());
        } else if (newNode.getKey() < root.getKey()) {
            root.setLeftChild(insertNode(root.getLeftChild(), newNode));
        } else {
            root.setRightChild(insertNode(root.getRightChild(), newNode));
        }
        return root;
    }

    public TreeNode search(int key) {
        return search(root, key);
    }

    private TreeNode search(TreeNode node, int key) {
        while (node != null) {
            int current = node.getKey();
            if (key < current) {
                node = node.getLeftChild();
            } else if (key > current) {
                node = node.getRightChild();
            } else {
                return node;
            }
            node = search(node, key);
        }
        return node;
    }
}
