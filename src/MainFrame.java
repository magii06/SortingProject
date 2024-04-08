import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
public class MainFrame extends JFrame {


    private SortPanel sortPanel;
    private JComboBox<String> sortComboBox;
    private JButton sortButton;

    public MainFrame() {
        super("Sorting App");


        sortPanel = new SortPanel();


        sortComboBox = new JComboBox<>(new String[]{"Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort", "Heap Sort"});


        sortButton = new JButton("Sort");


        sortButton.addActionListener(e -> {
            String selectedSort = (String) sortComboBox.getSelectedItem();
            sortPanel.sort(selectedSort);
        });


        JPanel panel = new JPanel();
        panel.add(sortComboBox);
        panel.add(sortButton);

        add(panel, BorderLayout.NORTH);
        add(sortPanel, BorderLayout.CENTER);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        MainFrame MainFrame = new MainFrame();
    }

}
