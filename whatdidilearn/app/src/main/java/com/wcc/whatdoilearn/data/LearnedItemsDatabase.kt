package com.wcc.whatdoilearn.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.wcc.whatdoilearn.entities.LearnedItem
import com.wcc.whatdoilearn.entities.UnderstandingLevel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [LearnedItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LearnedItemsDatabase: RoomDatabase() {

    abstract fun learnedItemDao(): LearnedItemDao

    companion object {
        // Singleton para evitar que multiplas instancias do baco de dados sejam abertas ao mesmo tempo
        @Volatile
        private var INSTANCE: LearnedItemsDatabase? = null

        private class LearnedItemDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.learnedItemDao())
                    }
                }
            }

            suspend fun populateDatabase(dao: LearnedItemDao) {
                val itemLearned1 = LearnedItem(
                    "Kotlin - Null safety",
                    "O sistema de tipos de Kotlin visa eliminar o perigo de referências nulas do código",
                    UnderstandingLevel.HIGH
                )
                dao.insert(itemLearned1)

                val itemLearned2 = LearnedItem(
                    "Layout editor",
                    "O Design Editor exibe o layout em vários dispositivos e versões do Android.É possível criar e editar um layout usando apenas componentes visuais.",
                    UnderstandingLevel.MEDIUM
                )
                dao.insert(itemLearned2)

                val itemLearned3 = LearnedItem(
                    "Git",
                    "É um sistema de controle de versão distribuído. Com ele é possível rastrear mudanças no código-fonte durante o desenvolvimento de software.",
                    UnderstandingLevel.LOW
                )
                dao.insert(itemLearned3)

                val itemLearned4 = LearnedItem(
                    "GroupView",
                    "É uma view especial que pode conter outras views (chamadas de filhos).É a classe base para layouts e contêineres de views.",
                    UnderstandingLevel.HIGH
                )
                dao.insert(itemLearned4)

            }
        }

        fun getDatabase(context: Context, scope: CoroutineScope): LearnedItemsDatabase {
            // se INSTANCE não é nulo, então retorna ela mesma,
            // se INSTANCE é nula, então cria uma instancia do banco
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LearnedItemsDatabase::class.java,
                    "learned_item_database"
                )
                .addCallback(LearnedItemDatabaseCallback(scope))
                .build()
                INSTANCE = instance
                instance
            }
        }

        fun getAll(): List<LearnedItem> {
            val itemLearned1 = LearnedItem(
                "Kotlin - Null safety",
                "O sistema de tipos de Kotlin visa eliminar o perigo de referências nulas do código",
                UnderstandingLevel.HIGH
            )
            val itemLearned2 = LearnedItem(
                "Layout editor",
                "O Design Editor exibe o layout em vários dispositivos e versões do Android.É possível criar e editar um layout usando apenas componentes visuais.",
                UnderstandingLevel.MEDIUM
            )
            val itemLearned3 = LearnedItem(
                "Git",
                "É um sistema de controle de versão distribuído. Com ele é possível rastrear mudanças no código-fonte durante o desenvolvimento de software.",
                UnderstandingLevel.LOW
            )
            val itemLearned4 = LearnedItem(
                "GroupView",
                "É uma view especial que pode conter outras views (chamadas de filhos).É a classe base para layouts e contêineres de views.",
                UnderstandingLevel.HIGH
            )

            return listOf(itemLearned1, itemLearned2, itemLearned3, itemLearned4)
        }
    }
}