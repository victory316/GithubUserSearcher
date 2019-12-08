package com.example.answer.github.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Leopold
 */
@Entity(tableName = "Bookmark")
data class Bookmark(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "language") val language: String?,
    @ColumnInfo(name = "stargazers_count") val stargazersCount: Int
) {
    companion object {
        fun to(repository: GithubRepository): Bookmark {
            return Bookmark(
                name = repository.name,
                description = repository.description,
                language = repository.language,
                stargazersCount = repository.stargazersCount
            )
        }
    }
}