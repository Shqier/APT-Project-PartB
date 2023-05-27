package IO;
import java.io.IOException;
import java.io.OutputStream;




public class MyCompressorOutputStream extends OutputStream {

    private OutputStream out;


    public MyCompressorOutputStream(OutputStream o) {
        this.out = o;
    }


    @Override
    public void write(int b) throws IOException { }


    @Override
    public void write(byte[] b) throws IOException {
        String rest = "";
        for(int i = 0; i < 12; i++) {
            out.write(b[i]);
        }

        int col = (int)b[2] * 127 + (int)b[3];
        int tempCol = col;
        int num;
        int i = 12;

        while(i < b.length) {
            if (rest.length() < 8 && rest.length() < tempCol)
            {
                rest += ((Byte) b[i]).toString();
            }

            if ((i == b.length - 1 || rest.length() == 8 || rest.length() == tempCol)) {
                num = Integer.parseInt(rest, 2);
                tempCol = tempCol - rest.length();
                out.write(num);
                rest = "";
            }

            if (tempCol <= 0)
            {
                tempCol = col;
            }

            i++;
        }
    }

}
