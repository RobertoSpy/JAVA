class Person{
  protected String name;
  protected String dateOfBirth;

   public Person(String name, String dateOfBirth){
    this.name = name;
    this.dateOfBirth = dateOfBirth;
   }

   public String getName(){
    return this.name;
   }

   public String getBirth(){
    return this.dateOfBirth;
   }
}