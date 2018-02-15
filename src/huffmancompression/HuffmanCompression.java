package huffmancompression;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * HuffmanCompressJava Class
 *
 * @author Drew Hans
 */
public class HuffmanCompression {

    final static String FILEINPATH = "./Alice's Adventures in Wonderland by Lewis Carroll.txt";
    final static String FILEOUTPATH = "./alicecompressed.hans";
    final static String HUFFMANCODESFILEPATH = "./alicecompressed.hanstable";

    /**
     * main method - the program starts here
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileInputStream fileIn = null; // holds original file input stream
        FileOutputStream fileOut = null; // holds compressed file output stream
        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream(); // holds bytes to be written to fileOut
        char[] asciiChars = new char[256]; // holds every single ascii char, acts as a hash table
        int[] asciiCharsFreqs = new int[256]; // holds the frequency of ascii chars, also acts as a hash table

        // initialize our array of asciiChars and our array of asciiCharsFreqs
        for (int i = 0; i < 256; i++) {
            asciiChars[i] = (char) i; // 
            asciiCharsFreqs[i] = 0; // frequency is always zero because we haven't seen anything yet
        }

        try {
            // get the original file input stream
            fileIn = new FileInputStream(FILEINPATH);

            // look at each ascii char in the original file and increment the corresponding freq value
            int c;
            while ((c = fileIn.read()) != -1) {
                asciiCharsFreqs[c]++;
            }

            // build our Huffman Tree using asciiChars and asciiCharsFreqs (each char with freq > 0 gets a node)
            HuffmanTree ht = makeHuffmanTree(asciiChars, asciiCharsFreqs);

            // calculate our Huffman codes from the Huffman Tree (chars with 0 freq have null String value)
            String[] huffmanCodes = getHuffmanCodes(ht);

            // reset fileIn and init fileOut
            fileIn = new FileInputStream(FILEINPATH);
            fileOut = new FileOutputStream(FILEOUTPATH);

            // look at each ascii char in the original file and write it's Huffman code to bytesOut
            byte[] character = new byte[1]; // holds a single byte (character) from original file
            int codeIndex = 0; // holds the int value of character[0] for indexing huffmanCodes array
            byte tempByte = 0; // holds a byte's worth of compressed file contents
            int tempbyteIndex = 0; // the next bit that needs to be filled in tempByte
            while (fileIn.read(character) != -1) {
                codeIndex = character[0] & 0xFF; // convert byte value into an int

                // visit each bit in huffmanCodes[character]
                for (int i = 0; i < huffmanCodes[codeIndex].length(); i++) {
                    // slide over bits by 1 to make room for new bit
                    tempByte = (byte) (tempByte << 1);

                    // shove new bit into tempByte
                    if (huffmanCodes[codeIndex].charAt(i) == '1') {
                        tempByte = (byte) (tempByte | 0x01);
                    } else {
                        // do nothing, during earlier bit slide the rightmost bit defaulted to zero 
                    }
                    tempbyteIndex++;

                    // if bytes filled
                    if (tempbyteIndex == 8) {
                        bytesOut.write(tempByte); // write the byte to buffer
                        bytesOut.flush(); // flushes the content to the underlying stream
                        tempbyteIndex = 0; // reset the index
                        tempByte = 0; // reset the byte value
                    }
                }//end for loop
            }//end while loop

            // write the bytes in bytesOut to fileOut
            fileOut.write(bytesOut.toByteArray());
            fileOut.flush(); // flushes the content to the underlying stream

            // write the huffman codes to separate file (needed for decompressing)
            FileWriter fileWriter = new FileWriter(HUFFMANCODESFILEPATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < 256; i++) {
                bufferedWriter.write(i + "," + huffmanCodes[i]);
                bufferedWriter.newLine(); // .write(...) does not automatically append a new line
            }

            // close streams once we're done with them
            if (fileIn != null) {
                fileIn.close();
            }
            if (fileOut != null) {
                fileOut.close();
            }
            if (bytesOut != null) {
                bytesOut.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch (IOException ioe) {
            System.out.println("IOException thrown in main method: " + ioe.toString());
            System.out.println(ioe.getMessage());
        } catch (Exception e) {
            System.out.println("Exception thrown in main method: " + e.toString());
            System.out.println(e.getMessage());
        }
    }//end main method

    /**
     * makeHuffmanTree - generates a HuffmanTree using a MinHeap (I didn't write the MinHeap code)
     *
     * @param asciiChars - a char array containing all 256 possible ascii char values
     * @param asciiCharFreqs - an int array containing the frequencies of all asciiChars in the original document
     * @return a HuffmanTree containing HuffmanNodes (note: Huffman codes have yet to be assigned)
     */
    private static HuffmanTree makeHuffmanTree(char[] asciiChars, int[] asciiCharFreqs) {
        // Create a min-heap to hold the HuffmanTrees as we build them
        MinHeap<HuffmanTree> h = new MinHeap<>();

        // Create a HuffmanTree for each asciiChar (with freq > 0) and add each HuffmanTree to the min-heap
        // (note: each HuffmanTree only contains one HuffmanNode)
        for (int i = 0; i < 256; i++) {
            if (asciiCharFreqs[i] > 0) {
                h.add(new HuffmanTree(asciiChars[i], asciiCharFreqs[i]));
            }
        }

        // Use the min-heap to combine all our HuffmanTrees into one tree
        while (h.getSize() > 1) {
            HuffmanTree ht1 = h.remove(); // remove the smallest weight tree
            HuffmanTree ht2 = h.remove(); // remove the next smallest weight tree

            // add new HuffmanTree with left ht1 and right ht2
            h.add(new HuffmanTree(ht1, ht2)); // (root node weight = ht1.getRoot.getWeight + ht2.getRoot.getWeight)
        }

        // return the final HuffmanTree from the min-heap
        return h.remove();
    }//end makeHuffmanTree method

    /**
     * getHuffmanCodes - starts the assignHuffmanCode method, then returns the codes
     *
     * @param ht - a HuffmanTree containing HuffmanNodes
     * @return a String array containing the Huffman codes for all chars (null => no code)
     */
    private static String[] getHuffmanCodes(HuffmanTree ht) {
        if (ht.getRoot() == null) {
            return null; // you screwed up loser
        }
        String[] codes = new String[256]; // default string values are null (chars with 0 freq will have null code)
        assignHuffmanCode(ht.getRoot(), codes); // recursively call assignHuffmanCode until every node has one
        return codes;
    }//end getHuffmanCodes method

    /**
     * assignHuffmanCode - recursive function for assigning Huffman codes to HuffmanNodes
     *
     * @param node - a HuffmanNode (on first method call node should be HuffmanTree's root node)
     * @param codes - a String array containing the Huffman codes for all chars (null => no code)
     */
    private static void assignHuffmanCode(HuffmanNode node, String[] codes) {
        // if the node's left is null then we've reached a leaf node
        if (node.getLeft() != null) {
            node.getLeft().setCode(node.getCode() + "0");
            assignHuffmanCode(node.getLeft(), codes);

            node.getRight().setCode(node.getCode() + "1");
            assignHuffmanCode(node.getRight(), codes);
        } else {
            codes[(int) node.getElement()] = node.getCode();
        }
    }//end assignHuffmanCode method

}//end HuffmanCompressJava class
