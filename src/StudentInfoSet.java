
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * Uses a map data structure to hold unique student IDs as keys with a corresponding StudentInfo object as its value
 * 
 * @author Thong Teav
 */
public class StudentInfoSet {
    //variables------------------------------------------------------------------------------------------------------------------
    private Map<String, StudentInfo> studentMap;
    private String description;
    private DOMUtilities domUtil;
    //---------------------------------------------------------------------------------------------------------------------------
    
    //constructor----------------------------------------------------------------------------------------------------------------
    /**
     * Creates an empty map and a new DOMUtilities object
     */
    public StudentInfoSet(){
        this.studentMap = new HashMap<>();
        this.domUtil = new DOMUtilities();
    }
    //---------------------------------------------------------------------------------------------------------------------------
    
    /**
     * Creates students from the document passed 
     * 
     * @param doc 
     */
    public void loadStudentsFromXML(Document doc){
        doc.getDocumentElement().normalize();
        Node rootXMLNode = doc.getDocumentElement();
        //get the description - check if exist or not
        Collection<Node> descriptionNodes = domUtil.getAllChildNodes(rootXMLNode, "description");
        if(!descriptionNodes.isEmpty()) {
            this.description = domUtil.getTextContent(descriptionNodes.iterator().next());
        }
        
        //get the students and their information
        Collection<Node> studentNodes = domUtil.getAllChildNodes(rootXMLNode, "student");
        for(Node studentNode : studentNodes){
            //get student ID - must have only 1
            String studentID = domUtil.getAttributeString(studentNode, "studentID");
            //get gender - optional - check if exist            
            String genderString = domUtil.getAttributeString(studentNode, "gender");
            char gender = 0;
            if(genderString != null){
                gender = genderString.charAt(0);
            }            
            
            //get first name - must have only 1
            Collection<Node> firstnameNodes = domUtil.getAllChildNodes(studentNode, "firstname");
            String firstname = domUtil.getTextContent(firstnameNodes.iterator().next());
            //get last name - must have only 1
            Collection<Node> lastnameNodes = domUtil.getAllChildNodes(studentNode, "lastname");
            String lastname = domUtil.getTextContent(lastnameNodes.iterator().next());
                        
            //get birthday - must have only 1
            Collection<Node> birthdayNodes = domUtil.getAllChildNodes(studentNode, "birthday");
            Node birthdayNode = birthdayNodes.iterator().next();
            String birthday = domUtil.getAttributeString(birthdayNode, "day") + "/" 
                    + domUtil.getAttributeString(birthdayNode, "month") + "/" 
                    + domUtil.getAttributeString(birthdayNode, "year");
            
            //create a new student from the information
            StudentInfo student = new StudentInfo(studentID, firstname, lastname, birthday, gender);
            
            //get papers - can be empty
            Collection<Node> paperNodes = domUtil.getAllChildNodes(studentNode, "paper");
            for(Node paperNode : paperNodes){
                String paperCode = domUtil.getTextContent(paperNode);
                //add the paper to the student info
                student.addPaper(paperCode);
            }
            
            //get picture URL - check if exists
            Collection<Node> pictureURLNodes = domUtil.getAllChildNodes(studentNode, "pictureURL");
            if(!pictureURLNodes.isEmpty()){
                String pictureURL = domUtil.getTextContent(pictureURLNodes.iterator().next());
                //add the picture URL
                student.setPictureFromFileName(pictureURL);
            }
            
            //add this student to the student map
            this.addStudent(student);
        }
    }
    
    /**
     * Add a new student to the map
     * 
     * @param student 
     */
    public void addStudent(StudentInfo student){
        this.studentMap.put(student.getStudentID(), student);
    }
    
    /**
     * Remove a student from the map using the specified student ID
     * 
     * @param studentID 
     */
    public void removeStudent(String studentID){
        this.studentMap.remove(studentID);
    }
    
    /**
     * Returns a student info using the specified student ID from the map
     * @param studentID
     * @return 
     */
    public StudentInfo getStudent(String studentID){
        return this.studentMap.get(studentID);
    }
    
    /**
     * Empties the student map
     * 
     */
    public void clear(){
        this.studentMap.clear();
    }
    
    /**
     * Returns an iterator to loop through the collection of student info objects
     * @return 
     */
    public Iterator<StudentInfo> iterator(){
        return this.studentMap.values().iterator();
    }
    
    /**
     * Returns the number of elements in the student map
     * 
     * @return 
     */
    public int size(){
        return this.studentMap.size();
    }
    
    /**
     * Returns a string representing the description of the student info set
     * 
     * @return 
     */
    public String getDescription(){
        return this.description;
    }
    
    /**
     * Sets the description of the student info set
     * 
     * @param description 
     */
    public void setDescription(String description){
        this.description = description;
    }
    
    /**
     * Checks if a certain key exists in the map
     * 
     * @param key a string specified by the user
     * @return a boolean value true if the key exists and false if otherwise
     */
    public boolean containKeys(String key){
        return this.studentMap.containsKey(key);
    }
    
    /**
     * Gets the student ID as a set of string
     * 
     * @return a set of string holding the student IDs
     */
    public Set<String> getStudentKeys(){
        return this.studentMap.keySet();
    }
    
    /**
     * Returns a string representing all students info stored in the map
     * 
     * @return 
     */
    public String toString(){
        String output = "";
        for(StudentInfo student : studentMap.values()){
            output += student;
        }
        return output;
    }
}
