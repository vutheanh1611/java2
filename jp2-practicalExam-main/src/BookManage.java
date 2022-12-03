import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BookManage {
    private ArrayList<Book> BookList = new ArrayList<Book>();

    Scanner sc = new Scanner(System.in);
    //input
    public String inputID() {
        System.out.print("Input book ID: ");
        String ID = sc.nextLine();
        return ID;
    }
    public String inputFirstName() {
        System.out.print("Input book first name: ");
        String firstname = sc.nextLine();
        return firstname;
    }
    public String inputLastName() {
        System.out.print("Input book last name: ");
        String lastname = sc.nextLine();
        return lastname;
    }
    public int inputAge() {
        System.out.print("Input book age: ");
        while (true) {
            try {
                int age = sc.nextInt();
                if (age < 0 && age > 150) {
                    throw new NumberFormatException("age is greater than 0 and must be less than 200");
                }
                return age;
            } catch (NumberFormatException ex) {
                System.out.print("Invalid! Input book ID again: ");
            }
        }
    }

    public void addBook(){
        String ID = inputID();
        String firstname = inputFirstName();
        String lastname = inputLastName();
        int age = inputAge();

        Book student = new Book(ID, firstname, lastname, age);
        BookList.add(student);
    }


    public void saveBook() throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream("book.txt");
            DataOutputStream dos = new DataOutputStream(fos);

            //read file
            FileInputStream fis = new FileInputStream("book.txt");
            DataInputStream dis = new DataInputStream(fis);
            String txt = dis.readLine();
            while (txt != null) {
                System.out.println(txt);
                txt = dis.readLine();
            }
        } catch (FileNotFoundException f) {
            System.out.println("File not found");
        } catch (IOException io) {
            System.out.println("Error...");
        }
    }

    //read files -> show student
    public void readFiles() throws IOException, ClassNotFoundException {
        File f = new File("students.txt");
        FileInputStream fin = new FileInputStream(f);
        ObjectInputStream objin = new ObjectInputStream(fin);

        BookList = new ArrayList<Book>();
        BookList = (ArrayList)objin.readObject();

        showBook();

        objin.close();
        fin.close();
    }

    public void showBook() {
        if (BookList.size()==0){
            System.out.println("List is empty");
        }
        else{
            System.out.println("|   EnrolID   |      Full name      |  Age  |");
            System.out.print("=============================================\n");
            for (Book st : BookList) {
                System.out.format("| %11s | %19s | %5d |\n",st.getEnrolID(),st.getFirstName() + " " + st.getLastName(),st.getAge());
            }
        }
    }

}
