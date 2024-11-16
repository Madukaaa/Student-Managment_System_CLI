class Student {
    private String id;
    private String name;
    private Module[] modules;

    //Constructor
    public Student(String id) {
        this.id = id;
        this.name = "";
        this.modules = new Module[3];
        modules[0] = new Module();
        modules[1] = new Module();
        modules[2] = new Module();
    }

    //Creating get id method
    public String getId() {
        return id;
    }

    //Creating the set name method
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //Creating the set module marks method
    public void setModuleMarks(int moduleIndex, int marks) {
        if (moduleIndex >= 0 && moduleIndex < 3 && marks >= 0 && marks <= 100) {
            modules[moduleIndex].setMarks(marks);
        }
    }

    //Creating the get module method
    public Module[] getModules() {
        return modules;
    }
}