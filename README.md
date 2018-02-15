# HuffmanCompression
This repository contains code written in Java 8 for Blackburn College's Spring 2018 Horizons In Computer Science class CS372.

HuffmanCompression takes in an ANSI (or 8-bit ascii) text file, counts the frequency of each ascii char in the file, uses a min-heap to build a Huffman tree, traverses the tree to generate Huffman codes for each ascii char with frequency greater than zero, and then uses the Huffman codes to compress the original file. 

During testing I was able to compress "Alice's Adventures in Wonderland by Lewis Carroll.txt" from 163 KB down to 97 KB (100 KB if you include the 3 KB table containing the Huffman codes, which you should). In HuffmanCompression.java I chose to write the compressed data and the table of Huffman codes to separate files to make debugging easier but in a real-world application you would want to combine both into a single file. alicecompressed.hans contains the compressed data and alicecompressed.hanstable contains the table of Huffman codes.

### Prerequisites
* Java 8 or higher

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
