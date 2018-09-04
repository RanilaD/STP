import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

public class MainForm extends JFrame {
    private JTree JTreeAVL;
    private JTree JTreeBST;
    private JPanel rootPanel;
    private JButton loadTreesFromFileButton;
    private JTabbedPane tabbedPane1;
    private JSpinner kMax;
    private JSpinner nElements;
    private JSpinner nIterations;
    private JButton randomSearchButton;
    private JLabel avlSearchResult;
    private JLabel bstSearchResult;

    private int kMaxValue;
    private int nElementsValue;
    private int nIterationsValue;

    public MainForm() {
        setupUi();
        JTreeBST.setModel(null);
        JTreeAVL.setModel(null);
    }

    private void setupUi() {
        setSize(600, 600);
        setContentPane(rootPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        loadTreesFromFileButton.addActionListener(e -> loadTrees());
        randomSearchButton.addActionListener(e -> randomSearch());

        kMax.addChangeListener(e -> kMaxValue = (int)kMax.getValue());
        nIterations.addChangeListener(e -> nIterationsValue = (int)nIterations.getValue());
        nElements.addChangeListener(e -> nElementsValue = (int)nElements.getValue());

        kMax.setValue(750000);
        nIterations.setValue(500000);
        nElements.setValue(400000);

        setVisible(true);
    }

    private void randomSearch() {
        bstSearchResult.setText("BST");
        avlSearchResult.setText("AVL");

        AVLTree avl = new AVLTree();
        BinarySearchTree bst = new BinarySearchTree();

        for (int i = 0; i < nElementsValue; ++i) {
            int randomValue = (int)(Math.random() * kMaxValue);
            TreeNode node = new TreeNode(randomValue, randomValue);
            avl.insert(node);
            bst.insert(node);
        }

        long bstStartTime = System.currentTimeMillis();
        for (int i = 0; i < nIterationsValue; ++i) {
            bst.search((int)(Math.random() * kMaxValue));
        }
        long bstElapsedMillis = System.currentTimeMillis() - bstStartTime;

        long avlStartTime = System.currentTimeMillis();
        for (int i = 0; i < nIterationsValue; ++i) {
            avl.search((int)(Math.random() * kMaxValue));
        }
        long avlElapsedMillis = System.currentTimeMillis() - avlStartTime;

        avlSearchResult.setText("AVL: затрачено " + avlElapsedMillis + " мс");
        bstSearchResult.setText("BST: затрачено " + bstElapsedMillis + " мс");
    }

    private void loadTrees() {
        BinarySearchTree bst = new BinarySearchTree();
        AVLTree avl = new AVLTree();

        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(ClassLoader.getSystemResource("keyValuePairs.txt").toURI())));
            while ((line = br.readLine()) != null) {
                String keyValuePair[] = line.split(":");
                try {
                    TreeNode node = new TreeNode(Integer.parseInt(keyValuePair[0]), Integer.parseInt(keyValuePair[1]));
                    bst.insert(node);
                    avl.insert(node);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
/*
        TreeNode nodes[] = new TreeNode[5];
        nodes[0] = new TreeNode(5, 123);
        nodes[1] = new TreeNode(6, 123);
        nodes[2] = new TreeNode(3, 123);
        nodes[3] = new TreeNode(7, 123);
        nodes[4] = new TreeNode(9, 123);
*/
/*
        TreeNode nodes[] = new TreeNode[5];
        nodes[0] = new TreeNode(5, 123);
        nodes[1] = new TreeNode(6, 123);
        nodes[2] = new TreeNode(4, 123);
        nodes[3] = new TreeNode(3, 123);
        nodes[4] = new TreeNode(1, 123);
*/
/*
        for (TreeNode node : nodes) {
            bst.insert(node);
        }
        for (TreeNode node : nodes) {
            avl.insert(node);
        }
*/
        loadTreeToModel(bst);
        loadTreeToModel(avl);
    }

    private void loadTreeToModel(Object tree) {
        if (!(tree instanceof AVLTree || tree instanceof BinarySearchTree)) return;

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultTreeModel model = new DefaultTreeModel(root);

        if (!(tree instanceof AVLTree)) {
            addNodeToModel(root, ((BinarySearchTree)tree).getRoot());
            JTreeBST.setModel(model);
            for (int i = 0; i < JTreeBST.getRowCount(); ++i) {
                JTreeBST.expandRow(i);
            }
        } else {
            addNodeToModel(root, ((AVLTree)tree).getRoot());
            JTreeAVL.setModel(model);
            for (int i = 0; i < JTreeAVL.getRowCount(); ++i) {
                JTreeAVL.expandRow(i);
            }
        }
    }

    private void addNodeToModel(DefaultMutableTreeNode root, TreeNode newNode) {
        if (newNode == null) return;
        DefaultMutableTreeNode newnode = new DefaultMutableTreeNode(newNode.getKey());
        root.add(newnode);
        if (newNode.getLeftChild() != null) {
            addNodeToModel(newnode, newNode.getLeftChild());
        }
        if (newNode.getRightChild() != null) {
            addNodeToModel(newnode, newNode.getRightChild());
        }
    }
}
