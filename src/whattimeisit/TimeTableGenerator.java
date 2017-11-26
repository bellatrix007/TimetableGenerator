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
import java.util.LinkedList;
import java.util.Random;

public class TimeTableGenerator {

    private int product;
    private int noOfDays;
    private int noOfPeriods;
    private int noOfSubjects;

    private int[] subjectHoursPerWeek = new int[50];
    private int[] initialExtra = new int[50];

    private int[][] initialValues = new int[7][50];
    private int[][] initialValuesCopy = new int[7][50];
    private int[] initialTotal = new int[50];
    private int outputTimetable[][] = new int[7][50];

    void setTimeTableSize(int days, int periods) {
        this.noOfDays = days;
        this.noOfPeriods = periods;
        this.product = days * periods;
    }

    void setNoSubject(int noOfSubjects) {
        this.noOfSubjects = noOfSubjects;
    }

    void setSubjectHoursPerWeek(int index, int noOfHours) {
        this.subjectHoursPerWeek[index] = noOfHours;
    }

    void setManuallyAllotedSubject(int day, int subjectIndex, int value) {
        this.initialValues[day][subjectIndex] = value;
        this.initialValuesCopy[day][subjectIndex] = value;
    }

    void findManuallyAllotedSubject() {
        for (int day = 0; day < noOfDays; day++) {
            for (int subject = 0; subject < noOfSubjects; subject++) {
                this.initialTotal[subject] = this.initialTotal[subject] + this.initialValues[day][subject];
            }
        }
    }

    void findUnallocatedSubjects() {
        for (int subject = 0; subject < noOfSubjects; subject++) {
            this.initialExtra[subject] = this.subjectHoursPerWeek[subject];
        }
    }

    void generateTimeTable() {
        LinkedList<Object> dataList1 = new LinkedList<Object>();

        for (int subject = 0; subject < noOfSubjects; subject++) {
            if (this.initialExtra[subject] > 0) {
                dataList1.add(subject);
            }
        }

        while (dataList1.size() != 0) {
            Random r = new Random();
            int rand = r.nextInt(dataList1.size());
            int subjectIndex = Integer.parseInt(String.valueOf(dataList1.get(rand)));

            int collect = 0;

            while (this.initialExtra[subjectIndex] > 0) {
                LinkedList<Object> dataList = new LinkedList<Object>();
                LinkedList<Object> tempList = new LinkedList<Object>();
                for (int day = 0; day < noOfDays; day++) {
                    if (this.initialValues[day][subjectIndex] == collect) {
                        tempList.add(day);
                    }
                }

                while (tempList.size() != 0) {
                    int randVal = r.nextInt(tempList.size());
                    dataList.add(tempList.remove(randVal));
                }

                for (int idx = dataList.size() - 1; idx > 0; idx--) {
                    for (int num = 0; num < idx; num++) {
                        int day1 = Integer.parseInt(String.valueOf(dataList.get(num)));
                        int day2 = Integer.parseInt(String.valueOf(dataList.get(num + 1)));

                        int sum1 = 0;
                        for (int subject = 0; subject < noOfSubjects; subject++) {
                            sum1 = sum1 + this.initialValues[day1][subject];
                        }

                        int sum2 = 0;
                        for (int subject = 0; subject < noOfSubjects; subject++) {
                            sum2 = sum2 + this.initialValues[day2][subject];
                        }

                        if (sum1 > sum2) {
                            Object obj = dataList.get(num);
                            dataList.set(num, dataList.get(num + 1));
                            dataList.set(num + 1, obj);
                        }
                    }
                }

                for (int idx = 0; idx < dataList.size(); idx++) {
                    int day = Integer.parseInt(String.valueOf(dataList.get(idx)));

                    int sum = 0;
                    for (int subject = 0; subject < noOfSubjects; subject++) {
                        sum = sum + this.initialValues[day][subject];
                    }

                    if (sum >= noOfPeriods) {
                    } else {
                        this.initialValues[day][subjectIndex]++;
                        this.initialExtra[subjectIndex]--;
                    }

                    if (this.initialExtra[subjectIndex] == 0) {
                        break;
                    }
                }
                collect++;
            }
            dataList1.remove(rand);
        }
    }

    int[][] getOutput() {

        for (int day = 0; day < noOfDays; day++) {
            for (int subject = 0; subject < noOfSubjects; subject++) {
                outputTimetable[day][subject] = this.initialValues[day][subject] - this.initialValuesCopy[day][subject];
            }
        }
        return outputTimetable;
    }
}
