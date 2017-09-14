package cn.tarena.ht.tool;

import org.apache.shiro.crypto.hash.Md5Hash;


public class MD5HashPassword {
	
	//获取密文
	public static String getPassword(String userName,String password){
		
		Md5Hash hash = new Md5Hash(password, userName, 3);
		
		return hash.toString();
	}
	
	
	public static void main(String[] args) {
		
		/**
		 * source  要加密的密码
		 * salt    盐
		 * hashIterations  哈希次数
		 */
		Md5Hash md5Hash = new Md5Hash("admin", "admin", 3);
		
		
		System.out.println(md5Hash);
	}
}
