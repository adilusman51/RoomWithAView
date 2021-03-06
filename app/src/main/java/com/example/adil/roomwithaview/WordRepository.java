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
        insert(wordList);
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

                Word[] words = new Word[objectList.size()];
                objectList.toArray(words);

                if(mAysncTaskDao != null) mAysncTaskDao.insert(words);
            }

            return null;
        }
    }

    public void update(Word word){
        List<Word> wordList = new ArrayList<>();
        wordList.add(word);
        update(wordList);
    }

    public void update(List<Word> wordList){
        new updateAsyncTask(mWordDao).execute(wordList);
    }

    private static class updateAsyncTask extends AsyncTask<List<?>, Void, Void>{

        private WordDao mAysncTaskDao;

        public updateAsyncTask(){
            this.mAysncTaskDao = null;
        }

        public updateAsyncTask(WordDao wordDao) {
            this.mAysncTaskDao = wordDao;
        }

        @Override
        protected Void doInBackground(List<?>... objectParams) {
            List<?> objectList = objectParams[0];

            if(objectList == null && objectList.isEmpty())
                return null;

            if (objectList.get(0) instanceof Word){

                Word[] words = new Word[objectList.size()];
                objectList.toArray(words);

                if(mAysncTaskDao != null) mAysncTaskDao.update(words);
            }

            return null;
        }
    }

    public void delete(Word word){
        List<Word> wordList = new ArrayList<>();
        wordList.add(word);
        delete(wordList);
    }

    public void delete(List<Word> wordList){
        new deleteAsyncTask(mWordDao).execute(wordList);
    }

    private static class deleteAsyncTask extends AsyncTask<List<?>, Void, Void>{

        private WordDao mAysncTaskDao;

        public deleteAsyncTask(){
            this.mAysncTaskDao = null;
        }

        public deleteAsyncTask(WordDao wordDao) {
            this.mAysncTaskDao = wordDao;
        }

        @Override
        protected Void doInBackground(List<?>... objectParams) {
            List<?> objectList = objectParams[0];

            if(objectList == null && objectList.isEmpty())
                return null;

            if (objectList.get(0) instanceof Word){

                Word[] words = new Word[objectList.size()];
                objectList.toArray(words);

                if(mAysncTaskDao != null) mAysncTaskDao.delete(words);
            }

            return null;
        }
    }
}
