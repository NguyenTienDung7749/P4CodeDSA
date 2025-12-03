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

    // ================== SORT 1: Selection Sort by name ==================
    public void selectionSortByName() {
        if (head == null) return;

        int n = getSize();
        Student[] a = toArray();
        
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j].getName().compareToIgnoreCase(a[minIndex].getName()) < 0) {
                    minIndex = j;
                }
            }
            // Swap minimum element with first element of unsorted part
            if (minIndex != i) {
                Student temp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = temp;
            }
        }
        
        fromArray(a);
        System.out.println("Results after using Selection Sort (by name):");
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

    // SORT 2: Merge Sort by mark (descending)
    public void mergeSortByMarkDesc() {
        int n = getSize();
        if (n <= 1) return;

        Student[] a = toArray();
        Student[] temp = new Student[n];
        mergeSortByMark(a, temp, 0, n - 1);
        fromArray(a);

        System.out.println("Results after using Merge Sort (in descending order of grade):");
        display();
    }

    private void mergeSortByMark(Student[] a, Student[] temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortByMark(a, temp, left, mid);
            mergeSortByMark(a, temp, mid + 1, right);
            mergeByMark(a, temp, left, mid, right);
        }
    }

    private void mergeByMark(Student[] a, Student[] temp, int left, int mid, int right) {
        // Copy to temp array
        for (int i = left; i <= right; i++) {
            temp[i] = a[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        // Merge back to original array (descending order by mark)
        while (i <= mid && j <= right) {
            if (temp[i].getMark() >= temp[j].getMark()) {
                a[k] = temp[i];
                i++;
            } else {
                a[k] = temp[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements from left half
        while (i <= mid) {
            a[k] = temp[i];
            i++;
            k++;
        }
    }

    // SEARCH 1: Jump Search by ID
    // Requires sorted array - jumps in blocks of √n, then linear search within block
    public Student jumpSearchById(String id) {
        int n = getSize();
        if (n == 0) return null;

        Student[] a = toArray();
        mergeSortByIdAsc(a);  // Sort by ID first

        int step = (int) Math.floor(Math.sqrt(n));
        int prev = 0;

        // Jump ahead until we find a block where the ID might be
        while (prev < n && a[Math.min(step, n) - 1].getId().compareToIgnoreCase(id) < 0) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
            if (prev >= n) return null;
        }

        // Linear search within the block
        while (prev < Math.min(step, n)) {
            if (a[prev].getId().compareToIgnoreCase(id) == 0) {
                return a[prev];
            }
            prev++;
        }

        return null;
    }

    // SEARCH 2: Interpolation Search by ID
    // Uses value-based estimation for position
    public Student interpolationSearchById(String id) {
        int n = getSize();
        if (n == 0) return null;

        Student[] a = toArray();
        mergeSortByIdAsc(a);  // Sort by ID first

        int low = 0;
        int high = n - 1;

        while (low <= high &&
               a[low].getId().compareToIgnoreCase(id) <= 0 &&
               a[high].getId().compareToIgnoreCase(id) >= 0) {

            if (low == high) {
                if (a[low].getId().compareToIgnoreCase(id) == 0) {
                    return a[low];
                }
                return null;
            }

            // Calculate interpolated position based on ID numeric values
            int lowIdVal = getNumericValue(a[low].getId());
            int highIdVal = getNumericValue(a[high].getId());
            int targetVal = getNumericValue(id);

            // Avoid division by zero
            if (highIdVal == lowIdVal) {
                if (a[low].getId().compareToIgnoreCase(id) == 0) {
                    return a[low];
                }
                return null;
            }

            int pos = low + (((targetVal - lowIdVal) * (high - low)) / (highIdVal - lowIdVal));

            // Ensure pos is within bounds
            if (pos < low) pos = low;
            if (pos > high) pos = high;

            int cmp = a[pos].getId().compareToIgnoreCase(id);

            if (cmp == 0) {
                return a[pos];
            } else if (cmp < 0) {
                low = pos + 1;
            } else {
                high = pos - 1;
            }
        }

        return null;
    }

    // Helper method to convert ID string to numeric value for interpolation
    private int getNumericValue(String id) {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            // If not a number, use hash-based value
            int hash = 0;
            for (char c : id.toLowerCase().toCharArray()) {
                hash = hash * 31 + c;
            }
            return Math.abs(hash);
        }
    }

    // Merge sort by ID ascending (for search operations)
    private void mergeSortByIdAsc(Student[] a) {
        int n = a.length;
        if (n <= 1) return;
        Student[] temp = new Student[n];
        mergeSortById(a, temp, 0, n - 1);
    }

    private void mergeSortById(Student[] a, Student[] temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortById(a, temp, left, mid);
            mergeSortById(a, temp, mid + 1, right);
            mergeById(a, temp, left, mid, right);
        }
    }

    private void mergeById(Student[] a, Student[] temp, int left, int mid, int right) {
        // Copy to temp array
        for (int i = left; i <= right; i++) {
            temp[i] = a[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        // Merge back to original array (ascending order by ID)
        while (i <= mid && j <= right) {
            if (temp[i].getId().compareToIgnoreCase(temp[j].getId()) <= 0) {
                a[k] = temp[i];
                i++;
            } else {
                a[k] = temp[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements from left half
        while (i <= mid) {
            a[k] = temp[i];
            i++;
            k++;
        }
    }
}