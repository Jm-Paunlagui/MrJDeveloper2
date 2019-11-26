package com.example.jmepaunlagui.mrjdeveloper;

import android.provider.BaseColumns;

public final class QuizContractCOD {
    private QuizContractCOD() {
    }

    public static class QuestionsTable_COD implements BaseColumns {
        public static final String TABLE_NAME_COD = "quiz_questions_COD";
        public static final String COLUMN_QUESTION_COD = "question_COD";
        public static final String COLUMN_OPTION1_COD = "option1_COD";
        public static final String COLUMN_OPTION2_COD = "option2_COD";
        public static final String COLUMN_OPTION3_COD = "option3_COD";
        public static final String COLUMN_OPTION4_COD = "option4_COD";
        public static final String COLUMN_ANSWER_NR_COD = "answer_nr_COD";
    }
}