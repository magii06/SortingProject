import javax.swing.ImageIcon;
public class SortElement {

    private ImageIcon image;
    private int size;

    public SortElement(ImageIcon image, int size) {
        this.image = image;
        this.size = size;
    }

    public ImageIcon getImage() {
        return image;
    }

    public int getSize() {
        return size;
    }
}
