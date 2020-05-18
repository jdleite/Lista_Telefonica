package br.com.lista_telefonica.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migrations {

    private static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

            database.execSQL("CREATE TABLE IF NOT EXISTS `new_contact` " +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`telephone` TEXT, " +
                    "`cellphone` TEXT, " +
                    "`personId` INTEGER NOT NULL)");

            database.execSQL("INSERT INTO new_contact (id, telephone, personId) " +
                    "SELECT id, telephone, personId FROM Contact");

            database.execSQL("DROP TABLE Contact");

            database.execSQL("ALTER TABLE new_contact RENAME TO Contact");

        }
    };

    static final Migration[] AllMigrations = {MIGRATION_1_2};
}
