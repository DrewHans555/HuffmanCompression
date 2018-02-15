package huffmancompression;

/**
 * This class defines a HuffmanNode designed to be used with a HuffmanTree structure
 *
 * @author Drew Hans
 */
public class HuffmanNode {

    private char element;
    private int weight;
    private String code;
    private HuffmanNode left;
    private HuffmanNode right;

    /**
     * HuffmanNode default constructor
     */
    public HuffmanNode() {
        this.element = '\0';
        this.weight = 0;
        this.code = "";
        this.left = null;
        this.right = null;
    }//end HuffmanNode constructor

    /**
     * HuffmanNode constructor for the following parameters
     *
     * @param e - an element value
     * @param freq - the element's frequency
     */
    public HuffmanNode(char e, int freq) {
        this.element = e;
        this.weight = freq;
        this.code = "";
        this.left = null;
        this.right = null;
    }//end HuffmanNode constructor

    /**
     * HuffmanNode constructor for the following parameters
     *
     * @param w - the node weight
     * @param l - a left HuffmanNode
     * @param r - a right HuffmanNode
     */
    public HuffmanNode(int w, HuffmanNode l, HuffmanNode r) {
        this.element = '\0';
        this.weight = w;
        this.code = "";
        this.left = l;
        this.right = r;
    }//end HuffmanNode constructor

    /**
     * isLeaf method
     *
     * @return boolean
     */
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }//end isLeaf method

    /**
     * Get Method for element
     *
     * @return this.element
     */
    public char getElement() {
        return this.element;
    }//end getElement method

    /**
     * Get Method for weight
     *
     * @return this.weight
     */
    public int getWeight() {
        return this.weight;
    }//end getWeight method

    /**
     * Get Method for code
     *
     * @return this.code
     */
    public String getCode() {
        return this.code;
    }//end getCode method

    /**
     * Get Method for the left node
     *
     * @return this.next
     */
    public HuffmanNode getLeft() {
        return this.left;
    }//end getLeft method

    /**
     * Get Method for the right node
     *
     * @return this.right
     */
    public HuffmanNode getRight() {
        return this.right;
    }//end getRight method

    /**
     * Set Method for element
     *
     * @param e - new element value
     */
    public void setElement(char e) {
        this.element = e;
    }//end setElement method

    /**
     * Set Method for weight
     *
     * @param w - new weight value
     */
    public void setWeight(int w) {
        this.weight = w;
    }//end setWeight method

    /**
     * Set Method for code
     *
     * @param c - new code value
     */
    public void setCode(String c) {
        this.code = c;
    }//end setCode method

    /**
     * Set Method for left node
     *
     * @param newL - new left value
     */
    public void setLeft(HuffmanNode newL) {
        this.left = newL;
    }//end setLeft method

    /**
     * Set Method for right node
     *
     * @param newR - new right value
     */
    public void setRight(HuffmanNode newR) {
        this.right = newR;
    }//end setRight method

}//end HuffmanNode class
