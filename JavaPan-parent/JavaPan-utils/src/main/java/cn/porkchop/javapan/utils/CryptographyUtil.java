package cn.porkchop.javapan.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class CryptographyUtil {

    /**
     * Md5加密
     * @param str
     * @param salt
     * @return
     */
    public static String md5(String str,String salt){
        return new Md5Hash(str,salt).toString();
    }

    public static void main(String[] args) {
        // effb40d0a4c6b58e5535ad8bbcbbfb12
        String password="123";
        System.out.println("Md5加密："+CryptographyUtil.md5(password, "cn.porkchop.javapan"));
    }
}

