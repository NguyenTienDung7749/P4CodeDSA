package DangKyHocLai.LinkedListQUeue;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RegistrationQueue queue = new RegistrationQueue();

        while (true) {
            System.out.println("\nQUEUE ĐĂNG KÝ HỌC LẠI");
            System.out.println("1. Đăng ký (enqueue)");
            System.out.println("2. Phục vụ (dequeue)");
            System.out.println("3. Xem người đầu (peek)");
            System.out.println("4. Tìm theo ID (search)");
            System.out.println("5. Bubble Sort (theo tên)");
            System.out.println("6. Hiển thị queue");
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
                    queue.enqueue(new Student(id, name, course));
                    System.out.println("Đã đăng ký!");
                    break;

                case 2:
                    Student served = queue.dequeue();
                    if (served == null) System.out.println("Queue rỗng.");
                    else System.out.println("Đã phục vụ: " + served);
                    break;

                case 3:
                    Student first = queue.peek();
                    System.out.println(first == null ? "Queue rỗng." : first);
                    break;

                case 4:
                    System.out.print("Nhập ID cần tìm: ");
                    Student s = queue.search(sc.nextLine());
                    System.out.println(s == null ? "Không tìm thấy." : s);
                    break;

                case 5:
                    queue.bubbleSortByName();
                    break;

                case 6:
                    queue.display();
                    break;

                case 0:
                    System.out.println("Thoát chương trình.");
                    return;
            }
        }
    }
}
