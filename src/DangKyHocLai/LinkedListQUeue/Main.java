package DangKyHocLai.LinkedListQUeue;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RegistrationQueue queue = new RegistrationQueue();

        while (true) {
            try {
                // MENU
                System.out.println("\nQUEUE ĐĂNG KÝ HỌC LẠI");
                System.out.println("1. Đăng ký (enqueue)");
                System.out.println("2. Phục vụ (dequeue)");
                System.out.println("3. Xem người đầu (peek)");
                System.out.println("4. Tìm theo ID (search)");
                System.out.println("5. Bubble Sort (theo tên)");
                System.out.println("6. Hiển thị queue");
                System.out.println("0. Thoát");
                System.out.print("Chọn: ");

                // ---- LỖI 1: Nhập chữ gây InputMismatchException
                int c = sc.nextInt();
                sc.nextLine(); // clear bộ đệm

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
                        if (served == null)
                            System.out.println("Queue rỗng.");
                        else
                            System.out.println("Đã phục vụ: " + served);
                        break;

                    case 3:
                        Student first = queue.peek();
                        System.out.println(first == null ? "Queue rỗng." : first);
                        break;

                    case 4:
                        System.out.print("Nhập ID cần tìm: ");
                        String searchID = sc.nextLine();

                        // LỖI 2 ID rỗng ( không nhập gì)
                        if (searchID.trim().isEmpty()) {
                            throw new IllegalArgumentException("ID không được để trống!");
                        }

                        // LỖI 3: ID không phải số → NumberFormatException
                        Integer.parseInt(searchID);

                        Student found = queue.search(searchID);
                        System.out.println(found == null ? "Không tìm thấy." : found);
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

                    default:
                        System.out.println("Không có lựa chọn này.");
                }

            }

            // LỖI 1: nhập chữ vào menu
            catch (InputMismatchException e) {
                System.out.println("Lỗi: Bạn phải nhập số trong menu (không được nhập chữ!).");
                sc.nextLine(); // clear input rác
            }

            // LỖI 2: ID không phải số
            catch (NumberFormatException e) {
                System.out.println("Lỗi: ID phải là số nguyên (không được nhập chữ hoặc ký tự!).");
            }

            // LỖI 2A: ID rỗng
            catch (IllegalArgumentException e) {
                System.out.println("Lỗi: " + e.getMessage());
            }

            // Lỗi khác
            catch (Exception e) {
                System.out.println("Lỗi không xác định: " + e.getMessage());
            }
        }
    }
}
