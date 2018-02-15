package huffmancompression;

/**
 * This class defines a HuffmanTree that uses HuffmanNodes for storing information
 *
 * @author Drew Hans
 */
public class HuffmanTree implements Comparable<HuffmanTree> {

    private HuffmanNode root;

    /**
     * HuffmanTree default constructor
     */
    public HuffmanTree() {
        this.root = null;
    }//end HuffmanTree constructor 

    /**
     * HuffmanTree constructor - combine two HuffmanTrees into one
     *
     * @param ht1 - new left HuffmanTree
     * @param ht2 - new right HuffmanTree
     */
    public HuffmanTree(HuffmanTree ht1, HuffmanTree ht2) {
        this.root = new HuffmanNode();
        this.root.setLeft(ht1.root);
        this.root.setRight(ht2.root);
        this.root.setWeight(ht1.getRoot().getWeight() + ht2.getRoot().getWeight());
    }//end HuffmanTree constructor

    /**
     * HuffmanTree constructor - create a tree containing one leaf node
     *
     * @param e - an element value for root
     * @param w - a weight value for root
     */
    public HuffmanTree(char e, int w) {
        this.root = new HuffmanNode(e, w);
    }//end HuffmanTree constructor 

    /**
     * compareTo method - from Comparable interface
     *
     * @param ht - another HuffmanTree
     * @return -1,0,1 as defined by Java's compareTo documentation
     */
    @Override
    public int compareTo(HuffmanTree ht) {
        if (ht.getRoot().getWeight() > this.getRoot().getWeight()) {
            return 1; // return positive if ht's weight is greater than this HuffmanTree's weight
        } else if (this.getRoot().getWeight() == ht.getRoot().getWeight()) {
            return 0; // return zero if ht's weight is equal to this HuffmanTree's weight
        } else {
            return -1; // return negative if ht's weight is less than this HuffmanTree's weight
        }
    }//end compareTo method

    /**
     * getRoot method
     *
     * @return this.root
     */
    public HuffmanNode getRoot() {
        return this.root;
    }//end getRoot method

}//end HuffmanTree class
