
package SchoolStructure;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author giagkas
 */
public class Student extends Name{
    
    private LocalDate dateOfBirth;
    private double tuitionFees;

    public Student(LocalDate dateOfBirth, double tuitionFees, String firstName, String lastName) {
        super(firstName, lastName);
        this.dateOfBirth = dateOfBirth;
        this.tuitionFees = tuitionFees;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getTuitionFees() {
        return tuitionFees;
    }

    public void setTuitionFees(double tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    @Override
    public String toString() {
        return "Student name: " + getFirstName() + ", Student surname: " + getLastName() + ", dateOfBirth: " + dateOfBirth + ", tuitionFees: " + tuitionFees;
    }

    public String printStudent() {
        return (this.getFirstName() + "\t"
                + this.getLastName() + "\t"
                + this.dateOfBirth + "\t"
                + this.tuitionFees);
    }

    public static Student randomStudentGenerator() {

        //random date
        int randomYear = 1970 + ((int) (Math.random() * 30));
        int randomMonth = 1 + (int) (Math.random() * 11);
        int randomDay = 1 + (int) (Math.random() * 28);//caution to leap years!!

        LocalDate randomDateOfBirth = LocalDate.of(randomYear, randomMonth, randomDay);

        //Random fees. not a vary trustworthy method to round to 2 decimal points.
        double randomFees = 2000.0 + Math.random() * 500.0;
        double scale = Math.pow(10, 2);//2 decimal points
        randomFees = Math.round(randomFees * scale) / scale;

        //random name generation from a list of names
        String randomFirstName;
        String randomLastName;
        //lists of names taken from http://listofrandomnames.com
        String firstNameString = "Darlene Argelia Cira Jordan Daphne Mikaela Donya Ara Jean Keturah Kala Roosevelt Brittani Fransisca Rena Theo Janita Carlotta Rubie Hailey Queenie Angeles Ana Lea Aaron Chloe Angelyn Karl Myrna Johanna";
        String lastNameString = "Sherrill Arthur Dee Leland Arnulfo Trula Jame Shery Gilberto Leigh Tyisha Lia Luana Odell Harriet Lupe Sommer Natalia Basilia Nila Larry Ione Victorina Cindie Sharell Ray Christi Joaquina Sean Petra";

        String firstNameArray[] = firstNameString.split(" ");
        String lastNameArray[] = lastNameString.split(" ");
        int rangeFirst = firstNameArray.length;
        int rangeLast = lastNameArray.length;

        randomFirstName = firstNameArray[(int) (Math.random() * rangeFirst)];
        randomLastName = lastNameArray[(int) (Math.random() * rangeLast)];

        return new Student(randomDateOfBirth, randomFees, randomFirstName, randomLastName);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) == false) {
            return false;
        }

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        /*if (Double.doubleToLongBits(this.tuitionFees) != Double.doubleToLongBits(other.tuitionFees)) {
            return false;
        }*/
        if (!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
            return false;
        }
        return true;
    }

}
