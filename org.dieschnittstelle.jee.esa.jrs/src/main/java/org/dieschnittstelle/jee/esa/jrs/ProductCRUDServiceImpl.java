package org.dieschnittstelle.jee.esa.jrs;

import java.util.List;
import org.apache.log4j.Logger;

import org.dieschnittstelle.jee.esa.entities.GenericCRUDExecutor;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;
import org.dieschnittstelle.jee.esa.entities.erp.IndividualisedProductItem;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

/*
UE JRS2: implementieren Sie hier die im Interface deklarierten Methoden
 */

public class ProductCRUDServiceImpl implements IProductCRUDService {

	protected static Logger logger = Logger.getLogger(ProductCRUDServiceImpl.class);

	private GenericCRUDExecutor<AbstractProduct> productCRUD;

	/**
	 * here we will be passed the context parameters by the resteasy framework
	 * note that the request context is only declared for illustration purposes, but will not be further used here
	 * @param servletContext
	 */
	public ProductCRUDServiceImpl(@Context ServletContext servletContext, @Context HttpServletRequest request) {
		logger.info("<constructor>: " + servletContext + "/" + request);
		// read out the dataAccessor
		this.productCRUD = (GenericCRUDExecutor<AbstractProduct>)servletContext.getAttribute("productCRUD");

		logger.debug("read out the productCRUD from the servlet context: " + this.productCRUD);
	}
	@Override
	public AbstractProduct createProduct(AbstractProduct prod) {
		return this.productCRUD.createObject(prod);
	}

	@Override
	public List<?> readAllProducts() {
		return this.productCRUD.readAllObjects();
	}

	@Override
	public AbstractProduct updateProduct(long id, AbstractProduct update) {
		return  this.productCRUD.updateObject(update);
	}

	@Override
	public boolean deleteProduct(long id) {
		return this.productCRUD.deleteObject(id);
	}

	@Override
	public AbstractProduct readProduct(long id) {
		return this.productCRUD.readObject(id);
	}

}
