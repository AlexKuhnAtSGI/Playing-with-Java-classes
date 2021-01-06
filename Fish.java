//COMP 1006A/1406A Assignment 2 Problem 3
//By Alexander Kuhn, ID# 101023154, July 18, 2016
//Purpose: This class allows you to create Fish objects for a game: the fish have unique IDs, sizes, health points, positions in a bowl, and horizontal/vertical speeds
//It has additional methods that allow Fish to mate, to eat other fish, and to swim around, as well as for users to check the status of a Fish 
//and whether or not it is close enough to another Fish to interact with it
public class Fish{
  /* constants for fish bowl and fish parameters (DO NOT CHANGE THESE) */
  /* -----------------------------------------------------------------
  /* a fish must have position that is within the fish bowl         
   * so 0 <= x <= WIDTH
 *    0 <= y <= HEIGHT
 * the speed of a fish must always satisfy 
 *    -MAX_SPEED <= dx,dy <= MAX_SPEED
   * the size of a fish must always satisfy 
 *    1 <= size <= MAX_SIZE
 * the health of a fish must be non-negative 
   ------------------------------------------------------------------- */
  public static final int WIDTH = 600;
  public static final int HEIGHT = 400;
  public static final int MAX_SPEED = 5;
  public static final int MAX_SIZE = 30;
  
  
  /* attributes of a fish object */
  private int id;     // unique id for each fish
  private int size;   // size of fish (1 <= size <= MAX_SIZE)
  private int health; // health of fish (0 means the fish is not alive)
  private static int idTracker = 0; //idTracker is a simple counter of the number of new fish - it increments every time a constructor is called, ensuring that each new fish gets a new ID
                                    //I could use id for this if I assigned id a value and made it static, but I'm not sure that's allowed
  
  private double x, y;      // x,y coordinates of the fish
  private double dx, dy;    // x,y speed values for the fish
  
  
  /* constructors */
  public Fish(int size, int health){
    idTracker += 1;
    this.id = idTracker;
    
    if ((size > 0) && (size <= 30)) {
      this.size = size;
    }
    else if (size > 30) {
      this.size = 30;
    }
    else {
      this.size = 1;
    }
    
    if (health > 0) {
      this.health = health;
    }
    else {
      this.health = 1;
    }
    
    this.x = Math.random() * WIDTH;
    this.y = Math.random() * HEIGHT;
    
    if (Math.random() >= 0.5) {
      this.dx = Math.random() * MAX_SPEED;
    }
    else {
      this.dx = Math.random() * ((-1) * MAX_SPEED);
    }
    
    if (Math.random() >= 0.5) {
      this.dy = Math.random() * MAX_SPEED;
    }
    else {
      this.dy = Math.random() * ((-1) * MAX_SPEED);
    }
    
    /* creates a fish with specified size > 0 and health > 0   */
    /* with a unique id and random position and speed          */
  }
  
  public Fish(int size, int health, double x, double y){
    idTracker += 1;
    this.id = idTracker;
    
    if ((size > 0) && (size <= MAX_SIZE)) {
      this.size = size;
    }
    else if (size > MAX_SIZE) {
      this.size = MAX_SIZE;
    }
    else {
      this.size = 1;
    }
    
    if (health > 0) {
      this.health = health;
    }
    else {
      this.health = 1;
    }
    
    if (x > WIDTH) {
      this.x = WIDTH;
    } 
    else if (x < 0) {
      this.x = 0;
    }
    else {
      this.x = x;
    }
    
    if (y > HEIGHT) {
      this.y = HEIGHT;
    }
    else if (y < 0) {
      this.y = 0;
    }
    else {
      this.y = y;
    }
    
    if (Math.random() >= 0.5) {
      this.dx = Math.random() * MAX_SPEED;
    }
    else {
      this.dx = Math.random() * ((-1) * MAX_SPEED);
    }
    
    if (Math.random() >= 0.5) {
      this.dy = Math.random() * MAX_SPEED;
    }
    else {
      this.dy = Math.random() * ((-1) * MAX_SPEED);
    }
 
    /* creates a fish with specified size >0, health > 0 and position  */
    /* with a unique id and random speed                               */
  }
  
  public Fish(int size, int health, 
              double x, double y, 
              double dx, double dy){
    idTracker += 1;
    this.id = idTracker;
    
    if ((size > 0) && (size <= MAX_SIZE)) {
      this.size = size;
    }
    else if (size > MAX_SIZE) {
      this.size = MAX_SIZE;
    }
    else {
      this.size = 1;
    }
    
    if (health > 0) {
      this.health = health;
    }
    else {
      this.health = 1;
    }
    
    if (x > WIDTH) {
      this.x = WIDTH;
    } 
    else if (x < 0) {
      this.x = 0;
    }
    else {
      this.x = x;
    }
    
    if (y > HEIGHT) {
      this.y = HEIGHT;
    }
    else if (y < 0) {
      this.y = 0;
    }
    else {
      this.y = y;
    }
    
    if (dx > MAX_SPEED) {
      this.dx = MAX_SPEED;
    }
    else if (dx < ((-1) * MAX_SPEED)) {
      this.dx = ((-1) * MAX_SPEED);
    } 
    else {
      this.dx = dx;
    }
    
    if (dy > MAX_SPEED) {
      this.dy = MAX_SPEED;
    }
    else if (dy < ((-1) * MAX_SPEED)) {
      this.dy = ((-1) * MAX_SPEED);
    } 
    else {
      this.dy = dy;
    }

  /* creates a fish with specified size > 0, health > 0, position and  */
    /* speed with a unique id                                           */
    }
  
  
  /* provided getters */
  public int getID(){ return id; }
  public int getSize(){ return size; }
  public int getHealth(){ return health; }
  public double getX(){ return x; }
  public double getY(){ return y; }
  public double getDX(){ return dx; }
  public double getDY(){ return dy; }
  
  /* these setters are for testing only!!      */
  /* only use them to test your other methods  */
  public void setSize(int s){ this.size = s; }
  public void setHealth(int h){ this.health = h; }
  public void setX(double x){ this.x = x; }
  public void setY(double y){ this.y = y; }
  public void setDX(double dx){ this.dx = dx; }
  public void setDY(double dy){ this.dy = dy; }
  
  
  /* provided helper methods */
  
  public String toString(){
    /* returns a string representation of a fish object           */
    /* format method of String class allows us to format numbers  */
    /* with specified number of digits, or decimal places         */
    String out = "Fish "; 
    out += String.format("%03d", this.getID()) + " : ";
    out += String.format("%02d", this.getHealth()) + " health : ";
    out += String.format("%02d", this.getSize()) + " size : ";
    out += "at (" + this.getX() + "," + this.getY() + ")";
    return out;
  }
  
  public boolean hasMated(Fish mater){
    /* since all attributes are private, the mate method
     cannot change the health of both fish. This
     helper method, called from mate, allows change the health 
     of the "this" fish from mate method.   
     */
    if( this.closeEnough(mater) && mater.getSize() == getSize() & this.getHealth() > 0){
      health -= 1;
      return true;
    }
    return false;
  }
  
  
  public boolean beenEaten(Fish eater){
    /* since all attributes are private, the eat method
     cannot change the health of the eaten fish. This
     helper method, called from eat, allows us to do this.  
     */
    if( this.closeEnough(eater) && eater.getSize() > getSize() ){
      health = 0;
      return true;
    }
    return false;
  }
  




  
  
  /* ------------------------------------------------------------- */
  /* methods                                                       */
  /* ------------------------------------------------------------- */
  
  public boolean eat(Fish other){
    // If this fish is bigger than the other fish and
    // this fish is close enough to the other fish, it 
    // eats it.
    // When a fish eats another fish, it takes all of the 
    // health of the other fish for itself and it also 
    // increases in size by one third of the size of the eaten fish.
    // Use integer division for all computations.
    // Example: if this fish has 11 health and size 6 and the fish 
    //          it eats has health 7 and size 5, then this fish 
    //          increases its health by 7 and increases its 
    //          size by 5/3 = 1.
    
  /* ------------------------------------------------------------
     purpose: as above
   preconditions: other is not null
   postconditions: returns true if this fish eats the other fish,
                   based on the conditions above
                   returns false otherwise 
     side effects: when the method returns true, the health of 
                 the other fish is reduced to zero and the health 
       of this fish is increased by previous health of 
       the other. Also, the size of this fish is increased
       by 1/3 (integer division rounded) the size of 
       the other fish.
      ------------------------------------------------------------ */
    if ((this.getSize() > other.getSize()) && this.closeEnough(other) == true) {
      this.health += other.getHealth();
      this.size += (other.getSize() / 3);
      if (this.getSize() > MAX_SIZE) {
        this.size = (MAX_SIZE);
      }
      other.beenEaten(this);
      
      return true;
    }
  
    return false;
    
  }
  
  public Fish mate(Fish other){
    /* If the two fish are close enough and have the same size
     *  (and each have health at least 1) then the fish mate
     *  and create a new baby fish.
     *  The baby fish has
     *     -size that is the average of size of parents
     *     -health that is the average of size of parents
     *     -position that is the average of size of parents
     *     -speed that is random
    *  Use integer division for integers.
     *  The health of both parents are reduced by 1 when they mate.
     *  Use the hasMated helper method to change the health of the
     *  "other" parent
     * 
     * Otherwise, the method returns null.
    * ------------------------------------------------------------
     purpose: as above
     preconditions: other is not null
     postcondition: if the conditions above are met returns 
                   a new fish object as described above
              otherwise, returns null
    side effects:  if a new fish is created, the health of this
                   and other are reduced by 1 when method ends
     -------------------------------------------------------------
     */
    int averagedSizeAsInteger = (this.getSize() + other.getSize()) / 2;
    double averagedSizeAsDouble = (this.getSize() + other.getSize()) / 2;
    //Only the size and health have to be rounded; might as well keep the x and y positions a double

    if ((this.closeEnough(other) == true && this.getSize() == other.getSize()) && ((this.getHealth() > 0) && (other.getHealth() > 0))) {
      Fish babyFish = new Fish(averagedSizeAsInteger, averagedSizeAsInteger, averagedSizeAsDouble, averagedSizeAsDouble);
      this.hasMated(other);
      other.hasMated(this);
      
      return babyFish;
        
    }
    
    return null;
  }
  
  
  public void swim(){
    /*
     Update the position of the fish using its speed.
   
     The fishes position is update by adding the speed
   to the position (for each coordinate)
    x += dx
      y += dy
     
   If the fish does not hit or go through a wall 
   (of the fish bowl) in this update then do this update
   and end the method.
   
   If the new value of x or y is outside of the walls
   (less then zero or greater then the width/height)
   then do not make this simple update. Instead, 
   you the fish must bounce off of the wall it hits.
     
   If the fish hits a vertical wall (left or right 
     side of fish bowl) then its x speed is negated and
   the x coordinate is not changed.
   
     If the fish hits a horizontal wall (top or bottom)
     then its y speed is negated and the y position is not
   changed.
     
   If the fish only hits one wall then the other coordinate
   will update in the normal way (from above). 
    
   Note that this will result in a slightly odd behaviour 
   when a fish bounces off of a wall. If you want to make the
   behaviour more smooth you are free to do so.
    
     The fish must always stay within the fish bowl. 
     Its position must always satisfy 
     0 <= x <= WIDTH and 0 <= y <= HEIGHT
   
     -------------------------------------------------------------
   purpose: as above
   preconditions: fish is alive (health is > 0)
   postconditions: outputs nothing
   side effects: as above
   --------------------------------------------------------------
     */
    if ((this.getX() + this.getDX() > WIDTH) || (this.getX() + this.getDX() < 0) ) {
      this.dx = this.getDX() * -1;
    }
    else {
      this.x = (this.getX() + this.getDX());
    }
    
    if ((this.getY() + this.getDY() > HEIGHT) || (this.getY() + this.getDY() < 0) ) {
      this.dy = (this.getDY() * -1);
    }
    else {
      this.y = (this.getY() + this.getDY());
    }
    
  }
  
  public boolean closeEnough(Fish other){
    /* returns true if the two fish are "close enough",      */
    /* where close enough is defined in the assignment specs */
    int sumOfSizes = (this.getSize() + other.getSize());
    double relativeDistance = Math.sqrt(( ( (this.getX() - other.getX()) * (this.getX() - other.getX() ) ) + ( (this.getY() - other.getY()) * (this.getY() - other.getY() ) ) ));
    
    if (relativeDistance <= sumOfSizes) {
      return true;
    }
    
    return false;
  }
  
  
  
  /* simple tests */
  public static void main(String[] args){
    Fish red = new Fish(5, 10, 30,40, 2, -2);
    Fish blue = new Fish(5, 10, 31,39, -3, 0.1);
    Fish b2 = new Fish(16, 3, 300,100, -3, 0.1);
    
    System.out.println( red.closeEnough(blue) );
    System.out.println( b2.closeEnough(blue) );
    System.out.println(red);
    System.out.println(blue);
    
    red.eat(blue);
    System.out.println(red);
    System.out.println(blue);
    
    Fish b3 = red.mate(blue);
    Fish b4 = red.mate(b2);
    blue.eat(red);
    System.out.println(red);
    System.out.println(blue);
    System.out.println(b3);
    System.out.println(b4);
    
  }
}