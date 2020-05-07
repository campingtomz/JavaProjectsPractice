import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.util.Comparator;

public class EmailApp {
    static int userCount = 0;// user to generate usre ID
    static ArrayList<Email> emailList = new ArrayList<Email>();//stores email users while program is running
    static Email selectedUser; //user selected for editing
    static String domainValue = "microsoft.com";// default domain

    public static void main(String args[]) throws IOException {
     int userCount = 0;
     readFromFile();
     Scanner input = new Scanner(System.in);
     int userInput = Integer.MAX_VALUE;
     while (userInput != 0) {
         System.out.println("Email Menu: ");
         System.out.println("1: Create new Email");
         System.out.println("2: Display Email Users");
         System.out.println("3: Search for Email User");
         System.out.println("4: Edit a User");
         System.out.println("0: Quit Program");
         String userInputTemp = input.nextLine();
         if(Character.isDigit((userInputTemp.charAt(0)))) {
             userInput = Character.getNumericValue(userInputTemp.charAt(0));
         }
         else{
             continue;
         }
         if (userInput < 0 || userInput > 4) {
             System.out.println("please enter a vaild choice");
             continue;
         }
         switch (userInput) {
             case 1:
                 createNewEmail();
                 break;
             case 2:
                 displayUsers();
                 break;
             case 3:
                 searchForUser();
                 break;
             case 4:
                 editUser();
                 break;
             case 0:
                 writeToFile();
                 System.exit(0);
                 break;
         }
     }
 }

    static public void createNewEmail(){
         System.out.println("Please enter the First name of the new User");
         Scanner inputName = new Scanner(System.in);
         String fName = inputName.nextLine();
         System.out.println("Please enter the Last name of the new User");
         String lName = inputName.nextLine();
         userCount++;
         Email em = new Email(fName, lName, userCount, domainValue);
         em.displayUser();
         emailList.add(em);
         }

    static void readFromFile() throws IOException {
        try {
            FileInputStream is = new FileInputStream("data.txt");
            ObjectInputStream ois = new ObjectInputStream(is);
            emailList = (ArrayList<Email>)ois.readObject();
            ois.close();
            is.close();
            userCount = emailList.size();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    static void writeToFile() throws IOException {
        try {
            FileOutputStream output = new FileOutputStream("data.txt");
            ObjectOutputStream oos = new ObjectOutputStream(output);
            oos.writeObject(emailList);
            oos.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public void displayUsers(){
         //Displays the current email users
         int length = emailList.size();
         if(emailList.isEmpty()){
             //if there are no email users, alerts the opperator
             System.out.println("There are currently No Emails available");
         }//end if
         else{
             System.out.println("Current User Count: " + userCount+ "\n\n");
             for(int i = 0; i < length; i++) {
                emailList.get(i).displayUser();
                System.out.println("4--- \n");
             }//end for
         }//end else
    }//end displayUser

    static public void searchForUser(){
        if(emailList.isEmpty()){
            //if there are no email users, alerts the opperator
            System.out.println("There are currently No Emails available");
        }//end if
        else {
            //Print out Search options
            System.out.println("Select how to Search");
            System.out.println("1: Search by LastName");
            System.out.println("2: Search by Email Address");
            System.out.println("3: Search by User Id");
            System.out.println("0: To return to previous menu");
            Scanner userInput = new Scanner(System.in);
            int selection = userInput.nextInt();
            switch(selection) {
                case 1:
                    //call to serach by lastname method
                    searchByLastName();
                    break;
                case 2:
                    searchByEmailAddress();
                    break;
                case 3:
                    searchById();
                    break;
                case 0:
                    return;
                default:
                    break;
            }
        }//end else
    }
    //Search Methods
    static public void searchByLastName(){
        System.out.println("Please enter the Last Name of the User: ");
        Scanner userInput = new Scanner(System.in);
        String lName = userInput.nextLine();
        for (Email em : emailList) {
            if (em.getLastName().equalsIgnoreCase(lName)) {
                System.out.println("User account found!");
                em.displayUser();
                selectedUser = em;
            } else {
                System.out.println("There is no User with the Last Name: " + lName);
            }
        }
    }

    static public void searchByEmailAddress(){
        System.out.println("Please enter the  of Email Address the User: ");
        Scanner userInput = new Scanner(System.in);
        String email = userInput.nextLine();
        for (Email em : emailList) {
            if (em.getEmail().equalsIgnoreCase(email)) {
                System.out.println("User account found!");
                em.displayUser();
                selectedUser = em;
            } else {
                System.out.println("There is no User with the Last Name: " + email);
            }
        }
    }

    static public void searchById() {
        System.out.println("Please enter the id of the User: ");
        Scanner userInput = new Scanner(System.in);
        int id = userInput.nextInt();
        for (Email em : emailList) {
            if (em.getUserNumber() == (id)) {
                System.out.println("User account found!");
                em.displayUser();
                selectedUser = em;
            } else {
                System.out.println("There is no User with the ID number: " + id);
            }
        }
    }

    //edit user: allows opporator to select a user and edit ther attibutes
    static public void editUser(){
        selectedUser = null;
        while(selectedUser == null) {
            System.out.println("Please select a user");
            searchForUser();
        }
        System.out.println("The selected User is: ");
        selectedUser.displayUser();
        selectedUser.editUser();
    }
}

