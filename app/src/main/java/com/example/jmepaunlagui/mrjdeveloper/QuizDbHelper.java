package com.example.jmepaunlagui.mrjdeveloper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {

        Question q1 = new Question("It is considered as one of the most popular programming language today. It is used for webpage functionality such as interactivity, data processing, and development of purpose built applications.", "A. JavaScript ", "B. Java", "C. C#", "D. CSS", 1);
        addQuestion(q1);
        Question q2 = new Question("Is it a type of Graphic User Interface component used for displaying text in a Java frame. It is also used to provide descriptions to other controls.", "A. JTextField", "B. JLabel", "C. JFrame", "D. JMessage", 2);
        addQuestion(q2);
        Question q3 = new Question("This method is invoked to ensure that applet components draw themselves on the applet window.", "A. tapprove()", "B. authorize()", "C. validate() ", "D. certify()", 3);
        addQuestion(q3);
        Question q4 = new Question("What are binary files?", "A. Files that contain text, mostly in binary format.", "B. Files that contain data other than text, mostly in binary format. ", "C. Files that contain records other than text, mostly in binary format.", "D.\tFiles that contain script, mostly in binary format.", 2);
        addQuestion(q4);
        Question q5 = new Question(" ____________ is the foundation of the .NET Framework", "A. Classic Language Runtime", "B. Classic Language Realtime", "C. Common Language Realtime", "D. Common Language Runtime ", 4);
        addQuestion(q5);
        Question q6 = new Question("What is the correct JavaScript syntax to write “Hello World”?", "A. “Hello World”", "B. (“Hello World”)", "C. document.write (“Hello World”) ", "D. response.write (“Hello World”)", 3);
        addQuestion(q6);
        Question q7 = new Question("Where is the correct place to insert a JavaScript?", "A. The <head> section", "B. Both the<head> section and the <body> section", "C. The <body> section", "D. The <title> section", 2);
        addQuestion(q7);
        Question q8 = new Question("What is the correct syntax for referring to an external script called “myEvent.js”?", "A. <script href = ”myEvent.js”>", "B. <script name = “myEvent.js”>", "C. <script id = “myEvent.js”> ", "D. <script src = “myEvent.js”> ", 4);
        addQuestion(q8);
        Question q9 = new Question("How do you add a comment in JavaScript", "A. ‘This is a comment", "B. <!- - This is a comment - -> ", "C. “This is a comment”", "D. //This is a comment", 2);
        addQuestion(q9);
        Question q10 = new Question("Inside which HTML element do you place the JavaScript?", "A. <script> ", "B. <javascript>", "C. <js>", "D. <scripting>", 1);
        addQuestion(q10);
        Question q11 = new Question("The language was developed by ___ of Netscape under the name Mocha, which was later renamed to LiveScript until JavaScript.", "A. James Gosling", "B. Steve Jobs", "C. Brendan Eich ", "D. Niklaus Wirth", 3);
        addQuestion(q11);
        Question q12 = new Question("This loop loops through the elements of an array or an object.", "A. Do while loop", "B. For loop", "C. For in ", "D. While loop", 3);
        addQuestion(q12);
        Question q13 = new Question("This loop is used when you know how many times the block of code must be iterated.", "A. Do while loop", "B. For loop", "C. For in", "D. While loop", 2);
        addQuestion(q13);
        Question q14 = new Question("This loop is used if you know when the condition will be met.", "A. Do while loop\t", "B. For loop", "C. For in", "D. While loop ", 4);
        addQuestion(q14);
        Question q15 = new Question("This statement will break the loop and continue executing the code that follows after the loop.", "A. Break statement ", "B. Continue statement", "C. For loop", "D. While loop", 1);
        addQuestion(q15);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}