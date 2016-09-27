package string;

public class StringUtil {
	public static void main(String[] args) {
		String pakg = "com/cst/jarvis";
		String replaceAll = pakg.replaceAll("\\/", "\\.");
		System.out.println(replaceAll);
	}
}
