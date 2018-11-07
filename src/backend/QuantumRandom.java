package backend;


import java.io.IOException;

/**
 * Generate true random numbers with Quantum Random Number Generator created by ANU
 * (Australian National University) Quantum Optics.
 * This implementation is based on the Random class available on the standard java's libraries.
 * Int, double, float and boolean generators use a true random seed of 1024 bits available on:
 * https://qrng.anu.edu.au/RawBin.php
 * Byte generator uses a true random seed of 1024 hexadecimal numbers available on:
 * https://qrng.anu.edu.au/RawHex.php
 */

public class QuantumRandom{

    private HTMLParser mHtmlParser;

    /**
     * Create a new quantum random number generator with t milliseconds available to grab one seed
     * @param t timeout in milliseconds
     */
    public QuantumRandom(int t){
        mHtmlParser = new HTMLParser(t);
    }

    /**
     * Returns a true random signed integer, ranging from 0 to 2^32-1.
     * @return true random and uniformly distributed signed int.
     * @throws IOException if it couldn't fetch data from the website
     */
    public int nextInt() throws IOException{
        String binary = mHtmlParser.getBinaryString();
        return Integer.parseInt(binary.substring(0,31),2);
    }

    /**
     * Returns a random integer ranging from 0 to n.
     * First, checks how many bits there's in n.
     * Then splits the binary string in substrings with the same bit length than n.
     * If the substring's value is greater than n, retries with the next substring.
     * If all substrings are greater than n, request more 1024 bits.
     * @param n the bound on the random number to be returned. Must be positive.
     * @return a true random int between 0 (inclusive) and n (exclusive)
     * @throws IllegalArgumentException if n isn't positive
     * @throws IOException if it couldn't fetch data from the website
     */
    public int nextInt(int n) throws IOException {
        if (n <= 0)
            throw new IllegalArgumentException("n must be positive");

        int tries = 0;
        boolean done = false;
        int randnum = 0;
        int bitsneeded = Integer.toBinaryString(n).length();

        do{
            String binary = mHtmlParser.getBinaryString();
            while((tries+1)*bitsneeded + bitsneeded < binary.length() && !done) {
                String tmp = binary.substring(bitsneeded * tries, bitsneeded * (tries + 1));
                randnum = Integer.parseInt(tmp, 2);

                while (randnum > n && (tries+1)*bitsneeded + bitsneeded < binary.length()){
                    tries++;
                    tmp = binary.substring(bitsneeded * tries, bitsneeded * (tries + 1));
                    randnum = Integer.parseInt(tmp, 2);
                }

                if(randnum > n)
                    tries = binary.length();
                else
                    done = true;

            }
            tries = 0;
        }while(!done);

        return randnum;
    }

    /**
     * Returns the next true random, uniformly distributed float value between 0.0 and 1.0.
     * The general contract of nextFloat is that one float value, chosen uniformly from the range
     * 0.0f (inclusive) to 1.0f (exclusive), is generated and returned.
     * All 2^24 possible float values of the form m x 2^-24,
     * where m is a positive integer less than 2^24,
     * are produced with equal probability.
     * @return the next true random and uniformly distributed float value between 0.0 and 1.0.
     * @throws IOException if it couldn't fetch data from the website
     */
    public float nextFloat() throws IOException {
        String binary = mHtmlParser.getBinaryString();
        return Integer.parseInt(binary.substring(0,24),2)/((float) (1 << 24));
    }

    /**
     * Returns the next true random and uniformly distributed double value between 0.0 and 1.0.
     * The general contract of nextDouble is that one double value, chosen uniformly from the range
     * 0.0d (inclusive) to 1.0d (exclusive), is generated and returned.
     * @return the next true random and uniformly distributed double value between 0.0 and 1.0.
     * @throws IOException if it couldn't fetch data from the website
     */
    public double nextDouble() throws IOException{
        String binary = mHtmlParser.getBinaryString();
        return (((long)Integer.parseInt(binary.substring(0,26),2) << 27)
                + Integer.parseInt(binary.substring(0,27),2))
                / (double)(1L << 53);
    }

    /**
     * Returns the next true random and uniformly distributed boolean value.
     * The general contract of nextBoolean is that one boolean value is generated and returned.
     * The values true and false are produced with equal probability.
     * @return the next true random and uniformly distributed boolean value.
     * @throws IOException if it couldn't fetch data from the website
     */
    public boolean nextBoolean() throws IOException{
        String binary = mHtmlParser.getBinaryString();
        return binary.charAt(0)!='0';
    }

    /**
     * Generates random bytes and places them into a user-supplied byte array.
     * The number of random bytes produced is equal to the length of the byte array.
     * @param bytes the byte array to fill with random bytes
     * @throws IOException if it couldn't fetch data from the website
     * @throws NullPointerException if the byte array is null
     */
    public void nextBytes(byte[] bytes) throws IOException {
        int size = 0;

        do {
            String hex = mHtmlParser.getHexString();
            for(int i = size; i < hex.length()+size-1; i++) {
                int tmp = Integer.parseInt(hex.substring(i-size,i-size+2),16);
                bytes[i] = (byte) tmp;
                if(i == bytes.length-1)
                    return;
            }

            size += hex.length();

        }while(size<bytes.length);
    }

}
