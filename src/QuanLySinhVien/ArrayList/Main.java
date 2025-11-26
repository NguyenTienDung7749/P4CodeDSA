package QuanLySinhVien.ArrayList;

import QuanLySinhVien.LinkedListQueue.Student;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RegistrationArrayList arr = new RegistrationArrayList();

        while (true) {
            System.out.println("\nStudent Management - ArrayList Structure (dynamic array)");
            System.out.println("1. Add students");
            System.out.println("2. Edit student by ID");
            System.out.println("3. Delete students by ID");
            System.out.println("4. Linear search by ID (Linear Search)");
            System.out.println("5. Binary search by ID (Binary Search)");
            System.out.println("6. Bubble Sort (by name)");
            System.out.println("7. Quick Sort (by ascending ID)");
            System.out.println("8. Show list");
            System.out.println("0. Exit");
            System.out.print("Select: ");

            int c;
            try {
                c = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer for the menu selection.");
                continue;
            }

            switch (c) {
                case 1:
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Grade (0 - 10): ");
                    double mark = Double.parseDouble(sc.nextLine());
                    arr.add(new Student(id, name, mark));
                    System.out.println("Added students.");
                    break;

                case 2:
                    System.out.print("Enter the ID to be edited: ");
                    String editId = sc.nextLine();
                    System.out.print("New name: ");
                    String newName = sc.nextLine();
                    System.out.print("New Grade (0 - 10):");
                    double newMark = Double.parseDouble(sc.nextLine());

                    if (arr.edit(editId, newName, newMark))
                        System.out.println("Student information updated.");
                    else
                        System.out.println("No student found with this ID.");
                    break;

                case 3:
                    System.out.print("Enter the ID to delete:");
                    String delId = sc.nextLine();
                    if (arr.delete(delId))
                        System.out.println("Student deleted.");
                    else
                        System.out.println("No student found with this ID.");
                    break;

                case 4:
                    System.out.print("Enter the ID you want to find (Linear Search): ");
                    Student s1 = arr.search(sc.nextLine());
                    System.out.println(s1 == null ? "Not found." : s1);
                    break;

                case 5:
                    // sort theo ID trước khi Binary Search
                    arr.quickSortByIdAsc();
                    System.out.print("Enter the ID you want to find (Binary Search): ");
                    Student s2 = arr.binarySearchById(sc.nextLine());
                    System.out.println(s2 == null ? "Not found." : s2);
                    break;

                case 6:
                    arr.bubbleSortByName();
                    break;

                case 7:
                    arr.quickSortByIdAsc();
                    break;

                case 8:
                    arr.display();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("There is no option for this.");
            }
        }
    }
}
