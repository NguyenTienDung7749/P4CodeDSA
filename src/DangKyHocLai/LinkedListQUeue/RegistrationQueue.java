package DangKyHocLai.LinkedListQUeue;

public class RegistrationQueue {
    private Node head;
    private Node tail;

    public boolean isEmpty() {
        return head == null;
    }

    public void enqueue(Student s) {
        Node node = new Node(s);
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

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
            System.out.println("Queue rỗng.");
            return;
        }
        System.out.println("Queue Hiện Tại:");
        Node current = head;
        int i = 1;
        while (current != null) {
            System.out.println(i + ". " + current.data);
            current = current.next;
            i++;
        }
    }

    // thuật toán search
    public Student search(String id) {
        Node current = head;
        while (current != null) {
            if (current.data.getId().equals(id)) return current.data;
            current = current.next;
        }
        return null;
    }

    // thuật toán sort ( dùng bubble sort)
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

        System.out.println("Kết quả sau khi dùng bubble sort:");
        display();
    }
}
