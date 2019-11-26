package com.example.jmepaunlagui.mrjdeveloper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.jmepaunlagui.mrjdeveloper.QuizContractDEF.*;
import java.util.ArrayList;

public class QuizDbHelperDEF extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyDefintionQuiz.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public QuizDbHelperDEF(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE_DEF = "CREATE TABLE " +
                QuestionsTable_DEF.TABLE_NAME_DEF + " ( " +
                QuestionsTable_DEF._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable_DEF.COLUMN_QUESTION_DEF + " TEXT, " +
                QuestionsTable_DEF.COLUMN_OPTION1_DEF + " TEXT, " +
                QuestionsTable_DEF.COLUMN_OPTION2_DEF + " TEXT, " +
                QuestionsTable_DEF.COLUMN_OPTION3_DEF + " TEXT, " +
                QuestionsTable_DEF.COLUMN_OPTION4_DEF + " TEXT, " +
                QuestionsTable_DEF.COLUMN_ANSWER_NR_DEF + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE_DEF);
        fillQuestionsTable_DEF();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable_DEF.TABLE_NAME_DEF);
        onCreate(db);
    }

    private void fillQuestionsTable_DEF() {
        QuestionDEF q1 = new QuestionDEF("It is a software-only platform that runs on the top of other hardware-based platform.", "A. Java Base", "B. Java Platform", "C. Java Stage", "D. Java Applets", 2);
        addQuestionDEF(q1);
        QuestionDEF q2 = new QuestionDEF("It is a type of components of java platform that serves as the base for the Java platform ported onto various hardware-based platforms.", "A. Java Application Interface", "B. Java Application Machine", "C. Java Machine Interface", "D. Java Virtual Machine ", 4);
        addQuestionDEF(q2);
        QuestionDEF q3 = new QuestionDEF("JVM stands for?", "A. Java Virtue Machine", "B. Java Virtual Mechanics", "C. Java Virtual Machine ", "D. Java Video Machine", 3);
        addQuestionDEF(q3);
        QuestionDEF q4 = new QuestionDEF("It is a special file format that can run on any system and can be executed by any computer system that has a Java interpreter is called?", "A. Bitcodes", "B. Bytecodes ", "C. Byte", "D. Bithcodes", 2);
        addQuestionDEF(q4);
        QuestionDEF q5 = new QuestionDEF("Who is the inventor of Pascal?", "A. Niklaus Wirth ", "B. James Gosling", "C. Chris Warth", "D. Steve Jobs", 1);
        addQuestionDEF(q5);
        QuestionDEF q6 = new QuestionDEF("Define SYNTAX...", "A. Rules for combining numbers into symbols", "B. Rules for combining words into statements ", "C. A way to mix letters and words", "D. Mixing characters into symbols", 2);
        addQuestionDEF(q6);
        QuestionDEF q7 = new QuestionDEF("It allows the programs to properly execute the code according to how it was designed.", "A. Syntax", "B. Grammar", " C. Statements", " D. Identifiers", 2);
        addQuestionDEF(q7);
        QuestionDEF q8 = new QuestionDEF("_____ is also called reserved words that is use in order to implement its features.", "A. Literals", "B. Identifiers", "C. Keywords", "D. Statements", 3);
        addQuestionDEF(q8);
        QuestionDEF q9 = new QuestionDEF("Multiple comment uses the symbol…", "A. //", "B. /* */ ", "C. ///", "D. **/ /**", 2);
        addQuestionDEF(q9);
        QuestionDEF q10 = new QuestionDEF("This is a special type of variable with multiple containers.", "A. Arrows", "B. Arrays", "C. Two-Dimensional Array ", "D. Multiple Dimensional Array", 4);
        addQuestionDEF(q10);
        QuestionDEF q11 = new QuestionDEF("Enables the use of an existing class to define new classes.", "A. Inheritance", "B. Polymorphism", "C. Interfaces", "D. Abstract Classes ", 1);
        addQuestionDEF(q11);
        QuestionDEF q12 = new QuestionDEF("Ability of an object to take many forms.It makes changes in the method definition for?", "A. Abstract Classes", "B. Interfaces", "C. Polymorphism", "D. Inheritance", 3);
        addQuestionDEF(q12);
        QuestionDEF q13 = new QuestionDEF("Used to specify methods that a class must implement.", "A. Polymorphism", "B. Interfaces", "C. Inheritance", "D. AbstractClasses", 2);
        addQuestionDEF(q13);
        QuestionDEF q14 = new QuestionDEF("A code block that contains statements executed whether or not an exception is thrown.", "A. Try", "B. Catch", "C. Finally", "D. Exception", 3);
        addQuestionDEF(q14);
        QuestionDEF q15 = new QuestionDEF("A plain, borderless surface that can hold UI components. It allows for greater possibilities In GUI arrangements.", "A. JComboBox", "B. JScrollPane", "C. JCheckBox", "D. JPanel", 4);
        addQuestionDEF(q15);
    }

    private void addQuestionDEF(QuestionDEF question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable_DEF.COLUMN_QUESTION_DEF, question.getDEFquestion());
        cv.put(QuestionsTable_DEF.COLUMN_OPTION1_DEF, question.getDEFoption1());
        cv.put(QuestionsTable_DEF.COLUMN_OPTION2_DEF, question.getDEFoption2());
        cv.put(QuestionsTable_DEF.COLUMN_OPTION3_DEF, question.getDEFoption3());
        cv.put(QuestionsTable_DEF.COLUMN_OPTION4_DEF, question.getDEFoption4());
        cv.put(QuestionsTable_DEF.COLUMN_ANSWER_NR_DEF, question.getDEFanswerNr());
        db.insert(QuestionsTable_DEF.TABLE_NAME_DEF, null, cv);
    }

    public ArrayList<QuestionDEF> getAllQuestions() {
        ArrayList<QuestionDEF> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable_DEF.TABLE_NAME_DEF, null);

        if (c.moveToFirst()) {
            do {
                QuestionDEF question = new QuestionDEF();
                question.setDEFquestion(c.getString(c.getColumnIndex(QuestionsTable_DEF.COLUMN_QUESTION_DEF)));
                question.setDEFoption1(c.getString(c.getColumnIndex(QuestionsTable_DEF.COLUMN_OPTION1_DEF)));
                question.setDEFoption2(c.getString(c.getColumnIndex(QuestionsTable_DEF.COLUMN_OPTION2_DEF)));
                question.setDEFoption3(c.getString(c.getColumnIndex(QuestionsTable_DEF.COLUMN_OPTION3_DEF)));
                question.setDEFoption4(c.getString(c.getColumnIndex(QuestionsTable_DEF.COLUMN_OPTION4_DEF)));
                question.setDEFanswerNr(c.getInt(c.getColumnIndex(QuestionsTable_DEF.COLUMN_ANSWER_NR_DEF)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}