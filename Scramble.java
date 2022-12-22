/*
 * Zack O'Rourke
 * Project 2 - Word Unscrambler Game
 * Swaps two letters of users choice and counts the amount of times. Displays final score of user.
 * Professor DeGood
 * October 30th, 2022
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class Scramble{  

    //Randomly swaps 2 letters many times and returns scrambled word
    public static String swapLetter(String word){
        int randNum = (int)(Math.random()*(80)+50);
        int letter1; 
        int letter2;
        char temp;
        char character[];

        for(int i = 0; i<randNum; i++){
            //Chooses random letters
            letter1 = (int)(Math.random()*word.length());
            letter2 = (int)(Math.random()*word.length());

            //Swaps two random letters
            character = word.toCharArray();
            temp = character[letter1];
            character[letter1] = character[letter2];
            character[letter2] = temp;
            word = new String(character);
        }

        return word;
    }

    //Method for user to swap two letters
    public static String userSwap(String word, int index1, int index2){
        char temp;
        char character[];

        character = word.toCharArray();
        temp = character[index1];
        character[index1] = character[index2];
        character[index2] = temp;
        word = new String(character);

        return word;
    }
    
    public static void main(String[] args)throws IOException{
        Scanner scnr = new Scanner(System.in);
        // File input stream
        FileInputStream fileByteStream = null; 
        Scanner inFS = null;  
        //opens the file for reading and creates a new Scanner using fileByteStream.
        fileByteStream = new FileInputStream("word1.txt"); 

        inFS = new Scanner(fileByteStream);

        int total = inFS.nextInt();
        String[] array = new String[total];
        int r = (int)(Math.random()*(total-1));
        String randWord;
        boolean rpt = true;
        boolean repeat = true;
        int counter = 0;

        //Creates array of words in document
        for(int i = 0; i<total; i++){
            array[i]=inFS.next();
        }

        //randWord is assigned the scrambled word
        randWord = swapLetter(array[r]);

        //menu
        while(repeat){
            System.out.println("-----");

            for(int i = 0; i<randWord.length();i++){
                System.out.print(i);
            }

            System.out.println("\n" + randWord);
            System.out.println("-----");
            System.out.println("Enter i to interchange letters");
            System.out.println("Enter s to solve");
            System.out.println("Enter q to quit"); 

            //User input prompt
            int input = scnr.next().charAt(0);
            rpt = true;
            while(rpt){
                switch(input){
                    case 'i':
                        System.out.println("Enter two indexes: ");
                        int indOne = scnr.nextInt();
                        int indTwo = scnr.nextInt();

                        //Checks to see if indexes are in range
                        if(indOne > randWord.length()-1 || indTwo > randWord.length()-1 || indOne < 0 || indTwo <0){
                            System.out.println("Invalid Indexes.");
                        }

                        //Switches two letters and adds 1 to counter
                        else{
                            randWord = userSwap(randWord, indOne, indTwo);
                            rpt = false;
                            counter++;
                        }

                    break;

                    //Breaks and prints unscrambled word
                    case 's':
                        System.out.println(array[r]);
                        repeat = false;
                        rpt = false;
                    break;

                    //Quits
                    case 'q':
                        repeat = false;
                        rpt = false;
                    break;

                    default:
                    System.out.println("Please try again.");
                }
                
            }

            //Congratulatory Message
            if(randWord.equals(array[r])){
                System.out.println("Congratulations! You unscrambled the word project in " + counter + " steps.");
                repeat = false;
            }

        }

        //closes the file and input stream
        fileByteStream.close(); 
      
    }
}