package test;

import org.springframework.util.DigestUtils;

public class TestMD5 {
	public static void main(String[] args) {
		String str="012345678";  
		 // 22975d8a5ed1b91445f6c55ac121505b
		 // 781e5e245d69b566979b86e28d23f2c7
		System.out.println(DigestUtils.md5DigestAsHex(str.getBytes()));
		
		System.out.println(Math.ceil((double)16/15));
	}
}
