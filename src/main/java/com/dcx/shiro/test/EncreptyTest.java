package com.dcx.shiro.test;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by DCX-PC on 2015-08-19.
 */
public class EncreptyTest {
    @Test
      public void testEncreptyWithBase64(){
        //加密---没有salt，容易破解
        String str = "hello";
        String base64Encoded = Base64.encodeToString(str.getBytes());
        String str2 = Base64.decodeToString(base64Encoded);
        Assert.assertEquals(str, str2);

    }

    @Test
    public void testEncreptyWithHex(){
        String str = "hello";
        String base64Encoded = Hex.encodeToString(str.getBytes());
        String str2 = new String(Hex.decode(base64Encoded.getBytes()));
        Assert.assertEquals(str, str2);
    }
}
