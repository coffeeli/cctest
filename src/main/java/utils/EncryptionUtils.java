package utils;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;


public class EncryptionUtils {
	
	/**
     * 密钥算法
    */
    private static final String KEY_ALGORITHM = "AES";
      
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
      
    /**
     * 初始化密钥
     *
     * @return byte[] 密钥
     * @throws Exception
     */
    public static byte[] initSecretKey() {
        //返回生成指定算法的秘密密钥的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
        //初始化此密钥生成器，使其具有确定的密钥大小
        //AES 要求密钥长度为 128
        kg.init(128);
        //生成一个密钥
        SecretKey  secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }
      
    /**
     * 转换密钥
     *
     * @param key   二进制密钥
     * @return 密钥
     */
    private static Key toKey(byte[] key){
        //生成密钥
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }
      
    /**
     * 加密
     *
     * @param data  待加密数据
     * @param key   密钥
     * @return byte[]   加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data,Key key) throws Exception{
        return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }
      
    /**
     * 加密
     *
     * @param data  待加密数据
     * @param key   二进制密钥
     * @return byte[]   加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data,byte[] key) throws Exception{
        return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }
      
      
    /**
     * 加密
     *
     * @param data  待加密数据
     * @param key   二进制密钥
     * @param cipherAlgorithm   加密算法/工作模式/填充方式
     * @return byte[]   加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{
        //还原密钥
        Key k = toKey(key);
        return encrypt(data, k, cipherAlgorithm);
    }
      
    /**
     * 加密
     *
     * @param data  待加密数据
     * @param key   密钥
     * @param cipherAlgorithm   加密算法/工作模式/填充方式
     * @return byte[]   加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{
        //实例化
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        //使用密钥初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, key);
        //执行操作
        return cipher.doFinal(data);
    }
      
      
      
    /**
     * 解密
     *
     * @param data  待解密数据
     * @param key   二进制密钥
     * @return byte[]   解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data,byte[] key) throws Exception{
        return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }
      
    /**
     * 解密
     *
     * @param data  待解密数据
     * @param key   密钥
     * @return byte[]   解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data,Key key) throws Exception{
        return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }
      
    /**
     * 解密
     *
     * @param data  待解密数据
     * @param key   二进制密钥
     * @param cipherAlgorithm   加密算法/工作模式/填充方式
     * @return byte[]   解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{
        //还原密钥
        Key k = toKey(key);
        return decrypt(data, k, cipherAlgorithm);
    }
    /**
     * 解密
     *
     * @param data  待解密数据
     * @param key   密钥
     * @param cipherAlgorithm   加密算法/工作模式/填充方式
     * @return byte[]   解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{
        //实例化
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        //使用密钥初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key);
        //执行操作
        return cipher.doFinal(data);
    }
      
    private static String  showByteArray(byte[] data){
        if(null == data){
            return null;
        }
        StringBuilder sb = new StringBuilder("{");
        for(byte b:data){
            sb.append(b).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("}");
        return sb.toString();
    }

    /**
     * 32位MD5加密大写
     * @param source
     * @return
     */
	public static String md5(String source) {  
    	 StringBuffer buf = new StringBuffer("");
         try {
             MessageDigest md = MessageDigest.getInstance("MD5");
             md.update(source.getBytes());
             byte b[] = md.digest();
             int i;
             for (int offset = 0; offset < b.length; offset++) {
                 i = b[offset];
                 if (i < 0)
                     i += 256;
                 if (i < 16)
                     buf.append("0");
                 buf.append(Integer.toHexString(i));
             }
  
         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
         }
         return buf.toString().toUpperCase();

    }  
    
	public static String aesEncode(String source){
		byte[] key = initSecretKey();
		 Key k = toKey(key);
		 byte[] encryptData = null;
		try {
			encryptData = encrypt(source.getBytes(), k);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showByteArray(encryptData);
	}
	
	public static String aesDecode(String encryptData){
		byte[] key = initSecretKey();
        Key k = toKey(key);
		byte[] decryptData = null;
		try {
			decryptData = decrypt(encryptData.getBytes(), k);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LogUtils.L.info("aesDecode : "+decryptData);
		return new String(decryptData);
	}
	
	
	 /**
     * 字节数组转hex字符串
     */
    public static String bytes2Hex(byte[] bts) {
        String des="";
        String tmp=null;
        for (int i=0;i<bts.length;i++) {
            tmp=(Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length()==1) {
                des+="0";
            }
            des+=tmp;
        }
        return des.toUpperCase();
    }
    
    private static byte toByte(char c) {
    	   byte b = (byte) "0123456789ABCDEF".indexOf(c);
    	   return b;
    }
    
    /**
     * hex字符串转字节数组
     */
    public static byte[] hex2Bytes(String hex) {
    	   int len = (hex.length() / 2);
    	   byte[] result = new byte[len];
    	   char[] achar = hex.toCharArray();
    	   for (int i = 0; i < len; i++) {
    	    int pos = i * 2;
    	    result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
    	   }
    	   return result;
    	}
    
    //DES加密
    public static byte[] desEncode(byte[] datasource, String password) {              
        try{  
        SecureRandom random = new SecureRandom();  
        DESKeySpec desKey = new DESKeySpec(password.getBytes());  
        //创建一个密匙工厂，然后用它把DESKeySpec转换成  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
        SecretKey securekey = keyFactory.generateSecret(desKey);  
        //Cipher对象实际完成加密操作  
        Cipher cipher = Cipher.getInstance("DES");  
        //用密匙初始化Cipher对象  
        cipher.init(Cipher.ENCRYPT_MODE, securekey, random);  
        //现在，获取数据并加密  
        //正式执行加密操作  
        return cipher.doFinal(datasource);  
        }catch(Throwable e){  
                e.printStackTrace();  
        }  
        return null;  
    }  
    
    //DES解密
    public static byte[] desDecode(byte[] src, String password) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }
	
    public static void main(String[] args) throws Exception {
    	String ds = "da73eff349dacfac6364cf6762d3d043efde05e2d39ed35fdc3a57a5826144367c5c19bf7d3320f173e45e8504c4abd9b2b670fb66fe8fa0c321cbbc6ece64b2fe0be32c2f1b6a27aa689d2afe08b0abcf0c3a1b4cb9f09a238264857b38df3461a8064cc1348a24567a8d21c6ed4c68482690695a8ce78d8f694377ab86e5a1c3817a2aaaf6338aee7edd1e791bb9aa09df86ba8ca4a57dd8aa5f138373237dbcc4f2fd5c29db0d88886acac59f7072f0fd6bccb48c11dfc320ecf4d8ea1af552228afcbacb1c685019d53aa391191714a9778694edbea907e49566464e9b2f9a0097bab655799bcd637ae1dc349d8e0802fea12e9f109c294a04eafaa760de577c0eb435a81d21e002b05825b987d975b09c7057aa4992c7f7f0a69ec8d12e97cd479421466430ff2cd30f84012352bfbe617ac447a4d3474685b298f8b36b76410aa6f217e397822aedf4e8221b16";
    	//System.out.println(new String(desDecode(hex2Bytes(ds),
    	//		XXTConstants.XXT_APP_PUBLIC_ACCOUNT_DES_PASSWD)));
    	
    	System.out.println(md5("Cms*Istudy2)14"));
    	byte[] key = initSecretKey();
        System.out.println("key："+showByteArray(key));
          
        Key k = toKey(key);
          
        String data ="AES数据";
        System.out.println("加密前数据: string:"+data);
        System.out.println("加密前数据: byte[]:"+showByteArray(data.getBytes()));
        System.out.println();
        byte[] encryptData = encrypt(data.getBytes(), k);
        System.out.println("加密后数据: byte[]:"+showByteArray(encryptData));
        //System.out.println("加密后数据: hexStr:"+Hex.encodeHexStr(encryptData));
        System.out.println();
        byte[] decryptData = decrypt(encryptData, k);
        System.out.println("解密后数据: byte[]:"+showByteArray(decryptData));
        System.out.println("解密后数据: string:"+new String(decryptData));
    }
}
