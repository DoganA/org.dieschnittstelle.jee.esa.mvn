/**
 *
 * @since v1.0
 * @version v2.0
 */
package org.dieschnittstelle.jee.esa.basics;


import org.dieschnittstelle.jee.esa.basics.annotations.AnnotatedStockItemBuilder;
import org.dieschnittstelle.jee.esa.basics.annotations.DisplayAs;
import org.dieschnittstelle.jee.esa.basics.annotations.StockItemProxyImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.dieschnittstelle.jee.esa.utils.Utils.*;

public class ShowAnnotations {

	public static void main(String[] args) {
		// we initialise the collection
		StockItemCollection collection = new StockItemCollection(
				"stockitems_annotations.xml", new AnnotatedStockItemBuilder());
		// we load the contents into the collection
		collection.load();

		for (IStockItem consumable : collection.getStockItems()) {
			;
			showAttributes(((StockItemProxyImpl)consumable).getProxiedObject());
		}

		// we initialise a consumer
		Consumer consumer = new Consumer();
		// ... and let them consume
		consumer.doShopping(collection.getStockItems());
	}

	/*
	 * UE BAS2 
	 */
	/*private static void showAttributes(Object consumable) {
		show("class is: " + consumable.getClass());
	}*/
	private static void showAttributes(Object consumable) {

		Class consumableClass = consumable.getClass();
		String formattedString = consumableClass.getSimpleName();
		ArrayList<String> formattedFieldStrings = new ArrayList<>();

		for (Field field : consumableClass.getDeclaredFields()) {
			String fieldDisplayName = field.getName();

			DisplayAs displayAsAnnotation = field.getAnnotation(DisplayAs.class);
			if (displayAsAnnotation != null)
				fieldDisplayName = displayAsAnnotation.value();

			formattedFieldStrings.add(" " + fieldDisplayName + ": " + getFieldValue(field, consumable));
		}

		formattedString += String.join(",", formattedFieldStrings);
		show("{" + formattedString + "}");
	}

	private static Object getFieldValue(Field field, Object instance) {
		Object fieldValue = null;

		try {
			Method fieldGetter = instance.getClass().getDeclaredMethod(getAccessorNameForField("get", field.getName()));
			fieldValue = fieldGetter.invoke(instance);
		} catch (Exception e) {
			try {
				field.setAccessible(true);
				fieldValue = field.get(instance);
			} catch (IllegalAccessException iae) {
			}
		}

		return fieldValue;
	}
	private static String getAccessorNameForField(String accessor,String fieldName) {
		return accessor + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
	}
}
