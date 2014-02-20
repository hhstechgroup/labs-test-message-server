package junit;

import org.junit.Test;
import sun.misc.BASE64Encoder;

public class AttachmentTest {

    @Test
    public void shouldPrintBase64Code() {
        BASE64Encoder encoder = new BASE64Encoder();
        String lol = "attachment";
        String s = encoder.encode(lol.getBytes());
        System.out.println(s);
    }
}
