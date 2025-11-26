package QuanLySinhVien.LinkedListQueue;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RegistrationQueue queue = new RegistrationQueue();

        while (true) {
            try {
                // MENU
                System.out.println("\nStudent Management - LinkedList (Queue) Structure");
                System.out.println("1. Add students (enqueue)");
                System.out.println("2. Delete the first student (dequeue - FIFO)");
                System.out.println("3. See first student (peek)");
                System.out.println("4. Search by ID (Linear Search)");
                System.out.println("5. Search by ID (Binary Search)");
                    System.out.println("6. Bubble Sort (by name)");
                System.out.println("7. Quick Sort (in descending order of grade)");
                System.out.println("8. Show list");
                System.out.println("0. Exit");
                System.out.print("Select: ");

                // LỖI 1: Nhập chữ gây InputMismatchException
                int c = sc.nextInt();
                sc.nextLine(); // clear bộ đệm

                switch (c) {
                    case 1:
                        System.out.print("ID: ");
                        String id = sc.nextLine();

                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        System.out.print("Grade (0 - 10): ");
                        String markStr = sc.nextLine();
                        double mark = Double.parseDouble(markStr);

                        queue.enqueue(new Student(id, name, mark));
                        System.out.println("Added student to list (LinkedList Queue)!");
                        break;

                    case 2:
                        Student served = queue.dequeue();
                        if (served == null)
                            System.out.println("The list is empty, there are no students to delete.");
                        else
                            System.out.println("First student deleted:" + served);
                        break;

                    case 3:
                        Student first = queue.peek();
                        System.out.println(first == null ? "Empty list." : first);
                        break;

                    case 4:
                        System.out.print("Enter the ID you want to find (Linear Search):");
                        String searchID = sc.nextLine();

                        // LỖI 2 ID rỗng ( không nhập gì)
                        if (searchID.trim().isEmpty()) {
                            throw new IllegalArgumentException("ID cannot be blank!");
                        }

                        // LỖI 3: ID không phải số → NumberFormatException
                        Integer.parseInt(searchID);

                        Student found = queue.search(searchID);
                        System.out.println(found == null ? "Not found." : found);
                        break;

                    case 5:
                        System.out.print("Enter the ID you want to find (Binary Search): ");
                        String searchID2 = sc.nextLine();

                        if (searchID2.trim().isEmpty()) {
                            throw new IllegalArgumentException("ID cannot be blank!");
                        }

                        Integer.parseInt(searchID2); // tạo NumberFormatException nếu nhập chữ

                        Student found2 = queue.binarySearchById(searchID2);
                        System.out.println(found2 == null ? "Not found." : found2);
                        break;

                    case 6:
                        queue.bubbleSortByName();
                        break;

                    case 7:
                        queue.quickSortByMarkDesc();
                        break;

                    case 8:
                        queue.display();
                        break;

                    case 0:
                        System.out.println("Exit the program.");
                        return;

                    default:
                        System.out.println("There is no option for this.");
                }

            }

            // LỖI 1: nhập chữ vào menu
            catch (InputMismatchException e) {
                System.out.println("Error: You must enter numbers in the menu (no letters!).");
                sc.nextLine(); // clear input rác
            }

            // LỖI 2: ID không phải số
            catch (NumberFormatException e) {
                System.out.println("Error: ID must be an integer (no letters or characters allowed!).");
            }

            // LỖI 2A: ID rỗng
            catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            // Lỗi khác
            catch (Exception e) {
                System.out.println("Unknown error: " + e.getMessage());
            }
        }
    }
}
