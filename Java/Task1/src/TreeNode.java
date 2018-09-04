public class TreeNode {
    protected int key;

    protected int value;

    protected TreeNode leftChild;

    protected TreeNode rightChild;

    protected TreeNode() { }

    public TreeNode(int key, int value) {
        this.key = key;
        this.value = value;
        leftChild = null;
        rightChild = null;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }
}
