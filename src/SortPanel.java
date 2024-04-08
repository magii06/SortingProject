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
        for (int i = 6; i > 0; i--) {
            Path resourceDirectory =
                    Paths.get("src", "resources");
            String absolutePath =
                    resourceDirectory.toFile().getAbsolutePath();
            ImageIcon imgIcon = new ImageIcon(absolutePath + "/Image" + i + ".png");
            SortElement se = new SortElement(imgIcon, i);
            elements.add(se);
            repaint();
        }
        setPreferredSize(new Dimension(2000, 500));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(null));
    }

    /* public void addElement(ImageIcon image, int size) {
         elements.add(new SortElement(image, size));
         repaint();
     }
 */
    // bubble sort metod
    private void bubbleSortElements() {
        int n = elements.size();
        for (int k = 0; k < n - 1; k++) {
            boolean swapped = false;
            for (int i = 0; i < n - k - 1; i++) {
                // srawnenie na elementite po razmer
                if (elements.get(i).getSize() > elements.get(i + 1).getSize()) {
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

    // heap sort  metod
    private void heapSortElements() {
        int n = elements.size();
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(elements, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            SortElement temp = elements.get(0);
            elements.set(0, elements.get(i));
            elements.set(i, temp); //  max heapify
            heapify(elements, i, 0);
        }
    }

    // Heapify funkciq
    private void heapify(List<SortElement> arr, int n, int i) {
        int largest = i; // largest kato koren
        int left = 2 * i + 1;
        left = 2 * i + 1;
        int right = 2 * i + 2;
        right = 2 * i + 2; // ako lqwo dete>koren
        if (left < n && elements.get(left).getSize() > elements.get(largest).getSize()) {
            largest = left;
        }
        if (right < n && elements.get(right).getSize() > elements.get(largest).getSize()) {
            largest = right;
        } // ako largest ne e koren
        if (largest != i) {
            SortElement temp = elements.get(i);
            elements.set(i, elements.get(largest));
            elements.set(largest, temp); // heapify
            heapify(elements, n, largest);
        }
    }


    // metod quicksort
    private void quickSortElements(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);
            quickSortElements(low, pivotIndex - 1);
            quickSortElements(pivotIndex + 1, high);
        }
    }

    private int partition(int low, int high) {
        int pivot = elements.get(high).getSize();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (elements.get(j).getSize() <= pivot) {
                i++;
                SortElement temp = elements.get(i);
                elements.set(i, elements.get(j));
                elements.set(j, temp);
            }
        }
        SortElement temp = elements.get(i + 1);
        elements.set(i + 1, elements.get(high));
        elements.set(high, temp);
        return i + 1;
    }


    // merge sort metod
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
        } else if (sortName.equals("Selection Sort")) {
            selectionSortElements();
        } else if (sortName.equals("Insertion Sort")) {
            insertionSortElements();
        } else if (sortName.equals("Quick Sort")) {
            quickSortElements(0, 5);
        } else if (sortName.equals("Heap Sort")) {
            heapify(elements, elements.size(), 5);
        }
        repaint();
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        int x = 10;
        int y = 10;
        int gap = 25;
        for (SortElement element : elements) {
            element.getImage().paintIcon(this, g, x, y);
            x += element.getImage().getIconWidth() + gap;
        }
    }
}

