//COMP 1006A/1406A Assignment 2 Problem 4
//By Alexander Kuhn, ID# 101023154, July 18, 2016
//Purpose: This class allows you to create CashRegister objects to tabulate the money in the register: these objects can store loonies, 5s, 10s, 20s, or 50s
//It has additional methods that handle purchases and returns using the preexisting Item and Money classes, as well as change converters that break bills or convert everything to loonies
public class CashRegister{
  /* these static attributes keep a count of how many    */
  /* times the updateMoney and allLoonies methods        */
  /* are called (do not use these!)                      */
  public static int updateMoneyCount = 0;
  public static int allLooniesCount  = 0;
    
 
  /* attributes                                */
  /* you need to define your own attributes.   */
  /* they should be private                    */
  private int d1;
  private int d5;
  private int d10;
  private int d20;
  private int d50;
  /* constructors */
  /* different ways of specifying the money amount */
  public CashRegister(){
    /* creates a register with zero money */
    this.d1 = 0;
    this.d5 = 0;
    this.d10 = 0;
    this.d20 = 0;
    this.d50 = 0;
  }
  
  public CashRegister(int[] money){
    for (int i = 0; i < 5; i += 1) {
      if (money[i] < 0) {
        money[i] = 0;
      }
    }
    //If someone keeps a negative value in money, this will convert it to just be 0, so the CashRegister it creates won't end up in debt
    //The for loop is set to run 5 times because the array it goes over will always have a length of 5
    //Similar safeguards exist throughout the other constructors
    this.d1 = money[0];
    this.d5 = money[1];
    this.d10 = money[2];
    this.d20 = money[3];
    this.d50 = money[4];
    /* creates a register with money specified in money array [loonies, 5s, 10s, 20s, 50s] */
  }
  
  public CashRegister(Money money){
    this.d1 = money.d1;
    if (this.d1 < 0){
      this.d1 = 0;
    }
    this.d5 = money.d5;
    if (this.d5 < 0){
      this.d5 = 0;
    }
    this.d10 = money.d10;
    if (this.d10 < 0){
      this.d10 = 0;
    }
    this.d20 = money.d20;
    if (this.d20 < 0){
      this.d20 = 0;
    }
    this.d50 = money.d50;
    if (this.d50 < 0){
      this.d50 = 0;
    }
    /* creates a register with the money specified in the money object  */  
  }
  
  public CashRegister(int n1, int n5, int n10, int n20, int n50){
   /* creates  aregster withspecified loonies n1, fives n5, tens n10 , twenties n20, fifties n50*/
    this.d1 = n1;
    if (this.d1 < 0){
      this.d1 = 0;
    }
    this.d5 = n5;
    if (this.d5 < 0){
      this.d5 = 0;
    }
    this.d10 = n10;
    if (this.d10 < 0){
      this.d10 = 0;
    }
    this.d20 = n20;
    if (this.d20 < 0){
      this.d20 = 0;
    }
    this.d50 = n50;
    if (this.d50 < 0){
      this.d50 = 0;
    }
  }
  
 
  /* ------------------------------------------------------------- 
   *   getters 
   * ------------------------------------------------------------- */
   
  /* returns number of loonies in the register */
  public int get1(){return this.d1;}
  
  /* returns number of five dollar bills in the register */
  public int get5(){return this.d5;}

  /* returns number of ten dollar bills in the register */
  public int get10(){return this.d10;}
 
  /* returns number of twenty dollar bills in the register */
 public int get20(){return this.d20;}
 
   /* returns number of fifty dollar bills in the register */
  public int get50(){return this.d50;}
  
    /* returns total value of all money in register      */
  public int getTotalValue(){
    int totalValue = 0;
    totalValue += this.d1;
    totalValue += 5 * this.d5;
    totalValue += 10 * this.d10;
    totalValue += 20 * this.d20;
    totalValue += 50 * this.d50;
    
    return totalValue;
  }
  
  /* returns all money in register as an array [loonies, 5s, 10s, 20s, 50s]   */
  public int[] getAll(){
    int[] moneyAsArray = new int[5];
    moneyAsArray[0] = this.d1;
    moneyAsArray[1] = this.d5;
    moneyAsArray[2] = this.d10;
    moneyAsArray[3] = this.d20;
    moneyAsArray[4] = this.d50;
    
    return moneyAsArray;
  }
  

  /* returns Money object that corresponds to all money  in the register */
  public Money getMoney(){ 
    Money moneyInRegister = new Money(this.d1, this.d5, this.d10, this.d20, this.d50);
    
    return moneyInRegister; 
  }
  


  
  /* ------------------------------------------------------------- 
   *   methods 
   * ------------------------------------------------------------- */
  
  
  protected Money purchaseItem(Item item, Money payment){
    /*-----------------------------------------------------------
     * process a purchase transaction
     * 
     * preconditions: -item and payment are both non-null
     * 
     * postconditions: -if the payment was not enough for the 
     *                  purchase returns null
     *                 -if payment was enough then returns a money
     *                  object with the change given (might be zero)
     * 
     * side effects: -if payment was enough, the money in the
     *                cash register is updated with the price
     *                of the transaction
     *               -if when making change, the cash register is 
     *                unable to make proper change, it will call
     *                the updateMoney() method to modify its money distribution.
    *                If it is still unable to make exact change, then 
    *                the allLoonies() method is called.
     *-------------------------------------------------------------------
     */
    CashRegister paymentInHand = new CashRegister(payment);
    int receivedPayment = paymentInHand.getTotalValue();
    int priceOfPurchase = item.getPrice();
    Money processedPayment = new Money();
    
    
    
    if (priceOfPurchase <= receivedPayment) {
      this.d1 += paymentInHand.d1;
      this.d5 += paymentInHand.d5;
      this.d10 += paymentInHand.d10;
      this.d20 += paymentInHand.d20;
      this.d50 += paymentInHand.d50;
      receivedPayment -= priceOfPurchase;
      while (receivedPayment > 0) {
        while ((receivedPayment >= 50) && (this.d50 > 0)) {
          receivedPayment -= 50;
          this.d50 -= 1;
          processedPayment.d50 += 1;
        }
        while ((receivedPayment >= 20) && (this.d20 > 0)) {
          receivedPayment -= 20;
          this.d20 -= 1;
          processedPayment.d20 += 1;
        }
        while ((receivedPayment >= 10) && (this.d10 > 0)) {
          receivedPayment -= 10;
          this.d10 -= 1;
          processedPayment.d10 += 1;
        }
        while ((receivedPayment >= 5) && (this.d5 > 0)) {
          receivedPayment -= 5;
          this.d5 -= 1;
          processedPayment.d5 += 1;
        }
        while ((receivedPayment >= 1) && (this.d1 > 0)) {
          receivedPayment -= 1;
          this.d1 -= 1;
          processedPayment.d1 += 1;
        }
        if (receivedPayment != 0) {
          this.updateMoney();
        }
      }
      return processedPayment;
    }
    return null;
  }
  
  
  
  protected Money returnItem(Item item){
    /*-----------------------------------------------------------
     * return an item (giving money back) 
     * 
     * preconditions: -item is non-null
     * 
     * postconditions: -if the register has enough money to give back 
    *                  for the item then that value is returned as
    *                  a money object. (The money object corresponds to 
    *                  the actual number of loonies/bill given back.)
    *                 -otherwise, returns null.
    * 
     * side effects: -if the register has enough money but cannot give 
    *                this amount exactly, it calls the updateMoney() 
    *                method to try to give the exact value. 
    *                If this also fails then the allLoonies() method
    *                is called.
    *               -the amount of money in the register after the method
    *                is reduced by the price if the register was able to 
    *                give this value.
    *-------------------------------------------------------------------
     */
    int priceOfItem = item.getPrice();
    Money totalChange = new Money();
    if (this.getTotalValue() >= priceOfItem){
      while (priceOfItem > 0) {
        while ((priceOfItem >= 50) && (this.d50 > 0)) {
          priceOfItem -= 50;
          this.d50 -= 1;
          totalChange.d50 += 1;
        }
        while ((priceOfItem >= 20) && (this.d20 > 0)) {
          priceOfItem -= 20;
          this.d20 -= 1;
          totalChange.d20 += 1;
        }
        while ((priceOfItem >= 10) && (this.d10 > 0)) {
          priceOfItem -= 10;
          this.d10 -= 1;
          totalChange.d10 += 1;
        }
        while ((priceOfItem >= 5) && (this.d5 > 0)) {
          priceOfItem -= 5;
          this.d5 -= 1;
          totalChange.d5 += 1;
        }
        while ((priceOfItem >= 1) && (this.d1 > 0)) {
          priceOfItem -= 1;
          this.d1 -= 1;
          totalChange.d1 += 1;
        }
        if (priceOfItem != 0) {
          this.updateMoney();
        }
      }
      return totalChange;
    }
    
    return null;
  }
  
  
  protected CashRegister updateMoney(){ 
    /*----------------------------------------------------
     * Purpose is to change the distribution of loonies/bills
    * while keeping the total value the same.
    * For example, 10 loonies might be exchanged for 2 five 
    * dollar bills.
     * 
    * preconditions - none
    * postconditions - returns itself (this)
    * side effects - the distribution of loonies/bills is possibly 
    *                changed in some way (it may not change) while
    *                the total value remains the same
     *-----------------------------------
     */
    
    /* DO NOT CHANGE THIS LINE */
  updateMoneyCount += 1;
    
    
    /*----------------------------------------------------
     * add your code below this comment block                                  
     *--------------------------------------------------- */
  int makeSmallChange = 1;
  //My goal for this method was to make sure it was called a maximum of one time per purchase/return, and to if at all possible prevent it from ever calling allLoonies
  //With this line in place, some amount of small change (minimum 5, maximum 10) will always get made whenever this method is called
  //I figure converting bigger bills into 10s will always solve any issues I have with paying out large amounts of change,
  //and this method will only be repeatedly called if I have to pay out a small amount of money that isn't a multiple of 5
  //Ensuring that I always have some amount of loonies in the register will solve that issue
  
  if (this.d10 >= 1) {
    this.d1 += 10;
    this.d10 -= 1;
    makeSmallChange = 0;
  }
  //I set this method up so that while it will always try to break the largest bills first, it will also make a small amount of change from a 5 (if possible)
  //and see if that gives it the change it needs
  //I did this because if the register has a large amount of money - $400, say - but none of it is in loonies, and someone comes in to return something worth $4,
  //just converting large bills would break all of them down into loonies for no reason
  //This method will still always break 50s if it gets called, because much as in real life, the larger a bill is the less useful it is for making change
  //This is especially true for a 50 - there's no reason I can think of that I'd ever want a 50 over 5 tens
  if (this.d50 != 0) {
    this.d10 += this.d50 * 5;
    this.d50 = 0;
    if (makeSmallChange != 0) {
      this.d10 -= 1;
      this.d1 += 10;
      makeSmallChange = 0;
    }
    return this;
  }
  else if (this.d20 != 0) {
    this.d10 += this.d20 * 2;
    this.d20 = 0;
    if (makeSmallChange != 0) {
      this.d10 -= 1;
      this.d1 += 10;
      makeSmallChange = 0;
    }
    return this;
  }
  else if (makeSmallChange == 1) {
    this.allLoonies();
  }








    /* DO NOT CHANGE THIS LINE */ 
    /* your method must return this */
    return this;
  }




  
  public CashRegister allLoonies(){
    /*--------------------------------------------------------------------
     * Purpose is to change all bills in the register to loonies.
  *
  * preconditions - none
  * postconditions - returns itself (this)
  * side effects - all money in the register is changed to loonies
  *                while the total value remains the same
     *------------------------------------------------------------------
     */
    
    /* DO NOT CHANGE THIS LINE */
  allLooniesCount += 1;
    
    
    /*----------------------------------------------------
     * add your code below this comment block                            
     *--------------------------------------------------- */
  this.d1 = this.getTotalValue();
  this.d5 = 0;
  this.d10 = 0;
  this.d20 = 0;
  this.d50 = 0;








    /* DO NOT CHANGE THIS LINE */ 
    /* your method must return this */
    return this;
  }
  
  //public static void main(String[] args) {
    //CashRegister test = new CashRegister(1,2,3,4,5);
    //Item testItem = new Item ("Priceless Relic", 50);
    //System.out.println(test.d1);
    //System.out.println(test.d5);
    //System.out.println(test.d10);
    //System.out.println(test.d20);
    //System.out.println(test.d50);
    //System.out.println(test.getTotalValue());
    
    //Item gum = new Item("Gum", 17);
    //CashRegister cash = new CashRegister();
    //cash.purchaseItem(gum, new Money(0,0,0,0,1));
    //System.out.println(cash.getTotalValue());
    //System.out.println(cash.get1());
    //System.out.println(cash.get10());
    //System.out.println(cash.allLoonies().get1());
    //System.out.println(cash.get5());
    
  //}  
}
  
