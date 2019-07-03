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
package org.mybatis.dynamic.sql.where.condition;

import java.util.Collection;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.mybatis.dynamic.sql.AbstractListValueCondition;
import org.mybatis.dynamic.sql.util.StringUtilities;

public class IsInCaseInsensitive extends AbstractListValueCondition<String> {

    protected IsInCaseInsensitive(Collection<String> values) {
        super(values, s -> s.map(StringUtilities::safelyUpperCase));
    }

    protected IsInCaseInsensitive(Collection<String> values, UnaryOperator<Stream<String>> valueStreamTransformer) {
        super(values, StringUtilities.upperCaseAfter(valueStreamTransformer));
    }

    @Override
    public String renderCondition(String columnName, Stream<String> placeholders) {
        return "upper(" + columnName + ") " + //$NON-NLS-1$ //$NON-NLS-2$
                placeholders.collect(Collectors.joining(",", "in (", ")")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    /**
     * This method allows you to modify the condition's values before they are placed into the parameter map.
     * For example, you could filter nulls, or trim strings, etc. This process will run before final rendering of SQL.
     * If you filter values out of the stream, then final condition will not reference those values. If you filter all
     * values out of the stream, then the condition will not render.
     * 
     * @param valueStreamTransformer a UnaryOperator that will transform the value stream before
     *     the values are placed in the parameter map
     * @return new condition with the specified transformer
     */
    public IsInCaseInsensitive then(UnaryOperator<Stream<String>> valueStreamTransformer) {
        return new IsInCaseInsensitive(values, valueStreamTransformer);
    }

    /**
     * This method allows you to modify the condition's values before they are placed into the parameter map.
     * For example, you could filter nulls, or trim strings, etc. This process will run before final rendering of SQL.
     * If you filter values out of the stream, then final condition will not reference those values. If you filter all
     * values out of the stream, then the condition will not render.
     * 
     * @param valueStreamTransformer a UnaryOperator that will transform the value stream before
     *     the values are placed in the parameter map
     * @return new condition with the specified transformer
     * 
     * @deprecated See {@link IsInCaseInsensitive#then(UnaryOperator)}
     */
    @Deprecated
    public IsInCaseInsensitive withValueStreamOperations(UnaryOperator<Stream<String>> valueStreamTransformer) {
        return then(valueStreamTransformer);
    }
    
    public static IsInCaseInsensitive of(Collection<String> values) {
        return new IsInCaseInsensitive(values);
    }
}
