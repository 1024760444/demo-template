package main;

public class Test {
	public static void main(String[] args) {
		System.out.println(fab(10));
	}
	
	public static int fab(int index) {
		if (index == 1 || index == 2) {
			return 1;
		} else {
			return fab(index - 1) + fab(index - 2);
		}
	}
}
