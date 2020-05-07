import javax.naming.Name;
import java.io.Serializable;
import java.util.Scanner;
import java.util.Random;
public class Email implements Serializable {
    private static final long serialVersionUID = 1L;
    private String email;
    private String firstName;
    private String lastName;
    private String department;
    private String password;
    private String alernateEmail;
    private String domain;
    private int userNumber;
    private int defaultPasswordLength = 15;
    private int mailboxCapacity = 500;
    //constructers & public Methods
    public Email(String fName, String lName, int userNumber, String domainValue){
        this.firstName = fName;
        this.lastName = lName;
        this.userNumber = userNumber;
        this.domain = domainValue;
        this.department = setDepartment();
        this.password = setPassword(defaultPasswordLength);
        email = this.firstName.toLowerCase() +"." + lastName.toLowerCase() + "@"+department + "." +domain;
        System.out.println(email);
    }
    public String getFirstName(){
        return(this.firstName);
    }
    public String getLastName(){
        return(this.lastName);
    }
    public int getUserNumber(){
        return(this.userNumber);
    }
    public String getEmail(){
        return(this.email);
    }
    public void displayUser(){
        System.out.println("Name: "+ this.firstName + " " + this.lastName );
        System.out.println("UserNumber: "+ this.userNumber);
        System.out.println("Email: " + this.email);
        System.out.println("Department: " + this.department);
    }

    @Override
    public String toString() {
        return ("Email{ id=" + this.userNumber + ", lastName=" + this.lastName + ", firstName=" + this.firstName
                + ", Email=" + this.email + ", Department=" + this.department + ", mailboxcapacity=" + this.mailboxCapacity + ", password=" + this.password);
    }

    //private methods
    private void changeEmail(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the new email for: " + this.firstName + " " + this.lastName);
        System.out.println("The current email is: " + this.email);
        String newEmail = input.nextLine();
        email = newEmail;
    }
    private String setDepartment(){
        Scanner input = new Scanner(System.in);
        boolean inputFlag = false;
        while(inputFlag == false) {
            System.out.println("Please Enter your Department: ");
            System.out.println("1 For Sales \n2 for Development\n3 for Accounting\n0 for None.");
            int depChoice = input.nextInt();
            if(depChoice > 3 || depChoice < 0){
                System.out.println("Please enter a vaild choice");
                continue;
            }
            else if(depChoice == 1){
                return("Sales");
            }
            else if(depChoice == 2){
                return("Development");
            }
            else if(depChoice == 3){
                return("Accounting");
            }
            else{
                return("");
            }

        }
        return(input.nextLine());
    }
    private String setPassword(int length){
        String passwordSet = "ABCDEFGHIJKLMNOPURSTUVWXYZ1234567890!@#$%^&*()";
        StringBuilder pw = new StringBuilder();
        for(int i = 0; i < length; i++){
            Random rand = new Random();
            int index = (int) rand.nextInt(passwordSet.length());
            char pwIndex = passwordSet.charAt(index);
            if(Character.isLetter(pwIndex)){
                if(index%2 == 0){
                    pwIndex = Character.toLowerCase(pwIndex);
                }
            }
            pw.append(pwIndex);
        }
        return(pw.toString());
    }
    private void changePassword(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a new Password for: " + this.firstName + " " + this.lastName);
        String pw = input.nextLine();
        this.password = pw;
    }
    private void changeMailBoxCapacity(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the MailBox Capacity in MB for: " + this.firstName + " " + this.lastName);
        System.out.println("The current capacity is: " + this.mailboxCapacity);
        boolean inputFlag = false;
        while(!inputFlag) {
            try {
                int capacity = Integer.parseInt(input.nextLine());
                this.mailboxCapacity = capacity;
            } catch (NumberFormatException e){
                System.out.println("Input is not Valid");
                continue;
            }
        }
    }
    private void changeAlternateEmail(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the Alternate Email for: " + this.firstName + " " + this.lastName);
        System.out.println("The current Alternate Email is: " + this.alernateEmail);
        this.alernateEmail = input.nextLine();
    }
     public void editUser(){
        Scanner input = new Scanner(System.in);
        int userInput = Integer.MAX_VALUE;
        while (userInput != 0) {
            System.out.println("User Edit Menu: ");
            System.out.println("1: Edit Email");
            System.out.println("2: Edit User Department");
            System.out.println("3: Edit User Password");
            System.out.println("4: Generate a new Password");
            System.out.println("5: Change MailBox Capacity");
            System.out.println("6: Change Alternate Email");
            System.out.println("0: Return to Main Menu");

            String userInputTemp = input.nextLine();
            if(Character.isDigit((userInputTemp.charAt(0)))) {
                userInput = Character.getNumericValue(userInputTemp.charAt(0));
            }
            else{
                continue;
            }
            if (userInput < 0 || userInput > 7) {
                System.out.println("please enter a vaild choice");
                continue;
            }
            switch (userInput) {
                case 1 :
                    changeEmail();
                    break;
                case 2:
                    String dep = setDepartment();
                    this.email = email = this.firstName.toLowerCase() +"." + lastName.toLowerCase() + "@"+department + "." +domain;
                break;
                case 3:
                    setPassword(defaultPasswordLength);
                    break;
                case 4:
                    changePassword();
                    break;
                case 5:
                    changeMailBoxCapacity();
                    break;
                case 6:
                    changeAlternateEmail();
                case 0:
                    return;
            }
        }
    }
}
