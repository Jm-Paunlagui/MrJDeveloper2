package com.example.jmepaunlagui.mrjdeveloper;

import android.provider.BaseColumns;

public final class QuizContractDEF {
    private QuizContractDEF() {
    }

    public static class QuestionsTable_DEF implements BaseColumns {
        public static final String TABLE_NAME_DEF = "quiz_questions_DEF";
        public static final String COLUMN_QUESTION_DEF = "question_DEF";
        public static final String COLUMN_OPTION1_DEF = "option1_DEF";
        public static final String COLUMN_OPTION2_DEF = "option2_DEF";
        public static final String COLUMN_OPTION3_DEF = "option3_DEF";
        public static final String COLUMN_OPTION4_DEF = "option4_DEF";
        public static final String COLUMN_ANSWER_NR_DEF = "answer_nr_DEF";
    }
}