public class Test {
  public static void main(String[] args) {
    int num = -65;
    System.out.println(Integer.toBinaryString(num));
    System.out.println(">>");
    System.out.println((num >> 1) + " " + Integer.toBinaryString(num >> 1));
    System.out.println((num >> 2) + " " + Integer.toBinaryString(num >> 1));
    System.out.println("<<");
    System.out.println((num << 1) + " " + Integer.toBinaryString(num << 1));
    System.out.println((num << 2) + " " + Integer.toBinaryString(num << 1));

    num = 65;
    System.out.println(Integer.toBinaryString(num));
    System.out.println(">>");
    System.out.println((num >> 1) + " " + Integer.toBinaryString(num >> 1));
    System.out.println((num >> 2) + " " + Integer.toBinaryString(num >> 1));
    System.out.println("<<");
    System.out.println((num << 1) + " " + Integer.toBinaryString(num << 1));
    System.out.println((num << 2) + " " + Integer.toBinaryString(num << 1));

    System.out.println(Integer.MIN_VALUE / -1);
  }
}
