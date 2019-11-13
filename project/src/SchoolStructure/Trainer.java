
package SchoolStructure;

import java.util.Objects;

/**
 *
 * @author giagkas
 */
public class Trainer extends Name{
    
    private String subject;

    public Trainer() {
        this("NoSubject", "NoFirstName", "NoLastName");
    }

    public Trainer(String subject, String firstName, String lastName) {
        super(firstName, lastName);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Trainer name: " + getFirstName() + ", surname:" + getLastName() + ", subject: " + subject;
    }

    public String printTrainer() {
        return (this.getFirstName() + "\t"
                + this.getLastName() + "\t"
                + this.subject);
    }

    public static Trainer randomTrainerGenerator() {
        String randomSubject;
        String randomSubjectString = "Data Structures/Computer Networks/Web Technology/Algorithms/Programming Languages/Database Systems/E-Commerce/Electronics";
        String randomSubjectArray[] = randomSubjectString.split("/");
        int rangeSubject = randomSubjectArray.length;
        randomSubject = randomSubjectArray[(int) (Math.random() * rangeSubject)];
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

        return new Trainer(randomSubject, randomFirstName, randomLastName);
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
        final Trainer other = (Trainer) obj;
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        return true;
    }

}
