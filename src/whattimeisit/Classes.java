/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whattimeisit;

/**
 *
 * @author Aditi
 */
public class Classes
{
	private String name;
	private String subjects[];
	private Teacher teachers[];
	private int noOfHours[];
	private int noOfSubjects;
	private TimeTableGenerator t;
        int answer[][] = new int[7][50];
        int output[][] = new int[7][50];
        //private int timeTable[][];
	 int actAnswer[][] = new int[5][50];
	Classes(String name, int noOfSubjects)
	{
		this.name = name;
		this.noOfSubjects  = noOfSubjects;
                subjects = new String[noOfSubjects];
                teachers = new Teacher[noOfSubjects];
                noOfHours = new int[noOfSubjects];
                t = new TimeTableGenerator();
	}
	
	Classes(Classes c)
	{
		this.name = c.name;
		this.subjects = c.subjects;
                this.teachers = c.teachers;
                this.noOfHours = c.noOfHours;
		
		this.noOfSubjects  = c.noOfSubjects;
		this.t = c.t;
                
	}
	
	void addSubject(int index,String subject,String teacher,int hrs)
	{
		this.subjects[index] = subject;
		this.teachers[index] = Teacher.findT(teacher);
		this.noOfHours[index] = hrs;
                //System.out.println("addes");
	}
        
        TimeTableGenerator getTTG()
        {
            return this.t;
        }
        
        int getNoOfSub()
        {
            return this.noOfSubjects;
        }
        Teacher getTeacher(int i)
        {
            return teachers[i];
        }
        
        int[] getHours()
        {
            return this.noOfHours;
        }
        
        void printH()
        {
            for(int i=0;i<this.getNoOfSub();i++)
            {
                System.out.print(this.subjects[i]);
            }
        }
        
        String getS(int index)
        {
            
            return this.subjects[index];
        }
        String getT(int index)
        {
            
            return this.teachers[index].getName();
        }
        
        public String getName(){
            return name;
        }
}
