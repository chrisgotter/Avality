import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class PickerPopup extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -694331407026370845L;
    private final JFileChooser fileChooser;
    public PickerPopup() {
        super("Test using JFilePicker");
         
        setLayout(new FlowLayout());
 
        // set up a file picker component
        JFilePicker filePicker = new JFilePicker("Pick a file", "Browse...");
        filePicker.addConvertActionListener((ActionEvent evt) -> clickConvert());
         
        // access JFileChooser class directly
        fileChooser = filePicker.getFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
         
        // add the component to the frame
        add(filePicker);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 110);
        setLocationRelativeTo(null);    // center on screen
    }
    private static String coalesce(String... values) {
        for (String value : values) {
            if (value != null) {
                return value;
            }
        }
        return null;
    }
    private void clickConvert() {
        try {
            File file = fileChooser.getSelectedFile();
            final Pattern pattern = Pattern.compile(
                    "^(?:(\"[^\"]*\")|([^,]*)),(?:(\"[^\"]*\")|([^,]*)),(?:(\"[^\"]*\")|([^,]*)),(?:(\"[^\"]*\")|([^,]*)),(?:(\"[^\"]*\")|([^,]*))$");
            Map<String, Company> companies = new HashMap<>();
            try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                while (line != null) {
        
                    final Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        final String userId = coalesce(matcher.group(1), matcher.group(2));
                        final String firstName = coalesce(matcher.group(3), matcher.group(4));
                        final String lastName = coalesce(matcher.group(5), matcher.group(6));
                        final String version = coalesce(matcher.group(7), matcher.group(8));
                        final String insuranceCompany = coalesce(matcher.group(9), matcher.group(10));
                        Company company = companies.get(insuranceCompany);
                        if (company == null) {
                            companies.put(insuranceCompany, company = new Company(insuranceCompany));
                        }
                        company.updateCustomer(userId, firstName, lastName, version);
                    } else {
                        throw new RuntimeException("\"" + line + "\" is improperly formatted");
                    }
                    line = reader.readLine();
                } 
                reader.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    
            String path = file.getAbsolutePath();
            String outputFolderPath = path.substring(0, path.lastIndexOf('.')) + "_conversion";
            File outputFolder = new File(outputFolderPath);
            if (!outputFolder.exists()) {
                outputFolder.mkdir();
            }
            StringBuffer buff = new StringBuffer("Conversion Results:");
            for (Company company : companies.values()) {
                final String outputFilePath = outputFolderPath + "\\" + company.getName().replace(' ', '_').toLowerCase() + ".csv";
                File outputFile = new File(outputFilePath);
                buff.append("\n").append(company.getName()).append(" written to ").append(outputFilePath);
                if (!outputFile.exists()) {
                    outputFile.createNewFile();
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, false));
                Iterator<Customer> itr = company.getCustomers().values().iterator();
                while (itr.hasNext()) {
                    writer.append(itr.next().toCSV());
                    if (itr.hasNext()) {
                        writer.append("\n");
                    }
                }
                writer.close();
            }
            JOptionPane.showMessageDialog(this, buff);
            this.close();
        } catch (IOException e) {
            
        }
    }
    public void close() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PickerPopup().setVisible(true);
            }
        });
    }
}
