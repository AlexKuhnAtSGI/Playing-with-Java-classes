//COMP 1006A/1406A Assignment 2 Problem 2
//By Alexander Kuhn, ID# 101023154, July 18, 2016
//Purpose: This class allows you to create Person objects with names (first, middle, and last), ages (in years), and heights (in cm)
//It has additional methods that convert the Person's height to feet and inches and that put all its attributes in an appropriate order and print them to the console
public class Person{
  private String first;
  private String middle = "";
  private String surname;
  private int age;
  private int height;
  
  //The only variable that needs a default value is middle, because there's a constructor that doesn't give middle a value
  //If middle wasn't initialized, you'd get null pointer exceptions
  
  //I reordered the code so that it's in the order presented in the rubric
  //I thought it made more sense for the constructors to be at the very beginning anyway
  //The getters/setters are now ordered that way, instead of being ordered with name-related methods first then height methods then age methods
  
  public Person(String first, String middle, String surname, int age, int height){
    // constructor that initializes the person's information
    // first - first name
    // middle - middle name
    // surname - surname
    // age - age in years
    // height - height in cm
    this.first = first;
    this.middle = middle;
    this.surname = surname;
    this.age = age;
    this.height = height;
    
  }

  public Person(String first, String surname, int age, int height){
    // constructor that initializes the person's information
    // (the person has no middle name)
    // first - first name
    // surname - surname
    // age - age in years
    // height - height in cm
    this.first = first;
    this.surname = surname;
    this.age = age;
    this.height = height;

  }

  public Person(){
    // creates the (default) person such that their
    // first name is "Cat", middle name is "Eel"
    // surname is "Dog", age is 99 years and
    // height is 17 cm
    first = "Cat";
    middle = "Eel";
    surname = "Dog";
    age = 99;
    height = 17;
  }
  
  
  
  public String getName(){
    // purpose: returns the name of the person
    // preconditions: none
    // postconditions: the person's name is returned as a String
    //                 in the following format:
    //               - first (capitalized) name followed by a space, 
    //                 followed by middle initial (capitalized),
    //                 followed by a dot ".",
    //                 followed a space followed by surname (capitalized).
    //               - if the middle name was empty ("") then 
    //                 first name followed by a space followed by surname
    //                 (both capitalized)
    //
    // examples: if the name was (first middle last)
    //  - Cat Eel Dog then it would return "Cat E. Dog"
    //  - Cat Dog (no middle name) then it would return "Cat Dog"
    
    String retrievedName = "";
    retrievedName += this.first.toUpperCase().charAt(0) + this.first.toLowerCase().substring(1);
    
    if (this.middle.intern() == "") {
      retrievedName += " ";
    }
    else {
      retrievedName += " " + this.middle.toUpperCase().charAt(0) + ". ";
    }
    //This section makes sure that there's always a space between first and surname, and adds the capitalized middle initial should it exist
    
    retrievedName += this.surname.toUpperCase().charAt(0) + this.surname.toLowerCase().substring(1);
    //For proper formatting, I have to tell retrievedName to capitalize the first letter in both first and surname, then add it to itself
    //After that, if the name is more than one character, it decapitalizes (probably not a real word) the rest of the string starting with the second letter, and adds that on
    
    return retrievedName;
  }
  
  public int  getHeight(){
   // returns the person's height (in centimetres)
   return this.height;
  }
  
  public int[]  getHeightFeetAndInches(){
    // purpose: returns the person's height in feet and inches
    // preconditions: none
    // postconditions: returns an array of length 2
    //                 - first element is the number feet
    //                 - second element is the number of inches.
    // Note: the conversion should be the best possible under the
    //      constraint that feet >= 0, and 0 <= inches < 12
    //
    // Note: 1 foot = 12 inches
    //       1 inch = 2.54 cm
    // 
    // examples: if the person's height is 126cm then their height 
    //            is 4 feet and 1.6063 inches.  Thus, the returned 
    //            array will be [4,2] (for 4 feet and 2 inches)
    //           if the person's height is 30cm then their height
    //            is 0 feet and 11.81102 inches. Thus, the returned 
    //            array will be [1,0] (for 1 foot)
    int footHeight = 0;
    int inchHeight = 0;
    double totalInches = this.height / 2.54;
    
    while (totalInches >= 12) {
      totalInches -= 12;
      footHeight += 1;
    }
    //This loop converts inches to feet
    
    totalInches = Math.round(totalInches);
    inchHeight = (int)totalInches;
    if (inchHeight >= 12) {
      inchHeight -= 12;
      footHeight += 1;
    }
    //This makes sure that if I round up 11.x inches to 12, I add an extra foot
    //Without this, I'd end up with things like 4 feet and 12 inches instead of 5 feet
    
    int footAndInchHeight[] = {footHeight, inchHeight};
    
    return footAndInchHeight;
  }
  
  public int    getAge(){
    // returns the person's age in years
    return this.age;
  }
  
  
  
  public void   setFirstName(String first){
    // sets the person's first name to the value of 'first'
    this.first = first;
  }

  public void   setMiddleName(String middle){
    // sets the person's middle name to the value of 'middle'
    this.middle = middle;
  }
  
  public void   setLastName(String last){
    // sets the person's surname to the value of 'last'
    this.surname = last;
  }
  
  public void   setHeight(int cm){
    // sets the current persons height
    // input is given in cm (centimetres)
    this.height = cm;
  }
  
  public void   setAge(int years){
    // sets the persons age to the given input (in years)
    this.age = years;
  }
  
  
  
  @Override
  public String toString(){
    // overrides Object's toString() method
    // returns a String in the following format: 
    //  "SURNAME, Firstname Middlename: age years old, height metres tall."
    // of if the person has no middle name
    //  "SURNAME, Firstname: age years old, height metres tall."
    // where
    // - SURNAME is in all uppercase letters
    // - Firstname and Middlename are capitalized
    // - age is in years
    // - height is in metres with exactly 2 decimal places  
    //   
    // example: the output might look like
    //      "DOG, Cat Eel: 12 years old, 0.23 metres tall."
    String personAsString = "";
    double convertedHeight = this.height;
    
    personAsString += this.surname.toUpperCase() + ", ";
    personAsString += this.first.toUpperCase().charAt(0) + this.first.toLowerCase().substring(1) + " ";
    if (this.middle.intern() != "") {
      personAsString += this.middle.toUpperCase().charAt(0) + this.middle.toLowerCase().substring(1);
    }
    personAsString += ": " + this.age + " years old, ";
    personAsString += (convertedHeight / 100) + " metres tall.";
     
    return personAsString;
  }
  
  //public static void main (String[] args){
    //Person p = new Person("ChaRLES", "erNEST", "Gooda", 21, 234);
    //System.out.println(p.toString());
  //}
  //For testing purposes only
  
}