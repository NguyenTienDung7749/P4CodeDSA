package QuanLySinhVien.Stack;

import QuanLySinhVien.LinkedListQueue.Student;

public class RegistrationStack {

    private Student[] arr;
    private int size;

    public RegistrationStack() {
        arr = new Student[5];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    // Stack operation: push (LIFO - add to top)
    public void push(Student s) {
        if (size == arr.length) expand();
        arr[size] = s;
        size++;
    }

    // Stack operation: pop (LIFO - remove from top)
    public Student pop() {
        if (size == 0) return null;
        Student top = arr[size - 1];
        arr[size - 1] = null;
        size--;
        return top;
    }

    // Stack operation: peek (view top without removing)
    public Student peek() {
        if (size == 0) return null;
        return arr[size - 1];
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
            System.out.println("The Stack is empty.");
            return;
        }

        System.out.println("STUDENT LIST (Stack - LIFO)");
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + arr[i]);
        }
    }

    // SEARCH 1: Jump Search by ID
    // Requires sorted array - jumps in blocks of √n, then linear search within block
    public Student jumpSearchById(String id) {
        if (size == 0) return null;
        
        // Sort first for jump search to work (internal method without display)
        mergeSortByIdInternal();
        
        int step = (int) Math.floor(Math.sqrt(size));
        int prev = 0;
        
        // Jump ahead until we find a block where the ID might be
        while (prev < size && arr[Math.min(step, size) - 1].getId().compareToIgnoreCase(id) < 0) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(size));
            if (prev >= size) return null;
        }
        
        // Linear search within the block
        while (prev < Math.min(step, size)) {
            if (arr[prev].getId().compareToIgnoreCase(id) == 0) {
                return arr[prev];
            }
            prev++;
        }
        
        return null;
    }

    // SEARCH 2: Interpolation Search by ID
    // Uses value-based estimation for position
    public Student interpolationSearchById(String id) {
        if (size == 0) return null;
        
        // Sort first for interpolation search to work (internal method without display)
        mergeSortByIdInternal();
        
        int low = 0;
        int high = size - 1;
        
        while (low <= high && 
               arr[low].getId().compareToIgnoreCase(id) <= 0 && 
               arr[high].getId().compareToIgnoreCase(id) >= 0) {
            
            if (low == high) {
                if (arr[low].getId().compareToIgnoreCase(id) == 0) {
                    return arr[low];
                }
                return null;
            }
            
            // Calculate interpolated position based on ID numeric values
            int lowIdVal = getNumericValue(arr[low].getId());
            int highIdVal = getNumericValue(arr[high].getId());
            int targetVal = getNumericValue(id);
            
            // Avoid division by zero
            if (highIdVal == lowIdVal) {
                if (arr[low].getId().compareToIgnoreCase(id) == 0) {
                    return arr[low];
                }
                return null;
            }
            
            int pos = low + (((targetVal - lowIdVal) * (high - low)) / (highIdVal - lowIdVal));
            
            // Ensure pos is within bounds
            if (pos < low) pos = low;
            if (pos > high) pos = high;
            
            int cmp = arr[pos].getId().compareToIgnoreCase(id);
            
            if (cmp == 0) {
                return arr[pos];
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

    // SORT 1: Selection Sort by name
    public void selectionSortByName() {
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (arr[j].getName().compareToIgnoreCase(arr[minIndex].getName()) < 0) {
                    minIndex = j;
                }
            }
            // Swap minimum element with first element of unsorted part
            if (minIndex != i) {
                Student temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }

        System.out.println("Stack after Selection Sort (by name):");
        display();
    }

    // indexOf to support edit/delete
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

    // SORT 2: Merge Sort by ID ascending
    public void mergeSortByIdAsc() {
        if (size <= 1) {
            System.out.println("List has 0 or 1 element, no need Merge Sort.");
            return;
        }
        
        Student[] temp = new Student[size];
        mergeSortById(arr, temp, 0, size - 1);
        System.out.println("Stack after Merge Sort (by ascending ID):");
        display();
    }
    
    // Internal merge sort without display (for search operations)
    private void mergeSortByIdInternal() {
        if (size <= 1) return;
        Student[] temp = new Student[size];
        mergeSortById(arr, temp, 0, size - 1);
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

        // Merge back to original array
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
        
        // Right half elements are already in place
    }
}
