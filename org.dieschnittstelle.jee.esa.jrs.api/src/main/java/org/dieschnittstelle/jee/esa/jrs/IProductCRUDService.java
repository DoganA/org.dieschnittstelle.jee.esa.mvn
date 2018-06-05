package org.dieschnittstelle.jee.esa.jrs;

import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;
import org.dieschnittstelle.jee.esa.entities.erp.IndividualisedProductItem;

import javax.ws.rs.*;
import java.util.List;

/*
 * UE JRS2: 
 * deklarieren Sie hier Methoden fuer:
 * - die Erstellung eines Produkts
 * - das Auslesen aller Produkte
 * - das Auslesen eines Produkts
 * - die Aktualisierung eines Produkts
 * - das Loeschen eines Produkts
 * und machen Sie diese Methoden mittels JAX-RS Annotationen als WebService verfuegbar
 */

/*
 * UE JRS3: aendern Sie Argument- und Rueckgabetypen der Methoden von IndividualisedProductItem auf AbstractProduct
 */

@Path("/products")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public interface IProductCRUDService {

	@POST
	public AbstractProduct createProduct(AbstractProduct product);

	@GET
	public List<?> readAllProducts();

	@PUT
	@Path("/{productId}")
	public AbstractProduct updateProduct(@PathParam("productId") long id, AbstractProduct update);

	@DELETE
	@Path("{productId}")
	public boolean deleteProduct(@PathParam("productId") long id);

	@GET
	@Path("{productId}")
	public AbstractProduct readProduct(@PathParam("productId") long id);

}
