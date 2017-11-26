
package whattimeisit;

import java.util.ArrayList;
import java.util.Scanner;

class Environment
{
    public int noOfClass,noOfPeriods;
    private ArrayList<Teacher> teacher;
    public Classes c[];
    
    Environment()
    {
        teacher=new ArrayList<Teacher>();
        this.noOfClass = 1;
        c = new Classes[1];
        this.noOfPeriods = 1;
    }
    
    public void setClassNum(int n){
        this.noOfClass = n;
        c = new Classes[n];
    }
    
    public void setPeriodNum(int n){
        this.noOfPeriods = n;
    }
    void addclass(int i,Classes c1)
    {
        this.c[i]=new Classes(c1);
    }
    Classes[] getClasses()
    {
        return c;
    }
    void generate()
    {
        for(int i=0;i<c.length;i++)
        {
            c[i].getTTG().setTimeTableSize(5, noOfPeriods);
            c[i].getTTG().setNoSubject(c[i].getNoOfSub());
            
            
            for(int j=0;j<c[i].getNoOfSub();j++)
            {
               // System.out.println(i+" "+j);
                c[i].getTTG().setSubjectHoursPerWeek(j,c[i].getHours()[j]);
            }
            
            //c[i].printH();

            for(int j=0;j<5;j++)
            {
                for(int k=0;k<c[i].getNoOfSub();k++)
                {
                    c[i].getTTG().setManuallyAllotedSubject(j, k, 0);
                }
            }
            
            c[i].getTTG().findManuallyAllotedSubject();
            c[i].getTTG().findUnallocatedSubjects();

            c[i].getTTG().generateTimeTable();

            
            c[i].output  = c[i].getTTG().getOutput();
            
            
           
            for(int j=0;j<5;j++)
            {
                PerDayGenerator pfg = new PerDayGenerator(noOfPeriods);
                c[i].answer[j] = pfg.Generator(c[i].output[j],c[i].getNoOfSub());
                c[i].actAnswer[j] = TeacherTimeTableSetter.setTable(this,i,j,c[i].getNoOfSub(),noOfPeriods,c[i].answer[j]/*class ke day ka*/,c[i]);
            }
            /*for(int j=0;j<5;j++){
            for(int k=0;k<noOfPeriods;k++){
            System.out.print(c[i].answer[j][k]+" ");}
            System.out.println();}*/
        }
        /*System.out.println("output : ");
        for(int i=0;i<noOfClass;i++){
            for(int j=0;j<5;j++)
            {
                for(int k=0;k<noOfPeriods;k++)
                {
                    if(c[i].actAnswer[j][k]!=-1)
                    System.out.print(c[i].getS(c[i].actAnswer[j][k])+" ");
                    else
                      System.out.print(c[i].actAnswer[j][k]+" ");      
                }
                System.out.println();
            }
            System.out.println("class completed");
        }*/
    }
    
    void checkForT(String Tname,String subName)
    {
        Teacher found = Teacher.findT(Tname);
                if(found==null)
                {
                    Teacher t1 = new Teacher(Tname,noOfPeriods);
                    teacher.add(t1);
                    Teacher.addT(t1);
                }
                else
                {
                    if(found.findSubject(subName)==-1)
                        found.addS(subName);
                }
    }
}

/*public class WhatTimeIsIT {
    public static void main(String[] args) {
        int nosub,n,m;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt(); //period
        m=sc.nextInt();//no. of classes
        Environment e=new Environment(n,m);
        for(int i=0;i<m;i++)
        {
            String c_name=sc.next();
            nosub=sc.nextInt();
            Classes c=new Classes(c_name,nosub);
            for(int j=0;j<nosub;j++)
            {
                String subName,Tname;
                int hrs;
                subName=sc.next();
                Tname=sc.next();
                e.checkForT(Tname,subName);
                hrs=sc.nextInt();
                c.addSubject(j,subName,Tname,hrs);
            }
            e.addclass(i,c);
        }
        e.generate();   
    }   
}*/