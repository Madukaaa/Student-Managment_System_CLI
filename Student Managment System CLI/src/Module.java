public class Module {
    private int marks;

    //Constructor
    public Module() {
        this.marks = 0;
    }

    //Creating the set marks method
    public void setMarks(int marks) {
        this.marks = marks;
    }

    //Creating the get marks method
    public int getMarks() {
        return marks;
    }

    //Creating the calculate grade method
    public static String calculateGrade(double averageMarks) {
        if (averageMarks >= 80) {
            return "Distinction";
        }
        else if (averageMarks >= 70) {
            return "Merit";
        }
        else if (averageMarks >= 40) {
            return "Pass";
        }
        else {
            return "Fail";
        }
    }
}