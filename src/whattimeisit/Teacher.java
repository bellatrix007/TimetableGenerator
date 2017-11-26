/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whattimeisit;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Aditi
 */
public class  Teacher
{
	private String name;
	private ArrayList<String> subjectsTaught;
	
	private static ArrayList<Teacher> TeacherRecord = new ArrayList<Teacher>();
 int flag[][];
	Teacher(String name,int n)
	{
		this.name = name;
		subjectsTaught = new ArrayList<String>();
		flag = new int[5][n];
	}
	
	static Teacher findT(String name)
	{
            for (Teacher t1 : TeacherRecord) {
                if(t1.name.equals(name))
                    return t1;
            }
		return null;	
	}
	
	void addS(String subject)
	{
		this.subjectsTaught.add(subject);
	}
	
	int findSubject(String subject)
	{
		for(String s1 : subjectsTaught)
		{
			if(s1.equals(subject))
				return 1;
		}
		return -1;
	}
        int getSchedule(int day , int period)
        {
            return  flag[day][period];
        }
        static void addT(Teacher t)
        {
            TeacherRecord.add(t);
        }

        String getName()
        {
            return this.name;
        }
}
