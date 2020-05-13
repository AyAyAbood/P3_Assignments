package p3hw1;

public class ITStudent extends Student implements st{

    public int mid;
    public int project;
    public int fin;

    public ITStudent(int mid, int project, int fin) {
        this.mid = mid;
        this.project = project;
        this.fin = fin;
        grade = (mid*0.30)+(project*0.30)+(fin*0.40);
    }

}
