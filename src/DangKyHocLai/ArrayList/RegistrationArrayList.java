package DangKyHocLai.ArrayList;

import DangKyHocLai.LinkedListQUeue.Student; // import thẳng luôn đỡ code lại

public class RegistrationArrayList {

    private Student[] arr;
    private int size;

    public RegistrationArrayList() {
        arr = new Student[5];  // capacity ban đầu
        size = 0;
    }

    public void add(Student s) {
        if (size == arr.length) expand();
        arr[size] = s;
        size++;
    }

    private void expand() {
        Student[] newArr = new Student[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    public void display() {
        if (size == 0) {
            System.out.println("RegistrationArrayList rỗng.");
            return;
        }

        System.out.println("Danh sách :");
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + arr[i]);
        }
    }

    // Linear Search
    public Student search(String id) {
        for (int i = 0; i < size; i++) {
            if (arr[i].getId().equals(id)) {
                return arr[i];
            }
        }
        return null;
    }

    // BUBBLE SORT BY NAME
    public void bubbleSortByName() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {

                if (arr[j].getName().compareToIgnoreCase(arr[j + 1].getName()) > 0) {
                    Student temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println("RegistrationArrayList sau Bubble Sort (theo tên):");
        display();
    }
}
