package lab5.task3;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

public class CipherFilterWriter extends FilterWriter {
    private int key; // Ключ для зсуву символів

    public CipherFilterWriter(Writer out, int key) {
        super(out);
        this.key = key;
    }

    @Override
    public void write(int c) throws IOException {
        super.write(c + key);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) {
            cbuf[i] = (char) (cbuf[i] + key);
        }
        super.write(cbuf, off, len);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        write(str.toCharArray(), off, len);
    }
}