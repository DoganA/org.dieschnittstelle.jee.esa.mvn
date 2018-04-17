/**
 * @created by Dogan Alkan
 * @date 17.04.2018
 * @version v1.0.0
 */
package org.dieschnittstelle.jee.esa.basics.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DisplayAs {
    String value();
}