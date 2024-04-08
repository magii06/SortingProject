import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.nio.file.Path;
import java.nio.file.Paths;
public class SortPanel extends JPanel {

    private List<SortElement> elements;
    public SortPanel() {
        elements = new ArrayList<>();
            for (int i=6;i>0;i--){
    Path resourceDirectory =
            Paths.get ("src","resources");
    String absolutePath =
            resourceDirectory.toFile().getAbsolutePath();
    ImageIcon imgIcon = new  ImageIcon(absolutePath+"/Image" +i+ ".png");
    SortElement se=new SortElement (imgIcon, i);
    elements.add (se);
    repaint ();
}
    setPreferredSize (new Dimension(2000, 500));
            setBackground(Color.WHITE);
            setBorder (BorderFactory.createLineBorder(null));
        }
   /* public void addElement(ImageIcon image, int size) {
        elements.add(new SortElement(image, size));
        repaint();
    }
*/
    // Helper method for bubble sort
    private void bubbleSortElements() {
        int n = elements.size();
        for (int k = 0; k < n - 1; k++) {
            boolean swapped = false;
            for (int i = 0; i < n - k - 1; i++) {
                // srawnenie na elementite po razmer
                if (elements.get(i).getSize() > elements.get(i + 1).getSize()) {
                    // Swap elements
                    SortElement temp = elements.get(i);
                    elements.set(i, elements.get(i + 1));
                    elements.set(i + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
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
            mergeSortElements(left, middle, right);
            mergeSortElements(middle + 1, middle, right);
            merge(left, middle, right);
        }
    }

    private void merge(int left, int middle, int right) {

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



    private void insertionSortElements() {
        int n = elements.size();
        for (int i = 1; i < n; ++i) {
            SortElement key = elements.get(i);
            int j = i - 1;
            while (j >= 0 && elements.get(j).getSize() > key.getSize()) {
                elements.set(j + 1, elements.get(j));
                j -= 1;
            }
            elements.set(j + 1, key);
        }
    }


    private void selectionSortElements() {
        int n = elements.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (elements.get(j).getSize() < elements.get(minIndex).getSize()) {
                    minIndex = j;
                }
            }
            SortElement temp = elements.get(i);
            elements.set(i, elements.get(minIndex));
            elements.set(minIndex, temp);
        }
    }



public void sort(String sortName) {

        if (sortName.equals("Bubble Sort")) {
            bubbleSortElements();
        } else if (sortName.equals("Merge Sort")) {
            mergeSortElements(0, elements.size() - 1, elements.size());
        } else {

        }
        repaint();
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

