import java.util.List;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
public class SortPanel extends JPanel {

    private final List<SortElement> elements;

    public SortPanel() {
        elements = new ArrayList<>();
        setPreferredSize(new Dimension(400, 400));
        setBorder(BorderFactory.createLineBorder(null));
    }

    public void addElement(ImageIcon image, int size) {
        elements.add(new SortElement(image, size));
        repaint();
    }

    // Helper method for bubble sort
    private void bubbleSortElements() {
        int n = elements.size();
        for (int k = 0; k < n - 1; k++) {
            boolean swapped = false;
            for (int i = 0; i < n - k - 1; i++) {
                // Compare elements based on their 'size' attribute
                if (elements.get(i).getSize() > elements.get(i + 1).getSize()) {
                    // Swap elements
                    SortElement temp = elements.get(i);
                    elements.set(i, elements.get(i + 1));
                    elements.set(i + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                // Array is already sorted
                break;
            }
        }
        // Helper method for heap sort (basic implementation)
       /* private void heapSortElements() {
            //int n = elements.size(); // Build the heap (rearrange array)
         for (int i = n / 2 - 1; i >= 0; i--) { heapify(elements, n, i); } // One by one extract an element from heap
         for (int i = n - 1; i > 0; i--) { // Move current root to end
              SortElement temp = elements.get(0); elements.set(0, elements.get(i)); elements.set(i, temp); // call max heapify on the reduced heap
              heapify(elements, i, 0); } } // Heapify function to maintain heap property
         private void heapify(List<SortElement> arr, int n, int i) { int largest = i; // initialize largest as root
             int left = 2 * i + 1;
             left = 2*i + 1
             int right = 2 * i + 2;
             right = 2*i + 2 // If left child is larger than root
             if (left < n && elements.get(left).getSize() > elements.get(largest).getSize()) { largest = left; } // If right child is larger than largest so far
             if (right < n && elements.get(right).getSize() > elements.get(largest).getSize()) { largest = right; } // If largest is not root
             if (largest != i) { SortElement temp = elements.get(i); elements.set(i, elements.get(largest)); elements.set(largest, temp); // Recursively heapify the affected sub-tree
                  heapify(elements, n, largest); } }
        */
// Helper method for quicksort (basic implementation)
        /*private void quickSortElements(int low, int high) { if (low < high) { // Choose the pivot element (here, the last element)
     int pivotIndex = partition(low, high); quickSortElements(low, pivotIndex - 1); // Sort left subarray
             quickSortElements(pivotIndex + 1, high); // Sort right subarray
             } }

             private int partition(int low, int high) { int pivot = elements.get(high).getSize(); int i = low - 1; for (int j = low; j < high; j++) { if (elements.get(j).getSize() <= pivot) { i++; SortElement temp = elements.get(i); elements.set(i, elements.get(j)); elements.set(j, temp); } } SortElement temp = elements.get(i + 1); elements.set(i + 1, elements.get(high)); elements.set(high, temp); return i + 1; }
      */
        // Helper method for merge sort (recursive)

        // Merge function for merge sort
        private void mergeSortElements(int left, int middle, int right) {
                    if (left < right) {
                        middle = (left + right) / 2;
                        mergeSortElements(left, middle); // Sort first half
                        mergeSortElements(middle + 1, right); // Sort second half
                        merge(left, middle, right); // Merge the sorted halves
                    }
                }
       private void merge(){
            // Create temporary subarrays
            int n1 = middle - left + 1;
            int n2 = right - middle;
            List<SortElement> leftSublist = new ArrayList<>();
            List<SortElement> rightSublist = new ArrayList<>();
            for (int i = 0; i < n1; i++) {
                leftSublist.add(elements.get(left + i));
            }
            for (int j = 0; j < n2; j++) {
                rightSublist.add(elements.get(middle + 1 + j));
            }
            // Merge the temporary arrays
            int i = 0, j = 0, k = left;
            while (i < n1 && j < n2) {
                if (leftSublist.get(i).getSize() <= rightSublist.get(j).getSize()) {
                    elements.set(k, leftSublist.get(i));
                    i++;
                } else {
                    elements.set(k, rightSublist.get(j));
                    j++;
                }
                k++;
            }
            // Copy remaining elements
            while (i < n1) {
                elements.set(k, leftSublist.get(i));
                i++;
                k++;
            }
            while (j < n2) {
                elements.set(k, rightSublist.get(j));
                j++;
                k++;
            }
        }


// Helper method for insertion sort
private void insertionSortElements() {
            n = elements.size();
            for (int i = 1; i < n; ++i) {
                SortElement key = elements.get(i);
                int j = i - 1; // Move elements of arr[0..i-1], that are greater than key, to one position ahead of their current position
 while (j >= 0 && elements.get(j).getSize() > key.getSize()) {
     elements.set(j + 1, elements.get(j)); j -= 1; }
 elements.set(j + 1, key); } }
// Helper method for selection sort
private void selectionSortElements() {
            n = elements.size();
            } for (int i = 0; i < n - 1; i++) {
    int minIndex = i; for (int j = i + 1; j < n; j++) {
        if (elements.get(j).getSize() < elements.get(minIndex).getSize()) { minIndex = j; } } // Swap the found minimum element with the first element of unsorted subarray
             SortElement temp = elements.get(i); elements.set(i, elements.get(minIndex)); elements.set(minIndex, temp); } }


    public void sort(String sortName) {
        // Call the appropriate sorting method based on sortName
        if (sortName.equals("Bubble Sort")) {
            bubbleSortElements();
        } else if (sortName.equals("Merge Sort")) {
            mergeSortElements(0, elements.size() - 1);
        } else {
            // Implement other sorting algorithms here
        }
        repaint(); // Refresh the visual representation
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        int x = 10;
        int y = 10;
        int gap = 20;
        for (SortElement element : elements) {
            element.getImage().paintIcon(this, g, x, y);
            x += element.getImage().getIconWidth() + gap;
        }
    }
}
