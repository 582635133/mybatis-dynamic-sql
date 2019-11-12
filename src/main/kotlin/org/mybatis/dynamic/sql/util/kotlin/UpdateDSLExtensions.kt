/**
 *    Copyright 2016-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.dynamic.sql.util.kotlin

import org.mybatis.dynamic.sql.BindableColumn
import org.mybatis.dynamic.sql.VisitableCondition
import org.mybatis.dynamic.sql.insert.InsertDSL
import org.mybatis.dynamic.sql.insert.MultiRowInsertDSL
import org.mybatis.dynamic.sql.update.UpdateDSL
import org.mybatis.dynamic.sql.update.UpdateModel
import org.mybatis.dynamic.sql.util.Buildable

// insert completers are here because sonar doesn't see them as covered if they are in a file by themselves
typealias InsertCompleter<T> = InsertDSL<T>.() -> InsertDSL<T>
typealias MultiRowInsertCompleter<T> = MultiRowInsertDSL<T>.() -> MultiRowInsertDSL<T>

typealias UpdateCompleter = UpdateDSL<UpdateModel>.() -> Buildable<UpdateModel>

fun <T> UpdateDSL<UpdateModel>.where(column: BindableColumn<T>, condition: VisitableCondition<T>, collect: CriteriaReceiver) =
    apply {
        where().where(column, condition, collect)
    }

fun <T> UpdateDSL<UpdateModel>.and(column: BindableColumn<T>, condition: VisitableCondition<T>) =
    apply {
        where().and(column, condition)
    }

fun <T> UpdateDSL<UpdateModel>.and(column: BindableColumn<T>, condition: VisitableCondition<T>, collect: CriteriaReceiver) =
    apply {
        where().and(column, condition, collect)
    }

fun <T> UpdateDSL<UpdateModel>.or(column: BindableColumn<T>, condition: VisitableCondition<T>) =
    apply {
        where().or(column, condition)
    }

fun <T> UpdateDSL<UpdateModel>.or(column: BindableColumn<T>, condition: VisitableCondition<T>, collect: CriteriaReceiver) =
    apply {
        where().or(column, condition, collect)
    }
