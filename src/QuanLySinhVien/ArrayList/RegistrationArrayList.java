package QuanLySinhVien.ArrayList;

import QuanLySinhVien.LinkedListQueue.Student;

public class RegistrationArrayList {

    private Student[] arr;
    private int size;

    public RegistrationArrayList() {
        arr = new Student[5];
        size = 0;
    }

    public int getSize() {
        return size;
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
            System.out.println("The ArrayList is empty.");
            return;
        }

        System.out.println("STUDENT LIST (ArrayList)");
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + arr[i]);
        }
    }

    // SEARCH 1: Linear Search theo ID
    public Student search(String id) {
        for (int i = 0; i < size; i++) {
            if (arr[i].getId().equals(id)) {
                return arr[i];
            }
        }
        return null;
    }

    // SEARCH 2: Binary Search theo ID
    // Mảng đã sort theo ID tăng dần (gọi quickSortByIdAsc trước)
    public Student binarySearchById(String id) {
        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = arr[mid].getId().compareToIgnoreCase(id);

            if (cmp == 0) {
                return arr[mid];
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    // SORT 1: Bubble Sort theo tên
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

        System.out.println("ArrayList after Bubble Sort (by name):");
        display();
    }

    // indexOf phục vụ edit/delete
    private int indexOf(String id) {
        for (int i = 0; i < size; i++) {
            if (arr[i].getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public boolean edit(String id, String newName, double newMark) {
        int idx = indexOf(id);
        if (idx == -1) return false;

        arr[idx].setName(newName);
        arr[idx].setMark(newMark);
        return true;
    }

    public boolean delete(String id) {
        int idx = indexOf(id);
        if (idx == -1) return false;

        for (int i = idx + 1; i < size; i++) {
            arr[i - 1] = arr[i];
        }
        size--;
        arr[size] = null;
        return true;
    }

    // SORT 2: Quick Sort theo ID tăng dần
    public void quickSortByIdAsc() {
        if (size <= 1) {
            System.out.println("List has 0 or 1 element, no need Quick Sort.");
            return;
        }
        quickSortByIdAsc(arr, 0, size - 1);
        System.out.println("ArrayList after Quick Sort (by ascending ID):");
        display();
    }

    private void quickSortByIdAsc(Student[] a, int low, int high) {
        if (low < high) {
            int p = partitionIdAsc(a, low, high);
            quickSortByIdAsc(a, low, p - 1);
            quickSortByIdAsc(a, p + 1, high);
        }
    }

    private int partitionIdAsc(Student[] a, int low, int high) {
        Student pivot = a[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (a[j].getId().compareToIgnoreCase(pivot.getId()) < 0) {
                i++;
                Student tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }
        Student tmp = a[i + 1];
        a[i + 1] = a[high];
        a[high] = tmp;
        return i + 1;
    }
}
