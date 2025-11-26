package QuanLySinhVien.LinkedListQueue;

public class RegistrationQueue {
    private Node head;
    private Node tail;

    public boolean isEmpty() {
        return head == null;
    }

    // Thêm sinh viên (enqueue)
    public void enqueue(Student s) {
        Node node = new Node(s);
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    // Xóa sinh viên đầu tiên (dequeue – FIFO)
    public Student dequeue() {
        if (isEmpty()) return null;

        Student out = head.data;
        head = head.next;

        if (head == null) tail = null;

        return out;
    }

    public Student peek() {
        return isEmpty() ? null : head.data;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("The list (LinkedList Queue) is empty.");
            return;
        }
        System.out.println("STUDENT LIST (LinkedList Queue)");
        Node current = head;
        int i = 1;
        while (current != null) {
            System.out.println(i + ". " + current.data);
            current = current.next;
            i++;
        }
    }

    // ================== SORT 1: Bubble Sort theo tên ==================
    public void bubbleSortByName() {
        if (head == null) return;

        boolean swapped;
        do {
            swapped = false;
            Node current = head;

            while (current.next != null) {
                if (current.data.getName()
                        .compareToIgnoreCase(current.next.data.getName()) > 0) {

                    // swap data
                    Student temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;

                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);

        System.out.println("Results after using Bubble Sort (by name):");
        display();
    }

    // Đổi LinkedList sang mảng Student[] và ngược lại
    private int getSize() {
        int count = 0;
        Node cur = head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    private Student[] toArray() {
        int n = getSize();
        Student[] a = new Student[n];
        Node cur = head;
        int i = 0;
        while (cur != null) {
            a[i++] = cur.data;
            cur = cur.next;
        }
        return a;
    }

    private void fromArray(Student[] a) {
        head = tail = null;
        for (Student s : a) {
            enqueue(s);  // tạo lại list theo thứ tự mới
        }
    }

    // SORT 2: Quick Sort theo điểm (giảm dần)
    public void quickSortByMarkDesc() {
        int n = getSize();
        if (n <= 1) return;

        Student[] a = toArray();
        quickSortByMark(a, 0, n - 1);
        fromArray(a);

        System.out.println("Results after using Quick Sort (in descending order):");
        display();
    }

    private void quickSortByMark(Student[] a, int low, int high) {
        if (low < high) {
            int p = partitionMarkDesc(a, low, high);
            quickSortByMark(a, low, p - 1);
            quickSortByMark(a, p + 1, high);
        }
    }

    private int partitionMarkDesc(Student[] a, int low, int high) {
        Student pivot = a[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (a[j].getMark() > pivot.getMark()) { // giảm dần
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

    // SEARCH 1: Linear Search theo ID
    public Student search(String id) {
        Node current = head;
        while (current != null) {
            if (current.data.getId().equals(id)) return current.data;
            current = current.next;
        }
        return null;
    }

    // SEARCH 2: Binary Search theo ID
    // Lưu ý: dùng mảng phụ, sort theo ID rồi binary search trên mảng.
    public Student binarySearchById(String id) {
        int n = getSize();
        if (n == 0) return null;

        Student[] a = toArray();
        quickSortByIdAsc(a, 0, n - 1); // sort theo ID trước

        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = a[mid].getId().compareToIgnoreCase(id);
            if (cmp == 0) return a[mid];
            else if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }

    // Quick sort theo ID tăng dần (phục vụ binary search)
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