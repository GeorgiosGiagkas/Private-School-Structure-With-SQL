
package SchoolStructure;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author giagkas
 */
public class Assignment {
    
    private String title;
    private String description;
    private LocalDateTime subDateTime;
    private int oralMark;
    private int totalMark;

    public Assignment(String title, String description, LocalDateTime subDateTime) {
        this(title, description, subDateTime, 0, 0);
    }

    public Assignment(String title, String description, LocalDateTime subDateTime, int oralMark, int totalMark) {
        this.title = title;
        this.description = description;
        this.subDateTime = subDateTime;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
    }

    //copy constructor
    public Assignment(Assignment assignment) {
        this(assignment.title, assignment.description, assignment.subDateTime);//LocalDayTime object is immutable
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getSubDateTime() {
        return subDateTime;
    }

    public void setSubDateTime(LocalDateTime subDateTime) {
        this.subDateTime = subDateTime;
    }

    public int getOralMark() {
        return oralMark;
    }

    public void setOralMark(int oralMark) {
        this.oralMark = oralMark;
    }

    public int getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }

    @Override
    public String toString() {
        return "Assignment title:" + title + ", description:" + description + ", subDateTime:" + subDateTime;
    }

    public String printAssignment() {
        return (this.title + "\t"
                + this.description + "\t"
                + this.subDateTime + "\t"
                + this.oralMark + "\t"
                + this.totalMark);
    }

    public static Assignment randomAssignmentGenerator() {

        //random date
        int randomYear = 2019;
        int randomMonth = 6 + (int) (Math.random() * 4);
        int randomDay = 1 + (int) (Math.random() * 28);//caution to leap years!!
        int randomHour = (int) (Math.random() * 24);
        LocalDateTime randomDate = LocalDateTime.of(randomYear, randomMonth, randomDay, randomHour, 0, 0);

        //random name generation from a list of names
        String randomTitle;
        String randomDescription;
        //lists of names taken from http://listofrandomnames.com
        String titleString = "Python:HTML / CSS:SQL:JavaScript:Bootstrap Framework:AngularJS / React:Version Control (Git):REST architecture:Web servers (Apache):xUnit:Unix command-line:UI / UX Design:Introduction to Cloud-based services:Object-oriented Programming:Computing Paradigms and Software Testing Techniques:Test-driven Development:Common Design Patterns:Relational Databases:Data / Class Modeling:Project Management Methods for Software Development:Introduction to DevOps";
        String descriptionString = "Individual:Group";

        String titleArray[] = titleString.split(":");
        String descriptionArray[] = descriptionString.split(":");
        int rangeTitle = titleArray.length;
        int rangeDescription = descriptionArray.length;

        randomTitle = titleArray[(int) (Math.random() * rangeTitle)];
        randomDescription = descriptionArray[(int) (Math.random() * rangeDescription)];

        return new Assignment(randomTitle, randomDescription, randomDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Assignment other = (Assignment) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.subDateTime, other.subDateTime)) {
            return false;
        }
        return true;
    }

}
