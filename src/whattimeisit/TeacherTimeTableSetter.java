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
public class TeacherTimeTableSetter {
    
    
    static int[] setTable(Environment e,int batch,int day,int noOfSub,int noOfPeriods,int table[]/*class ke day ka*/,Classes c)
    {
        Teacher t=null;
        int timeTable[][]=new int[5][50];
        int ctr=100;
        for(int i=0;i<noOfPeriods;i++)
        {
            timeTable[day][i] = -1;
        }
        for(int i=0;i<noOfPeriods;i++)
        {
            if(table[i]!=-1)
            {
                t = c.getTeacher(table[i]);
                if(t.getSchedule(day,i)==0)
                {
                    timeTable[day][i] = table[i]; 
                }
                else
                {
                    PerDayGenerator pdg = new PerDayGenerator(noOfPeriods);
                    table=pdg.Generator(c.output[day],noOfSub);
                    i=-1;
                   ctr--;
                   if(ctr==0)
                   {
                        e.c[batch-1].actAnswer[day] = setTable(e,batch-1,day,noOfSub,noOfPeriods,pdg.Generator(e.c[batch-1].output[day], noOfSub),e.c[batch-1]);
                        i=-1;
                   }
                }
            }
        }
        for(int i=0;i<noOfPeriods;i++)
        {
            if(table[i]!=-1){
                 t = c.getTeacher(table[i]);
            t.flag[day][i]=1;
            }
        }
        return timeTable[day];
    }
}
