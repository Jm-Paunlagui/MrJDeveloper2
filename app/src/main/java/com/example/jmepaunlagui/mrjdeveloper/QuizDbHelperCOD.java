package com.example.jmepaunlagui.mrjdeveloper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.jmepaunlagui.mrjdeveloper.QuizContractCOD.*;
import java.util.ArrayList;

public class QuizDbHelperCOD extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyCodeQuiz.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public QuizDbHelperCOD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE_COD = "CREATE TABLE " +
                QuestionsTable_COD.TABLE_NAME_COD + " ( " +
                QuestionsTable_COD._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable_COD.COLUMN_QUESTION_COD + " TEXT, " +
                QuestionsTable_COD.COLUMN_OPTION1_COD + " TEXT , " +
                QuestionsTable_COD.COLUMN_OPTION2_COD + " TEXT , " +
                QuestionsTable_COD.COLUMN_OPTION3_COD + " TEXT , " +
                QuestionsTable_COD.COLUMN_OPTION4_COD + " TEXT , " +
                QuestionsTable_COD.COLUMN_ANSWER_NR_COD + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE_COD);
        fillQuestionsTableCOD();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable_COD.TABLE_NAME_COD);
        onCreate(db);
    }

    private void fillQuestionsTableCOD() {
        QuestionCOD q1 = new QuestionCOD("How to make new frame by using the JAVA Programming Language (object-oriented) in your platform (NetBeans)?", "A. import javax.swing.JFrame;\n" +
                "public static void main(String[] args) {\n" +
                "JFrame hello = new JFrame(“New Frame”);\n" +
                "hello.setSize(300,300);\n" +
                "hello.setVisible(true);\t\n" +
                "}\n", "B.import java.Frame;\n" +
                "public static void main(String[] args) {\n" +
                "Frame hello = new Frame(“New Frame”);\n" +
                "hello.setSize(300,300);\n" +
                "hello.setVisible(true);\n" +
                "}\n", "C.import javax.Swing.JavaFrame;\n" +
                "public static void main(String[] args) {\n" +
                "JavaFrame hello = new JavaFrame(“New Frame”);\n" +
                "hello.setsize(300,300);\n" +
                "hello.setvisible(true);\n" +
                "}\n", "D. import java.swing.JFrame;\n" +
                " public static void main(String[] args) {\n" +
                "JFrame hello = new JFrame(“New Frame”);\n" +
                "hello.setSize(300,300);\n" +
                "hello.setVisible(false);\n" +
                "}\n", 1);
        addQuestionCOD(q1);
        QuestionCOD q2 = new QuestionCOD("Import java.swing.JFrame;\n" +
                "Public static void main(String[] args) {\n" +
                "JFrame hello = new JFrame(“New Frame”);\n" +
                "hello.setSize(300,300);\n" +
                "hello.setVisible(true);\n" +
                "}\n" +
                "In which line makes the program error?\n", "A.First Line and Third Line", "B.Second Line and Third Line", "C.First Line and Second Line", "D.Third Line and Fourth Line", 3);
        addQuestionCOD(q2);
        QuestionCOD q3 = new QuestionCOD("How to display the value of 25 in an array?", "A.Int[] array = {5, 25, 3, 7, 4};\n" +
                "System.out.println(array[1]);\n", "B.int[] array = {5, 25, 3, 7, 4};\n" +
                "System.Out.Println(array[1]);\n", "C.int[] array = {5, 25, 3, 7, 4};\n" +
                "System.out.println(array[1])\n", "D.int[] array = {5, 25, 3, 7, 4};\n" +
                "System.out.println(array[1]);\n", 4);
        addQuestionCOD(q3);
        QuestionCOD q4 = new QuestionCOD("How to sum up array?", "A.int[] arr = new int[5];\n" +
                "System.out.println(arr.length);\n", "B.int[] arr = new Int[5];\n" +
                "System.out.printLn(arr.length);\n", "C.int[] Arr = New Int[5];\n" +
                "System.Out.println(arr.length);\n", "D.int[] arr = new int[5];\n" +
                "system.out.Println(arr.length);\n", 1);
        addQuestionCOD(q4);
        QuestionCOD q5 = new QuestionCOD("Which of the following code that will result an output of “Welcome”?", "A.int age = 25;\n" +
                "if(age<=0){\n" +
                "System.out.println(“Error”);\n" +
                "}\n" +
                "else if(age<=16){\n" +
                "System.out.println(“Too Young”);\n" +
                "}\n" +
                "else if(age<100){\n" +
                "System.out.println(“Welcome”);\n" +
                "}\n" +
                "else{\n" +
                "System.out.println(“Really?”);\n" +
                "}\n", "B.int age = 25;\n" +
                "if(age<=0){\n" +
                "System.out.printInteger(“Error”);\n" +
                "}\n" +
                "else if(age<=16){\n" +
                "System.out.printInteger(“Too Young”);\n" +
                "}\n" +
                "else if(age<100){\n" +
                "System.out.printInteger(“Welcome”);\n" +
                "}\n" +
                "else{\n" +
                "System.out.printInteger(“Really?”);\n" +
                "}\n", "C.int age = 25;\n" +
                "if(age<=0){\n" +
                "System.out.printlnt(“Error”);\n" +
                "}\n" +
                "else if(age<=16){\n" +
                "System.out.printlnt(“Too Young”);\n" +
                "}\n" +
                "else if(age<100){\n" +
                "System.out.printlnt(“Welcome”);\n" +
                "}\n" +
                "else{\n" +
                "System.out.printlnt(“Really?”);\n" +
                "}\n", "D.int age = 25;\n" +
                "if(age<=0){\n" +
                "System.out.println(“Error”);\n" +
                "}\n" +
                " If else(age<=16){\n" +
                "System.out.println(“Too Young”);\n" +
                "}\n" +
                "If else (age<100){\n" +
                "System.out.println(“Welcome”);\n" +
                "}\n" +
                "else{\n" +
                "System.out.println(“Really?”);\n" +
                "}\n", 1);
        addQuestionCOD(q5);
        QuestionCOD q6 = new QuestionCOD("String ask = JOptionPane.showInputDialog(“What is your gender?”);\n" +
                "if(ask.equal(“Male”){\n" +
                "System.out.println(“So you were a boy.”);\n" +
                "}\n" +
                "else if(ask.equals(“Female”){\n" +
                "System.out.println(“So you were a girl.”);\n" +
                "}\n" +
                "else{\n" +
                "System.out.println(“Oh okay”);\n" +
                "}\n" +
                "What makes the syntax error?\n", "A.Between first line and third line ", "B.Fourth line", "C.Between fifth line and seventh line", "D.Eight line ", 1);
        addQuestionCOD(q6);
        QuestionCOD q7 = new QuestionCOD("Which of the following codes that shows the proper use of modulo?", "A.int given = 5;\n" +
                "int given1 = 4;\n" +
                "int solve = given % given1;\n" +
                "System.out.println(solve);\n" +
                "//OUTPUT: 1\n", "B.int given = 5;\n" +
                "int given1 = 4;\n" +
                "int solve = given / given1;\n" +
                "System.out.println(solve);\n" +
                "//OUTPUT: 1\n", "C.int given = 5;\n" +
                "int given1 = 4;\n" +
                "int solve = given; % given1;\n" +
                "System.out.println(solve);\n" +
                "//OUTPUT: 1\n", "D.int given = 5;\n" +
                "int given1 = 4;\n" +
                "int solve = given % given1;\n" +
                "System.out.println(solve);\n" +
                "//OUTPUT: 9\n", 3);
        addQuestionCOD(q7);
        QuestionCOD q8 = new QuestionCOD("Which of the following is an example of matrices?", "A.String[][] matrices = {{“Dave”, “Bill”, “John”},{“Mark”, “Lisa”, “Bryan”},{“William”, “Joshua”, “Ben”}};", "B.String[][] matrices = {[“Dave”, “Bill”, “John”],[Mark”, “Lisa”, “Bryan”],[“William”, “Joshua”, “Ben”]};", "C.String[][] matrices = {(“Dave”, “Bill”, “John”),(“Mark”, “Lisa”, “Bryan”),(“William”, “Joshua”, “Ben”)};", "D.String[][] matrices = {{“Dave”, “Bill”, “John”}{“Mark”, “Lisa”, “Bryan”}{“William”, “Joshua”, “Ben”}};", 1);
        addQuestionCOD(q8);
        QuestionCOD q9 = new QuestionCOD("Int x = 1;\n" +
                "do {\n" +
                "System.out.println(x);\n" +
                "x++;\n" +
                "} while (x < 5);\n" +
                "What makes the condition error?\n", "A.First Line", "B.Second Line", "C.Third Line", "D.Fourth Line", 1);
        addQuestionCOD(q9);
        QuestionCOD q10 = new QuestionCOD("int age = 0;\n" +
                "do {\n" +
                "\tSystem.out.println(age);\n" +
                "\tage++;\n" +
                "} while (age<18);\n" +
                "What is the output?\n", "A.1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17", "B.1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18", "C.1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19", "D.1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18, 19, 20", 1);
        addQuestionCOD(q10);

    }

    private void addQuestionCOD(QuestionCOD questionCOD) {
        ContentValues cvCOD = new ContentValues();
        cvCOD.put(QuestionsTable_COD.COLUMN_QUESTION_COD, questionCOD.getCODquestion());
        cvCOD.put(QuestionsTable_COD.COLUMN_OPTION1_COD, questionCOD.getCODoption1());
        cvCOD.put(QuestionsTable_COD.COLUMN_OPTION2_COD, questionCOD.getCODoption2());
        cvCOD.put(QuestionsTable_COD.COLUMN_OPTION3_COD, questionCOD.getCODoption3());
        cvCOD.put(QuestionsTable_COD.COLUMN_OPTION4_COD, questionCOD.getCODoption4());
        cvCOD.put(QuestionsTable_COD.COLUMN_ANSWER_NR_COD, questionCOD.getCODanswerNr());
        db.insert(QuestionsTable_COD.TABLE_NAME_COD, null, cvCOD);
    }

    public ArrayList<QuestionCOD> getAllQuestionsCOD() {
        ArrayList<QuestionCOD> questionCODList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cCOD = db.rawQuery("SELECT * FROM " + QuestionsTable_COD.TABLE_NAME_COD, null);
        if (cCOD.moveToFirst()) {
            do {
                QuestionCOD questionCOD = new QuestionCOD();
                questionCOD.setCODquestion(cCOD.getString(cCOD.getColumnIndex(QuestionsTable_COD.COLUMN_QUESTION_COD)));
                questionCOD.setCODoption1(cCOD.getString(cCOD.getColumnIndex(QuestionsTable_COD.COLUMN_OPTION1_COD)));
                questionCOD.setCODoption2(cCOD.getString(cCOD.getColumnIndex(QuestionsTable_COD.COLUMN_OPTION2_COD)));
                questionCOD.setCODoption3(cCOD.getString(cCOD.getColumnIndex(QuestionsTable_COD.COLUMN_OPTION3_COD)));
                questionCOD.setCODoption4(cCOD.getString(cCOD.getColumnIndex(QuestionsTable_COD.COLUMN_OPTION4_COD)));
                questionCOD.setCODanswerNr(cCOD.getInt(cCOD.getColumnIndex(QuestionsTable_COD.COLUMN_ANSWER_NR_COD)));
                questionCODList.add(questionCOD);
            } while (cCOD.moveToNext());
        }
        cCOD.close();
        return questionCODList;
    }
}