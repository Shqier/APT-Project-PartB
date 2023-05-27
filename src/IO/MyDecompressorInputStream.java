package IO;
import java.io.IOException;
import java.io.InputStream;



public class MyDecompressorInputStream extends InputStream {

    private InputStream in;



    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }



    @Override
    public int read() throws IOException {
        return 0;
    }


    @Override
    public int read(byte[] b) throws IOException {
        int cols = (int)b[2] * 127 + (int)b[3];
        int binaryLength;
        int colNum = cols;

        for(int i = 0; i < 12; i++) {
            b[i] = ((Integer) in.read()).byteValue();
        }


        for(int i = 12; i < b.length; i++) {
            Integer num = in.read();
            String numAsBinary = Integer.toBinaryString(num);
            binaryLength = numAsBinary.length();

            if (binaryLength >= 8) {
                colNum -= 8;
            }

            else {
                for (int j = 0; j < 8 - binaryLength && colNum > numAsBinary.length(); j++) {
                    numAsBinary = "0" + numAsBinary;
                }
                colNum = colNum - numAsBinary.length();
            }

            for (int k = 0; k < numAsBinary.length() && i < b.length; k++) {
                b[i++] = Byte.parseByte(String.valueOf(numAsBinary.charAt(k)));
            }
            if (colNum <= 0) {
                colNum = cols;
            }
            i--;
        }

        return 0;
    }

}