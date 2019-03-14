
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class holds information of a single student
 * 
 * @author Thong Teav
 */
public class StudentInfo{
    //variables------------------------------------------------------------------------------------------------------------------
    private String firstName, lastName;
    private String birthDate, studentID;
    private String picutreFileLocation;
    private char gender;
    private List<String> enrolledPapers;
    //---------------------------------------------------------------------------------------------------------------------------
    
    //constructors---------------------------------------------------------------------------------------------------------------
    /**
     * Creates a student with their ID and default values for other attributes
     * 
     * @param studentID 
     */
    public StudentInfo(String studentID){
        this.studentID = studentID;
        this.firstName = "Firstname";
        this.lastName = "Lastname";
        this.birthDate = "01/01/2000";
        this.gender = 'M';
        this.enrolledPapers = new ArrayList<String>();
    }
    
    /**
     * Creates a student with student ID with the specified information
     * 
     * @param studentID 
     * @param firstName
     * @param lastName
     * @param birthDate
     * @param gender 
     */
    public StudentInfo(String studentID, String firstName, String lastName, String birthDate, char gender){
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.enrolledPapers = new ArrayList<>();
    }
    //---------------------------------------------------------------------------------------------------------------------------
    
    /**
     * Adds a paper to the list of papers using the specified string
     * 
     * @param paper a String representing the paper name
     */
    public void addPaper(String paper){
        if(paper != null){
            this.enrolledPapers.add(paper);
        }
    }
    
    /**
     * Gets the list of enrolled papers as a list of strings
     * 
     * @return 
     */
    public List<String> getEnrolledPapers(){
        return this.enrolledPapers;
    }
    
    /**
     * Sets the path to the picture of the student
     * 
     * @param fileName a String representing the path of the picture
     */
    public void setPictureFromFileName(String fileName){
        this.picutreFileLocation = fileName;
    }
    
    /**
     * Gets the path name to the picture
     * 
     * @return 
     */
    public String getPictureFileName(){
        return this.picutreFileLocation;
    }
    
    /**
     * Returns the first name of the student
     * 
     * @return 
     */
    public String getFirstName(){
        return this.firstName;
    }
    
    /**
     * Returns the last name of the student
     * 
     * @return 
     */
    public String getLastName(){
        return this.lastName;
    }
    
    /**
     * Returns the birth date of the student in the format dd/mm/yyyy
     * 
     * @return 
     */
    public String getBirthDate(){
        return this.birthDate;
    }
    
    /**
     * Gets the student ID of the student
     * 
     * @return 
     */
    public String getStudentID(){
        return this.studentID;
    }
    
    /**
     * Gets the gender of the student
     * 
     * @return 
     */
    public char getGender(){
        return this.gender;
    }
    
    /**
     * Gets a panel including different information about the student
     * 
     * @return 
     */
    public JComponent getStudentPanel(){
        return new StudentPanel();
    }
    
    /**
     * Returns a string representing different information of the student
     * 
     * @return 
     */
    public String toString(){
        String output = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n";
        output += "Name: " + firstName + " " + lastName + "\n";
        output += "Student ID: " + studentID + "\n";
        output += "Birthdate: " + birthDate + "\n";
        output += "Gender: " + gender + "\n";
        output += "Papers enrolled in: \n";
        for(String paper: enrolledPapers){
            output += "\t" + paper + "\n";
        }
        output += ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n";
        return output;
    }
    
    private class StudentPanel extends JPanel{
        private JTextField fNameTextField, lNameTextField;
        private JComboBox dayComboBox, monthComboBox, yearComboBox;
        private ButtonGroup btnGroup;
        private JRadioButton maleBtn, femaleBtn;
        private JLabel imgLabel;
        private ImageIcon image;
        
        public StudentPanel(){            
            //create name textfield
            JPanel namePanel = new JPanel(new GridLayout(2, 2));
            String[] names = {"First name: ", "Last name: "};
            JLabel fNameLbl = new JLabel(names[0], JLabel.TRAILING);
            JLabel lNameLbl = new JLabel(names[1], JLabel.TRAILING);
            this.fNameTextField = new JTextField(firstName);
            this.lNameTextField = new JTextField(lastName);   
            DocumentListener dl = new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent e) {                    
                    if(!warn()) {
                        firstName = fNameTextField.getText();
                        lastName = lNameTextField.getText();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(!warn()) {
                        firstName = fNameTextField.getText();
                        lastName = lNameTextField.getText();
                    }
                }

                public boolean warn() {
                    if (fNameTextField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "First name cannot be empty", "Error Massage",
                                JOptionPane.ERROR_MESSAGE);
                        return true;
                    } 
                    else if (lNameTextField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Last name cannot be empty", "Error Massage",
                                JOptionPane.ERROR_MESSAGE);
                        return true;
                    } 
                    
                    if (!fNameTextField.getText().matches("^[a-zA-Z ]*$")){
                        JOptionPane.showMessageDialog(null,
                                "First name can only contain text and spaces", "Error Massage",
                                JOptionPane.ERROR_MESSAGE);
                        return true;
                    } 
                    else if (!lNameTextField.getText().matches("^[a-zA-Z ]*$")){
                        JOptionPane.showMessageDialog(null,
                                "Last name can only contain text and spaces", "Error Massage",
                                JOptionPane.ERROR_MESSAGE);
                        return true;
                    }
                    return false;
                }
            }; 
            fNameTextField.getDocument().addDocumentListener(dl);
            lNameTextField.getDocument().addDocumentListener(dl);
            
            namePanel.add(fNameLbl);
            namePanel.add(fNameTextField);
            namePanel.add(lNameLbl);
            namePanel.add(lNameTextField);
            namePanel.setBorder(BorderFactory.createTitledBorder("Name"));
            
            
            //create birthday box
            String[] birthday = birthDate.split("/");
            YearMonth yearMonthObj = YearMonth.of(Integer.parseInt(birthday[2]), Integer.parseInt(birthday[1]));
            int daysInMonth = yearMonthObj.lengthOfMonth();
            ArrayList<String> dayString = new ArrayList<>();
            for(int i = 1; i <= daysInMonth; i++){
                if(i < 10){
                    dayString.add("0" + i);
                } else {
                    dayString.add(i + "");
                }
            }
            JPanel birthdayPanel = new JPanel();
            String[] monthString = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
            ArrayList<String> yearString = new ArrayList<>();
            for(int i = 1990; i <= 2017; i++){
                yearString.add(i + "");
            }
            JLabel dayLabel = new JLabel("Day");
            JLabel monthLabel = new JLabel("Month");
            JLabel yearLabel = new JLabel("Year");
            dayComboBox = new JComboBox(dayString.toArray());
            dayComboBox.setSelectedItem(birthday[0]);
            monthComboBox = new JComboBox(monthString);
            monthComboBox.setSelectedItem(birthday[1]);
            yearComboBox = new JComboBox(yearString.toArray());
            yearComboBox.setSelectedItem(birthday[2]);
            ItemListener il = new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange() == ItemEvent.SELECTED){
                        String day = (String) birthDate.split("/")[0];
                        YearMonth yearMonthObj = YearMonth.of(Integer.parseInt((String) yearComboBox.getSelectedItem()), Integer.parseInt((String) monthComboBox.getSelectedItem()));
                        int daysInMonth = yearMonthObj.lengthOfMonth();
                        dayString.clear();
                        dayComboBox.removeAllItems();
                        for (int i = 1; i <= daysInMonth; i++) {
                            if (i < 10) {                                
                                dayString.add("0" + i);
                                dayComboBox.addItem("0" + i);
                            } else {
                                dayString.add(i + "");
                                dayComboBox.addItem(i + "");
                            }
                        }  
                        dayComboBox.setSelectedItem(day);
                        birthDate = dayComboBox.getSelectedItem() + "/" + monthComboBox.getSelectedItem() + "/" + yearComboBox.getSelectedItem();
//                        System.out.println(birthDate);
                    }
                }
            };
            monthComboBox.addItemListener(il);
            yearComboBox.addItemListener(il);
            dayComboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    birthDate = dayComboBox.getSelectedItem() + "/" + monthComboBox.getSelectedItem() + "/" + yearComboBox.getSelectedItem();
                }
            });       
            
            birthdayPanel.add(dayLabel);
            birthdayPanel.add(dayComboBox);
            birthdayPanel.add(monthLabel);
            birthdayPanel.add(monthComboBox);
            birthdayPanel.add(yearLabel);
            birthdayPanel.add(yearComboBox);
            birthdayPanel.setBorder(BorderFactory.createTitledBorder("Birth date"));
            
            //create gender radio box
            JPanel genderPanel = new JPanel();
            this.maleBtn = new JRadioButton("Male");
            this.femaleBtn = new JRadioButton("Female"); 
            if(gender == 'M'){
                maleBtn.setSelected(true);
            } else {
                femaleBtn.setSelected(true);
            }
            maleBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gender = 'M';
                }
            });
            femaleBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gender = 'F';
                }
            });
            btnGroup = new ButtonGroup();
            btnGroup.add(this.maleBtn);
            btnGroup.add(this.femaleBtn);
            genderPanel.add(this.maleBtn);
            genderPanel.add(this.femaleBtn);
            genderPanel.setBorder(BorderFactory.createTitledBorder("Gender"));
            
            //add the picture from file path
            image = new ImageIcon(picutreFileLocation);
            imgLabel = new JLabel();
            imgLabel.setIcon(image);
           
            //add the different panels to the large panel     
            JPanel topPanel = new JPanel();
            topPanel.add(namePanel);
            topPanel.add(genderPanel);
            topPanel.add(birthdayPanel);
            
            JPanel midPanel = new JPanel();
            midPanel.add(new JLabel("Student ID: "));
            JTextField idTF = new JTextField(studentID);
            idTF.setEditable(false);
            midPanel.add(idTF);
            
            JPanel btmPanel = new JPanel();    
            btmPanel.add(imgLabel);
            this.setLayout(new BorderLayout());
            this.add(topPanel, BorderLayout.NORTH);
            this.add(midPanel, BorderLayout.CENTER);
            this.add(btmPanel, BorderLayout.SOUTH);
            this.setBorder(BorderFactory.createTitledBorder("Student Info"));
        }
    }
        
    public static void main(String[] args){
        StudentInfo student = new StudentInfo("12345678", "Tony", "Johnson", "02/02/1991", 'M');
        student.setPictureFromFileName("fernbird.png");
        
        //get screen size
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        
        JFrame frame = new JFrame("Student");
        frame.add(student.getStudentPanel());
        frame.pack();
        frame.setMinimumSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
