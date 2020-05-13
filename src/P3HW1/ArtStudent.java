package p3hw1;

public class ArtStudent extends Student implements st{
    public int mid;
    public int report;
    public int fin;

    public ArtStudent(int mid, int report, int fin) {
        this.mid = mid;
        this.report = report;
        this.fin = fin;
        grade = (mid*0.40)+(report*0.10)+(fin*0.50);
    }
}
