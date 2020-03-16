package com.example.tema2;

import android.os.AsyncTask;


    // DO NOT PERFORM OPERATION ON MAIN THREAD AS APP WILL CRASH
    // See more details about AsyncTask in the next chapter
    public class InsertTask extends AsyncTask<User, Void, Void> {
        OnUserRepositoryActionListener listener;
        InsertTask(OnUserRepositoryActionListener listener) {
            this.listener = listener;
        }
        @Override
        protected Void doInBackground(User... users) {

            ApplicationController.getAppDatabase().userDao().insertTask(users[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listener.actionSuccess();
        }
    }
