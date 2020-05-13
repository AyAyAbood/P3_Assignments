package p3hw3part2;

public class Q3 {

    public static void main(String[] args) {
        System.out.println("1- first, the terminal operation \"for each\" initiates processing of a stream pipeline’s intermediate operations.\n"
                + "2- the intermediate operation \"filter\" filters the employees according to their salary, meaning those with salary equal or greater to 800 and lesser or equal to 1200 will be selected.\n"
                + "3- the intermediate operation \"map\" will transform the employees’ salaries value therefore increasing it by 2% (employee * 1.02), and returning the id, name and department value unchanged.\n"
                + "4- the terminal operation \"collect\" will create a new collection of elements depending on the prior stream, here, it will use the Collectors method's \"groupingBy\" to group employees by department, and the Counting method will count the number of input elements.\n"
                + "5- the terminal operation \"forEach\" will pass on every element, like an iterator, in this case it will print the name of each department and the number of its employees.");
    }

    /*
    i wrote it here again, just to be sure. 
    
    1- first, the terminal operation "for each" initiates processing of a stream pipeline’s intermediate operations.
    2- the intermediate operation "filter" filters the employees according to their salary, meaning those with salary equal or greater to 800 and lesser or equal to 1200 will be selected.
    3- the intermediate operation "map" will transform the employees’ salaries value therefore increasing it by 2% (employee * 1.02), and returning the id, name and department value unchanged.
    4- the terminal operation "collect" will create a new collection of elements depending on the prior stream, here, it will use the Collectors method's "groupingBy" to group employees by department, and the Counting method will count the number of input elements.
    5- the terminal operation "forEach" will pass on every element, like an iterator, in this case it will print the name of each department and the number of its employees.

     */
}
