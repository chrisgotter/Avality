// taken from: https://www.codejava.net/java-se/swing/file-picker-component-in-swing 

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JFilePicker extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1795992968956468115L;
    private JLabel label;
    private JTextField textField;
    private JButton browseButton;

    private JFileChooser fileChooser;
    private JButton convertButton;
    public JFilePicker(String textFieldLabel, String buttonLabel) {

        fileChooser = new JFileChooser();
        FileNameExtensionFilter csvExtension = new FileNameExtensionFilter(".csv", "csv");
        FileNameExtensionFilter txtExtension = new FileNameExtensionFilter(".txt", "txt");
        fileChooser.addChoosableFileFilter(csvExtension);
        fileChooser.addChoosableFileFilter(txtExtension);
        fileChooser.setFileFilter(csvExtension);
        setLayout(new BorderLayout(5, 5));

        // creates the GUI
        label = new JLabel(textFieldLabel);

        textField = new JTextField(30);
        textField.setEnabled(false);
        browseButton = new JButton(buttonLabel);
        convertButton = new JButton("Convert");

        textField.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                browseButtonActionPerformed();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
        browseButton.addActionListener((ActionEvent evt) -> browseButtonActionPerformed());
        
        JPanel wrapper = new JPanel();
        wrapper.add(convertButton);
        add(label, BorderLayout.WEST);
        add(textField, BorderLayout.CENTER);
        add(browseButton, BorderLayout.EAST);
        add(wrapper, BorderLayout.SOUTH);
    }
    public void addConvertActionListener(ActionListener l) {
        convertButton.addActionListener(l);
    }
    private void browseButtonActionPerformed() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }



    public void addFileTypeFilter(String extension, String description) {
        FileTypeFilter filter = new FileTypeFilter(extension, description);
        fileChooser.addChoosableFileFilter(filter);
    }

    public File getSelectedFile() {
        return this.fileChooser.getSelectedFile();
    }

    public String getSelectedFilePath() {
        return textField.getText();
    }

    public JFileChooser getFileChooser() {
        return this.fileChooser;
    }
}
