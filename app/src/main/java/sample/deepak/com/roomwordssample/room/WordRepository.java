package sample.deepak.com.roomwordssample.room;

import android.app.Application;
import android.os.AsyncTask;


import java.util.List;

import androidx.lifecycle.LiveData;
import sample.deepak.com.roomwordssample.dao.WordDao;
import sample.deepak.com.roomwordssample.model.Word;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

   public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

 public   LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }


    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}