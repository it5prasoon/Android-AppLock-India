package com.webers.applock.data.database.pattern

import androidx.room.*

@Entity(tableName = "pattern")
@TypeConverters(PatternTypeConverter::class)
data class PatternEntity(
    @PrimaryKey
    @ColumnInfo(name = "pattern_metadata")
    val patternMetadata: PatternDotMetadata
)