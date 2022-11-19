package gcu.calebwolin;
import java.sql.*;
import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;


/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args )
    {
        System.out.println("Welcome to pet world");

        // create instance of DAO
        PetStoreDataAccessObject dao = new PetStoreDataAccessObject();
        ArrayList<Pet> myPets = dao.getAllPets();

        int choice = -1;
        while(choice != 0)
        {
            System.out.println();
            try
            {
                System.out.println("choose an option: ");
                System.out.println("(1) Show all pets, \n(2) Show one pet, \n(3) Search for a pet, \n(4) Add a pet, \n(5) Update a pet, \n(6) Delete a pet, \n(0) Quite program");
            
                choice = Integer.parseInt(System.console().readLine());

                switch(choice)
                {
                    case 1:
                        System.out.println("========================= SHOW ALL PETS ===========================");
                        for(Pet pet : myPets)
                        {
                            System.out.println(pet);
                        }
                        break;
                    case 2:
                        System.out.println("========================= CHOOSE AN ID: ===========================");
                        int idOfPet = Integer.parseInt(System.console().readLine());
                        Pet foundPet = dao.getById(idOfPet);
                        System.out.println(foundPet);
                        break;
                    case 3:
                        System.out.println("========================= ENTER SEARCH TERM: ===========================");
                        String searchTerm = System.console().readLine();
                        ArrayList<Pet> found = dao.searchForMany(searchTerm);
                        System.out.println("These are the " + found.size() + " pets I found whose names match the search term: ");
                        for(Pet pet : found)
                        {
                            System.out.println(pet);
                        }
                        break;
                    case 4:
                        System.out.println("========================= Add a new pet ===========================");
                        System.out.println("Enter pet Name: ");
                        String name = System.console().readLine();
                        System.out.println("Enter pet Description: ");
                        String description = System.console().readLine();
                        System.out.println("Enter pet Price: ");
                        double price = Double.parseDouble(System.console().readLine());
                        int categoryId = myPets.size() + 1;

                        Pet p = new Pet();
                        p.setId(categoryId);
                        p.setName(name);
                        p.setDescription(description);
                        p.setPrice(price);
                        p.setCategoryId(1);
                    
                        int updates = dao.addOne(p);
            
                        System.out.println(updates + " items inserted");
                        System.out.println("========================= show all pets ===========================");
                        myPets = dao.getAllPets();
                        for(Pet pet : myPets)
                        {
                            System.out.println(pet);
                        }
                        break;
                    case 5:
                        System.out.println("========================= Choose a pet ID to update: ===========================");
                        int inputId = Integer.parseInt(System.console().readLine());
                        System.out.println("Enter updated name: ");
                        String petName = System.console().readLine();
                        System.out.println("Enter updated description: ");
                        String petDescription = System.console().readLine();
                        System.out.println("Enter updated price: ");
                        double petPrice = Double.parseDouble(System.console().readLine());

                        p = new Pet();
                        p.setId(inputId);
                        p.setName(petName);
                        p.setDescription(petDescription);
                        p.setPrice(petPrice);
                        p.setCategoryId(1);
            
                        updates = dao.updateOne(p);
            
                        System.out.println(updates + " updates applied");
                        System.out.println("=========================  show all pets ===========================");
                        myPets = dao.getAllPets();
                        for(Pet pet : myPets)
                        {
                            System.out.println(pet);
                        }
                        break;
                    case 6:
                        System.out.println("=========================  Enter an ID to delete pet: ===========================");
                        int deleteID = Integer.parseInt(System.console().readLine());

                        p = new Pet();
                        p.setId(deleteID);
                        updates = dao.deleteOne(p);
                        System.out.println(updates + " deletes applied");
            
                        System.out.println("========================= show all pets ===========================");
                        myPets = dao.getAllPets();
                        for(Pet pet : myPets)
                        {
                            System.out.println(pet);
                        }
                        break;
                    case 0:
                        System.out.println("Goodbye");
                        break;
                    default:
                        break;
                }
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        

        

        

        

        

        

    }
}
