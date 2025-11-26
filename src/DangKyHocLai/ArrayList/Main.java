package DangKyHocLai.ArrayList;

import DangKyHocLai.LinkedListQUeue.Student;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RegistrationArrayList arr = new RegistrationArrayList();

        while (true) {
            System.out.println("\n ArrayList Đăng Ký Học Lại:");
            System.out.println("1. Thêm (add)");
            System.out.println("2. Tìm kiếm (search)");
            System.out.println("3. Bubble Sort (theo tên)");
            System.out.println("4. Hiển thị");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int c = sc.nextInt();
            sc.nextLine();

            switch (c) {
                case 1:
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Tên: ");
                    String name = sc.nextLine();
                    System.out.print("Môn học: ");
                    String course = sc.nextLine();
                    arr.add(new Student(id, name, course));
                    break;

                case 2:
                    System.out.print("Nhập ID cần tìm: ");
                    Student s = arr.search(sc.nextLine());
                    System.out.println(s == null ? "Không tìm thấy." : s);
                    break;

                case 3:
                    arr.bubbleSortByName();
                    break;

                case 4:
                    arr.display();
                    break;

                case 0:
                    return;
            }
        }
    }
}

