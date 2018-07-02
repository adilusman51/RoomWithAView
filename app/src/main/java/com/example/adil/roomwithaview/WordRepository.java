package com.example.adil.roomwithaview;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mWordList;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        this.mWordDao = db.wordDao();
        this.mWordList = mWordDao.getAllWords();
    }

    LiveData<List<Word>> getAllWords(){
        return mWordList;
    }

    public void insert(Word word){
        List<Word> wordList = new ArrayList<>();
        wordList.add(word);
        new insertAsyncTask(mWordDao).execute(wordList);
    }

    public void insert(List<Word> wordList){
        new insertAsyncTask(mWordDao).execute(wordList);
    }

    private static class insertAsyncTask extends AsyncTask<List<?>, Void, Void>{

        private WordDao mAysncTaskDao;

        public insertAsyncTask(){
            this.mAysncTaskDao = null;
        }

        public insertAsyncTask(WordDao wordDao) {
            this.mAysncTaskDao = wordDao;
        }

        @Override
        protected Void doInBackground(List<?>... objectParams) {
            List<?> objectList = objectParams[0];

            if(objectList == null && objectList.isEmpty())
                return null;

            if (objectList.get(0) instanceof Word){
                for (int i = 0; i < objectList.size(); i++){
                    if(mAysncTaskDao == null)
                        return null;
                    else
                        mAysncTaskDao.insert((Word) objectList.get(i));
                }
            }

            return null;
        }
    }

    //TODO UPDATE AND DELETE WRAPPERS
}
