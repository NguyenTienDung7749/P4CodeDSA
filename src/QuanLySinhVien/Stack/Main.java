package QuanLySinhVien.Stack;

import QuanLySinhVien.LinkedListQueue.Student;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RegistrationStack stack = new RegistrationStack();

        while (true) {
            try {
                System.out.println("\nStudent Management - Stack Structure (LIFO)");
                System.out.println("1. Push student (add to top)");
                System.out.println("2. Pop student (remove from top - LIFO)");
                System.out.println("3. Peek top student");
                System.out.println("4. Edit student by ID");
                System.out.println("5. Delete student by ID");
                System.out.println("6. Jump Search by ID");
                System.out.println("7. Interpolation Search by ID");
                System.out.println("8. Selection Sort (by name)");
                System.out.println("9. Merge Sort (by ID)");
                System.out.println("10. Show list");
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
                        
                        // Check for empty ID
                        if (id.trim().isEmpty()) {
                            throw new IllegalArgumentException("ID cannot be blank!");
                        }
                        
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        
                        // Check for null name
                        if (name == null) {
                            throw new NullPointerException("Name cannot be null!");
                        }
                        
                        System.out.print("Grade (0 - 10): ");
                        double mark = Double.parseDouble(sc.nextLine());
                        stack.push(new Student(id, name, mark));
                        System.out.println("Pushed student to stack.");
                        break;

                    case 2:
                        Student popped = stack.pop();
                        if (popped == null)
                            System.out.println("Stack is empty, no student to pop.");
                        else
                            System.out.println("Popped student: " + popped);
                        break;

                    case 3:
                        Student top = stack.peek();
                        if (top == null)
                            System.out.println("Stack is empty.");
                        else
                            System.out.println("Top student: " + top);
                        break;

                    case 4:
                        System.out.print("Enter the ID to be edited: ");
                        String editId = sc.nextLine();
                        
                        if (editId.trim().isEmpty()) {
                            throw new IllegalArgumentException("ID cannot be blank!");
                        }
                        
                        System.out.print("New name: ");
                        String newName = sc.nextLine();
                        System.out.print("New Grade (0 - 10): ");
                        double newMark = Double.parseDouble(sc.nextLine());

                        if (stack.edit(editId, newName, newMark))
                            System.out.println("Student information updated.");
                        else
                            System.out.println("No student found with this ID.");
                        break;

                    case 5:
                        System.out.print("Enter the ID to delete: ");
                        String delId = sc.nextLine();
                        
                        if (delId.trim().isEmpty()) {
                            throw new IllegalArgumentException("ID cannot be blank!");
                        }
                        
                        if (stack.delete(delId))
                            System.out.println("Student deleted.");
                        else
                            System.out.println("No student found with this ID.");
                        break;

                    case 6:
                        System.out.print("Enter the ID you want to find (Jump Search): ");
                        String searchId1 = sc.nextLine();
                        
                        if (searchId1.trim().isEmpty()) {
                            throw new IllegalArgumentException("ID cannot be blank!");
                        }
                        
                        // Validate ID is numeric
                        Integer.parseInt(searchId1);
                        
                        Student s1 = stack.jumpSearchById(searchId1);
                        System.out.println(s1 == null ? "Not found." : s1);
                        break;

                    case 7:
                        System.out.print("Enter the ID you want to find (Interpolation Search): ");
                        String searchId2 = sc.nextLine();
                        
                        if (searchId2.trim().isEmpty()) {
                            throw new IllegalArgumentException("ID cannot be blank!");
                        }
                        
                        // Validate ID is numeric
                        Integer.parseInt(searchId2);
                        
                        Student s2 = stack.interpolationSearchById(searchId2);
                        System.out.println(s2 == null ? "Not found." : s2);
                        break;

                    case 8:
                        stack.selectionSortByName();
                        break;

                    case 9:
                        stack.mergeSortByIdAsc();
                        break;

                    case 10:
                        stack.display();
                        break;

                    case 0:
                        System.out.println("Exit the program.");
                        return;

                    default:
                        System.out.println("There is no option for this.");
                }
            }
            // Handle menu input that is not a number
            catch (InputMismatchException e) {
                System.out.println("Error: You must enter numbers in the menu (no letters!).");
                sc.nextLine(); // clear input buffer
            }
            // Handle ID not being a number
            catch (NumberFormatException e) {
                System.out.println("Error: ID must be an integer (no letters or characters allowed!).");
            }
            // Handle empty ID
            catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            // Handle array index out of bounds
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error: Invalid index access - " + e.getMessage());
            }
            // Handle null pointer
            catch (NullPointerException e) {
                System.out.println("Error: Null object reference - " + e.getMessage());
            }
            // Handle other errors
            catch (Exception e) {
                System.out.println("Unknown error: " + e.getMessage());
            }
        }
    }
}
