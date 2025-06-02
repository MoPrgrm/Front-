package kr.co.gachon.moproject_d.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {WordEntity.class}, version = 1)
public abstract class WordDatabase extends RoomDatabase {
    public abstract WordDao wordDao();

    private static volatile WordDatabase INSTANCE;
    private static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(1);

    public static WordDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    WordDatabase.class, "word_database")
                            .addCallback(roomCallback) // 여기 주목!
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // 초기 하드코딩 데이터 삽입
    private static final Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                WordDao dao = INSTANCE.wordDao();
                dao.insert(new WordEntity("apple", "사과", "English"));
                dao.insert(new WordEntity("banana", "바나나", "English"));
                dao.insert(new WordEntity("computer", "컴퓨터", "English"));
                dao.insert(new WordEntity("book", "책", "English"));
            });
        }
    };
}
